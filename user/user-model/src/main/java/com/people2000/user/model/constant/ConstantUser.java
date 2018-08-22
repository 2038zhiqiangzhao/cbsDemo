package com.people2000.user.model.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2015/4/3.
 */
public class ConstantUser {

	public static final String COOKIE_NAME = "ut";// cookie名称
	public static final int COOKIE_EXPIRATION_TIME = 1;// cookie过期时间 单位天
	public static final String default_role = "4";// 默认用户角色
	/**
	 * 菜单根节点ID
	 */
	public static final Long TREE_ROOT_ID = 30L;

	/**
	 * 用户审核状态
	 */
	public interface user_status {
		public static final int wait = 0;// 待审核
		public static final int success = 1;// 审核成功
		public static final int fail = 2;// 审核失败
	}

	/**
	 * 用户类型
	 */
	public interface user_type {
		public static final int backend = 1;// 系统管理员
		public static final int main = 2;// 商家主账户
		public static final int child = 3;// 商家子账户
		public static final int member = 4;// 会员账户
		public static final int business = 5;// 企业用户
		public static final int userManage = 6;// 用户管理员
		public static final int operation = 7;// 运营人员
		public static final int service = 8;// 服务人员
		public static final int lawyer = 9;// 律师人员
	}

	public interface entity_type {
		public static final int merchant = 1;// 商家
		public static final int distributor = 2;// 分销商
	}

	/**
	 * 校验后执行的方法
	 */
	public interface check_somthing {
		public static final int mobile_binding = 1;// 手机绑定
		public static final int mobile_dahua = 2;// 手机解绑
	}

	/**
	 * 操作者类型
	 */
	public interface operator_type {
		public static final String operator = "1";// 运营
	}

	/**
	 * 角色类型
	 */
	public interface roel_type {
		public static final Long parent = 1l;// 非子账户角色
		public static final Long child = 2l;// 子账户角色
	}

	/**
	 * 是否可用:默认0否;1是
	 */
	public interface isAvailable {
		public static final int isAvailable = 1;// 不可用
		public static final int notAvailable = 0;// 可用
	}

	/**
	 * 是否已删除
	 */
	public interface isDelete {
		public static final int normal = 0;// 正常
		public static final int delete = 1;// 删除
	}

	/**
	 * 是否是web项目环境
	 */
	static boolean webENV = false;

	public static boolean isWebENV() {
		return webENV;
	}

	public static void setWebENV(boolean webENV) {
		ConstantUser.webENV = webENV;
	}

	public boolean isWebENV2() {
		return webENV;
	}

	public void setWebENV2(boolean webENV) {
		ConstantUser.webENV = webENV;
	}

	// 权限类型 1 菜单 2按钮
	public interface function_type {
		public static final Integer menu = 1;
		public static final Integer btn = 2;
	}

	// 证件类型 1 身份证 2其他
	public interface Card_type {
		public static final Integer identityCard = 1;
		public static final Integer other = 2;
	}

	// 公司id
	public interface Company_id {
		public static final Long blbl = 4L;
		public static final Long zg = 5L;
		public static final Long ds = 10L;
	}

	// u_user_product_relation 表中对应的状态
	public static final int u_user_product_relation_status_error = -1; // 初始化状态
	public static final int u_user_product_relation_status_instance = 1; // 初始化状态
	public static final int u_user_product_relation_status_test = 2; // 测试状态
	public static final int u_user_product_relation_status_Normal = 3; // 正常运行状态
	public static final int u_user_product_relation_status_ban = 4; // 禁用状态
	public static final Integer u_user_address_default_is_true = 1; // 默认地址
	public static final Integer u_user_address_default_is_false = 0; // 非默认地址

	public interface filter {
		public static final String noLogin = "noLogin";// 免登录
		public static final String noFunction = "noFunction";// 免登录
		public static final String url = "url";// 更换type之后跳转
	}

	public interface invoiceType {
		public static final int no_support = 0;// 都不支持
		public static final int general_invoice = 1;// 支持普通发票
		public static final int value_added = 2;// 支持增值发票
		public static final int all_support = 3;// 都支持
	}

	public interface defaultRole {
		public static final Long backend = 991l;// 后台运营人员默认权限
		public static final Long main = 992l;// 商家主账户注册默认权限
		public static final Long mainPass = 993l;// 商家主账户审核通过默认权限
		public static final Long child = 994l;// 商家子账户默认权限
		public static final Long member = 995l;// 会员账户默认权限
		public static final Long business = 996l;// 企业用户默认权限
		public static final Long login = 5l;// 可登陆权限
	}

	public interface defaultRole_pass_status {
		public static final String nopass = "0";// 后台运营人员默认权限
		public static final String pass = "1";// 商家主账户注册默认权限
	}

	public interface sex {
		public static final int man = 0;// 男人
		public static final int woman = 1;// 女人
		public static final int secrecy = 2;// 保密

	}

	public interface identity_type {
		public static final int mechanician = 1;// 机械师
		public static final int diagnostician = 2;// 诊断师
	}

	public interface mq_topic {
		public static final String register_topic = "ouser_register_notice";// 注册topic
		public static final String ouser_mobile_change_topic = "ouser_mobile_change_notice";// 注册topic
	}

	public interface client_type {
		public static final int ios = 1;// ios
		public static final int android = 2;// 安卓
		public static final int web = 3;// web
	}

	public interface account_opt_type {
		public static final int recharge = 0;// 充值
		public static final int pay = 1;// 支付
	}

	public interface pay_mode {
		public static final int canMinus = 10017002;// 允许为负数
		public static final int canNotMinus = 10017001;// 不允许为负数
	}

	public static final List<Integer> authList_1 = new ArrayList<Integer>() {
		{
			add(1);
		}
	};

	public static final List<Integer> authList_2 = new ArrayList<Integer>() {
		{
			add(2);
			add(3);
			add(8);
		}
	};

	public static final List<Integer> authList_3 = new ArrayList<Integer>() {
		{
			add(8);
		}
	};

	public static final Map<Integer, List<Integer>> login_check_map = new HashMap<Integer, List<Integer>>() {
		{
			put(1, authList_1);
			put(0, authList_2);
			put(2, authList_3);
		}
	};

}