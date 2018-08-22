app.controller("yuangongController", [
		"$scope",
		"$http",
		"$stateParams",
		"userUtil",
		"departmentService",
		"orgService",
		"$document",
		function($scope, $http, $stateParams, userUtil, departmentService,orgService,$document){
			
			var  QUERY_CURRENT_ORG='org/getCurrentOrg';
			
			$scope.orgList = {};
			$scope.positionList = {};			
			$scope.addYonghu={};
			
			$scope.queryRoleCondition={};
				
			$scope.selectRoleList=[];//已选角色列表
			$scope.roleList=[];
			
			
			$scope.addUserRoleList="";//角色文本
			
			var code2;
			var zTree2 = {};
		    $scope.setCheck2 = function() {
    			zTree2 = $.fn.zTree.getZTreeObj("treeDemo2"),
    			py = "p",
    			sy = "s",
    			pn = "p",
    			sn = "s",
    			type = { "Y":py + sy, "N":pn + sn};
    			zTree2.setting.check.chkboxType = type;
    			$scope.showCode2('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
	    	}
		
	    	$scope.showCode2 = function(str) {
	    			if (!code2) code2 = $("#code");
	    			code2.empty();
	    			code2.append("<li>"+str+"</li>");
	    	}
	    	
	    	$scope.onClick2= function (event, treeId, treeNode,clickFlag) {
	            var zTree2 = $.fn.zTree.getZTreeObj("treeDemo2");
	            var nodes = zTree2.getSelectedNodes();	            
	            $scope.$apply(function(){
	            	$scope.orgNameChange=nodes[0].name;
	            	$scope.addYonghu.orgId=nodes[0].orgId;
	            });
	            
	        };
    	
			
			$scope.init = function() {
				
	    		var setting2 = {check: {enable: false},data: {simpleData: {enable: true}},callback: {onClick:$scope.onClick2}};
	    		var param={};	    		
	        	        	
	        	userUtil.postRequest(QUERY_CURRENT_ORG, param, function(result){
	        		if(result.code == 0){//获取数据成功
    					var zNodes = result.data;	    					
    					$.fn.zTree.init($("#treeDemo2"), setting2, zNodes);
    					$scope.setCheck2();
    					$("#py").bind("change", true);
    					$("#sy").bind("change", true);
    					$("#pn").bind("change", true);
    					$("#sn").bind("change", true);
    				}
	    		});
		
				departmentService.getAllPostitionInfo(param, function(result) {
					if (result.code == 0) {//获取数据成功
						$scope.positionList = result.data;
					}else{
						$.MsgBox.Alert("提示", "获取岗位列表失败");
					}
				});
				
				//查询所有平台
	            $http.post('platform/queryPlatformList.do', param).success(function (result) {
					if(result.code==0){
						$scope.platFormList=result.data;            		
					}else{
						$.MsgBox.Alert("提示", "获取平台列表失败");
					}
	            });

			}

			$scope.init();
			
			//展示菜单
			$scope.showMenu=function(){				
				var cityObj = $("#citySel");
				if($("#menuContent").is(":hidden")){
					$("#menuContent").css({left:"0px",top: cityObj.outerHeight() + "px","z-index":'999'}).slideDown("fast");
				}else{
					$("#menuContent").hide();
				}	
			}
			
			$document.bind("click", function(event) {
		        //$scope.showFlag=false;
				// debugger
		    	if(!$("#menuContent").is(":hidden")){
		    		$("#menuContent").hide();
				}
		        $scope.$apply();             
		     }); 
			
			
			$scope.addOneUser=function(){//保存
				
				if($scope.addYonghu.username==null||$scope.addYonghu.username==''){
					$scope.username_error_tip=true;
					$scope.username_error="用户名必填";
					return;
				}else{
					var reg = "[0-9a-zA-Z\u4e00-\u9fa5]{6,20}";     
			        var r = $scope.addYonghu.username.match(reg);
			        if(r==null){
			        	$scope.username_error_tip=true;
						$scope.username_error="6-20个字符";
						return;
			        }
				}
				$scope.username_error_tip=false;
				
				if($scope.addYonghu.password==null||$scope.addYonghu.password==''){
					$scope.password_error_tip=true;
					$scope.password_error="密码必填";
					return;
				}else{
					var reg2="^[A-Za-z0-9\\.\\,\\`\\~\\!\\@\\#\\$\\%\\\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\{\\]\\}\\\\|\\;\\:\\‘\\’\\“\\”\\<\\>\\/?]{6,18}$";
					var r2=$scope.addYonghu.password.match(reg2);
					if(r2==null){
						$scope.password_error_tip=true;
						$scope.password_error="密码需为6位-18位，包含字母+数字";
						return;
					}
				}
				$scope.password_error_tip=false;
				
				if($scope.addYonghu.identityCardName==null||$scope.addYonghu.identityCardName==''){
					$scope.idCardName_error_tip=true;
					$scope.idCardName_error="姓名必填";
					return;
				}else{
					var reg = "[0-9a-zA-Z\u4e00-\u9fa5]{6,20}";     
			        var r = $scope.addYonghu.username.match(reg);
			        if(r==null){
			        	$scope.idCardName_error_tip=true;
						$scope.idCardName_error="2-20个字符";
						return;
			        }
				}
				
				$scope.idCardName_error_tip=false;
				
				
				if($scope.addYonghu.mobile==null||$scope.addYonghu.mobile==''){
					$scope.mobile_error_tip=true;
					$scope.mobile_error="手机号必填";
					return;
				}else{
					var reg3=/^1[2|3|4|5|6|7|8]\d{9}$/;
					var r3=$scope.addYonghu.mobile.match(reg3);
					if(r3==null){
						$scope.mobile_error_tip=true;
						$scope.mobile_error="手机号格式不正确";
						return;
					}
				}
				
				$scope.mobile_error_tip=false;
				
				if($scope.addYonghu.email!=null && $scope.addYonghu.email!=''){
					var reg2=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
					var r2=$scope.addYonghu.email.match(reg2);
					if(r2==null){
						$scope.email_error_tip=true;
						$scope.email_error="邮箱格式不正确";
						return;
					}
				}
				
//				function checkEmail ( callback ){
//					departmentService.queryUserByEmail($scope.queryContectByEmail, function(result) {
//						if (result.code == 0) {//获取数据成功
//							if(result!=null && result.total>0){
//								$scope.email_error_tip=true;
//								$scope.email_error="该邮箱已存在!";
//								return callback($scope.email_error_tip);
//							}
//							return callback(false);
//						}else{
//							$.MsgBox.Alert("提示", "获取用户信息失败(验证邮箱是否重复!)");
//							return callback(true);
//						}
//					});
//				}
//				//校验邮箱是否重复
//				$scope.queryContectByEmail={};
//				$scope.queryContectByEmail.email=$scope.addYonghu.email;
				
				$scope.email_error_tip=false;
				
//				var i=0;
//				(function(i){
//					
//				})(i);
//				checkEmail(function(result ){
//					$scope.email_error_tip = result;
//				})
//				
//				if($scope.email_error_tip){
//					return ;
//				}
				
				
			
				
				if($scope.addYonghu.orgId==null||$scope.addYonghu.orgId==''){
					$scope.org_error_tip=true;
					$scope.org_error="部门必填";
					return;
				}
				$scope.org_error_tip=false;
				
				$scope.addYonghuDisabled=true;
				// debugger
				departmentService.addYonghuSave($scope.addYonghu,function(result){
					$scope.addYonghuDisabled = false;
					if (result.code == 0) {//获取数据成功
						if(result.data==0){
							$.MsgBox.Alert("提示", "员工注册成功");
							return;						
						}else if(result.data==-1){
							$scope.mobile_error_tip=true;
							$scope.mobile_error="手机号重复";
							return;
						}else if(result.data==-2){
							$scope.username_error_tip=true;
							$scope.username_error="用户名重复";
							return;
						}else if(result.data==-3){
							$scope.idCardName_error_tip=true;
							$scope.idCardName_error="姓名重复";
							return;
						}else if(result.data==-5){
							$scope.email_error_tip=true;
							$scope.email_error	="邮箱重复";
							return;
						}
						
					} 
				});
			}
			
			
			
						
			$scope.cancel=function(){//取消
				userUtil.redirectTo("#/org-user" , true);
			}
			
			$scope.queryRoleInfo=function(){//查询角色信息
				 $http.post('role/queryListRoleData.do',$scope.queryRoleCondition).success(function (result) {
						if(result.code==0){
							$scope.roleList=result.data;            		
						}else{
							$.MsgBox.Alert("提示", "获取平台列表失败");
						}
		         });
			}
			$scope.addRoleForUser=function(e){//增加用户角色
				$scope.selectRoleList.push(e);				
				var index = $scope.roleList.indexOf(e);
				if (index > -1) { 
					$scope.roleList.splice(index, 1);
				}
			}
			
			$scope.delSite = function($index){
				$scope.roleList.push( $scope.selectRoleList[$index]);
		        $scope.selectRoleList.splice($index,1);		        
		    }
			
			$scope.userRoleBack=function(){
				$scope.selectRoleList=[];
				$scope.addYonghu.roleIds=$scope.selectRoleList;
				$scope.addUserRoleList="";
				$('#formT').modal('hide');
			}
			$scope.savaUserRole=function(){//保存用户角色
				var listRole=[];
				for(var i in  $scope.selectRoleList){
					if(listRole.length>0){
						var flag=1;
						for(var j in listRole){
							if($scope.selectRoleList[i].id==listRole[j]){
								flag=0;
								break;
							}
						}
						if(flag==1){
							listRole.push($scope.selectRoleList[i].id);
							$scope.addUserRoleList+=(","+$scope.selectRoleList[i].name);
						}
					}else{
						listRole.push($scope.selectRoleList[i].id);
						$scope.addUserRoleList+=(","+$scope.selectRoleList[i].name);
					}
				}
				$scope.addYonghu.roleIds=listRole;
				if($scope.addUserRoleList.length>0){
					$scope.addUserRoleList=$scope.addUserRoleList.substring(1);
				}							
				$('#formT').modal('hide');
			}
			
		}]);