package com.witshare.mars.controller;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.ProjectDailyInfoBean;
import com.witshare.mars.pojo.dto.ProjectSummaryBean;
import com.witshare.mars.pojo.dto.RecordUserTxBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.pojo.vo.DistributionStatusVo;
import com.witshare.mars.service.ProjectDailyInfoService;
import com.witshare.mars.service.ProjectSummaryService;
import com.witshare.mars.service.UserTxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统计
 */
@Controller
@RequestMapping("/management")
public class ManagementProjectStatisticController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProjectSummaryService projectSummaryService;
    @Autowired
    private ProjectDailyInfoService projectDailyInfoService;
    @Autowired
    private UserTxService userTxService;

    /**
     * 查询项目统计列表
     */
    @ResponseBody
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public ResponseBean list(ProjectSummaryBean projectSummaryBean) {

        PageInfo<ProjectSummaryBean> pageInfo = projectSummaryService.getList(projectSummaryBean);
        return ResponseBean.newInstanceSuccess(pageInfo);
    }

    /**
     * 查询单个项目
     */
    @ResponseBody
    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public ResponseBean get(ProjectDailyInfoBean projectDailyInfoBean) {

        PageInfo<ProjectDailyInfoBean> pageInfo = projectDailyInfoService.getList(projectDailyInfoBean);
        return ResponseBean.newInstanceSuccess(pageInfo);
    }

    /**
     * 交易明细表
     */
    @ResponseBody
    @RequestMapping(value = "/tx-infos", method = RequestMethod.GET)
    public ResponseBean txInfos(RecordUserTxBean recordUserTxBean) {

        PageInfo<RecordUserTxBean> pageInfo = userTxService.getList(recordUserTxBean);
        return ResponseBean.newInstanceSuccess(pageInfo);
    }

    /**
     * 打币状态明细
     */
    @ResponseBody
    @RequestMapping(value = "/distribution-infos", method = RequestMethod.GET)
    public ResponseBean distributions(RecordUserTxBean recordUserTxBean) {

        PageInfo<DistributionStatusVo> pageInfo = userTxService.getPlatformStatusCount(recordUserTxBean);
        return ResponseBean.newInstanceSuccess(pageInfo);
    }


}
