package com.witshare.mars.service;


import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.JobAndTrigger;

public interface JobAndTriggerService {
    PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
