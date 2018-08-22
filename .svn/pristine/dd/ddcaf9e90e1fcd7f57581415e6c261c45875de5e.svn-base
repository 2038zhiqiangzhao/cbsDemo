app.service('orgUserService', ["$http","userUtil","userConstant",
                                        function($http,userUtil,userConstant) {
var service = {};
	
	var QUERY_CURRENT_ORG='org/getCurrentOrg';
	var QUERY_ORG_USER='org/queryOrgUser';
	var QUERY_ADD_USER='org/queryAddUser';
	var DELETE_ORG_USER='org/deleteOrgUser';
	var PUT_ORG_USER='org/putOrgUser';
	
	
	
	service.queryCurrentOrg = function (param, success, failed){
		userUtil.postRequest(QUERY_CURRENT_ORG, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	service.queryOrgUser=function(param,success,failed){
		userUtil.postRequest(QUERY_ORG_USER, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	
	service.queryAddUser=function(param,success,failed){
		userUtil.postRequest(QUERY_ADD_USER, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	
	service.deleteOrgUser=function(param,success,failed){
		userUtil.postRequest(DELETE_ORG_USER, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	
	service.putOrgUser=function(param,success,failed){
		userUtil.postRequest(PUT_ORG_USER, param, function(result){
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
