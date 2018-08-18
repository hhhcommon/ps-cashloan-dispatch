package com.adpanshi.cashloan.dispatch.config.mapper;

import com.adpanshi.cashloan.dispatch.config.model.ProductConfig;
import com.adpanshi.cashloan.dispatch.config.model.ProductConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cl_conf_product
     *
     * @mbg.generated
     */
    long countByExample(ProductConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cl_conf_product
     *
     * @mbg.generated
     */
    int deleteByExample(ProductConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cl_conf_product
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cl_conf_product
     *
     * @mbg.generated
     */
    int insert(ProductConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cl_conf_product
     *
     * @mbg.generated
     */
    int insertSelective(ProductConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cl_conf_product
     *
     * @mbg.generated
     */
    List<ProductConfig> selectByExample(ProductConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cl_conf_product
     *
     * @mbg.generated
     */
    ProductConfig selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cl_conf_product
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ProductConfig record, @Param("example") ProductConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cl_conf_product
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ProductConfig record, @Param("example") ProductConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cl_conf_product
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ProductConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cl_conf_product
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ProductConfig record);
}