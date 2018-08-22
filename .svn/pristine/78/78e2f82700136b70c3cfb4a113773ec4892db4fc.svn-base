package com.people2000.user.model.enums;

/**
 * 
 * 项目名称：morning-cms-pojo 类名称：FUNCTypeEnum 类描述：FUNCTypeEnum 用户状态枚举表述常量数据字段
 * 创建人：陈星星 创建时间：2017年2月4日 下午11:02:04
 */
public enum FunctionTypeEnum {

	FIRST_FUNC(1, "一级目录"), SECOND_FUNC(2, "二级目录"), THIRD_FUNC(3, "三级目录"), HANDLE_FUNC(
			0, "操作目录");

	private Integer type;

	private String typeInfo;

	private FunctionTypeEnum(Integer type, String typeInfo) {
		this.type = type;
		this.typeInfo = typeInfo;
	}

	public Integer getType() {
		return type;
	}

	public String getTypeInfo() {
		return typeInfo;
	}

	public static FunctionTypeEnum stateOf(int index) {
		for (FunctionTypeEnum typeEnum : values()) {
			if (typeEnum.getType() == index) {
				return typeEnum;
			}
		}
		return null;
	}
}
