package com.witshare.mars.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.dao.mysql.ProjectSummaryMapper;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.domain.ProjectSummary;
import com.witshare.mars.pojo.domain.ProjectSummaryExample;
import com.witshare.mars.pojo.dto.ProjectSummaryBean;
import com.witshare.mars.service.ProjectSummaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class ProjectSummaryServiceImpl implements ProjectSummaryService {

    @Autowired
    private ProjectSummaryMapper projectSummaryMapper;

    @Override
    public PageInfo<ProjectSummaryBean> getList(ProjectSummaryBean projectSummaryBean) {
        if (projectSummaryBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        Integer pageNum = projectSummaryBean.getPageNum();
        Integer pageSize = projectSummaryBean.getPageSize();
        if (pageNum == null || pageSize == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String queryStr = projectSummaryBean.getQueryStr();
        ProjectSummaryExample projectSummaryExample = new ProjectSummaryExample();
        if (StringUtils.isNotEmpty(queryStr)) {
            projectSummaryExample.or().andProjectTokenLike("%" + queryStr + "%");
        }
        PageInfo<ProjectSummary> pageInfos = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> projectSummaryMapper.selectByExample(projectSummaryExample));
        PageInfo<ProjectSummaryBean> projectSummaryBeanPageInfo = new PageInfo<>();
        List<ProjectSummary> list = pageInfos.getList();
        LinkedList<ProjectSummaryBean> projectSummaryBeans = new LinkedList<>();
        list.forEach(p -> {
            ProjectSummaryBean bean = new ProjectSummaryBean();
            BeanUtils.copyProperties(p, bean);
            projectSummaryBeans.add(bean);
        });
        pageInfos.setList(null);
        BeanUtils.copyProperties(pageInfos, projectSummaryBeanPageInfo);
        projectSummaryBeanPageInfo.setList(projectSummaryBeans);
        return projectSummaryBeanPageInfo;
    }
}
