package com.adpanshi.cashloan.dispatch.run.enums;


import com.adpanshi.cashloan.common.enums.ContentEnum;

/**
 * 任务状态
 */
public enum NodeStatus implements ContentEnum {

    INIT("提交", 0),
    PROCESSING("处理中", 10),
    SUCCESS("成功", 20),
    FAIL("失败", 30);

    private String content;
    private Integer value;

    private NodeStatus(String content, Integer value) {
        this.content = content;
        this.value = value;
    }
    @Override
    public boolean equalsValue(Integer value)
    {
        return (value != null) && (value.equals(getValue()));
    }

    public static NodeStatus valueOf(Integer value) {
        NodeStatus[] entities = NodeStatus.values();
        for (NodeStatus entity : entities) {
            if (entity.getValue().equals(value)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return content;
    }
}
