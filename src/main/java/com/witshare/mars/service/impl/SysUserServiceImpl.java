package com.witshare.mars.service.impl;

import com.google.gson.Gson;
import com.witshare.mars.config.CurrentThreadContext;
import com.witshare.mars.constant.*;
import com.witshare.mars.dao.mysql.StaticSysUserMapper;
import com.witshare.mars.dao.mysql.SysUserMapper;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.domain.SysUser;
import com.witshare.mars.pojo.domain.SysUserExample;
import com.witshare.mars.pojo.dto.SyncChannelRegisterCount;
import com.witshare.mars.pojo.dto.SysUserBean;
import com.witshare.mars.service.QingyunStorageService;
import com.witshare.mars.service.SysProjectService;
import com.witshare.mars.service.SysUserService;
import com.witshare.mars.service.VerifyCodeService;
import com.witshare.mars.util.JsonUtils;
import com.witshare.mars.util.RedisKeyUtil;
import com.witshare.mars.util.WitshareUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.witshare.mars.constant.CacheConsts.COOKIE_USER_TOKEN;
import static com.witshare.mars.constant.CacheConsts.SHIRO_SESSION_EXPIRE_TIME;
import static com.witshare.mars.pojo.dto.SysUserBean.*;

/**
 * @see SysUserService
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private final static Gson GSON = new Gson();
    private final static String MANAGEMENT_PAGE = "/management/page";
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private StartupRunnerDefault startupRunnerDefault;
    @Autowired
    private StaticSysUserMapper staticSysUserMapper;
    @Autowired
    private VerifyCodeService verifyCodeService;
    @Autowired
    private RedisCommonDao redisCommonDao;
    @Autowired
    private PropertiesConfig propertiesConfig;
    @Autowired
    private SysProjectService sysProjectServiceImpl;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private QingyunStorageService qingyunStorageService;

    /**
     * @see SysUserService#register(Map)
     */
    @Override
    public void register(Map<String, String> requestBody) {
        if (requestBody == null || requestBody.size() < 3) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String email = requestBody.get(EMAIL);
        String password = requestBody.get(PASSWORD);
        String verifyCode = requestBody.get(VERIFY_CODE);
//        String imgVerifyCode = requestBody.get(IMG_VERIFY_CODE);
//        String imgToken = requestBody.get(IMG_TOKEN);
        if (StringUtils.isAnyBlank(email, password, verifyCode)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        //获取图像验证码
//        String imgVerifyCodeDb = redisCommonDao.getAndDelete(RedisKeyUtil.getVerifyCodeImgKey(imgToken.trim()));
//        if (!StringUtils.equals(imgVerifyCodeDb, imgVerifyCode)) {
//            throw new WitshareException(EnumResponseText.ErrorKaptcha);
//        }
        SysUserBean userBean = getByEmail(email, null);
        if (userBean != null && EnumStatus.Valid.getValue() == userBean.getUserStatus() && StringUtils.equals(email, userBean.getEmail())) {
            throw new WitshareException(EnumResponseText.ExistEmail);
        }
        boolean match = verifyCodeService.checkRegisterVerifyCode(email, verifyCode);
        if (userBean == null || !match) {
            throw new WitshareException(EnumResponseText.ErrorVerifyCode);
        }
        String salt = WitshareUtils.getUUID();
        String userPassword = WitshareUtils.encryptPassword(email, salt, password);
        SysUser sysUser = new SysUser();
        sysUser.setId(userBean.getId());
        sysUser.setUserPassword(userPassword);
        sysUser.setSalt(salt);
        sysUser.setUserStatus(EnumStatus.Valid.getValue());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);

        this.login(requestBody);
    }


    /**
     * @see SysUserService#login(Map)
     */
    @Override
    public void login(Map<String, String> requestBody) {
        if (requestBody == null || requestBody.size() < 2) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String email = requestBody.get(EMAIL);
        String password = requestBody.get(PASSWORD);
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        SysUserBean userBean = getByEmail(email, EnumStatus.Valid);
        if (userBean == null) {
            throw new WitshareException(EnumResponseText.ErrorEmailOrPassword);
        }
        String userPassword = WitshareUtils.encryptPassword(email, userBean.getSalt(), password);
        if (!StringUtils.equals(userPassword, userBean.getUserPassword())) {
            throw new WitshareException(EnumResponseText.ErrorEmailOrPassword);
        }
        String token = WitshareUtils.getUUID();
        //删除原有的token
        String originToken = redisCommonDao.getHash(RedisKeyUtil.getEmailTokenKey(), email);
        if (!StringUtils.isEmpty(originToken)) {
            redisCommonDao.delHash(RedisKeyUtil.getTokenEmailKey(), originToken);
        }
        redisCommonDao.putHash(RedisKeyUtil.getEmailTokenKey(), email, token);
        redisCommonDao.putHash(RedisKeyUtil.getTokenEmailKey(), token, email);

        //将token置入cookie
        Cookie cookie = new Cookie(COOKIE_USER_TOKEN, token);
        cookie.setMaxAge(SHIRO_SESSION_EXPIRE_TIME);
        CurrentThreadContext.getResponse().addCookie(cookie);

        //缓存用户数据到redis
        String callApiInfoKey = RedisKeyUtil.getCallApiInfo(email);
        redisCommonDao.setString(callApiInfoKey, new Gson().toJson(userBean));

    }

    /**
     * @see SysUserService#postPassword(Map)
     */
    @Override
    public void postPassword(Map<String, String> requestBody) {
        if (requestBody == null || requestBody.size() < 2) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String password = requestBody.get(PASSWORD);
        String originPassword = requestBody.get(ORIGIN_PASSWORD);

        if (StringUtils.isEmpty(password)) {
            throw new WitshareException(EnumResponseText.ErrorPassword);
        }
        if (StringUtils.isEmpty(originPassword)) {
            throw new WitshareException(EnumResponseText.ErrorOriginPassword);
        }
        SysUserBean userBean = this.getCurrentUser();
        String salt = userBean.getSalt();
        String email = userBean.getEmail();
        String dbPassword = userBean.getUserPassword();
        String userPassword = WitshareUtils.encryptPassword(email, salt, originPassword);
        if (!StringUtils.equals(userPassword, dbPassword)) {
            throw new WitshareException(EnumResponseText.ErrorOriginPassword);
        }
        dbPassword = WitshareUtils.encryptPassword(email, salt, password);
        redisCommonDao.delRedisKey(RedisKeyUtil.getCallApiInfo(email));
        updatePassword(userBean.getId(), dbPassword);

    }

    /**
     * @see SysUserService#getCurrentUser()
     */
    public SysUserBean getCurrentUser() {
        String token = CurrentThreadContext.getToken();
        LOGGER.info("getCurrentUser,token:{}", token);
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        String email = redisCommonDao.getHash(RedisKeyUtil.getTokenEmailKey(), token);
        if (StringUtils.isEmpty(email)) {
            return null;
        }
        String userBeanJson = redisCommonDao.getString(RedisKeyUtil.getCallApiInfo(email));
        SysUserBean userBean;
        if (StringUtils.isEmpty(userBeanJson)) {
            userBean = getByEmail(email, null);
        } else {
            userBean = JsonUtils.jsonToObjByGson(userBeanJson, SysUserBean.class);
        }
        String managementPage = userBean.getManagementPage();
        if (userBean.isAdmin() && !StringUtils.isAnyBlank(managementPage)) {
            userBean.setManagementPage(propertiesConfig.contextPath + managementPage);
        }
        return userBean;
    }


    /**
     * @see SysUserService#putPassword(Map)
     */
    @Override
    public void putPassword(Map<String, String> requestBody) {
        if (requestBody == null || requestBody.size() < 3) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String email = requestBody.get(EMAIL);
        String password = requestBody.get(PASSWORD);
        String verifyCode = requestBody.get(VERIFY_CODE);

        SysUserBean userBean = this.getByEmail(email, null);
        if (StringUtils.isEmpty(email) || userBean == null) {
            throw new WitshareException(EnumResponseText.NeedLogUp);
        }

        if (EnumStatus.InValid.getValue() == userBean.getUserStatus()) {
            throw new WitshareException(EnumResponseText.AccountSuspended);
        }

        if (StringUtils.isEmpty(password)) {
            throw new WitshareException(EnumResponseText.ErrorPassword);
        }
        boolean verifyCodeMatch = verifyCodeService.checkVerifyCode(email, verifyCode);
        if (!verifyCodeMatch) {
            throw new WitshareException(EnumResponseText.ErrorVerifyCode);
        }
        String userPassword = WitshareUtils.encryptPassword(email, userBean.getSalt(), password);
        updatePassword(userBean.getId(), userPassword);

    }

    /**
     * @see SysUserService#postNickname(Map)
     */
    @Override
    public void postNickname(Map<String, String> requestBody) {
        if (requestBody == null || requestBody.size() < 1) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String nickname = requestBody.get(NICKNAME);
        if (StringUtils.isEmpty(nickname) || nickname.length() > 30) {
            throw new WitshareException(EnumResponseText.ErrorNickname);
        }

        SysUserBean currentUser = this.getCurrentUser();
        if (StringUtils.equals(nickname, currentUser.getNickname())) {
            return;
        }
        Long currentUserId = currentUser.getId();
        SysUser sysUser = new SysUser();
        sysUser.setId(currentUserId);
        sysUser.setNickname(nickname);
        sysUser.setUpdateTime(new Timestamp(new Date().getTime()));
        try {
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
        } catch (Exception e) {
            LOGGER.error("postNickname fail.nickname:{},userDb:{}", nickname, GSON.toJson(sysUser), e);
            throw new WitshareException(EnumResponseText.ExistNickname);
        }
        if (redisCommonDao.isMember(RedisKeyUtil.getIndexUserSetKey(), currentUserId.toString())) {
            redisCommonDao.delRedisKey(RedisKeyUtil.getIndexUserKey());
        }
        redisCommonDao.delRedisKey(RedisKeyUtil.getCallApiInfo(currentUser.getEmail()));
    }

    /**
     * @see SysUserService#postAvatar(Map)
     */
    @Override
    public void postAvatar(Map<String, String> requestBody) {
        if (requestBody == null || requestBody.size() < 1) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String avatarStr = requestBody.get(AVATAR);
        if (StringUtils.isEmpty(avatarStr)) {
            throw new WitshareException(EnumResponseText.ErrorPicture);
        }

        SysUserBean currentUser = this.getCurrentUser();
        SysUser sysUser = new SysUser();
        Long currentUserId = currentUser.getId();
        sysUser.setId(currentUserId);
        String objectName = qingyunStorageService.uploadToQingyun(avatarStr, currentUserId.toString(), EnumStorage.Avatar);
        sysUser.setHeadImgUrl(objectName);
        sysUser.setUpdateTime(new Timestamp(new Date().getTime()));
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (redisCommonDao.isMember(RedisKeyUtil.getIndexUserSetKey(), currentUserId.toString())) {
            redisCommonDao.delRedisKey(RedisKeyUtil.getIndexUserKey());
        }
        redisCommonDao.delRedisKey(RedisKeyUtil.getCallApiInfo(currentUser.getEmail()));
    }

    /**
     * @see SysUserService#logout()
     */
    @Override
    public void logout() {
        String token = CurrentThreadContext.getToken();
        String email = CurrentThreadContext.getEmail();
        if (StringUtils.isEmpty(token)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        //删除 cookie
        Cookie cookie = new Cookie(COOKIE_USER_TOKEN, token);
        cookie.setMaxAge(0);
        CurrentThreadContext.getResponse().addCookie(cookie);

        redisCommonDao.delHash(RedisKeyUtil.getTokenEmailKey(), token);
        redisCommonDao.delHash(RedisKeyUtil.getEmailTokenKey(), email);
    }


    /**
     * 更新密码
     *
     * @param userId
     * @param password
     */
    private void updatePassword(Long userId, String password) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setUserPassword(password);
        sysUser.setUserStatus(EnumStatus.Valid.getValue());
        sysUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     * @see SysUserService#getByNickname(String, EnumStatus)
     */
    @Override
    public SysUserBean getByNickname(String nickname, EnumStatus status) {
        if (StringUtils.isEmpty(nickname)) {
            return null;
        }
        SysUserExample sysUserExample = new SysUserExample();
        if (status != null) {
            sysUserExample.or().andNicknameEqualTo(nickname).andUserStatusEqualTo(status.getValue());
        } else {
            sysUserExample.or().andNicknameEqualTo(nickname);
        }
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (sysUsers != null && sysUsers.size() > 0) {
            SysUserBean sysUserBean = SysUserBean.newInstance();
            BeanUtils.copyProperties(sysUsers.get(0), sysUserBean);
            return sysUserBean;
        }
        return null;
    }

    /**
     * @see SysUserService#getByEmail(String, EnumStatus)
     */
    @Override
    public SysUserBean getByEmail(String email, EnumStatus status) {
        if (StringUtils.isEmpty(email)) {
            return null;
        }
        SysUserExample sysUserExample = new SysUserExample();
        if (status != null) {
            sysUserExample.or().andEmailEqualTo(email).andUserStatusEqualTo(status.getValue());
        } else {
            sysUserExample.or().andEmailEqualTo(email);
        }
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (CollectionUtils.isNotEmpty(sysUsers)) {
            SysUserBean sysUserBean = SysUserBean.newInstance();
            BeanUtils.copyProperties(sysUsers.get(0), sysUserBean);
            //是否是管理员
            boolean isAdmin = startupRunnerDefault.getAdminUserSet().contains(email);
            sysUserBean.setAdmin(isAdmin)
                    .setManagementPage(isAdmin ? MANAGEMENT_PAGE : null);
            return sysUserBean;
        }
        return null;
    }


    /**
     * @see SysUserService#getAvatar(String)
     */
    @Override
    public String getAvatar(String headImgUrl) {
        if (StringUtils.isEmpty(headImgUrl)) {
            return propertiesConfig.defaultAvatar;
        }
        return sysProjectServiceImpl.getPictureUrl(headImgUrl);
    }

    /**
     * @see SysUserService#hideUser(Long)
     */
    @Override
    public void hideUser(Long id) {
        if (id == null) {
            throw new WitshareException(EnumResponseText.ErrorId);
        }
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        if (sysUser == null) {
            throw new WitshareException(EnumResponseText.ErrorId);
        }
        SysUserBean currentUser = sysUserService.getCurrentUser();
        if (id == currentUser.getId()) {
            throw new WitshareException(EnumResponseText.CANNOTBLOCKEDYOURSELF);
        }
        staticSysUserMapper.modifyUserStatus(id);
        //清缓存
        redisCommonDao.delRedisKey(RedisKeyUtil.getIndexUserKey());
        redisCommonDao.delRedisKey(RedisKeyUtil.getUserStatisticKey(id));
        //剔除用户
        String email = sysUser.getEmail();
        String token = redisCommonDao.getHash(RedisKeyUtil.getEmailTokenKey(), email);
        redisCommonDao.delHash(RedisKeyUtil.getTokenEmailKey(), token);
        redisCommonDao.delHash(RedisKeyUtil.getEmailTokenKey(), email);

    }

    /**
     * @see SysUserService#getByUserId(Long, EnumStatus)
     */
    @Override
    public SysUserBean getByUserId(Long userId, EnumStatus status) {
        if (userId == null) {
            return null;
        }
        SysUserExample sysUserExample = new SysUserExample();
        if (status != null) {
            sysUserExample.or().andIdEqualTo(userId).andUserStatusEqualTo(status.getValue());
        } else {
            sysUserExample.or().andIdEqualTo(userId);
        }
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (sysUsers != null && sysUsers.size() > 0) {
            SysUserBean sysUserBean = SysUserBean.newInstance();
            BeanUtils.copyProperties(sysUsers.get(0), sysUserBean);
            return sysUserBean;
        }
        return null;
    }

    /**
     * @see SysUserService#modifyUserInfo(Long, Map)
     */
    @Override
    public void modifyUserInfo(Long id, Map<String, String> requestBody) {
        if (requestBody == null || requestBody.size() < 1) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String nickname = requestBody.get(NICKNAME);
        String password = requestBody.get(PASSWORD);

        if (id == null) {
            throw new WitshareException(EnumResponseText.ErrorId);
        }
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        if (sysUser == null) {
            throw new WitshareException(EnumResponseText.ErrorId);
        }
        if (StringUtils.isEmpty(nickname) && StringUtils.isEmpty(password)) {
            throw new WitshareException(EnumResponseText.ErrorId);
        }
        if (!StringUtils.isEmpty(password)) {
            String salt = sysUser.getSalt();
            String email = sysUser.getEmail();
            String newPassword = WitshareUtils.encryptPassword(email, salt, password);
            sysUser.setUserPassword(newPassword);
        }
        if (!StringUtils.isEmpty(nickname)) {

            //重名检测
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.or().andNicknameEqualTo(nickname);
            List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
            if (!CollectionUtils.isEmpty(sysUsers) && id != sysUsers.get(0).getId()) {
                throw new WitshareException(EnumResponseText.ExistNickname);
            }
            sysUser.setNickname(nickname);
        }
        sysUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        sysUserMapper.updateByPrimaryKeySelective(sysUser);

        String email = sysUser.getEmail();
        //清缓存
        redisCommonDao.delRedisKey(RedisKeyUtil.getIndexUserKey());
        redisCommonDao.delRedisKey(RedisKeyUtil.getUserStatisticKey(id));
        String token = redisCommonDao.getHash(RedisKeyUtil.getEmailTokenKey(), email);
        redisCommonDao.delHash(RedisKeyUtil.getEmailTokenKey(), email);
        redisCommonDao.delHash(RedisKeyUtil.getTokenEmailKey(), token);

    }


    @Override
    public void syncChannelRegisterCount() {

        String redisKey = RedisKeyUtil.getChannelRegisterCountKey();
        List<SyncChannelRegisterCount> list = staticSysUserMapper.syncChannelRegisterCount();
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        if (CollectionUtils.isEmpty(list)) {
            redisCommonDao.delRedisKey(redisKey);
            return;
        }

        list.forEach(p -> {
            String channel = p.getChannel();
            Integer registerCount = p.getRegisterCount();
            map.put(channel, String.valueOf(registerCount));
        });

        Integer total = list.stream().filter(p -> p.getRegisterCount() != null)
                .mapToInt(SyncChannelRegisterCount::getRegisterCount).sum();
        map.put("total", String.valueOf(total));

        redisCommonDao.delRedisKey(redisKey);
        redisCommonDao.putMapAll(redisKey, map, 1, TimeUnit.HOURS);

    }
}
