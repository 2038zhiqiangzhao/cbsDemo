app.service('functionService', ["$http","userUtil","userConstant",
                                        function($http,userUtil,userConstant) {
	var service = {};
	//url定义	
	var QUERY_FUNCTION = 'function/getAllFunctionByPlatform';	
	//url定义	
	var QUERY_ROLE_FUNCTION = 'function/getAllRoleFunctionByPlatform';
		
	var QUERY_USER_FUNCTION = 'function/getAllUserFunctionByPlatform';
	
	var SAVE_FUNCTION = 'function/saveFunction';
	
	var UPDATE_FUNCTION = 'function/updateFunction';
	
	/**
	 * 查询平台所有功能
	 */
	service.queryFunction = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_FUNCTION, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	/**
	 * 查询角色当前平台对应权限
	 */
	service.queryRoleFunction = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_ROLE_FUNCTION, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	/**
	 * 查询用户当前平台所有功能
	 */
	service.queryUserFunction = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_USER_FUNCTION, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	/**
	 * 保存权限
	 */
	service.saveFunction = function (param, success, failed)
	{
		userUtil.postRequest(SAVE_FUNCTION, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	/**
	 * 修改权限
	 */
	service.updateFunction = function (param, success, failed)
	{
		userUtil.postRequest(UPDATE_FUNCTION, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	return service;
}]);
