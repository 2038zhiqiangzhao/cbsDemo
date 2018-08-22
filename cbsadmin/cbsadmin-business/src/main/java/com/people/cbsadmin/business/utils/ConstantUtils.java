package com.people.cbsadmin.business.utils;

public class ConstantUtils {
	public static final int ISAVALABLE_YES = 1;
	public static final int ISAVALABLE_NO = 0;

	public static final String DEFAULT_SPLIT = ",";// 默认分割符
	public static final int ORDER_UNPAID = 1006001;// 订单状态--待支付
	public static final int ORDER_UNUSED = 1006002;// 订单状态--待使用
	public static final int ORDER_IN_USE = 1006003;// 订单状态--使用中
	public static final int ORDER_DONE = 1006004;// 订单状态--已完成
	public static final int ORDER_REFUND = 1006005;// 订单状态--退款中
	public static final int ORDER_REFUNDED = 1006006;// 订单状态--已退款
	public static final int ORDER_CANCELED = 1006007;// 订单状态--已取消
	public static final int ORDER_PAY_STATE_NO = 0;// 订单支付状态--待支付
	public static final int ORDER_PAY_STATE_YES = 1;// 订单状态--已经支付
	public static final int COMMUNITY_ORDER_PAY_STATE_YES = 1013015;// 订单状态--已结束 =已经支付，注意活动订单状态和以前的不同
	public static final int COMMUNITY_ORDER_PAY_STATE_NO = 1013013;// 订单状态--未开始 =等待支付，注意活动订单状态和以前的不同
	public static final int COMMUNITY_ORDER_PAY_STATE_ING = 1013014;// 订单状态--进行中 =等待支付，注意活动订单状态和以前的不同
	public static final int MAIN_ORDER_MEETING = 1;// 订单大类型--会议室
	public static final int MAIN_ORDER_REST = 2;// 订单类型--餐厅
	public static final int MAIN_ORDER_QX = 3;// 订单类型--扫码
	public static final int ORDER_MEETING_ROOM = 1;// 订单子类型--普通会议室
	public static final int ORDER_ASSEMBLY_ROOM = 2;// 订单子类型--组合会议室
	public static final String ORDER_NUMBER = "ORDER_NUMBER";// 订单序列名称
	public static final String ACCOUNT_ORDER_NUMBER = "ACCOUNT_ORDER_NUMBER";// 订单序列名称

	public static final boolean USER_MESSAGE_IS_ANSWER = true;
	public static final boolean USER_MESSAGE_NO_ANSWER = false;

	public static final int USER_MESSAGE_TYPE_TIPS = 10015001;// 提醒消息

	public static final String[] EXCEL_COMMITINFO = new String[]{"活动编号","标题","副标题","作者","微信连接"};

}
