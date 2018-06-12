package com.witshare.mars.service;

import com.witshare.mars.constant.EnumStorage;

/**
 * 对象存储实现类
 */
public interface QingyunStorageService {
    /**
     * 上传到供应商
     *
     * @param dataStr
     * @param token
     * @param type
     * @return
     */
    String uploadToQingyun(String dataStr, String token, EnumStorage type);

}
