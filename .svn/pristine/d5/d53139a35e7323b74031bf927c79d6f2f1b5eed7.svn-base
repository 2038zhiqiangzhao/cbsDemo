app.service('roleListService', ["$http","userUtil","userConstant",
                                        function($http,userUtil,userConstant) {
	var service = {};
	
	//url定义	
	var ADD_ROLE = 'role/saveRole';
	
	var DELETE_ROLE = 'role/deleteRole';
	
	var UPDATE_ROLE = 'role/updateRole';
	
	var QUERY_ROLE_BYID='role/getRoleById';
	
	var QUERY_ALL_ROLE_PAGE = 'role/queryAllRoleDataPage';
	
	var QUERY_ALL_ROLE = 'role/queryListRoleData';
	
	var QUERY_ROLE_BYNAME='role/queryRoleNameForAdd';

	var QUERY_ROLE_BYCODE='role/queryCodeForAdd';


	
	

	
	
	/**
	 * 增加角色
	 */
	service.addRole = function (param, success, failed)
	{
		userUtil.postRequest(ADD_ROLE, param, function(result){
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
	 * 删除角色
	 */
	service.deleteRole = function (param, success, failed)
	{
		userUtil.postRequest(DELETE_ROLE, param, function(result){
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
	service.updateRole = function (param, success, failed)
	{
		userUtil.postRequest(UPDATE_ROLE, param, function(result){
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
	 * 根据id查询角色
	 */
	service.roleById = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_ROLE_BYID, param, function(result){
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
	 * 分页查询所有角色
	 */
	service.queryAllRolePage = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_ALL_ROLE_PAGE, param, function(result){
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
	 * 查询所有角色
	 */
	service.queryAllRole = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_ALL_ROLE, param, function(result){
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
	 * 根据角色名查询
	 */
	service.queryRoleByName = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_ROLE_BYNAME, param, function(result){
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
	 * 根据角色code查询
	 */
	service.queryRoleByCode = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_ROLE_BYCODE, param, function(result){
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
