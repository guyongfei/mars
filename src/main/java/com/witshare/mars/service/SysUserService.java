package com.witshare.mars.service;

import com.witshare.mars.constant.EnumStatus;
import com.witshare.mars.pojo.dto.SysUserBean;

import java.util.Map;

/**
 * 用户实现类
 */
public interface SysUserService {
    /**
     * 通过昵称获取用户
     *
     * @param nickname
     * @param status
     * @return
     */
    SysUserBean getByNickname(String nickname, EnumStatus status);

    /**
     * 通过邮箱获取用户
     *
     * @param email
     * @param status
     * @return
     */
    SysUserBean getByEmail(String email, EnumStatus status);

    /**
     * 转化头像地址
     *
     * @param headImgUrl
     * @return
     */
    String getAvatar(String headImgUrl);

    /**
     * 注册
     *
     * @param requestBody
     */
    void register(Map<String, String> requestBody);

    /**
     * 修改密码
     *
     * @param requestBody
     */
    void postPassword(Map<String, String> requestBody);

    /**
     * 重置密码
     *
     * @param requestBody
     */
    void putPassword(Map<String, String> requestBody);

    /**
     * 修改昵称
     *
     * @param requestBody
     */
    void postNickname(Map<String, String> requestBody);

    /**
     * 修改头像
     *
     * @param requestBody
     */
    void postAvatar(Map<String, String> requestBody);

    /**
     * 登录
     *
     * @param requestBody
     * @return
     */
    void login(Map<String, String> requestBody);

    /**
     * 登出
     */
    void logout();

    /**
     * 获取当前用户
     *
     * @return
     */
    SysUserBean getCurrentUser();

    /**
     * 隐藏用户
     *
     * @param id
     */
    void hideUser(Long id);

    /**
     * 通过id获取用户
     *
     * @param userId
     * @param status
     * @return
     */
    SysUserBean getByUserId(Long userId, EnumStatus status);

    /**
     * 修改用户信息(密码、昵称)
     *
     * @param id
     * @param requestBody
     */
    void modifyUserInfo(Long id, Map<String, String> requestBody);


    void syncChannelRegisterCount();


}
