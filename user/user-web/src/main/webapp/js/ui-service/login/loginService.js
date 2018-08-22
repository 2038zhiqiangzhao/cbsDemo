app.service('loginService', ["$http","userUtil","userConstant",
                                        function($http,userUtil,userConstant) {
	var service = {};
	//url定义
	var USER_LOGIN = 'mobileLogin/login';
	//联合登录
	var USER_UNION_LOGIN='login/unionLogin';
	
	/**
	 * 登录
	 */
	service.login = function (param, success, failed)
	{
		userUtil.postRequest(USER_LOGIN, param, function(result){
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
	 * 联合登录
	 */
	service.unionLogin = function (param, success, failed)
	{
		userUtil.postRequest(USER_UNION_LOGIN, param, function(result){
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
