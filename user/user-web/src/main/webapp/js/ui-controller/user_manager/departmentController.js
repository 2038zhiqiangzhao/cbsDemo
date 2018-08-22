app.controller("departmentController", [
		"$scope",
		"$http",
		"$stateParams",
		"userUtil",
		"userService",
		"roleListService",
		"userRoleService",
		"departmentService",
		"orgService",
		"$document",
		function($scope, $http, $stateParams, userUtil, userService,roleListService,userRoleService,departmentService,orgService,$document) {
			
			$scope.queryCondition= {};//查询条件
			$scope.orgSaveInfo={};
			
			$scope.editUser = {};
			$scope.editOrgList = {};
			$scope.orgList = {};
			$scope.positionList = {};
			
			$scope.orgUserList={};
			
			$scope.orgChangeInfo={};//用户部门变更信息
			$scope.currentPage1 = 1;
			$scope.itemsPerPage1 = 10;
			$scope.pageSizes1 = [ 10, 20, 50, 100 ];
			$scope.editPage1 = true;

			$scope.orgNewInfo={};//新增部门的信息
			$scope.orgEditInfo={};//编辑部门的信息
			$scope.orgDelInfo={};//删除部门的信息
			$scope.delLevel=0;//删除部门level 0顶级部门
			$scope.userDoorCardMap={}; // 用户门禁卡map key：userId value：object
			
			var code;
			var code2
			var zTree = {};
			var zTree2 = {};
			
			
        	var container = document.getElementById('container');   
        	var menu = document.getElementById('menu');
        	
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
		    	
	    	$scope.onClick = function(event, treeId, treeNode,clickFlag) {
	            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	            var nodes = zTree.getSelectedNodes();
	            $scope.$apply(function(){
		            $scope.queryCondition.orgId=nodes[0].orgId;
		            
		            $scope.query();//点击查询
		            
		            //置空
		            $scope.orgNewInfo={};
		            $scope.orgEditInfo={};
		            $scope.orgDelInfo.id={};
		            
		            
		            //新增
		            $scope.newPOrg=nodes[0].name;
		            $scope.newPOrgCode=nodes[0].id;
		            $scope.orgNewInfo.parentCode=nodes[0].id;
		            
		            
		            
		            //编辑
		            if(treeNode.getParentNode()){
		            	$scope.editPOrg=treeNode.getParentNode().name;
				        $scope.editPOrgCode=treeNode.getParentNode().id;
				        $scope.orgEditInfo.parentCode=treeNode.getParentNode().id;
		            }
		            $scope.orgEditInfo.id=nodes[0].orgId;
		            $scope.orgEditInfo.code=nodes[0].id;
		            $scope.orgEditInfo.orgName=nodes[0].name;
		            
		            //删除
		            $scope.orgDelInfo.id=nodes[0].orgId;
		            $scope.orgDelInfo.code=nodes[0].id;//部门编码
		            if(treeNode.getParentNode()){
		            	$scope.orgDelInfo.parentCode=treeNode.getParentNode().id;
		            }	            
		            
	            });
	        };
	        
	        //部门树右击事件
	        $scope.zTreeOnRightClick=function(event, treeId, treeNode) {
	            //alert(treeNode ? treeNode.tId + ", " + treeNode.name : "isRoot");
	        	//debugger
	        	
	        	if(treeNode){
	        		 //置空
	                $scope.orgNewInfo={};
		            $scope.orgEditInfo={};
		            $scope.orgDelInfo.id={};
		            
		            //新增
		            $scope.newPOrg=treeNode.name;
		            $scope.newPOrgCode=treeNode.id;
		            $scope.orgNewInfo.parentCode=treeNode.id;
		            		            
		            
		            //编辑
		            if(treeNode.getParentNode()){
		            	$scope.editPOrg=treeNode.getParentNode().name;
				        $scope.editPOrgCode=treeNode.getParentNode().id;
				        $scope.orgEditInfo.parentCode=treeNode.getParentNode().id;
		            }
		            $scope.orgEditInfo.id=treeNode.orgId;
		            $scope.orgEditInfo.code=treeNode.id;
		            $scope.orgEditInfo.orgName=treeNode.name;
		            
		            //删除
		            $scope.orgDelInfo.id=treeNode.orgId;
		            $scope.orgDelInfo.code=treeNode.id;//部门编码
		            $scope.delLevel=treeNode.level;
		            if(treeNode.getParentNode()){
		            	$scope.orgDelInfo.parentCode=treeNode.getParentNode().id;
		            }	     
		            
	        		menu.style.visibility = "hidden";

		        	/*获取当前鼠标右键按下后的位置，据此定义菜单显示的位置*/
		            var rightedge = container.clientWidth-event.clientX;
		            var bottomedge = container.clientHeight-event.clientY;
		     
		            /*如果从鼠标位置到容器右边的空间小于菜单的宽度，就定位菜单的左坐标（Left）为当前鼠标位置向左一个菜单宽度*/
		            if (rightedge < menu.offsetWidth)                
		                menu.style.left = container.scrollLeft + event.clientX - menu.offsetWidth + "px";             
		            else
		            /*否则，就定位菜单的左坐标为当前鼠标位置*/
		            menu.style.left = container.scrollLeft + event.clientX + "px";
		             
		            /*如果从鼠标位置到容器下边的空间小于菜单的高度，就定位菜单的上坐标（Top）为当前鼠标位置向上一个菜单高度*/
		            if (bottomedge < menu.offsetHeight)
		                menu.style.top = container.scrollTop + event.clientY - menu.offsetHeight + "px";
		            else
		            /*否则，就定位菜单的上坐标为当前鼠标位置*/
		            menu.style.top = container.scrollTop + event.clientY + "px";
		                 
		            /*设置菜单可见*/
		            menu.style.visibility = "visible";         
	        	}
	        	     
	        };
	        
	        //查询高亮
	        $scope.getFontCss=function(treeId, treeNode) {  
	            return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};  
	        }  
	        
	        var nodeList;
	        var treeId;
	        $scope.changeColor=function(id,key,value){  
	        	// debugger
	            treeId = id;
	        	var treeObj = $.fn.zTree.getZTreeObj(treeId); 
	        	list=treeObj.getNodes();
	        	if(list){
	        		nodeList=[];
	        		//清空高亮
	        		for(var i=0;i<list.length;i++){
	        			$scope.getAllNodes(list[i]);
	        		}
	        		$scope.updateNodes(false);
	        	}
	        		             
	            if(value != ""){  
	                var treeObj = $.fn.zTree.getZTreeObj(treeId); 
	                nodeList = treeObj.getNodesByParamFuzzy(key, value); 	                
	                if(nodeList && nodeList.length>0){  
	                	$scope.updateNodes(true);  
	                }  
	            }  
	        }  
	        $scope.updateNodes=function(highlight) {  
	            var treeObj = $.fn.zTree.getZTreeObj(treeId);  
	            for( var i=0; i<nodeList.length;i++) {  
	                nodeList[i].highlight = highlight;  
	                treeObj.updateNode(nodeList[i]);  
	            }  
	        }
	        
	        $scope.getAllNodes=function(node){
	        	if(node){
	        		if(node.isParent){
	        			nodeList.push(node);
	        			var childrenNodes = node.children;
	        	        if (childrenNodes) {
	        	            for (var i = 0; i < childrenNodes.length; i++) {
	        	                nodeList.push(childrenNodes[i]);
	        	                $scope.getAllNodes(childrenNodes[i]);
	        	            }
	        	        }
	        		}else{
	        			nodeList.push(node);
	        		}
	        	}
	        }
		        
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
	            	$scope.orgChangeInfo.orgId=nodes[0].orgId;
	            });
	            //$scope.pOrg=nodes[0].name;

	        };
	    	
	        
	        $scope.query=function(){
				$scope.getData($scope.currentPage1,$scope.itemsPerPage1);
			}			
	    
			//当有姓名作为查询条件时,清空ZTree选中的节点(跨部门姓名查询)
	        $scope.queryTransDepartment=function(){
	        	//判断姓名是否不为空
	        	if((null!=$scope.queryCondition.identityCardName && $scope.queryCondition.identityCardName!='')
					|| (null!=$scope.queryCondition.username && $scope.queryCondition.username!='')
					|| (null!=$scope.queryCondition.mobile && $scope.queryCondition.mobile!='')){
	        		zTree.cancelSelectedNode();
	        		$scope.queryCondition.orgId="";
	        	}
				$scope.getData($scope.currentPage1,$scope.itemsPerPage1);
			}
	        
	        $scope.queryUserDoorCardList = function(){
	        	var querys = {
	        			isMain:true,// 主卡
	        			userIds:[]
	        	};
	        	angular.forEach($scope.orgUserList, function(e){
	        		querys.userIds.push(e.userId);
	        	});
	        	departmentService.queryUserDoorCardList(querys, function(result){
	        		if (result.code == 0) {
	        			$scope.userDoorCardList = result.data;
		        		angular.forEach($scope.userDoorCardList, function(e){
		        			$scope.userDoorCardMap[e.userId] = e;
		        		});
	        		}
	        		
	        	});
	        	
	        };
	
	    	
			$scope.getData =function(currentPage1,itemsPerPage1){
				$scope.queryCondition.currentPage = currentPage1 - 0;
				$scope.queryCondition.itemsPerPage = itemsPerPage1;
				$scope.queryDepartmentInfoDisabled = true;
				$scope.progressing=true;
				departmentService.queryDepartmentInfo($scope.queryCondition,function(result) {
							$scope.queryDepartmentInfoDisabled = false;
							if (result.code == 0) {//获取数据成功									
								$scope.orgUserList = result.data;
								//数据转换下
								for(var i in $scope.orgUserList){
									if($scope.orgUserList[i].isAvailable=='0'){
										$scope.orgUserList[i].isAvailableValue='停用';							
									}else $scope.orgUserList[i].isAvailableValue='启用';
									
									if($scope.orgUserList[i].orgId!=null && $scope.orgUserList[i].orgId!=''){
										for(var j in $scope.orgList){
											if($scope.orgUserList[i].orgId==$scope.orgList[j].id){
												$scope.orgUserList[i].orgName=$scope.orgList[j].orgName;
											}
										}
									}
									if($scope.orgUserList[i].positionId!=null&&$scope.orgUserList[i].positionId!=''){
										for(var j in $scope.positionList){
											if($scope.orgUserList[i].positionId==$scope.positionList[j].id){
												$scope.orgUserList[i].positionName=$scope.positionList[j].positionName;
											}
										}
									}
								}
								$scope.totalItems1 = result.total;
								$scope.pageCount1 = Math.ceil($scope.totalItems1/$scope.itemsPerPage1);
								$scope.progressing = false;
								
								$scope.queryUserDoorCardList();
							} else {
								$scope.progressing = false;
								$.MsgBox.Alert("提示", result.data);
							}
				});
			}
			
			$scope.getData($scope.currentPage1,$scope.itemsPerPage1);
			
			
	    	$scope.init = function(){
	    		var setting = {check: {enable: false},data: {simpleData: {enable: true}},callback: {onClick:$scope.onClick,onRightClick: $scope.zTreeOnRightClick},view :{fontCss: $scope.getFontCss}  };
	    		var setting2 = {check: {enable: false},data: {simpleData: {enable: true}},callback: {onClick:$scope.onClick2}};
	    		var param={};
	        	orgService.queryCurrentOrg(param,function(result){
	    				if(result.code == 0){//获取数据成功
	    					var zNodes = result.data;
	    					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	    					$scope.setCheck();

							$.fn.zTree.init($("#treeDemo2"), setting2, zNodes);
	    					$scope.setCheck2();
	    					$("#py").bind("change", true);
	    					$("#sy").bind("change", true);
	    					$("#pn").bind("change", true);
	    					$("#sn").bind("change", true);
							var zTree = $.fn.zTree.getZTreeObj("treeDemo");
							var zTree2 = $.fn.zTree.getZTreeObj("treeDemo2");
							zTree.expandAll(false);
							zTree2.expandAll(false);
	    				}
	    		});
	    		
	    		
	    		if(sessionStorage.getItem("um_queryInfo")!=null){
					
					$scope.queryCondition =jQuery.parseJSON(sessionStorage.getItem("um_queryInfo"));
					sessionStorage.removeItem("um_queryInfo");
					$scope.query();
				}
				
				var param = {};
				departmentService.getAllOrgInfo(param, function(result) {
					if (result.code == 0) {//获取数据成功
						$scope.orgList = result.data;
					}
				});

				departmentService.getAllPostitionInfo(param, function(result) {
					if (result.code == 0) {//获取数据成功
						$scope.positionList = result.data;
					}
				});
				
	    	}
	    	$scope.init();
			
		
			$scope.selectAll = function(){//全选
				var bischecked=$('#topCheckBox').is(':checked'); 
				var fruit=$('input[name="userList"]'); 
				fruit.prop('checked',bischecked); 
			}
			
			//展示菜单
			$scope.showMenu=function($event){
			
				 //$event.stopPropagation();		
				//event.stopPropagation();
				var cityObj = $("#citySel");
				if($("#menuContent").is(":hidden")){
					$("#menuContent").css({top: cityObj.outerHeight() + "px","z-index":'999'}).slideDown("fast");
				}else{
					$("#menuContent").hide();
				}
				
			}
			
		    $document.bind("click", function(event) {
		        //$scope.showFlag=false;
		    	if(!$("#menuContent").is(":hidden")){
		    		$("#menuContent").hide();
				}
		    	menu.style.visibility = "hidden";
		        $scope.$apply();             
		     }); 
			
			$scope.orgChange=function(){
				if($scope.orgChangeInfo.orgId==null || $scope.orgChangeInfo.orgId==""){
					$.MsgBox.Alert("系统提示", "请先选择需要移动到的部门");
					return;
				}
				changeArr=[];
				$('input[name="userList"]:checked').each(function(){
					var val=$(this).val();
					changeArr.push(val)
				});
				if(0>=changeArr.length){
					$.MsgBox.Alert("系统提示", "请先选择需要移动部门的用户");
					return;
				}
				var changeParam={
						"userIds":changeArr,
						"orgId":$scope.orgChangeInfo.orgId
				};
				departmentService.orgChange(changeParam, function(result) {
					if (result.code == 0) {//获取数据成功
						$scope.query();
						$.MsgBox.Alert("系统提示", "部门移动操作成功");
						$('#topCheckBox').attr("checked",false);
						return;
					}else{
						$.MsgBox.Alert("系统提示", "部门移动操作失败");
						return;
					}
				});
			}
			
			$scope.resetInfo=function(){//重置按钮
				$scope.queryCondition={};
				zTree.destroy();
	    		$scope.init();
			}
			

			//新增部门按钮
			$scope.oneOrgAdd=function(){
				$scope.cleanData();
				if(zTree.getSelectedNodes().length<=0){
					$.MsgBox.Alert("系统提示", "请先选择添加部门的上级部门");
					return;
				}
				if(zTree.getSelectedNodes()[0].level>=9){
					$.MsgBox.Alert("系统提示", "部门层级最多十级");
					return;
				}
				$('#orgDialog1').modal('show');
			}
			
			$scope.oneOrgAdd2=function(){
				$scope.cleanData();
				$('#orgDialog1').modal('show');
			}
			//新增部门前,清除之前的输入缓存
			$scope.cleanData=function(){
				$scope.orgNewInfo.code='';
				$scope.orgNewInfo.orgName='';
			}
			//保存新增部门
			$scope.saveNewOrg=function(){
				if(null==$scope.orgNewInfo.code||""==$scope.orgNewInfo.code||null==$scope.orgNewInfo.orgName||""==$scope.orgNewInfo.orgName){
	    			$.MsgBox.Alert("提示", "部门编码和部门名称不能为空");
	    			return ;
	    		}
				if($scope.orgNewInfo.orgName.length>15){
					$.MsgBox.Alert("提示", "部门名称长度不能超过15字符!");
	    			return ;
				}
	    		
	    		if(!/^\d*$/.test($scope.orgNewInfo.code)){
	        		$.MsgBox.Alert("提示", "编码必须为数字");
	    			return ;
	    		}
	    		$scope.addDisabled = true;
	    		orgService.addOrg($scope.orgNewInfo,function(result){
	        		$scope.addDisabled = false;
					if(result.code == 0){//获取数据成功
						$.MsgBox.Alert("提示", "新增成功！");
						zTree.destroy();
						zTree2.destroy();
			    		$scope.init();
			    		
			    		$('#orgDialog1').modal('hide');
					}else{
						$.MsgBox.Alert("提示", result.data);
					}
	        	});
	    		
			}
			
			//编辑部门按钮
			$scope.oneOrgedit=function(){
				if(zTree.getSelectedNodes().length<=0){
					$.MsgBox.Alert("系统提示", "请先选择需要编辑的部门");
					return;
				}
				$('#orgDialog2').modal('show');			
			}
			
			$scope.oneOrgedit2=function(){
				$('#orgDialog2').modal('show');			
			}
			
			//保存编辑部门
			$scope.saveEditOrg=function(){
				
				if(null==$scope.orgEditInfo.code||""==$scope.orgEditInfo.code||null==$scope.orgEditInfo.orgName||""==$scope.orgEditInfo.orgName){
	    			$.MsgBox.Alert("提示", "部门编码和部门名称不能为空");
	    			return ;
	    		}
				if(null!=$scope.orgEditInfo.orgName && $scope.orgEditInfo.orgName.length>15){
					$.MsgBox.Alert("提示", "部门名称长度不能超过15字符!");
	    			return ;
				}
	    		if(!/^\d*$/.test($scope.orgEditInfo.code)){
	        		$.MsgBox.Alert("提示", "编码必须为数字");
	    			return ;
	    		}
	    		$scope.editDisabled = true;
	    		orgService.editOrg($scope.orgEditInfo,function(result){
	        		$scope.editDisabled = false;
					if(result.code == 0){//获取数据成功
						$.MsgBox.Alert("提示", "修改成功！");
						zTree.destroy();
						zTree2.destroy();
			    		$scope.init();
			    		
			    		$('#orgDialog2').modal('hide');
					}else{
						$.MsgBox.Alert("提示", result.data);
					}
	        	});
	    		
					
			}
			
			//删除部门按钮
			$scope.oneOrgdel=function(){
				if(zTree.getSelectedNodes().length<=0){
					$.MsgBox.Alert("系统提示", "请先选择需要删除的部门");
					return;
				}
				/*for (var int = 0; int < zTree.getSelectedNodes().length; int++) {
					var node = zTree.getSelectedNodes()[int];
					if (node.children!=null&&node.children.length!=0) {
						$.MsgBox.Alert("系统提示", "存在下级部门,不能删除!");
						return;
					}
				}*/
				if(zTree.getSelectedNodes()[0].level<=0){
					$.MsgBox.Alert("系统提示", "顶级部门不能删除");
					return;
				}
				$('#comfirmDeleteOrgModal').modal('show');
			}
			
			$scope.oneOrgdel2=function(){
				
				if($scope.delLevel<=0){
					$.MsgBox.Alert("系统提示", "顶级部门不能删除");
					return;
				}
				/*for (var int = 0; int < zTree.getSelectedNodes().length; int++) {
					var node = zTree.getSelectedNodes()[int];
					if (node.children!=null&&node.children.length!=0) {
						$.MsgBox.Alert("系统提示", "存在下级部门,不能删除!");
						return;
					}
				}*/
				$('#comfirmDeleteOrgModal').modal('show');
			}
			
			$scope.delOneOrg=function(){
				$('#comfirmDeleteOrgModal').modal('hide');
				//$scope.delDisabled = true;
        		orgService.deleteOrg($scope.orgDelInfo,function(result){
            		//$scope.delDisabled = false;
    				if(result.code == 0){//获取数据成功
    					$.MsgBox.Alert("提示", "删除成功！");
    					zTree.destroy();
    					zTree2.destroy();
    		    		$scope.init();
    		    		
    					$scope.queryCondition.orgId="";
    				}else{
    					$.MsgBox.Alert("提示", result.data);
    				}
            	});
			}
			
			$scope.oneOrgSave=function(){//保存一个部门
				if(zTree.getSelectedNodes()&&zTree.getSelectedNodes().length>0){					
					
					if($scope.orgSaveInfo.code==null||$scope.orgSaveInfo.code==''){
						$.MsgBox.Alert("系统提示", "部门编码必填");
						return;
					}
					if($scope.orgSaveInfo.orgName==null||$scope.orgSaveInfo.orgName==''){
						$.MsgBox.Alert("系统提示", "部门名称必填");
						return;
					}
					$scope.saveOrgInfoDisabled=true;
					orgService.addOrg($scope.orgSaveInfo,function(result){
		        		$scope.saveOrgInfoDisabled = false;
						if(result.code == 0){//获取数据成功
							$.MsgBox.Alert("提示", "新增成功！");
							zTree.destroy();
				    		$scope.init();
						}else{
							$.MsgBox.Alert("提示", result.data);
						}
		        	});
				}else{
					$.MsgBox.Alert("系统提示", "请先选择上级部门");
					return;
				}
					

			}
		
			
			$scope.addgongyuan=function(){//添加员工
				$scope.queryCondition2=$scope.queryCondition;
				sessionStorage.setItem("um_queryInfo",angular.toJson($scope.queryCondition2));//用户管理-存储查询条件信息
				userUtil.redirectTo("#/yuangongAdd" , true);
			}
			//角色相关
			$scope.queryRoleContent={};
			$scope.ownRoleList = new Array();
			$scope.roleList = new Array();
			$scope.ownRoleChecks = {};
			$scope.allRoleChecks = {};
			
			$scope.initEdit=function(id){
				$scope.hasEmail = false;
				$scope.saveCheckDisabled = false;
				var queryData={"id":id};
				userService.queryUserDetail(queryData,function(result) {
					if (result.code == 0) {//获取数据成功
						$scope.editUser = result.data.userObj;
						$scope.ownRoleList = result.data.ownRoleList;
						$scope.roleList = result.data.roleList;
						$scope.moveOwnRole($scope.ownRoleList,$scope.roleList);
					}
				})
			}
			
			$scope.queryRole=function(){
				roleListService.queryAllRole($scope.queryRoleContent,function(result) {
					if (result.code == 0) {//获取数据成功
						$scope.roleList = result.data;
						$scope.moveOwnRole($scope.ownRoleList,$scope.roleList);
					}
				})
			}
			
		  $scope.moveOwnRole=function(a,b){
			  var b_length=b.length;
			  for (var i=0; i<b_length; i++) {  
			       for (var j=0; j<a.length; j++) {  
			          if (b[i].id == a[j].id) {  
			              b.splice(i, 1);
			              b_length--;
			              i--;
			              break;
			          }  
			       }  
			  }
		  }
		  
		  $scope.addRole=function(){
			  $("#allRoleSelect option:selected").each(function() {
				  var all_role_length=$scope.roleList.length;
				  for (var j=0; j<all_role_length; j++) {  
			          if ($scope.roleList[j].id == $(this).attr("value")) {
			        	  $scope.ownRoleList.push({"id":$scope.roleList[j].id,"name":$scope.roleList[j].name})
			        	  $scope.roleList.splice(j, 1);
			        	  all_role_length--;
			        	  j--;
			          }  
			       }
		      });	
		  }
		  
		  $scope.deleteRole=function(){
			  $("#ownRoleSelect option:selected").each(function() {
				  var own_role_length=$scope.ownRoleList.length;
				  for (var j=0; j<own_role_length; j++) {  
			          if ($scope.ownRoleList[j].id == $(this).attr("value")) {
			        	  $scope.roleList.push({"id":$scope.ownRoleList[j].id,"name":$scope.ownRoleList[j].name})
			        	  $scope.ownRoleList.splice(j, 1);
			        	  own_role_length--;
			        	  j--;
			          }  
			       }
		      });	
		  }
		 
		 //校验修改员工信息时邮箱的唯一性(ng-blur,ng-change触发)
		 $scope.checkEmail=function(){
			 $scope.queryContectByEmail={};
			 $scope.queryContectByEmail.email=$scope.editUser.email;
			 departmentService.queryUserByEmail($scope.queryContectByEmail,function(result) {
				 if(result.data.length > 0){
//						alert("存在");
						$scope.hasEmail = true;
						$scope.saveCheckDisabled = true;
					}else{
//						alert("不存在");
						$scope.hasEmail = false;
						$scope.saveCheckDisabled = false;
					}
			 })
		 }
		 
		 $scope.updateUser=function(){
			 if($scope.editUser.identityCardName=='' || null==$scope.editUser.identityCardName){
				 $.MsgBox.Alert("系统提示", "姓名不能为空！");
				 return;
			 }
			 var userData={"id":$scope.editUser.id,"identityCardName":$scope.editUser.identityCardName,"mobile":$scope.editUser.mobile,"email":$scope.editUser.email,"isAvailable":$scope.editUser.isAvailable,"positionId":$scope.editUser.positionId,"orgId":$scope.editUser.orgId};
			 
			 userService.updateUserDetail(userData,function(result) {
				if (result.code == 0) {//获取数据成功
					$.MsgBox.Alert("系统提示", "修改成功");
					$('#editForm').modal('hide');//隐藏修改弹窗
					$scope.query();//重新查询
				}else{
					$.MsgBox.Alert("系统提示", result.data);
				}
			})
		 }

		 $scope.resetPassWord=function(){
			 var userData={"id":$scope.editUser.id,"mobile":$scope.editUser.mobile};
			 userService.resetPassWord(userData,function(result) {
				if (result.code == 0) {//获取数据成功
					$.MsgBox.Alert("系统提示", "密码重置成功");
				}else{
					$.MsgBox.Alert("系统提示", result.data);
				}
			})
		 }
		 
		 $scope.saveRole=function(){
			 var roleArray=new Array();
			 for (var j=0; j<$scope.ownRoleList.length; j++) {  
				 roleArray.push($scope.ownRoleList[j].id);
			 }
			 var userData={"userId":$scope.editUser.id,"roleList":roleArray};
			 userRoleService.updateUserRole(userData,function(result) {
					if (result.code == 0) {//获取数据成功
						$.MsgBox.Alert("系统提示", "修改成功");
					}else{
						$.MsgBox.Alert("系统提示", "修改失败");
					}
			})
		 }
		 
		 $scope.doorCardEdit = function(userId){
			 var cardId = '';
			 if ($scope.userDoorCardMap[userId]) {
				 cardId = $scope.userDoorCardMap[userId].id;
			 }
			 var menu = {
					 id:'30OU08',
					 url:'/mzadmin-web/#/userDoorCardEdit/' + cardId,
					 name:'门禁卡信息',
					 refresh: true
			 };
			 if (cardId == '') {
				 menu.url = '/mzadmin-web/#/userDoorCardAdd/' + userId;
			 }
			 
			 window.parent.clickMenuURL(menu);
		 };
		 
		 $scope.serviceSpace = function(userId){
			 var menu = {
					 id:'30OU0115',
					 url:'/mzadmin-web/#/userServiceSpace/' + userId,
					 name:'服务区',
					 refresh: true
			 };
			 
			 window.parent.clickMenuURL(menu);
		 };
		 
		}]);