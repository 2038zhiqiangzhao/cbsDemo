app.controller("roleFunctionController", ["$scope", "$http", "$stateParams", "userUtil", "roleFunctionService","platformService","roleService","functionService",function($scope, $http, $stateParams, userUtil, roleFunctionService,platformService,roleService,functionService) {
	
	var code;
 	var zTree = {};
 		
 	$scope.setCheck = function() {
 			zTree = $.fn.zTree.getZTreeObj("treeDemo"),
 			py = "p",
 			sy = "s",
 			pn = "p",
 			sn = "s",
 			type = { "Y":py + sy, "N":pn + sn};
 			zTree.setting.check.chkboxType = type;
 			$scope.showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
 	}
 	
 	$scope.showCode = function(str) {
 			if (!code) code = $("#code");
 			code.empty();
 			code.append("<li>"+str+"</li>");
 	}
 	
	$scope.platform = "";
	$scope.role = "";
	var connection = {};
	$scope.init = function(){
		var setting = {check: {enable: true},data: {simpleData: {enable: true}}};
		connection={"platformId":$scope.platform.id,"roleId":($scope.role==null?null:$scope.role.id)};
    	functionService.queryRoleFunction(connection,function(result){
				if(result.code == 0){//获取数据成功
					var zNodes = result.data;
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					$scope.setCheck();
					$("#py").bind("change", true);
					$("#sy").bind("change", true);
					$("#pn").bind("change", true);
					$("#sn").bind("change", true);
				}
		})
	}
	$scope.init();
    
    //所有平台查询
    $scope.platforms = [];
    $scope.getPlatform = function () {
    	var params = {};
    	platformService.queryPlatformAll(params,function(result){
	        	if(result.code == "0"){
	        		$scope.platforms = result.data;
	        	}
	        });
    };
    
    $scope.getPlatform();
    
    $scope.changePlatform = function(){
    	zTree.destroy();
		$scope.init();
		$scope.queryRole();
    };
    
	//查询方法
	$scope.queryRole = function(){
		$scope.role="";
		 //获取选中radio的值
		var connection={"platformId":$scope.platform==null?null:$scope.platform.id};
		roleService.queryAllRoleByUser(connection,function(result){
			if(result.code == 0){//获取数据成功
				$scope.role_list = result.data;
			}
		})
	}
	//保存角色授权
	$scope.saveRoleFunction = function(){
		var nodes = zTree.getCheckedNodes();
		var functionIds = [];
		for(var i=0 ;i<nodes.length;i++){
			functionIds.push(nodes[i].functionId);
		}
		if($scope.platform.id==null||$scope.platform.id==""){
			$.MsgBox.Alert("提示", "请选择平台！");
			return;
		}
		if($scope.role.id==null||$scope.role.id==""){
			$.MsgBox.Alert("提示", "请选择角色！");
			return;
		}
		var connection={"platformId":$scope.platform.id,"roleId":$scope.role.id,"functionIds":functionIds};
		roleFunctionService.saveRoleFunction(connection,function(result){
			if(result.code == 0){//获取数据成功
				$.MsgBox.Alert("提示", "保存成功！");
			}
		})
	}
	
	 $scope.changeRole = function(){
		 zTree.destroy();
		 $scope.init();
     };
}])