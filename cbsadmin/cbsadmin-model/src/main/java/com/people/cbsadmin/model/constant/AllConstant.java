package com.people.cbsadmin.model.constant;

import java.util.HashMap;

public class AllConstant {

	public static final long bindOrderTimeLimit = 45 * 60 * 1000L;// 下单时间限制（分钟）

	public static final HashMap<Integer, String> areaMap = new HashMap<Integer, String>() {
		{
			put(1010001, "A");
			put(1010002, "B");
			put(1010003, "C");
			put(1010004, "D");
			put(1010005, "E");
			put(1010006, "F");
			put(1010007, "G");
			put(1010008, "H");
		}
	};



	public static interface IsAvailable {
		public static final int YES = 1;
		public static final int NOT = 0;
	}

	public static interface IsDeleted {
		public static final int YES = 1;
		public static final int NOT = 0;
	}

	public static interface OrderPayState {
		public static final int ORDER_PAY_STATE_NO = 0;// 订单支付状态--待支付
		public static final int ORDER_PAY_STATE_YES = 1;// 订单状态--已经支付
	}

	public static interface PayNotifyStatus {
		public static final int HANDLER_YES = 1;// 已经处理
		public static final int HANDLER_NO = 0;// 未处理
	}

	public static interface PayNotifySource {
		public static final int ALIPAY = 1;// 支付通知来源 :支付宝
		public static final int WECHATPAY = 0; // 支付通知来源：微信
	}

	public static interface PayNotifyResultCode {
		public static final String SUCCESS = "SUCCESS";// 支付通知结果：成功
		public static final String FAIL = "FAIL"; // 支付通知结构：失败
	}

	public static interface OrderPayway {// 支付方式
		public static final int ALIPAY = 10011001;// 支付宝
		public static final int WECHAR_PAY = 10011002; // 微信
		public static final int BANK = 10011003;// 银行转账
		public static final int ACCOUNT_PAY = 10011004;// 账户余额支付
	}

	public static interface OrderStatus {// 订单状态
		public static final int ORDER_UNPAID = 1006001;// 订单状态--待支付
		public static final int ORDER_UNUSED = 1006002;// 订单状态--待使用
		public static final int ORDER_IN_USE = 1006003;// 订单状态--使用中
		public static final int ORDER_DONE = 1006004;// 订单状态--已完成
		public static final int ORDER_REFUND = 1006005;// 订单状态--退款中
		public static final int ORDER_REFUNDED = 1006006;// 订单状态--已退款
		public static final int ORDER_CANCELED = 1006007;// 订单状态--已取消
	}

	public static interface OrderRefundApplyAuditState {// 退款单状态
		public static final int WAITING_AUDIT = 10020001;// 初始化，待审核
		public static final int AUDIT_PASS = 10020002;// 审核通过
		public static final int AUDIT_NOT_PASS = 10020003;// 审核不通过
		public static final int CANCEL = 10020004;// 已取消
	}

	public static interface OrderRefundApplyHandlerState {// 退款单状态
		public static final int UNDONE = 0;// 未处理
		public static final int DONE = 1;// 已经处理
	}

	public static interface OrderRefundApplyRefundState {// 退款单状态
		public static final int INIT = 10022001;// 待退款
		public static final int SUCCESS = 10022002;// 退款成功
		public static final int FAIL = 10022003;// 退款失败
	}

	public static interface AccountPayMode {// 账户支付模式
		public static final int DEFAULT = 10017001;// 非授信
		public static final int CAN_MINUS = 10017002; // 授信
	}

	public static interface OrderSource {// 订单来源
		public static final int APP = 10016001;// 移动端APP
		public static final int WAP = 10016002; // 移动端H5
		public static final int PC = 10016003; // PC
	}

	public static interface AccountPrepayStatus {// 预支付订单状态
		public static final int WAITING_PAY = 0;// 待支付
		public static final int PAYED = 1;// 已支付
		public static final int COMPLETEED = 2; // 已完成
		public static final int CLOSED = 3; // 已关闭
	}

	public static interface AccountRecordOptType {// 账单操作类型
		public static final int RECHARGE = 0;// 充值
		public static final int PAY = 1;// 支付
		public static final int REFUND = 2;// 支付
	}

	public static interface OrderOutState {
		public static final int INIT = 0;// 初始化
		public static final int SUCCESS = 1;// 成功
	}

	public static interface SequenceName {
		public static final String ORDER_NUMBER = "ORDER_NUMBER";
		public static final String CARD_NO = "CARD_NO";
	}

	public static interface OrderAcceptUserState {
		public static final int INIT = 0; // 初始化
		public static final int SELECTED = 1;
		public static final int GIVEUP = 2;
	}


	public static interface OrderCodeTag {
		public static final String ACCOUNT_ORDER = "A";// 初始化
	}

	public static interface isRead {
		public static final boolean YES = true;
		public static final boolean NOT = false;
	}

	public static interface DoorCardIsMain {
		public static final boolean YES = true;
		public static final boolean NOT = false;
	}

	public static interface DoorCardType {
		public static final int DAY = 10021001;// 日卡
		public static final int MONTH = 10021002;// 月卡
		public static final int YEAR = 10021003;// 年卡
	}

	


	public static interface AppType {
		public static final int ios = 2;// ios
		public static final int android = 1;// 安卓
	}

	public static interface CommunityCategory {// 咨询编辑分类
		public static final int COMMUNITY_UNIQUE = 1013008;// 医谷独家
		public static final int COMMUNITY_STORY = 1013009;// 医创故事

	}
	
	public static interface CommunityActivityStates {//活动订单状态
		public static final int ACTIVITY_NOUSER = 1013013;// 未开始
		public static final int ACTIVITY_ING = 1013014;// 进行中
		public static final int ACTIVITY_OVER = 1013015;// 已结束
		
	}
	public static interface AplyActivityStates {//活动状态
		public static final int ACTIVITY_0 = 1013010;// 报名中
		public static final int ACTIVITY_1 = 1013011;// 报名截止
		public static final int ACTIVITY_2 = 1013012;// 活动结束
		
	}

	public static final String QRURL ="https://www.kuaizhan.com/common/encode-png?large=true&data=";
	public static final String CHECKMESSAGE ="当前传入的活动编号不匹配";
	public static final String ADMININFOCHCK ="等待校验中...";
	public static final String ADMININFOCHCKSERY ="当前秘钥匹配失败...";
	public static final String ADMINMANAGWER ="您已经成为";
	public static final String ADMINMANAGWERTWO ="活动的组织者,无需再次绑定";
	public static final String BINDADMINMANAGWER ="您已经成为";
	public static final String BINDADMINMANAGWERTWO ="活动的组织者";
	public static final String ODERTRPE ="3";
	public static final String ADMINTYPE ="4";
}
