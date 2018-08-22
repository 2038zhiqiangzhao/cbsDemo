app.service('userService', ["$http","userUtil","userConstant",
                                        function($http,userUtil,userConstant) {
	var service = {};
	//url定义	
	var QUERY_USER = 'user/queryAllUserDataPage';
	
	var QUERY_USER_DETAIL = 'user/getUserDetail';
	
	var QUERY_BACKUSER = 'user/queryListManageUserData';
	
	var UPDATE_USER_PASSWORD = 'user/updatePassWord';
	
	var SAVE_MANAGE_USER = 'user/saveManageUser';
	
	var UPDATE_USER_DETAIL = 'user/updateUserDetail';
	
	var RESET_USER_PASSWORD = 'user/resetUserPassword';
	
	/**
	 * 查询用户详情
	 */
	service.queryUserDetail = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_USER_DETAIL, param, function(result){
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
	 * 修改用户资料
	 */
	service.updateUserDetail = function (param, success, failed)
	{
		userUtil.postRequest(UPDATE_USER_DETAIL, param, function(result){
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
	 * 查询所有用户
	 */
	service.queryAllUser = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_USER, param, function(result){
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
	 * 查询后台所有用户
	 */
	service.queryAllBackUser = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_BACKUSER, param, function(result){
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
	 * 更新用户
	 */
	service.updateUserPassword = function (param, success, failed)
	{
		userUtil.postRequest(UPDATE_USER_PASSWORD, param, function(result){
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
	 * 重置密码
	 */
	service.resetPassWord = function (param, success, failed)
	{
		userUtil.postRequest(RESET_USER_PASSWORD, param, function(result){
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
	 * 保存管理员
	 */
	service.saveManageUser = function (param, success, failed)
	{
		userUtil.postRequest(SAVE_MANAGE_USER, param, function(result){
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
