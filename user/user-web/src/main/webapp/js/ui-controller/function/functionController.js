 app.controller("functionController", ["$scope", "$http", "$stateParams", "userUtil", "functionService","platformService",function($scope, $http, $stateParams, userUtil, functionService,platformService) {
      
        $scope.addfunction = {};
        $scope.editfunction = {};
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
            var functionParam={"id":nodes[0].functionId};
            $http.post('function/getFunctionById.do', functionParam)
	            .success(function (result) {
	            	 $scope.addfunction.code=result.data.code+"XX";
	            	 $scope.addfunction.parentCode=result.data.code;
            		 $scope.addfunction.parentName=result.data.name;
            		 $scope.addfunction.level=(result.data.level==null?1:result.data.level+1);
            		 $scope.editfunction.code=result.data.code;
            		 $scope.editfunction.name=result.data.name;
            		 $scope.editfunction.parentCode=result.data.parentCode;
            		 $scope.editfunction.level=(result.data.level==null?1:result.data.level);
            		 $scope.editfunction.type=result.data.type;
            		 $scope.editfunction.domain=result.data.domain;
            		 $scope.editfunction.path=result.data.path;
            		 $scope.editfunction.id=result.data.id;
            })
        };
    	
    	$scope.platform = "";
    	var connection = {};
    	$scope.init = function(){
    		var setting = {check: {enable: false},data: {simpleData: {enable: true}},callback: {onClick:onClick}};
    		connection={"platformId":$scope.platform.id};
        	functionService.queryFunction(connection,function(result){
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
        };
        
        $scope.addCheck = function() {
    		if (typeof $scope.addfunction == 'undefined') {
    			$scope.addfunction = {};
    		}
    		if (typeof $scope.addfunction.code == 'undefined' || $scope.addfunction.code == '') {
    			$.MsgBox.Alert("提示", "权限代码不能为空！");
    			return false;
    		} 
    		if (typeof $scope.addfunction.name == 'undefined' || $scope.addfunction.name == '') {
    			$.MsgBox.Alert("提示", "权限名称不能为空！");
    			return false;
    		} 
    		if (typeof $scope.addfunction.level == 'undefined' || $scope.addfunction.level == '') {
    			$.MsgBox.Alert("提示", "权限层级不能为空！");
    			return false;
    		}
    		if (typeof $scope.addfunction.type == 'undefined' || $scope.addfunction.type == '') {
    			$.MsgBox.Alert("提示", "权限类型不能为空！");
    			return false;
    		}
    		/*if (typeof $scope.addfunction.domain == 'undefined' || $scope.addfunction.domain == '') {
    			$.MsgBox.Alert("提示", "作用域不能为空！");
    			return false;
    		}
    		if (typeof $scope.addfunction.path == 'undefined' || $scope.addfunction.path == '') {
    			$.MsgBox.Alert("提示", "路径不能为空！");
    			return false;
    		}*/
    		return true;
    	}
        
        $scope.editCheck = function() {
    		if (typeof $scope.editfunction == 'undefined') {
    			$scope.editfunction = {};
    		}
    		if (typeof $scope.editfunction.code == 'undefined' || $scope.editfunction.code == '') {
    			$.MsgBox.Alert("提示", "权限代码不能为空！");
    			return false;
    		} 
    		if (typeof $scope.editfunction.name == 'undefined' || $scope.editfunction.name == '') {
    			$.MsgBox.Alert("提示", "权限名称不能为空！");
    			return false;
    		} 
    		if (typeof $scope.editfunction.level == 'undefined' || $scope.editfunction.level == '') {
    			$.MsgBox.Alert("提示", "权限层级不能为空！");
    			return false;
    		}
    		if (typeof $scope.editfunction.type == 'undefined' || $scope.editfunction.type == '') {
    			$.MsgBox.Alert("提示", "权限类型不能为空！");
    			return false;
    		}
    		if (typeof $scope.editfunction.parentCode == 'undefined' || $scope.editfunction.parentCode == '') {
    			$.MsgBox.Alert("提示", "上级权限代码不能为空！");
    			return false;
    		}
    		/*if (typeof $scope.editfunction.domain == 'undefined' || $scope.editfunction.domain == '') {
    			$.MsgBox.Alert("提示", "作用域不能为空！");
    			return false;
    		}
    		if (typeof $scope.editfunction.path == 'undefined' || $scope.editfunction.path == '') {
    			$.MsgBox.Alert("提示", "路径不能为空！");
    			return false;
    		}*/
    		return true;
    	}
        
        //新增权限
        $scope.addFunction = function() {
        	if (!$scope.addCheck()) {
    			return;
    		}
        	if($scope.platform==null||$scope.platform.id==null){
        		$.MsgBox.Alert("提示", "请选择平台");
    			return ;
    		}
        	if(!/^\d*$/.test($scope.addfunction.code)){
        		$.MsgBox.Alert("提示", "编码必须为数字");
    			return ;
    		}
        	$scope.addfunction.platformId=$scope.platform.id;
        	$scope.disabled = true;
        	functionService.saveFunction($scope.addfunction,function(result){
        		$scope.disabled = false;
				if(result.code == 0){//获取数据成功
					$.MsgBox.Alert("提示", "保存成功！");
					zTree.destroy();
		    		$scope.init();
				}else{
					$.MsgBox.Alert("提示", result.data);
				}
        	})
        }
        
      //修改权限
        $scope.editFunction = function() {
        	if (!$scope.editCheck()) {
    			return;
    		}
        	if(!/^\d*$/.test($scope.editfunction.code)){
        		$.MsgBox.Alert("提示", "编码必须为数字");
    			return ;
    		}
        	if(!/^\d*$/.test($scope.editfunction.parentCode)){
        		$.MsgBox.Alert("提示", "上级编码必须为数字");
    			return ;
    		}
        	$scope.editfunction.platformId=$scope.platform.id;
        	$scope.editdisabled = true;
        	functionService.updateFunction($scope.editfunction,function(result){
        		$scope.editdisabled = false;
				if(result.code == 0){//获取数据成功
					$.MsgBox.Alert("提示", "修改成功！");
					zTree.destroy();
		    		$scope.init();
				}else{
					$.MsgBox.Alert("提示", result.data);
				}
        	})
        }

    }]);