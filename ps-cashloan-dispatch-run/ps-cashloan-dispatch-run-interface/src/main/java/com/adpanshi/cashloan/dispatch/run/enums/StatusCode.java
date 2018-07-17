package com.adpanshi.cashloan.dispatch.run.enums;


/**
 * 状态枚举
 */
public enum StatusCode {
    //通用
    SUCCESS("交互成功", 10000),
    PARAMS_WRONG("非法传参", 10020),
    OTHER_ERROR("其它错误", 10099),
    SKIP_TASK("任务跳过", 10021),

    INNER_CROSS_VALIDATION_MASTER_FAIL("内部本人信息交叉验证失败",60001),
    INNER_CROSS_VALIDATION_MASTER_VARIABLE_FAIL("内部本人信息交叉变量抽取失败",60002),
    INNER_CROSS_VALIDATION_MASTER_FEATURE_FAIL("内部本人信息交叉特征抽取失败",60003),

    FULL_USER_BASE_INFO_FAIL("完整用户基本信息获取失败",70020),
    FULL_USER_BASE_INFO_VARIABLE_FAIL("完整用户基本信息变量抽取失败",70021),
    FULL_USER_BASE_INFO_FEATURE_FAIL("完整用户基本信息特征抽取失败",70022),
    ;

    private String content;
    private Integer value;

    private StatusCode(String content, Integer value) {
        this.content = content;
        this.value = value;
    }

    public static StatusCode valueOf(Integer value) {
        StatusCode[] entities = StatusCode.values();
        for (StatusCode entity : entities) {
            if (entity.getValue().equals(value)) {
                return entity;
            }
        }
        return null;
    }

    public String getContent() {
        return this.content;
    }

    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return content;
    }
}
