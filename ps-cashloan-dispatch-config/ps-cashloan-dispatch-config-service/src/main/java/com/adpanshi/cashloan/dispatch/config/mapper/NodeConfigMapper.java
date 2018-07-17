package com.adpanshi.cashloan.dispatch.config.mapper;

import com.adpanshi.cashloan.dispatch.config.model.NodeConfig;
import com.adpanshi.cashloan.dispatch.config.model.NodeConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NodeConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_node
     *
     * @mbg.generated
     */
    long countByExample(NodeConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_node
     *
     * @mbg.generated
     */
    int deleteByExample(NodeConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_node
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_node
     *
     * @mbg.generated
     */
    int insert(NodeConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_node
     *
     * @mbg.generated
     */
    int insertSelective(NodeConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_node
     *
     * @mbg.generated
     */
    List<NodeConfig> selectByExample(NodeConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_node
     *
     * @mbg.generated
     */
    NodeConfig selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_node
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") NodeConfig record, @Param("example") NodeConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_node
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") NodeConfig record, @Param("example") NodeConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_node
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(NodeConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_node
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(NodeConfig record);
}