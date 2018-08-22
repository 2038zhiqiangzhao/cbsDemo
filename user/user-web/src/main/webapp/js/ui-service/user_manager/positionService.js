app.service('positionService', ["$http","userUtil","userConstant",function($http,userUtil,userConstant) {
	var service = {};
	//url定义
	var QUERY_POSITION_LIST = 'position/queryPositionList';
	
	var ADD_POSITION = 'position/addPosition';
	
	var UPDATE_POSITION ='position/updatePosition';
	
	var DELETE_POSITION ='position/deletePosition';
	
	var QUERY_POSITION_BY_NAME = 'position/queryPositionByName';

	/**
	 * 查询岗位信息列表
	 */
	service.queryPositionList = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_POSITION_LIST, param, function(result){
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
	 * 新增
	 */
	service.savePosition = function(param, success, failed){
		userUtil.postRequest(ADD_POSITION, param, function(result){
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
	 * 修改
	 */
	service.updatePosition = function(param, success,failed){
		userUtil.postRequest(UPDATE_POSITION,param,function(result){
			if(success){
				success(result);
			}
		},function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	/**
	 * 删除
	 */
	service.deletePosition = function(param, success,failed){
		userUtil.postRequest(DELETE_POSITION,param,function(result){
			if(success){
				success(result);
			}
		},function(err){
			if(failed){
				failed(failed);
			}
		});
	}
	
	/**
	 * 更具岗位名查询是否存在queryPositionByName
	 */
	service.queryPositionByName = function (param, success, failed)
	{
		userUtil.postRequest(QUERY_POSITION_BY_NAME, param, function(result){
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
