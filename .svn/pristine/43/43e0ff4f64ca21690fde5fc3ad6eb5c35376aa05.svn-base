app.controller("platformGroupController", ["$scope", "$http", "$stateParams", "userUtil", "platformGroupService","platformService",function($scope, $http, $stateParams, userUtil, platformGroupService, platformService) {
	$scope.querycontent = {};
	$scope.addplatformGroup = {};
	$scope.mdfplatformGroup = {};
	//查询方法
	$scope.query = function(){
		platformGroupService.queryPlatformGroup($scope.querycontent,function(result){
				if(result.code == 0){//获取数据成功
					$scope.platform_group_list = result.data;
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
		$scope.addplatformGroup = {};
		$scope.querycontent={};
		$scope.queryAllPlatform();
		$('#topCheckBox').prop('checked',false); 
	}
	
	$scope.editData = function(platformGroupId,platformGroupName){
		$scope.mdfplatformGroup = {};
		$scope.mdfplatformGroup = {'platformGroupId':platformGroupId,'platformGroupName':platformGroupName};
		$scope.queryPlatform();
	}
	
	//查询方法
	$scope.queryAllPlatform = function(){
		platformService.queryPlatformAll($scope.querycontent,function(result){
			if(result.code == 0){//获取数据成功
				$scope.platform_list = result.data;
			}
		})
	}
	
	//全选/反选
	$scope.allcheck = function(){
		var bischecked=$('#topCheckBox').is(':checked'); 
		var fruit=$('input[name="platform"]'); 
		fruit.prop('checked',bischecked); 
	}
	//全选/反选
	$scope.allcheck1 = function(){
		var bischecked=$('#topCheckBox1').is(':checked'); 
		var fruit=$('input[name="platform1"]'); 
		fruit.prop('checked',bischecked); 
	}
	$scope.savePlatformGroup = function(){
		if (typeof $scope.addplatformGroup.platformGroupName == 'undefined'|| $scope.addplatformGroup.platformGroupName == '') {
			alert("请输入平台组名称");
			return;
		}
		var platformIds="";
		$('input[name="platform"]:checked').each(function(){ 
		      var sfruit=$(this).val(); 
		      platformIds=platformIds+sfruit+",";
		   
		});
		if(platformIds==""){
			alert("请选择平台");
			return;
		}
		platformIds=platformIds.substring(0,platformIds.length-1);
		$scope.addplatformGroup.platformIds=platformIds;
		platformGroupService.addPlatformGroup($scope.addplatformGroup,function(result){
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
	
	//查询方法
	$scope.queryPlatform = function(){
		platformGroupService.queryGroupPlatform($scope.mdfplatformGroup,function(result){
			if(result.code == 0){//获取数据成功
				$scope.platform_list_1 = result.data;
			}
		})
	}
	
	$scope.modifyPlatformGroup = function(){
		if (typeof $scope.mdfplatformGroup.platformGroupId == 'undefined'|| $scope.mdfplatformGroup.platformGroupId == '') {
			alert("页面不合法");
			return;
		}
		if (typeof $scope.mdfplatformGroup.platformGroupName == 'undefined'|| $scope.mdfplatformGroup.platformGroupName == '') {
			alert("请输入平台组名称");
			return;
		}
		var platformIds="";
		$('input[name="platform1"]:checked').each(function(){ 
		      var sfruit=$(this).val(); 
		      platformIds=platformIds+sfruit+",";
		   
		});
		if(platformIds==""){
			alert("请选择平台");
			return;
		}
		platformIds=platformIds.substring(0,platformIds.length-1);
		$scope.mdfplatformGroup.platformIds=platformIds;
		platformGroupService.modifyPlatformGroup($scope.mdfplatformGroup,function(result){
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
}])