package com.witshare.mars.service;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.BasePageBean;
import com.witshare.mars.pojo.dto.PlatformAddressBean;

import java.util.Map;

/**
 * Created by user on 2018/6/27.
 */
public interface PlatformAddressService {

    void delete(String address);

    String getPlatformAddress();

    int count();

    void add(Map<String, String> requestBody);

    PageInfo<PlatformAddressBean> getList(BasePageBean basePageBean);


}
