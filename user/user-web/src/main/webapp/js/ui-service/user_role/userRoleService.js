app.service('userRoleService', ["$http","userUtil","userConstant",
                                        function($http,userUtil,userConstant) {
	var service = {};
	
	//url定义	
	var ADD_USER_ROLE = 'userRole/addUserRole';
	
	var DELETE_USER_ROLE = 'userRole/deleteUserRole';
	
	var UPDATE_USER_ROLE = 'userRole/updateUserRole';
	
	/**
	 * 用户增加角色
	 */
	service.addUserRole = function (param, success, failed)
	{
		userUtil.postRequest(ADD_USER_ROLE, param, function(result){
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
	 * 用户删除角色
	 */
	service.deleteUserRole = function (param, success, failed)
	{
		userUtil.postRequest(DELETE_USER_ROLE, param, function(result){
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
	 * 用户修改角色
	 */
	service.updateUserRole = function (param, success, failed)
	{
		userUtil.postRequest(UPDATE_USER_ROLE, param, function(result){
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
