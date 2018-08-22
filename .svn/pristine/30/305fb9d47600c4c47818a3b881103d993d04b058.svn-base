 app.controller("userRoleController", ["$scope", "$http", "$stateParams", "userUtil", "userRoleService","userService","platformService","roleService","functionService",function($scope, $http, $stateParams, userUtil, userRoleService,userService,platformService,roleService,functionService) {
	    $scope.querycontent = {};
        $scope.querycontent.currentPage = 1;
    	$scope.querycontent.itemsPerPage = 10;
    	
    	$scope.platform = "";
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
       
    	//分页配置
    	$scope.paginationConf = {
    		currentPage: 1,
    		totalItems: 1,
    		itemsPerPage: 10,
    		pagesLength: 15,
			editPage: true,
    		perPageOptions: [ 10, 20, 50, 100 ],
    		rememberPerPage: 'perPageItems',
    		onChange: function () {
    			$scope.querycontent.currentPage = $scope.paginationConf.currentPage;
    			$scope.querycontent.itemsPerPage = $scope.paginationConf.itemsPerPage;
    			$scope.query();
    		}
    	};
    	
    	
    	//查询方法
    	$scope.queryRole = function(){
    		 //获取选中radio的值
            var userId = $('input[name="user"]:checked').val();
    		var connection={"platformId":$scope.platform==null?null:$scope.platform.id,"userId":userId==null?null:userId};
    		roleService.queryAllRoleByUser(connection,function(result){
    			if(result.code == 0){//获取数据成功
    				$scope.role_list = result.data;
    			}
    		})
    	}
    	
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
     	
     	$scope.init = function(){
    		var setting = {check: {enable: false},data: {simpleData: {enable: true}}};
    		 //获取选中radio的值
            var userId = $('input[name="user"]:checked').val();
    		var connection={"platformId":$scope.platform==null?null:$scope.platform.id,"userId":userId==null?null:userId};
        	functionService.queryUserFunction(connection,function(result){
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
     	
     	$scope.checkUser= function(){
    		$scope.queryRole();
    		zTree.destroy();
    		$scope.init();
    	}
     	
     	//查询方法
    	$scope.query = function(currentPage,itemsPerPage){
			$scope.querycontent.currentPage =(currentPage||1) ;
			$scope.querycontent.itemsPerPage = (itemsPerPage||10);
    		$('input[name="user"]:checked').attr("checked",false); 
    		$scope.querycontent.platformId=($scope.platform==null?null:$scope.platform.id);
    		userService.queryAllUser($scope.querycontent,function(result){
    				if(result.code == 0){//获取数据成功
    					$scope.user_list = result.data;
    					$scope.paginationConf.totalItems = result.total;
    					if(result.data.length > 0){
    						$scope.hasResult = false;
    					}else{
    						$scope.hasResult = true;
    					}
    				}
    			})
    		$scope.queryRole();
    		$scope.init();
    	}
    	
    	$scope.query(1,10);
    	
    	$scope.changeRole= function(id){
    		var userId = $('input[name="user"]:checked').val();
    		if(userId==null||userId==""){
    			$.MsgBox.Alert("提示", "请选择用户！");
    			return;
    		}
    		var checkStatus = $('#'+id).prop('checked');
    		var connection={"roleId":id,"userId":userId};
    		if(checkStatus){
    			userRoleService.addUserRole(connection,function(result){
    				if(result.code == 0){//获取数据成功
    					$.MsgBox.Alert("提示", "增加角色成功！");
    					$scope.queryRole();
    		    		$scope.init();
    				}else{
    					$.MsgBox.Alert("提示", "增加角色失败！");
    				}
    			})
    		}else{
    			userRoleService.deleteUserRole(connection,function(result){
    				if(result.code == 0){//获取数据成功
    					$.MsgBox.Alert("提示", "删除角色成功！");
    					$scope.queryRole();
    		    		$scope.init();
    				}else{
    					$.MsgBox.Alert("提示", "删除角色失败！");
    				}
    			})
    		}
    	}
    	
    	//新增时，情况之前数据
    	$scope.cleanData = function(){
    		$scope.user = {};
    	}
    	
    	$scope.saveCheck = function() {
    		if (typeof $scope.user == 'undefined') {
    			$scope.user = {};
    		}
    		if (typeof $scope.user.username == 'undefined' || $scope.user.username == '') {
    			$.MsgBox.Alert("提示", "用户名不能为空！");
    			return false;
    		} 
    		if (typeof $scope.user.password == 'undefined' || $scope.user.password == '') {
    			$.MsgBox.Alert("提示", "密码不能为空！");
    			return false;
    		} 
    		if (typeof $scope.user.password1 == 'undefined' || $scope.user.password1 == '') {
    			$.MsgBox.Alert("提示", "确认密码不能为空！");
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.saveUser = function(){
    		if (!$scope.saveCheck()) {
    			return;
    		}
    		userService.saveManageUser($scope.user,function(result){
    			if(result.code == 0){//获取数据成功
    				$.MsgBox.Alert("提示", "保存成功！");
    				$('#myModal').modal('hide');
    				$scope.query();
    			}else{
    				$.MsgBox.Alert("提示", result.data);
    			}
    		})
    	}
    	
}]);