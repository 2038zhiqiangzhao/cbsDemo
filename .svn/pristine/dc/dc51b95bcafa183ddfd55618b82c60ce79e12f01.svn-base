 app.controller("roleManageController", ["$scope", "$http", "$stateParams", "userUtil","roleListService","platformService","functionService","roleFunctionService",function($scope, $http, $stateParams, userUtil, roleListService,platformService,functionService,roleFunctionService) {
      
		$scope.querycontent = {};
		$scope.platform = "";
		
		//修改角色
		$scope.editRole={};
		
		$scope.close = function (t) {
			console.log(t);
		}
		$scope.options1={
		    formatDate:'d.m.Y',
		    timepickerScrollbar:false,
		    step:1,
		    onClose:$scope.close,
		    timepicker:false
		}

		$scope.options2={
		    format:'Y-m-d',
		    lang:'zh',
		    step:1,
		    timepicker:false,
		    timepickerScrollbar:false
		}

		$scope.options3={
		    format:'Y-m-d',
		    lang:'zh',
		    step:1,
		    timepicker:false,
		    timepickerScrollbar:false
		}
		
		var code;
	 	var zTree = {};
	 	var zTree1 = {};
	 		
	 	$scope.setCheck = function(setting, zNodes) {
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			py = "p",
			sy = "s",
			pn = "p",
			sn = "s",
			type = { "Y":py + sy, "N":pn + sn};
			zTree.setting.check.chkboxType = type;
			$scope.showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
			$("#py").bind("change", true);
			$("#sy").bind("change", true);
			$("#pn").bind("change", true);
			$("#sn").bind("change", true);
			zTree.expandAll(false);
	 	}
	 	
		$scope.setCheck1 = function(setting, zNodes) {
			$.fn.zTree.init($("#treeDemo1"), setting, zNodes);
			zTree1 = $.fn.zTree.getZTreeObj("treeDemo1"),
 			py = "p",
 			sy = "s",
 			pn = "p",
 			sn = "s",
 			type = { "Y":py + sy, "N":pn + sn};
			zTree1.setting.check.chkboxType = type;
 			$scope.showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
			$("#py").bind("change", true);
			$("#sy").bind("change", true);
			$("#pn").bind("change", true);
			$("#sn").bind("change", true);
			zTree1.expandAll(false);
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
						$scope.setCheck(setting, zNodes);
					}
			})
		}
		
		$scope.init1 = function(){
			var setting = {check: {enable: true},data: {simpleData: {enable: true}}};
			connection={"platformId":$scope.editRole.platformId,"roleId":($scope.editRole==null?null:$scope.editRole.id)};
	    	functionService.queryRoleFunction(connection,function(result){
					if(result.code == 0){//获取数据成功
						var zNodes = result.data;
						$scope.setCheck1(setting, zNodes);
					}
			})
		}
		
		
		//分页参数
		$scope.currentPage = 1;
		$scope.itemsPerPage = 10;
		$scope.pageSizes = [ 10, 20, 50, 100 ];
		$scope.editPage = true;
		
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
			$scope.querycontent.currentPage=$scope.currentPage;
			$scope.querycontent.itemsPerPage=$scope.itemsPerPage;
    		roleListService.queryAllRolePage($scope.querycontent,function(result){
					if(result.code == 0){//获取数据成功
						$scope.role_list = result.data;
						$scope.totalItems = result.total;
						$scope.pageCount = Math.ceil($scope.totalItems/$scope.itemsPerPage);
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
		
		//删除角色
		$scope.deleteRole= function(id){
			$.MsgBox.Confirm("提示","是否删除?",function(){
				var connection={"id":id};
	    		roleListService.deleteRole(connection,function(result){
					if(result.code == 0){//获取数据成功
						$.MsgBox.Alert("提示", "删除成功！");
						$scope.query();
			    	
					}else{
						$.MsgBox.Alert("提示", "删除角色失败！");
					}
				})
			})
    	}
		
		$scope.cleanData = function(){
			$scope.addRole = {};
			$scope.addPlatform = "";
			$scope.init();
			$scope.addRole.desc='';//初始化新增角色描述的值,不然长度判断报错
			$scope.addRole.name='';
		}
		
		$scope.changePlatform= function(){
			$scope.init();
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
			/*if($scope.editRole.code==null || $scope.editRole.code==""){
				$.MsgBox.Alert("提示", "角色代码不能为空！");
				return;
			}*/
    		if($scope.editRole.code!=null && $scope.editRole.code!=""){
    			if(!/^[a-zA-Z0-9_]*$/.test($scope.editRole.code)){
            		$.MsgBox.Alert("提示", "角色代码必须为数字、字母、下划线！");
        			return ;
        		}
    		}
        	
        	if ($scope.editRole.name.length > 20) {
        		$.MsgBox.Alert("提示", "角色名不能超过200字！");
        		return;
        	}
        	if ($scope.editRole.desc.length > 200) {
    			$.MsgBox.Alert("提示", "角色描述不能超过200字！");
    			return;
    		}
    		$scope.editRole.platformId=$scope.platform.id;
    		roleListService.updateRole($scope.editRole,function(result){
    			if(result.code == 0){//获取数据成功
    				$.MsgBox.Alert("提示", "修改成功！");
    				$('#editForm').modal('hide');
    				$scope.cleanMessage();
    				$scope.query();
    				$('#editForm').modal('hide');
    			}else{
    				$.MsgBox.Alert("提示", "修改失败！");
    			}
        	})
		}
    	
    	//取消时清除错误信息
    	$scope.cleanMessage = function(){
    		$scope.hasRole = false;
			$scope.saveCheckDisabled = false;
			$scope.hasRoleE = false;
			$scope.saveCheckDisabledE = false;
    	}
    	
    	//新增时检查角色名是否存在(新增)
    	$scope.checkRoleByName = function(){
    		$scope.queryContentVO2 = {};
    		$scope.queryContentVO2.name=$scope.addRole.name;
    		
    		roleListService.queryRoleByName($scope.queryContentVO2,function(result){
    			if(result.data.length > 0){
    				$scope.hasRole = true;
    				$scope.saveCheckDisabled = true;
    			}else{
    				$scope.hasRole = false;
    				$scope.saveCheckDisabled = false;
    			}
    		});
    		
    	}
    	//检查角色code是否存在(新增)
    	$scope.checkRoleByCode = function(){
    		$scope.queryContentVO3 = {};
    		$scope.queryContentVO3.code=$scope.addRole.code;
    		
    		roleListService.queryRoleByCode($scope.queryContentVO3,function(result){
    			if(result.data.length > 0){
    				$scope.hasRole = true;
    				$scope.saveCheckDisabled = true;
    			}else{
    				$scope.hasRole = false;
    				$scope.saveCheckDisabled = false;
    			}
    		});
    		
    	}
    	
    	//检查角色名是否存在(编辑)
    	$scope.checkRoleByNameE = function(){
    		$scope.queryContentVOE = {};
    		$scope.queryContentVOE.name=$scope.editRole.name;
    		
    		roleListService.queryRoleByName($scope.queryContentVOE,function(result){
    			if(result.data.length > 0){
    				$scope.hasRoleE = true;
    				$scope.saveCheckDisabledE = true;
    			}else{
    				$scope.hasRoleE = false;
    				$scope.saveCheckDisabledE = false;
    			}
    		});
    		
    	}
    	//检查角色code是否存在(编辑)
    	$scope.checkRoleByCodeE = function(){
    		$scope.queryContentVOE = {};
    		$scope.queryContentVOE.code=$scope.editRole.code;
    		
    		roleListService.queryRoleByCode($scope.queryContentVOE,function(result){
    			if(result.data.length > 0){
    				$scope.hasRoleE = true;
    				$scope.saveCheckDisabledE = true;
    			}else{
    				$scope.hasRoleE = false;
    				$scope.saveCheckDisabledE = false;
    			}
    		});
    		
    	}
    	
    	//保存角色
		$scope.saveRole = function(){
					
			if($scope.addRole.name==null || $scope.addRole.name==""){
				$.MsgBox.Alert("提示", "角色名称不能为空！");
				return;
			}
			/*if($scope.addRole.code==null || $scope.addRole.code==""){
				$.MsgBox.Alert("提示", "角色代码不能为空！");
				return;
			}*/
        	if(!/^[a-zA-Z0-9_]*$/.test($scope.addRole.code)){
        		$.MsgBox.Alert("提示", "角色代码必须为数字、字母、下划线！");
    			return ;
    		}
        	if ($scope.addRole.name.length > 20) {
        		$.MsgBox.Alert("提示", "角色名称不能超过20字！");
        		return ;
        	}
        	if ($scope.addRole.desc.length > 200) {
    			$.MsgBox.Alert("提示", "角色描述不能超过200字！");
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
    				$('#addForm').modal('hide');
    				$scope.cleanMessage();
    				$scope.query();
    				$('#addForm').modal('hide');
    			}else{
    				$.MsgBox.Alert("提示", "保存失败！");
    			}
        	})
		}
		
		
		//展示更新角色
		$scope.showRoleFunction= function(id){
			$("#treeDemo1").empty();
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
					$('#editFunctionForm').modal('hide');
				}
			})
		}

 }]);