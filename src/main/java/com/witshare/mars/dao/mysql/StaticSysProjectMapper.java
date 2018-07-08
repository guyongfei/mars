package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.domain.RecordPlatformTx;
import com.witshare.mars.pojo.dto.ProjectReqBean;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.pojo.vo.SysProjectBeanFrontListVo;

import java.util.List;

public interface StaticSysProjectMapper {

    int checkExist(SysProjectBean sysProjectBean);

    int saveOrUpdate(SysProjectBean sysProjectBean);

    List<SysProjectBeanFrontListVo> selectProjectList(ProjectReqBean projectReqBean);

    int modifyProjectStatus(Long id);

    int modifyDefaultProject(Long id);

    List<SysProjectBean> selectByTxAddress(RecordPlatformTx bean);


}