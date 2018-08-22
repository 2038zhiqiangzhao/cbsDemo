app.service('roleService', ["$http","userUtil","userConstant",
                                        function($http,userUtil,userConstant) {
	var service = {};
	//url定义		
	var QUERY_ROLE_USER = 'role/queryRoleDataByUser';
	
	/**
	 * 查询所有角色
	 */
	service.queryAllRoleByUser = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_ROLE_USER, param, function(result){
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
