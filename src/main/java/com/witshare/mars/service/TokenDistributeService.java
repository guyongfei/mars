package com.witshare.mars.service;

import com.witshare.mars.pojo.dto.TokenDistributeBean;
import com.witshare.mars.pojo.util.ResponseBean;

/**
 * 打币操作
 */
public interface TokenDistributeService {

    /**
     * 执行打币
     *
     * @param tokenDistributeBean
     * @return
     */
    ResponseBean execTokenDistribute(TokenDistributeBean tokenDistributeBean);

    /**
     * 获取打币进度
     *
     * @param projectGid
     * @return
     */
    ResponseBean getTokenDistributeProgress(String projectGid);

    String getToken(String url, String method, String queryParam, String bodyJson);
}
