package com.young.base.constant;

import java.math.BigDecimal;
import java.util.HashMap;

public class Const {

	//加密 密钥
	public static String LOGIN_ACCOUNTS_SALT = "qingcwuyu";
	public static String LOGIN_PASSWORD_SALT = "qcwy";
	
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
    //女仆费  58元/人/小时
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
    public static Integer CON_NOTE_BIRTHDAY_BUY_TYPE = 102;//生日福利购买
    public static Integer CON_NOTE_BIRTHDAY_TYPE = 101;//使用生日福利
    public static Integer CON_NOTE_SUBTRACT_TYPE = 100;//使用抵扣券
    public static Integer CON_NOTE_ACT_DISCOUNT_TYPE = 98;//活动折扣
    public static Integer CON_NOTE_VIP_DISCOUNT_TYPE = 99;//会员折扣
    
    //会员使用表 类型
    public static Integer VIP_USE_NOTE_TYPE_VIP_RECHARGE = 1;//会员充值
    public static Integer VIP_USE_NOTE_TYPE_VIP_USE = 2;//会员消费
    
    //活动表 类型
    public static Integer ACTIVITY_TYPE_SCORE = 1;//积分翻倍
    public static Integer ACTIVITY_TYPE_DISCOUNT = 2;//消费打折
    
    //积分表 类型
    public static Integer SCORE_TYPE_GET = 1;//获取
    public static Integer SCORE_TYPE_CONSUME = 2;//消耗
    
    //会员等级对应的折扣
    public static HashMap<String, String > LEVELDISCOUNT = new HashMap<String, String>(){
		
        private static final long serialVersionUID = 1L;

        {
	        put("lv1","1");      
	        put("lv2","0.98");      
	        put("lv3","0.95");      
	        put("lv4","0.92");      
	        put("lv5","0.88");      
	    }
	}; 
	//积分升级会员等级对应积分
	public static HashMap<String, BigDecimal > LEVELUPGRADE = new HashMap<String, BigDecimal>(){
		
        private static final long serialVersionUID = 1L;

        {
			put("1-2",new BigDecimal(2000));      
			put("2-3",new BigDecimal(6000));      
			put("3-4",new BigDecimal(10000));      
		}
	}; 
	//对应折扣
	public static HashMap<String, String > DISCOUNT = new HashMap<String, String>(){
		
        private static final long serialVersionUID = 1L;

        {
			put("0.99","九九");      
			put("0.98","九八");      
			put("0.97","九七");      
			put("0.96","九六");      
			put("0.95","九五");      
			put("0.94","九四");      
			put("0.93","九三");      
			put("0.92","九二");      
			put("0.91","九一");      
			put("0.9","九");      
			put("0.89","八九");      
			put("0.88","八八");      
			put("0.87","八七");      
			put("0.86","八六");      
			put("0.85","八五");      
			put("0.84","八四");      
			put("0.83","八三");      
			put("0.82","八二");      
			put("0.81","八一");      
			put("0.8","八");      
		}
	}; 
    
}
