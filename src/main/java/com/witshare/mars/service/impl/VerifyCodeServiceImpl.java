package com.witshare.mars.service.impl;

import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.constant.EnumStatus;
import com.witshare.mars.dao.mysql.StaticSysUserMapper;
import com.witshare.mars.dao.mysql.SysUserMapper;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.job.Task;
import com.witshare.mars.pojo.domain.SysUser;
import com.witshare.mars.pojo.domain.SysUserExample;
import com.witshare.mars.pojo.dto.SysUserBean;
import com.witshare.mars.service.SysUserService;
import com.witshare.mars.service.VerifyCodeService;
import com.witshare.mars.util.RedisKeyUtil;
import com.witshare.mars.util.WitshareUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.witshare.mars.pojo.dto.SysUserBean.EMAIL;


/**
 * @see VerifyCodeService
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private RedisCommonDao redisCommonDao;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private StaticSysUserMapper staticSysUserMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private Task task;

    /**
     * @see VerifyCodeService#sendRegisterVerificationCode(Map)
     */
    @Override
    public void sendRegisterVerificationCode(Map<String, String> requestBody) {
        if (requestBody == null || requestBody.size() < 1) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        if (!requestBody.containsKey(EMAIL)) {
            throw new WitshareException(EnumResponseText.ErrorEmail);
        }
        String email = requestBody.get(EMAIL);
        if (StringUtils.isEmpty(email)) {
            throw new WitshareException(EnumResponseText.ErrorEmail);
        }
        //密码保存成功后才会变为有效
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.or().andEmailEqualTo(email).andSaltIsNotNull();
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (!CollectionUtils.isEmpty(sysUsers)) {
            //如果已经注册且状态为0，表示被冻结；未被冬季则表示已经存在该账号
            if (EnumStatus.InValid.getValue() == sysUsers.get(0).getUserStatus()) {
                throw new WitshareException(EnumResponseText.AccountSuspended);
            }
            throw new WitshareException(EnumResponseText.ExistEmail);
        }
        SysUserBean sysUserBean = null;
        //检查昵称是否有重名的
        while (true) {
            String nickname = System.currentTimeMillis() + WitshareUtils.generateRandomEnChar(2);
            SysUserBean userDb = sysUserService.getByNickname(nickname, null);
            if (userDb == null) {
                sysUserBean = new SysUserBean(email, nickname, "");
                break;
            }
        }
        //存表
        staticSysUserMapper.saveOrUpdate(sysUserBean);
        //发送验证码
        task.sendEmailVerifyCode(email, Boolean.TRUE);
    }

    /**
     * @see VerifyCodeService#checkRegisterVerifyCode(String, String)
     */
    @Override
    public boolean checkRegisterVerifyCode(String email, String verifyCode) {
        String redisKey = RedisKeyUtil.getRegisterEmailVerifyCodeKey(email, verifyCode);
        String redisCode = redisCommonDao.getString(redisKey);
        boolean equals = StringUtils.equals(redisCode, verifyCode);
        if (equals) {
            redisCommonDao.delRedisKey(redisKey);
            return true;
        }
        return false;
    }

    /**
     * @see VerifyCodeService#sendVerifyCode(Map)
     */
    @Override
    public void sendVerifyCode(Map<String, String> requestBody) {
        if (requestBody == null || requestBody.size() < 1) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String email = requestBody.get(EMAIL);
        if (StringUtils.isEmpty(email)) {
            throw new WitshareException(EnumResponseText.ErrorEmail);
        }
        SysUserBean sysUserBean = sysUserService.getByEmail(email, null);
        if (sysUserBean == null) {
            throw new WitshareException(EnumResponseText.NeedLogUp);
        }
        //发送验证码
        task.sendEmailVerifyCode(sysUserBean.getEmail(), Boolean.FALSE);
    }

    /**
     * @see VerifyCodeService#checkVerifyCode(String, String)
     */
    @Override
    public boolean checkVerifyCode(String phoneNo, String verifyCode) {
        String redisKey = RedisKeyUtil.getUserEmailVerifyCodeKey(phoneNo, verifyCode);
        String redisCode = redisCommonDao.getString(redisKey);
        boolean equals = StringUtils.equals(verifyCode, redisCode);
        if (equals) {
            redisCommonDao.delRedisKey(redisKey);
            return true;
        }
        return false;
    }
}
