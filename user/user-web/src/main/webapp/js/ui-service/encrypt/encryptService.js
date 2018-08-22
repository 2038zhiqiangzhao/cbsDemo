app.service('encryptService', ["$http","userUtil","userConstant",
                                        function($http,userUtil,userConstant) {
	var service = {};
	//url定义
	var QUERY_USER_ENCRYPT = 'encryptInfo/fEncryptInfo';
	
	var QUERY_ENCRYPT_BY_ID = 'encryptInfo/fEncryptInfoById';
	
	/**
	 * 查询加密信息
	 */
	service.queryEncryptInfo = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_USER_ENCRYPT, param, function(result){
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
	 * 查询加密信息
	 */
	service.queryEncryptInfoById = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_ENCRYPT_BY_ID, param, function(result){
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
