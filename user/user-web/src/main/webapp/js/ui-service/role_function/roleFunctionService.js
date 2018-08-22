app.service('roleFunctionService', ["$http","userUtil","userConstant",
                                        function($http,userUtil,userConstant) {
	var service = {};
	
	//url定义	
	var SAVE_ROLE_FUNCTION = 'function/saveRoleFunction';
		
	/**
	 * 角色授权
	 */
	service.saveRoleFunction = function (param, success, failed)
	{
		userUtil.postRequest(SAVE_ROLE_FUNCTION, param, function(result){
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