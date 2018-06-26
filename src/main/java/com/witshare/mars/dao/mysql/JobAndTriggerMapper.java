package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.dto.JobAndTrigger;

import java.util.List;


public interface JobAndTriggerMapper {

//    @AnnotationTargetDataSource("quartzDatasource")
    List<JobAndTrigger> getJobAndTriggerDetails();

}
