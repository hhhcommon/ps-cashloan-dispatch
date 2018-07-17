package com.adpanshi.cashloan.dispatch.config.model;

import java.io.Serializable;
import java.util.Date;

public class ProductConfig implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dp_conf_product.FId
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dp_conf_product.FNumber
     *
     * @mbg.generated
     */
    private String number;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dp_conf_product.FName
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dp_conf_product.FIsEnable
     *
     * @mbg.generated
     */
    private Boolean isEnable;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dp_conf_product.FCreateTime
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dp_conf_product.FCreateUserId
     *
     * @mbg.generated
     */
    private Integer createUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dp_conf_product.FLastModifyTime
     *
     * @mbg.generated
     */
    private Date lastModifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dp_conf_product.FModifyUserId
     *
     * @mbg.generated
     */
    private Integer modifyUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dp_conf_product.FIsdelete
     *
     * @mbg.generated
     */
    private Integer isdelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dp_conf_product
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dp_conf_product.FId
     *
     * @return the value of dp_conf_product.FId
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dp_conf_product.FId
     *
     * @param id the value for dp_conf_product.FId
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dp_conf_product.FNumber
     *
     * @return the value of dp_conf_product.FNumber
     *
     * @mbg.generated
     */
    public String getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dp_conf_product.FNumber
     *
     * @param number the value for dp_conf_product.FNumber
     *
     * @mbg.generated
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dp_conf_product.FName
     *
     * @return the value of dp_conf_product.FName
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dp_conf_product.FName
     *
     * @param name the value for dp_conf_product.FName
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dp_conf_product.FIsEnable
     *
     * @return the value of dp_conf_product.FIsEnable
     *
     * @mbg.generated
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dp_conf_product.FIsEnable
     *
     * @param isEnable the value for dp_conf_product.FIsEnable
     *
     * @mbg.generated
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dp_conf_product.FCreateTime
     *
     * @return the value of dp_conf_product.FCreateTime
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dp_conf_product.FCreateTime
     *
     * @param createTime the value for dp_conf_product.FCreateTime
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dp_conf_product.FCreateUserId
     *
     * @return the value of dp_conf_product.FCreateUserId
     *
     * @mbg.generated
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dp_conf_product.FCreateUserId
     *
     * @param createUserId the value for dp_conf_product.FCreateUserId
     *
     * @mbg.generated
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dp_conf_product.FLastModifyTime
     *
     * @return the value of dp_conf_product.FLastModifyTime
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dp_conf_product.FLastModifyTime
     *
     * @param lastModifyTime the value for dp_conf_product.FLastModifyTime
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dp_conf_product.FModifyUserId
     *
     * @return the value of dp_conf_product.FModifyUserId
     *
     * @mbg.generated
     */
    public Integer getModifyUserId() {
        return modifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dp_conf_product.FModifyUserId
     *
     * @param modifyUserId the value for dp_conf_product.FModifyUserId
     *
     * @mbg.generated
     */
    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dp_conf_product.FIsdelete
     *
     * @return the value of dp_conf_product.FIsdelete
     *
     * @mbg.generated
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dp_conf_product.FIsdelete
     *
     * @param isdelete the value for dp_conf_product.FIsdelete
     *
     * @mbg.generated
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_product
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProductConfig other = (ProductConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getIsEnable() == null ? other.getIsEnable() == null : this.getIsEnable().equals(other.getIsEnable()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getModifyUserId() == null ? other.getModifyUserId() == null : this.getModifyUserId().equals(other.getModifyUserId()))
            && (this.getIsdelete() == null ? other.getIsdelete() == null : this.getIsdelete().equals(other.getIsdelete()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dp_conf_product
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIsEnable() == null) ? 0 : getIsEnable().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getModifyUserId() == null) ? 0 : getModifyUserId().hashCode());
        result = prime * result + ((getIsdelete() == null) ? 0 : getIsdelete().hashCode());
        return result;
    }
}