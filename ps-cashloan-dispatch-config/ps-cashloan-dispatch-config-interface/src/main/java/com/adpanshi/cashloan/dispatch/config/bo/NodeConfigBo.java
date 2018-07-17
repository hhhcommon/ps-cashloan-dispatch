package com.adpanshi.cashloan.dispatch.config.bo;

import java.io.Serializable;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class NodeConfigBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String number;
    private String name;
    private Integer prodectId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProdectId() {
        return prodectId;
    }

    public void setProdectId(Integer prodectId) {
        this.prodectId = prodectId;
    }
}
