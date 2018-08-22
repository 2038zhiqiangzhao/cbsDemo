 app.controller("orgUserController", ["$scope", "$http", "$stateParams", "userUtil", "orgUserService",function($scope, $http, $stateParams, userUtil, orgUserService) {
	
 	$scope.orgUserList={};//组织内人员查询
 	$scope.addOrgUser={};//可加入组织人员查询
	 
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
         //var param={"id":nodes[0].orgId};
         //$scope.orgUserList.orgName=treeNode.name; //不生效是咋回事
         $('.zzjgmc').val(treeNode.name);
         $scope.orgUserList.orgId=nodes[0].orgId;
         $scope.addOrgUser.orgId=nodes[0].orgId;
         
         $scope.queryOrgUser();
		 $scope.queryAddUser();
     };
 	
 	$scope.init = function(){
 		var setting = {check: {enable: false},data: {simpleData: {enable: true}},callback: {onClick:onClick}};
 		var param={};
     	orgUserService.queryCurrentOrg(param,function(result){
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
 	
 	//查询组织内人员
 	$scope.queryOrgUser=function(){//查询组织内的用户
 		if( null==$scope.orgUserList.orgId||""== $scope.orgUserList.orgId){
 			$.MsgBox.Alert("提示", "请在左侧组织树选择查询的组织！");
 			return ;
 		}
 
 		$scope.queryOrgUserDisabled = true;
 		orgUserService.queryOrgUser($scope.orgUserList,function(result){
     		$scope.queryOrgUserDisabled = false;
     			noLogin(result);
				if(result.code == 0){//获取数据成功
					$scope.user_list = result.data;
					$scope.paginationConf3.totalItems = result.total;
					if(result.data.length > 0){
						$scope.hasResult = false;
					}else{
						$scope.hasResult = true;
					}
				}else{
					$.MsgBox.Alert("提示", result.data);
				}
     	});
 	};
 	
 	$scope.queryAddUser=function(){//查询可添加到组织内的用户
 		if( null==$scope.orgUserList.orgId||""== $scope.orgUserList.orgId){
 			$.MsgBox.Alert("提示", "请在左侧组织树选择查询的组织！");
 			return ;
 		}
 		$scope.queryAddUserDisabled = true;
 		orgUserService.queryAddUser($scope.addOrgUser,function(result){
 			noLogin(result);
     		$scope.queryAddUserDisabled = false;
				if(result.code == 0){//获取数据成功
					$scope.user_list2 = result.data;
					$scope.paginationConf2.totalItems = result.total;
					if(result.data.length > 0){
						$scope.hasResult2 = false;
					}else{
						$scope.hasResult2 = true;
					}
				}else{
					$.MsgBox.Alert("提示", result.data);
				}
     	});
 	};
 	
 	
	$scope.deleteOrgUser=function(){//删除组织内的用户
		var userList=$('input[name="userList"]:checked').val();
		if(null==userList||""==userList){
			$.MsgBox.Alert("提示", "请选择需要进行删除的人员!");
		}
		var delArr=new Array();
		$('input[name="userList"]:checked').each(function(){
			var val=$(this).val();
			delArr.push(val)
		});
		var param={
			"userIds":delArr,
			"orgId":$scope.orgUserList.orgId
		};
		$scope.deleteOrgUserDisabled = true;
		orgUserService.deleteOrgUser(param,function(result){
			noLogin(result);
			$scope.deleteOrgUserDisabled = false;
			if(result.code == 0){//获取数据成功
				$.MsgBox.Alert("提示", "删除成功!");
				$scope.queryOrgUser();
				$scope.queryAddUser();
			}else{
				$.MsgBox.Alert("提示", result.data);
			}
		});
		
 	};
 	
 	
	$scope.putOrgUser=function(){//增加组织用户
		var userList2=$('input[name="userList2"]:checked').val();
		if(null==userList2||""==userList2){
			$.MsgBox.Alert("提示", "请选择需要加入组织的人员!");
		}
		var addArr=new Array();
		$('input[name="userList2"]:checked').each(function(){
			var val=$(this).val();
			addArr.push(val);
		});
		var param={
			"userIds":addArr,
			"orgId":$scope.orgUserList.orgId
		};
		$scope.putOrgUserDisabled = true;
		orgUserService.putOrgUser(param,function(result){
			noLogin(result);
			$scope.putOrgUserDisabled = false;
			if(result.code == 0){//获取数据成功
				$.MsgBox.Alert("提示", "新增成功!");
				$scope.queryOrgUser();
				$scope.queryAddUser();
			}else{
				$.MsgBox.Alert("提示", result.data);
			}
		});
 	};
 	//分页配置
	$scope.paginationConf3 = {
		currentPage: 1,
		totalItems: 0,
		itemsPerPage: 10,
		pagesLength: 10,
		perPageOptions: [10,20],
		rememberPerPage: 'perPageItems',
		onChange: function () {
			$scope.orgUserList.currentPage = $scope.paginationConf3.currentPage;
			$scope.orgUserList.itemsPerPage = $scope.paginationConf3.itemsPerPage;
			$scope.queryOrgUser();
		}
	};
	$scope.orgUserList.currentPage = $scope.paginationConf3.currentPage;
	$scope.orgUserList.itemsPerPage = $scope.paginationConf3.itemsPerPage;
 	
	//分页配置
	$scope.paginationConf2 = {
		currentPage: 1,
		totalItems: 0,
		itemsPerPage: 10,
		pagesLength: 10,
		perPageOptions: [10,20],
		rememberPerPage: 'perPageItems',
		onChange: function () {
			$scope.addOrgUser.currentPage = $scope.paginationConf2.currentPage;
			$scope.addOrgUser.itemsPerPage = $scope.paginationConf2.itemsPerPage;
			$scope.queryAddUser();
		}
	};
	$scope.addOrgUser.currentPage = $scope.paginationConf2.currentPage;
	$scope.addOrgUser.itemsPerPage = $scope.paginationConf2.itemsPerPage;
	
    }]);