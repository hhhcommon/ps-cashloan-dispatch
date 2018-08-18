package com.adpanshi.cashloan.dispatch.run.constant;

/**
 * 调度中心常量类
 */
public class DispatchConstatnt {

    /**
     * 调度中心异步任务事件BizDefineCode
     */
    public static final String BizDefineCode = "psCashloanDispatch";

    /**
     * 调度中心异步任务事件NodeDefineCode
     */
    public static final String NodeDefineCode = "psCashloanDispatch-runTask";

    public class PsCashloanNodeDefine {

        /** 注册节点 */
        public static final String INDIA_OLOAN_APP_REGISTER = "india_oloan_app_register";
        /** 登录 */
        public static final String INDIA_OLOAN_APP_LOGIN = "india_oloan_app_login";
        /** 用户基本信息 */
        public static final String india_oloan_user_baseinfo = "india_oloan_user_baseInfo";
        /** 紧急联系人 */
        public static final String INDIA_OLOAN_EMERGENCY_CONTACT = "india_oloan_emergency_contact";
        /** 盘卡信息 */
        public static final String INDIA_OLOAN_PANCARD = "india_oloan_panCard";
        /** Equifax信用报告 */
        public static final String INDIA_OLOAN_EQUIFAX = "india_oloan_equifax";
        /** 爬取设备通讯录 */
        public static final String INDIA_OLOAN_APP_CONTACTS = "india_oloan_app_contacts";
        /** 爬取设备短信 */
        public static final String INDIA_OLOAN_APP_SMS = "india_oloan_app_sms";
        /** 磨盒sim卡信息 */
        public static final String INDIA_OLOAN_MOXIE_SIM = "india_oloan_moxie_sim";
        /** 磨盒社交信息 */
        public static final String INDIA_OLOAN_MOXIE_SNS = "india_oloan_moxie_sns";
        /** 同盾信用保镖 */
        public static final String INDIA_OLOAN_TONGDUN_CREDITBODYGUARD = "india_oloan_tongdun_creditBodyguard";
        /** 同盾设备指纹 */
        public static final String INDIA_OLOAN_TONGDUN_DEVICEFINGERPRINTING = "india_oloan_tongdun_deviceFingerprinting";

    }

}
