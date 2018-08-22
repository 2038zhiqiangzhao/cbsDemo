app.factory('userConstant',function(){//常量类
	var constant = {};
	/**
	 * 常量定义
	 * */
	var ADUIT_STATUS = [{'id':0,'text':'未审核'},{'id':1,'text':'审核通过'},{'id':2,'text':'审核拒绝'}];//审核状态
	var USER_GRADE = [{'id':1,'text':'普通用户'},{'id':2,'text':'VIP用户'}];
	var COMBINED_GOODS_PRICE_TYPE = [{id: 1, text: '子品总价百分比'}, {id: 2, text: '子品总价减'},{id: 3, text: '绝对单价'}];

	var STATION_ADUIT_STATUS = [{'id':1,'text':'保存未审核'},{'id':2,'text':'提交审核'},{'id':3,'text':'待审核'},{'id':4,'text':'审核通过'},{'id':-1,'text':'审核不通过'}];
	
	
	var SHOW_REALITY_REPERTORY_NUM = [{'id':-1,'text':'未添加库存量'},{'id':null,'text':0}];
	//状态位+
	var SUCCESS = '0';//成功
	var FAILED = '-1';//失败
	/**end*/
	
	/**
	 * 方法定义
	 * */
	constant.getShowRealityRepertoryNum = function(){//商品库存量
		return SHOW_REALITY_REPERTORY_NUM;
	};
	 
	constant.getUserGrade = function(){//获取用户等级
		return USER_GRADE;
	};
	constant.getAduitStatus = function(){//获取审核状态
		return ADUIT_STATUS;
	};
	
	constant.getSuccessStatus = function(){//获取成功状态
		return SUCCESS;
	};
	
	constant.getFailedStatus = function(){//获取失败状态
		return FAILED;
	};
	
	constant.getCombinedGoodsPriceType = function(){//获取组合商品价格类型
		return COMBINED_GOODS_PRICE_TYPE;
	};
	constant.getStationAduitStatus=function(){
		return STATION_ADUIT_STATUS;
	}
	
	/**end*/
	return constant;
});