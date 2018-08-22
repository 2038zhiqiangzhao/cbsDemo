app.service('registerService', ["$http","userUtil","userConstant",
                                        function($http,userUtil,userConstant) {
	var service = {};
	//url定义
	var SEND_CAPTCHAS = 'register/sendCaptchas';
	
	var REGISTER = 'register/register';
	
	var CHECK_USERNAME = 'register/checkUserName';
	
	/**
	 * 发送验证码
	 */
	service.sendCaptchas = function (param, success, failed)
	{
		userUtil.postRequest(SEND_CAPTCHAS, param, function(result){
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
	 * 注册
	 */
	service.register = function (param, success, failed)
	{
		userUtil.postRequest(REGISTER, param, function(result){
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
	 * 校验用户名
	 */
	service.checkUserName = function (param, success, failed)
	{
		userUtil.postRequest(CHECK_USERNAME, param, function(result){
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
