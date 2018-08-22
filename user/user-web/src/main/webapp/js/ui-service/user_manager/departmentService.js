app.service('departmentService', ["$http","userUtil","userConstant",
                                        function($http,userUtil,userConstant) {
	var service = {};
	
	var GET_ALL_ORG_INFO='org/getAllOrgInfo';
	var GET_ALL_POSITION_INFO='org/getAllPostitionInfo';
	var QUERY_USER_BY_ORG_INFO='org/queryUserByOrgInfo';
	var USER_ORG_CHANGE='org/userOrgChange';
	var ADD_YONG_HU='org/addYonghu';
	var QUERY_USER_BY_EMAIL='user/queryUserByEmail';
	
	var QUERY_USER_DOOR_CARD_LIST='/mzadmin-web/userDoorCard/queryUserDoorCardList';
	
	service.getAllOrgInfo = function (param, success, failed){
		userUtil.postRequest(GET_ALL_ORG_INFO, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	//根据邮箱验证查询用户,校验邮箱唯一
	service.queryUserByEmail = function (param, success, failed){
		userUtil.postRequest(QUERY_USER_BY_EMAIL, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	
	service.getAllPostitionInfo = function (param, success, failed){
		userUtil.postRequest(GET_ALL_POSITION_INFO, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	service.queryDepartmentInfo=function(param,success,failed){
		userUtil.postRequest(QUERY_USER_BY_ORG_INFO, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	service.orgChange=function(param,success,failed){
		userUtil.postRequest(USER_ORG_CHANGE, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	service.addYonghuSave=function(param,success,failed){
		userUtil.postRequest(ADD_YONG_HU, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	};
	
	service.queryUserDoorCardList=function(param,success,failed){
		userUtil.postRequest(QUERY_USER_DOOR_CARD_LIST, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	};
	
	return service;
}]);
