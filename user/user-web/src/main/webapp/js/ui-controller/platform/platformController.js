app.controller("platformController", ["$scope", "$http", "$stateParams", "userUtil", "platformService",function($scope, $http, $stateParams, userUtil, platformService) {
	$scope.querycontent = {};
	$scope.addplatform = {};
	$scope.mdfplatform = {};
	//查询方法
	$scope.query = function(){
		platformService.queryPlatformAll($scope.querycontent,function(result){
				if(result.code == 0){//获取数据成功
					$scope.platform_list = result.data;
					if(result.data.length > 0){
						$scope.hasResult = false;
					}else{
						$scope.hasResult = true;
					}
				}
			})
	}
	$scope.query();
	
	//新增时，情况之前数据
	$scope.cleanData = function(){
		$scope.addplatform = {};
	}
	
	$scope.savePlatform = function(){
		platformService.addPlatform($scope.addplatform,function(result){
				if(result.code == 0){//获取数据成功
					alert("保存成功");
					$('#myModal').modal('hide');
					$scope.query();
				}
				if(result.code == "-1"){//获取数据失败
					alert(result.data);
				}
			})
	}
	
	$scope.editData = function(id,platformName){
		$scope.mdfplatform = {};
		$scope.mdfplatform = {'id':id,'platformName':platformName};
	}
	
	$scope.modifyPlatform = function(){
		platformService.modifyPlatform($scope.mdfplatform,function(result){
				if(result.code == 0){//获取数据成功
					alert("修改成功");
					$('#myModifyModal').modal('hide');
					$scope.query();
				}
				if(result.code == "-1"){//获取数据失败
					alert(result.data);
				}
			})
	}

}])