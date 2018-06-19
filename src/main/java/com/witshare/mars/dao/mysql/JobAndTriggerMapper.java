package com.witshare.mars.dao.mysql;

import com.witshare.mars.config.AnnotationTargetDataSource;
import com.witshare.mars.pojo.dto.JobAndTrigger;

import java.util.List;

/**
 * Created by user on 2018/6/19.
 */
public interface JobAndTriggerMapper {

    @AnnotationTargetDataSource("quartzDatasource")
    List<JobAndTrigger> getJobAndTriggerDetails();

}
