package com.adpanshi.cashloan.dispatch.config.mapper;

import com.adpanshi.cashloan.dispatch.config.model.TaskRelationConfig;
import com.adpanshi.cashloan.dispatch.config.model.TaskRelationConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskRelationConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task_relation
     *
     * @mbg.generated
     */
    long countByExample(TaskRelationConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task_relation
     *
     * @mbg.generated
     */
    int deleteByExample(TaskRelationConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task_relation
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task_relation
     *
     * @mbg.generated
     */
    int insert(TaskRelationConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task_relation
     *
     * @mbg.generated
     */
    int insertSelective(TaskRelationConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task_relation
     *
     * @mbg.generated
     */
    List<TaskRelationConfig> selectByExample(TaskRelationConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task_relation
     *
     * @mbg.generated
     */
    TaskRelationConfig selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task_relation
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TaskRelationConfig record, @Param("example") TaskRelationConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task_relation
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TaskRelationConfig record, @Param("example") TaskRelationConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task_relation
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TaskRelationConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task_relation
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TaskRelationConfig record);
}