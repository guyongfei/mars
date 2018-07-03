package com.witshare.mars.dao.mysql;

import java.util.Set;

/**
 * Created by user on 2018/7/2.
 */
public interface StaticPlatformAddressMapper {

    int saveOrUpdate(Set<String> collection);
}
