package com.witshare.mars.service;

import com.witshare.mars.pojo.dto.SysUserAddressBean;

/**
 * Created by user on 2018/6/20.
 */
public interface SysUserAddressService {

    SysUserAddressBean get(String userGid, String projectGid);
}
