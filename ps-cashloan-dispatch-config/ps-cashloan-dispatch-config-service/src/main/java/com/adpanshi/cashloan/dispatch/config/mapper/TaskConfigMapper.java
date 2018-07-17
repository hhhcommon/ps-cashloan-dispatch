package com.adpanshi.cashloan.dispatch.config.mapper;

import com.adpanshi.cashloan.dispatch.config.model.TaskConfig;
import com.adpanshi.cashloan.dispatch.config.model.TaskConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task
     *
     * @mbg.generated
     */
    long countByExample(TaskConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task
     *
     * @mbg.generated
     */
    int deleteByExample(TaskConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task
     *
     * @mbg.generated
     */
    int insert(TaskConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task
     *
     * @mbg.generated
     */
    int insertSelective(TaskConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task
     *
     * @mbg.generated
     */
    List<TaskConfig> selectByExample(TaskConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task
     *
     * @mbg.generated
     */
    TaskConfig selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TaskConfig record, @Param("example") TaskConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TaskConfig record, @Param("example") TaskConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TaskConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_task
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TaskConfig record);

    List<TaskConfig> findNoParentAsyncTask(@Param("nodeNumber") String nodeNumber);
}