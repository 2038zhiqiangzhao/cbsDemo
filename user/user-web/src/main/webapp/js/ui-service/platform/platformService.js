app.service('platformService', ["$http","userUtil","userConstant",function($http,userUtil,userConstant) {
	var service = {};
	//url定义
	var QUERY_PLATFORM = 'platform/queryPlatformByCurrentCompany';
	
	var QUERY_PLATFORM_ALL = 'platform/queryPlatformList';
	
	var ADD_PLATFORM = 'platform/addPlatform';
	
	var MODIFY_PLATFORM = 'platform/modifyPlatform';
	
	/**
	 * 查询平台
	 */
	service.queryPlatform = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_PLATFORM, param, function(result){
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
	 * 查询平台
	 */
	service.queryPlatformAll = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_PLATFORM_ALL, param, function(result){
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
	 *新增平台
	 */
	service.addPlatform = function (param, success, failed)
	{
		userUtil.postRequest(ADD_PLATFORM, param, function(result){
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
	 *修改平台
	 */
	service.modifyPlatform = function (param, success, failed)
	{
		userUtil.postRequest(MODIFY_PLATFORM, param, function(result){
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
