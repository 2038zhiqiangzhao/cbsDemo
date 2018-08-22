app.service('platformGroupService', ["$http","userUtil","userConstant",function($http,userUtil,userConstant) {
	var service = {};
	//url定义
	var QUERY_PLATFORM_GROUP = 'platform/queryPlatformGroupList';
	
	var QUERY_GROUP_PLATFORM = 'platform/queryGroupPlatformList';
		
	var ADD_PLATFORM_GROUP = 'platform/addPlatformGroup';
	
	var MODIFY_PLATFORM_GROUP = 'platform/modifyPlatformGroup';
	
	/**
	 * 查询平台组
	 */
	service.queryPlatformGroup = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_PLATFORM_GROUP, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	service.queryGroupPlatform = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_GROUP_PLATFORM, param, function(result){
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
	 *新增平台组
	 */
	service.addPlatformGroup = function (param, success, failed)
	{
		userUtil.postRequest(ADD_PLATFORM_GROUP, param, function(result){
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
	 *修改平台组
	 */
	service.modifyPlatformGroup = function (param, success, failed)
	{
		userUtil.postRequest(MODIFY_PLATFORM_GROUP, param, function(result){
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
