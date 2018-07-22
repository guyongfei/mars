package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.dto.GlobalSimpleBean;
import com.witshare.mars.pojo.dto.SyncChannelRegisterCount;
import com.witshare.mars.pojo.dto.SysUserBean;
import com.witshare.mars.pojo.vo.SysUserViewVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaticSysUserMapper {

    List<SyncChannelRegisterCount> syncChannelRegisterCount();


    Long saveOrUpdate(SysUserBean sysUserBean);


    List<GlobalSimpleBean> selectShowUserList(@Param("indexShow") Integer indexShow, @Param("name") String name);

    int updateShowUsers(List<Long> ids);

    int updateUserFollowAndComment(@Param("userId") Long userId, @Param("followCount") Long followCount, @Param("commentCount") Long commentCount);

    int modifyUserStatus(Long id);

}