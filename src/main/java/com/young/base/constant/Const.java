package com.young.base.constant;

import java.math.BigDecimal;

public class Const {

	//加密 密钥
	public static String LOGIN_PASSWORD_SALT = "qingcwuyu";
	
	//session 存放员工实体
    public static String LOGIN_SESSION_STAFF = "staff";
    
    //字典表KEY
    /*----------start--------------*/
    
    //店员等级
    public static String STAFF_LEVEL = "staff_level";
    //店员在岗状态
    public static String STAFF_STATUS = "staff_status";
    //包厢类型
    public static String BOX_TYPE = "box_type";
    //包厢状态
    public static String BOX_STATUS = "box_status";
    //客流状态
    public static String PASS_FLOW_STATUS = "pass_flow_status";
    //客流使用包厢情况
    public static String PASS_FLOW_USE = "pass_flow_use";
    
    /*----------end--------------*/
    
    //入场费  28元/人
    public static BigDecimal ADMISSION_FEE =  new BigDecimal(28);
    //入场费  28元/人
    public static BigDecimal MAID_FEE =  new BigDecimal(58);
    
    //公用  是否
    public static Integer PUBLIC_YES = 1;
    public static Integer PUBLIC_NO = 0;
    
    //员工在岗状态
    public static Integer STAFF_ZAI = 1;
    public static Integer STAFF_LI = 2;
    
    //包厢类型
    public static Integer BOX_TYPE_XIAOBAO = 1;
    public static Integer BOX_TYPE_DABAO = 2;
    public static Integer BOX_TYPE_LANGRENSHA = 3;
    public static Integer BOX_TYPE_JUBENSHA = 4;
    
    //包厢使用状态
    public static Integer BOX_NO_USE_STATUS = 0;
    public static Integer BOX_USE_STATUS = 1;
    public static Integer BOX_MAKE_STATUS = 2;
    
    //客流状态
    public static Integer PASS_FLOW_LI_STATUS = 0;
    public static Integer PASS_FLOW_ZAI_STATUS = 1;
    
    //消费类型
    public static Integer CON_NOTE_RU_TYPE = 1;//入场
    public static Integer CON_NOTE_BOX_TYPE = 2;//包厢
    public static Integer CON_NOTE_MAID_TYPE = 3;//女仆
    public static Integer CON_NOTE_OTHER_TYPE = 4;//其他
    public static Integer CON_NOTE_DISCOUNT_TYPE = 99;//使用折扣
    
}
