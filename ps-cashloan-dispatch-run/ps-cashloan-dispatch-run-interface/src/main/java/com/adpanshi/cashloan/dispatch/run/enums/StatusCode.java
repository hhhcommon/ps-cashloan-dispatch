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

    INNER_CROSS_VALIDATION_ADDRESS_FEATURE_FAIL("内部地址交叉验证特征抽取失败",60013),
    FULL_THIRDPARTY_PANCARD_INFO_FAIL("完整的第三方盘卡信息获取失败", 60021),

    USER_REGISTER_FAIL("用户注册信息获取失败",70010),
    USER_REGISTER_VARIABLE_FAIL("用户注册信息变量抽取失败",70011),
    USER_REGISTER_FEATURE_FAIL("用户注册信息特征抽取失败",70012),

    FULL_USER_BASE_INFO_FAIL("完整用户基本信息获取失败",70020),
    FULL_USER_BASE_INFO_VARIABLE_FAIL("完整用户基本信息变量抽取失败",70021),
    FULL_USER_BASE_INFO_FEATURE_FAIL("完整用户基本信息特征抽取失败",70022),

    FULL_MOXIE_SIM_INFO_FAIL("魔盒SIM信息获取失败",70030),
    FULL_MOXIE_SIM_INFO_VARIABLE_FAIL("魔盒SIM信息变量抽取失败",70031),
    FULL_MOXIE_SIM_INFO_FEATURE_FAIL("魔盒SIM信息特征抽取失败",70032),

    FULL_MOXIE_SNS_INFO_FAIL("魔盒SNS信息获取失败",70040),
    FULL_MOXIE_SNS_INFO_VARIABLE_FAIL("魔盒SNS信息变量抽取失败",70041),
    FULL_MOXIE_SNS_INFO_FEATURE_FAIL("魔盒SNS信息特征抽取失败",70042),

    FULL_EQUIFAX_CREDITREPORT_INFO_FAIL("信用报告信息获取失败",70050),
    FULL_EQUIFAX_CREDITREPORT_INFO_VARIABLE_FAIL("信用报告信息变量抽取失败",70051),
    FULL_EQUIFAX_CREDITREPORT_INFO_FEATURE_FAIL("信用报告信息特征抽取失败",70052),

    FULL_USER_SMS_INFO_FAIL("用户短信信息获取失败",70060),
    FULL_USER_SMS_INFO_VARIABLE_FAIL("用户短信信息变量抽取失败",70061),
    FULL_USER_SMS_INFO_FEATURE_FAIL("用户短信信息特征抽取失败",70062),

    FULL_CONTACTS_INFO_FAIL("用户通讯录信息获取失败",70070),
    FULL_CONTACTS_INFO_VARIABLE_FAIL("用户通讯录信息变量抽取失败",70071),
    FULL_CONTACTS_INFO_FEATURE_FAIL("用户通讯录信息特征抽取失败",70072),

    FULL_APPLICATION_INFO_FAIL("app应用信息获取失败",70080),
    FULL_APPLICATION_INFO_VARIABLE_FAIL("app应用信息变量抽取失败",70081),
    FULL_APPLICATION_INFO_FEATURE_FAIL("app应用信息特征抽取失败",70082),

    FULL_EMERGENCY_INFO_FAIL("app紧急联系人获取失败",70090),
    FULL_EMERGENCY_INFO_VARIABLE_FAIL("app紧急联系人变量抽取失败",70091),
    FULL_EMERGENCY_INFO_FEATURE_FAIL("app紧急联系人特征抽取失败",70092),

    FULL_CALLRECORD_FAIL("app通话记录获取失败",70100),
    FULL_CALLRECORD_VARIABLE_FAIL("app通话记录变量抽取失败",70101),
    FULL_CALLRECORD_FEATURE_FAIL("app通话记录特征抽取失败",70102),

    FULL_TD_BODYGUARD_INFO_FAIL("同盾保镖信息获取失败",70110),
    FULL_TD_BODYGUARD_INFO_VARIABLE_FAIL("同盾保镖信息变量抽取失败",70111),
    FULL_TD_BODYGUARD_INFO_FEATURE_FAIL("同盾保镖信息特征抽取失败",70112),

    OLOAN_LOAN_APPLY_FAIL("oloan贷款申请失败", 70120),
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
