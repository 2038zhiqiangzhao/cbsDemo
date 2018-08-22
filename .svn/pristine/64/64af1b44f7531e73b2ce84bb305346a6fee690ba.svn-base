 app.controller("orgController", ["$scope", "$http", "$stateParams", "userUtil", "orgService",function($scope, $http, $stateParams, userUtil, orgService) {
      
	 
	 	
	 	$scope.addOrg={};//新增组织
	 	$scope.editOrg={};//修改组织
	 	$scope.deleteOrg={};//删除组织
	 	
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
    	
    	function onClick(event, treeId, treeNode,clickFlag) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = zTree.getSelectedNodes();
            var param={"id":nodes[0].orgId};
            $http.post('org/getOrgById.do', param)
	            .success(function (result) {
	            	 noLogin(result);
	            	 $scope.addOrg={};	
	            	 $scope.addOrg.code=result.data.code+"01";
	            	 $scope.addOrg.parentCode=result.data.code;
            		 $scope.addOrg.parentName=result.data.orgName;
            		 
            		 $scope.editOrg={};
            		 
            		 $scope.editOrg.id=result.data.id;
            		 $scope.editOrg.code=result.data.code;
            		 $scope.editOrg.parentName=treeNode.getParentNode().name;
            		 if(true==treeNode.isParent){//有子节点不能更改组织代码
            			 $('.edit-zzdm').attr('readonly',true);
            		 }else{
            			 $('.edit-zzdm').attr('readonly',false);
            		 }
            		 $scope.editOrg.orgName=result.data.orgName;
            		 $scope.editOrg.parentCode=result.data.parentCode;
            		 //$scope.editOrg.parentName=result.data.orgName;
            		 $scope.editOrg.isVirtual=(null==result.data.isVirtual||''==result.data.isVirtual)?0:result.data.isVirtual;
            		 $scope.editOrg.remarks=result.data.remarks;
            		 
            		 
            		 $scope.deleteOrg={};
            		 $scope.deleteOrg.id=result.data.id;
	            	 $scope.deleteOrg.code=result.data.code;
            		 $scope.deleteOrg.orgName=result.data.orgName;
            		 $scope.deleteOrg.isDelete=result.data.isDelete;
            		
            		 
            })
        };
    	
    	$scope.init = function(){
    		var setting = {check: {enable: false},data: {simpleData: {enable: true}},callback: {onClick:onClick}};
    		var param={};
        	orgService.queryCurrentOrg(param,function(result){
        			noLogin(result);
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
    	
    	//保存新增
    	$scope.add_Org=function(){
    		
    		if(isEmptyObject($scope.addOrg)){
    			$.MsgBox.Alert("提示", "请在左侧组织树选择组织");
    			return ;
    		}
    		if(null==$scope.addOrg.code||""==$scope.addOrg.code||null==$scope.addOrg.orgName||""==$scope.addOrg.orgName){
    			$.MsgBox.Alert("提示", "组织代码和组织名称不能为空");
    			return ;
    		}
    		
    		if(!/^\d*$/.test($scope.addOrg.code)){
        		$.MsgBox.Alert("提示", "编码必须为数字");
    			return ;
    		}
    		$scope.addDisabled = true;
    		orgService.addOrg($scope.addOrg,function(result){
        		$scope.addDisabled = false;
				if(result.code == 0){//获取数据成功
					$.MsgBox.Alert("提示", "新增成功！");
					zTree.destroy();
		    		$scope.init();
				}else{
					$.MsgBox.Alert("提示", result.data);
				}
        	});
    	};
    	
    	//保存修改
    	$scope.edit_Org=function(){
    		
    		if(isEmptyObject($scope.editOrg)){
    			$.MsgBox.Alert("提示", "请在左侧组织树选择组织");
    			return ;
    		}
    		if(null==$scope.editOrg.code||""==$scope.editOrg.code||null==$scope.editOrg.orgName||""==$scope.editOrg.orgName){
    			$.MsgBox.Alert("提示", "组织代码和组织名称不能为空");
    			return ;
    		}
    		if(!/^\d*$/.test($scope.editOrg.code)){
        		$.MsgBox.Alert("提示", "编码必须为数字");
    			return ;
    		}
    		$scope.editDisabled = true;
    		orgService.editOrg($scope.editOrg,function(result){
        		$scope.editDisabled = false;
				if(result.code == 0){//获取数据成功
					noLogin(result);
					$.MsgBox.Alert("提示", "修改成功！");
					zTree.destroy();
		    		$scope.init();
				}else{
					$.MsgBox.Alert("提示", result.data);
				}
        	});
    		
    	};
    	
    	//保存删除
    	$scope.delete_Org=function(){
    		if(isEmptyObject($scope.deleteOrg)){
    			$.MsgBox.Alert("提示", "请在左侧组织树选择组织");
    			return ;
    		}
    		if(1==$scope.deleteOrg.isDeleted){
    			$scope.deleteDisabled = true;
        		orgService.deleteOrg($scope.deleteOrg,function(result){
        			noLogin(result);
            		$scope.deleteDisabled = false;
    				if(result.code == 0){//获取数据成功
    					$.MsgBox.Alert("提示", "删除成功！");
    					zTree.destroy();
    		    		$scope.init();
    				}else{
    					$.MsgBox.Alert("提示", result.data);
    				}
            	});
    		}
    	};
    }]);