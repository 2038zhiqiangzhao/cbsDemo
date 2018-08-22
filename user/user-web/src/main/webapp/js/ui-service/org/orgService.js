app.service('orgService', ["$http","userUtil","userConstant",
                                        function($http,userUtil,userConstant) {
	var service = {};
	
	var  QUERY_CURRENT_ORG='org/getCurrentOrg';
	var ADD_ORG='org/addOneOrg';
	var EDIT_ORG='org/editOneOrg';
	var DELETE_ORG='org/deleteOneOrg';
	
	
	
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
	
	service.addOrg=function(param,success,failed){
		userUtil.postRequest(ADD_ORG, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	service.editOrg=function(param,success,failed){
		userUtil.postRequest(EDIT_ORG, param, function(result){
			if(success){
				success(result);
			}
		}, function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	
	service.deleteOrg=function(param,success,failed){
		userUtil.postRequest(DELETE_ORG, param, function(result){
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
