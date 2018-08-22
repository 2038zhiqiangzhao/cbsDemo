 app.controller("roleListController", ["$scope", "$http", "$stateParams", "$timeout","userUtil", "roleListService","platformService","functionService","roleFunctionService",function($scope, $http, $stateParams, $timeout,userUtil, roleListService,platformService,functionService,roleFunctionService) {
	 	$scope.querycontent = {};
		$scope.role = {};
		$scope.deleteRole = {};
		//新增角色
		$scope.addRole = {};
		//修改角色
		$scope.editRole={};
		
		//分页参数
		$scope.querycontent.currentPage = 1;
		$scope.querycontent.itemsPerPage = 10;

		var code;
	 	var zTree = {};
	 	var zTree1 = {};
	 		
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
	 	
		$scope.setCheck1 = function() {
			zTree1 = $.fn.zTree.getZTreeObj("treeDemo1"),
 			py = "p",
 			sy = "s",
 			pn = "p",
 			sn = "s",
 			type = { "Y":py + sy, "N":pn + sn};
			zTree1.setting.check.chkboxType = type;
 			$scope.showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
		}
	 	
	 	$scope.showCode = function(str) {
	 			if (!code) code = $("#code");
	 			code.empty();
	 			code.append("<li>"+str+"</li>");
	 	}
	 	
		$scope.platform = "";
		$scope.addPlatform = "";
		$scope.role = "";
		var connection = {};
		$scope.init = function(){
			var setting = {check: {enable: true},data: {simpleData: {enable: true}}};
			connection={"platformId":$scope.addPlatform.id,"roleId":null};
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
		
		$scope.init1 = function(){
			var setting = {check: {enable: true},data: {simpleData: {enable: true}}};
			connection={"platformId":$scope.editRole.platformId,"roleId":($scope.editRole==null?null:$scope.editRole.id)};
	    	functionService.queryRoleFunction(connection,function(result){
					if(result.code == 0){//获取数据成功
						var zNodes = result.data;
						$.fn.zTree.init($("#treeDemo1"), setting, zNodes);
						$scope.setCheck1();
						$("#py").bind("change", true);
						$("#sy").bind("change", true);
						$("#pn").bind("change", true);
						$("#sn").bind("change", true);
					}
			})
		}
		
		//分页配置
		$scope.paginationConf = {
			currentPage: 1,
			totalItems: 1,
			itemsPerPage: 10,
			pagesLength: 15,
			perPageOptions: [10, 20, 30, 40, 50],
			rememberPerPage: 'perPageItems',
			onChange: function () {
				$scope.querycontent.currentPage = $scope.paginationConf.currentPage;
				$scope.querycontent.itemsPerPage = $scope.paginationConf.itemsPerPage;
				$scope.query();
			}
		};
		
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
		
		//查询方法
		$scope.query = function(){
			$scope.querycontent.platformId=($scope.platform==null?null:$scope.platform.id);
    		roleListService.queryAllRolePage($scope.querycontent,function(result){
					if(result.code == 0){//获取数据成功
						$scope.role_list = result.data;
						$scope.paginationConf.totalItems = result.total;
						if(result.data.length > 0){
							$scope.hasResult = false;
						}else{
							$scope.hasResult = true;
						}
					}
				})
		}
		$scope.query();
		
	
		
		$scope.showPlatform= function(id){
			var data= $scope.platforms;
			for(var o in data){
				if(data[o].id==id){
					return data[o].platformName;
				}
		    } 
		}
		
		$scope.cleanData = function(){
			$scope.role = {};
			$scope.addPlatform = "";
			$scope.init();
		}
		
		$scope.changePlatform= function(){
			$scope.init();
		}
		

		//删除角色
		$scope.deleteRole= function(id){
			if(confirm("是否删除?")){
    		var connection={"id":id};
    		roleListService.deleteRole(connection,function(result){
				if(result.code == 0){//获取数据成功
					$.MsgBox.Alert("提示", "删除成功！");
					$scope.query();
		    	
				}else{
					$.MsgBox.Alert("提示", "删除角色失败！");
				}
			})}else{
				$scope.query();
			}
    	}
		
		
		//根据id查询角色
		$scope.roleById= function(id){
			var connection={"id":id};
    		roleListService.roleById(connection,function(result){
				if(result.code == 0){//获取数据成功
					$scope.editRole = result.data;
				}
			})
		}
		
		
		
		//修改角色
    	$scope.updateRole = function(){
    		if($scope.editRole.name==null || $scope.editRole.name==""){
				$.MsgBox.Alert("提示", "角色名称不能为空！");
				return;
			}
			if($scope.editRole.code==null || $scope.editRole.code==""){
				$.MsgBox.Alert("提示", "角色代码不能为空！");
				return;
			}
        	if(!/^[a-zA-Z0-9_]*$/.test($scope.editRole.code)){
        		$.MsgBox.Alert("提示", "角色代码必须为数字、字母、下划线！");
    			return ;
    		}
    		$scope.editRole.platformId=$scope.platform.id;
    		roleListService.updateRole($scope.editRole,function(result){
    			if(result.code == 0){//获取数据成功
    				$.MsgBox.Alert("提示", "修改成功！");
    				$('#myModalUpdate').modal('hide');
    				$scope.query();
    			}else{
    				$.MsgBox.Alert("提示", "修改失败！");
    			}
        	})
		}
    	
    	
    	//保存角色
		$scope.saveRole = function(){
					
			if($scope.addRole.name==null || $scope.addRole.name==""){
				$.MsgBox.Alert("提示", "角色名称不能为空！");
				return;
			}
			if($scope.addRole.code==null || $scope.addRole.code==""){
				$.MsgBox.Alert("提示", "角色代码不能为空！");
				return;
			}
        	if(!/^[a-zA-Z0-9_]*$/.test($scope.addRole.code)){
        		$.MsgBox.Alert("提示", "角色代码必须为数字、字母、下划线！");
    			return ;
    		}
        	
			var nodes = zTree.getCheckedNodes();
			var functionIds = [];
			for(var i=0 ;i<nodes.length;i++){
				functionIds.push(nodes[i].functionId);
			}
			if($scope.addPlatform==null||$scope.addPlatform.id==null){
				$.MsgBox.Alert("提示", "请选择平台！");
				return;
			}
			
			$scope.addRole.functionIds=functionIds;
			$scope.addRole.platformId=$scope.addPlatform.id;
			roleListService.addRole($scope.addRole,function(result){
    			if(result.code == 0){//获取数据成功
    				$.MsgBox.Alert("提示", "保存成功！");
    				$('#myModal').modal('hide');
    				$scope.query();
    			}else{
    				$.MsgBox.Alert("提示", "保存失败！");
    			}
        	})
		}
		
		
		//展示更新角色
		$scope.showRoleFunction= function(id){
			var connection={"id":id};
    		roleListService.roleById(connection,function(result){
				if(result.code == 0){//获取数据成功
					$scope.editRole=result.data;
					$scope.init1();
				}
			})
		}
		
		//更新角色
		$scope.updateRoleFunction= function(){
			var nodes = zTree1.getCheckedNodes();
			var functionIds = [];
			for(var i=0 ;i<nodes.length;i++){
				functionIds.push(nodes[i].functionId);
			}
			if($scope.editRole.id==null||$scope.editRole.id==""){
				$.MsgBox.Alert("提示", "角色不能为空！");
				return;
			}
			if($scope.editRole.platformId==null||$scope.role.platformId==""){
				$.MsgBox.Alert("提示", "平台不能为空！");
				return;
			}
			var connection={"platformId":$scope.editRole.platformId,"roleId":$scope.editRole.id,"functionIds":functionIds};
			roleFunctionService.saveRoleFunction(connection,function(result){
				if(result.code == 0){//获取数据成功
					$.MsgBox.Alert("提示", "保存成功！");
					$('#myRoleUpdate').modal('hide');
				}
			})
		}
		
 	
 }]);


