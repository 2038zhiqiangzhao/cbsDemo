app.controller("positionController", ["$scope", "$http", "$stateParams", "userUtil", "positionService",function($scope, $http, $stateParams, userUtil, positionService) {
	$scope.querycontent = {};
	$scope.position_list={};
	$scope.queryContentVO = {};
	$scope.saveCheckDisabled=false;
	$scope.hasPositionName = false;
	$scope.mdfposition = {};
	$scope.mdfposition2 = {};
	$scope.addPositionDesc = '';
	//分页参数
	($scope.currentPage1 = 1, $scope.itemsPerPage1 = 10,$scope.pageSizes1 = [ 10, 20, 50, 100 ],$scope.editPage1 = true);
	
	//查询方法
	$scope.query = function(){
		//时间转换(字符串转date)
		var str =$('#from-time').val();
		str = str.replace(/-/g,"/");
		var start = new Date(str );
		
		var str2 =$('#to-time').val();
		str2 = str2.replace(/-/g,"/");
		var end = new Date(str2 );
		
		
		$scope.queryContentVO.positionName=$scope.positionName;
		$scope.queryContentVO.createTimeStart=start;
		$scope.queryContentVO.createTimeEnd=end;
		$scope.queryContentVO.currentPage = $scope.currentPage1 - 0;
		$scope.queryContentVO.itemsPerPage = $scope.itemsPerPage1;
		if($scope.queryContentVO.positionName == ''){
			$scope.queryContentVO.positionName=null;
		}
		
		positionService.queryPositionList($scope.queryContentVO,function(result){
			if(result.code == 0){//获取数据成功
				$scope.position_list = result.data;
				$scope.totalItems1 = result.total;
				$scope.pageCount1 = Math.ceil($scope.totalItems1/$scope.itemsPerPage1);
			}
			if(result.data.length > 0){
				$scope.hasResult = false;
			}else{
				$scope.hasResult = true;
			}
		});
	}
	//点击新增时清空之前输入的信息
	$scope.cleanData = function(){
		$scope.addPositionName="";
		$scope.addPositionDesc="";
	}
	
	//新增校验岗位名称是否存在
	$scope.checkPositionName = function(){
		$scope.queryContentVO2 = {};
		$scope.queryContentVO2.positionName=$scope.addPositionName;
		
		positionService.queryPositionByName($scope.queryContentVO2,function(result){
//			alert("查询结果条数:"+result.data.length);
			if(result.data.length > 0){
//				alert("存在该岗位名");
				$scope.hasPositionName = true;
				$scope.saveCheckDisabled = true;
			}else{
//				alert("不存在该岗位名");
				$scope.hasPositionName = false;
				$scope.saveCheckDisabled = false;
			}
		});
		
	}
	
	//取消保存时清除错误提示信息
	$scope.backHasPositionNameValue = function(){
		$scope.hasPositionName = false;
	};
	
	//更新校验岗位名称是否存在
	$scope.updateCheckPositionName = function(){
		$scope.queryContentVO3 = {};
		$scope.queryContentVO3.positionName=$scope.mdfposition.positionName;
		
		positionService.queryPositionByName($scope.queryContentVO3,function(result){
//			alert("查询结果条数:"+result.data.length);
			if(result.data.length > 0){
//				alert("存在该岗位名");
				$scope.hasPositionName = true;
				$scope.saveCheckDisabled = true;
			}else{
//				alert("不存在该岗位名");
				$scope.hasPositionName = false;
				$scope.saveCheckDisabled = false;
			}
		});
		
	};
	
	
	
	$scope.query();
	//新增保存时的参数校验
	$scope.saveCheck = function() {
		if (typeof $scope.addPositionName == 'undefined'|| $scope.addPositionName == ''  ) {
			$.MsgBox.Alert("提示", "岗位名不能为空！");
			return false;
		}
		if ($scope.addPositionName.length > 20) {
			$.MsgBox.Alert("提示", "岗位名不能超过20字！");
			return false;
		}
		if ($scope.addPositionDesc.length > 200) {
			$.MsgBox.Alert("提示", "岗位描述不能超过200字！");
			return false;
		}
		
		return true;
	};
	//编辑保存时的参数校验
	$scope.updateSaveCheck = function() {
		if (typeof $scope.mdfposition.positionName == 'undefined'|| $scope.mdfposition.positionName == ''  ) {
			$.MsgBox.Alert("提示", "岗位名不能为空！");
			return false;
		}
		if ($scope.mdfposition.positionName.length > 20) {
			$.MsgBox.Alert("提示", "岗位名不能超过20字！");
			return false;
		}
		if ($scope.mdfposition.positionDesc.length > 200) {
			$.MsgBox.Alert("提示", "岗位描述不能超过200字！");
			return false;
		}
		return true;
	};
	
	//新增保存
	$scope.savePosition = function(){
		//校验参数
		if (!$scope.saveCheck()) {
			return;
		}
		//设置保存对象参数
		$scope.addDTO = {};
		$scope.addDTO.positionName=$scope.addPositionName;
		$scope.addDTO.positionDesc=$scope.addPositionDesc;
		
		positionService.savePosition($scope.addDTO,function(result){
			if(result.code == 0){//获取数据成功
				$.MsgBox.Alert("提示", "保存成功！");
				$('#formT').modal('hide');
				$scope.query();
			}else{
				$.MsgBox.Alert("提示", result.data);
			}
		})
	};
	
	$scope.editData = function(id,positionName,positionDesc){
		$scope.mdfposition = {};
		$scope.mdfposition = {'id':id,'positionName':positionName,'positionDesc':positionDesc};
	}
	
	//编辑保存
	$scope.updatePosition = function(){
		//校验参数
		if (!$scope.updateSaveCheck()) {
			return;
		}
		positionService.updatePosition($scope.mdfposition,function(result){
				if(result.code == 0){//获取数据成功
					$.MsgBox.Alert("提示", "修改成功！");
					$('#formM').modal('hide');
					$scope.query();
				}
				if(result.code == "-1"){//获取数据失败
					$.MsgBox.Alert("提示", result.data);
				}
			})
	};
	
	//删除时传递id
	$scope.editData2 = function(id){
//		$scope.mdfposition2 = {};
		$scope.mdfposition2 = {'id':id};
	};
	
	//通过id删除
	$scope.deletePosition = function(){
		positionService.deletePosition($scope.mdfposition2,function(result){
				if(result.code == 0){//获取数据成功
					$.MsgBox.Alert("提示", "删除成功！");
					$('#deleteT').modal('hide');
					$scope.query();
				}
				if(result.code == "-1"){//获取数据失败
					$.MsgBox.Alert("提示", result.data);
				}
			})
	}

	
}]);