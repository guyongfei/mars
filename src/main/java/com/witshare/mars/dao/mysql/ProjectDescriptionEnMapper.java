package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.domain.ProjectDescriptionEn;
import com.witshare.mars.pojo.domain.ProjectDescriptionEnExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectDescriptionEnMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_en
     *
     * @mbggenerated
     */
    int countByExample(ProjectDescriptionEnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_en
     *
     * @mbggenerated
     */
    int deleteByExample(ProjectDescriptionEnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_en
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_en
     *
     * @mbggenerated
     */
    int insert(ProjectDescriptionEn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_en
     *
     * @mbggenerated
     */
    int insertSelective(ProjectDescriptionEn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_en
     *
     * @mbggenerated
     */
    List<ProjectDescriptionEn> selectByExampleWithBLOBs(ProjectDescriptionEnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_en
     *
     * @mbggenerated
     */
    List<ProjectDescriptionEn> selectByExample(ProjectDescriptionEnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_en
     *
     * @mbggenerated
     */
    ProjectDescriptionEn selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_en
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ProjectDescriptionEn record, @Param("example") ProjectDescriptionEnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_en
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") ProjectDescriptionEn record, @Param("example") ProjectDescriptionEnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_en
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ProjectDescriptionEn record, @Param("example") ProjectDescriptionEnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_en
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ProjectDescriptionEn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_en
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(ProjectDescriptionEn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_en
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ProjectDescriptionEn record);
}