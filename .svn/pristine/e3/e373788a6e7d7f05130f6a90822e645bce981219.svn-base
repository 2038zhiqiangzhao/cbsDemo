/**
 */
'use strict';
app.controller("communityActivityCtrl", [
		'$scope',
		'$state',
		'$stateParams',
		'$http',
		'platformUtil',
		'Dic',
		function($scope, $state, $stateParams, $http, platformUtil, Dic) {
			'use strict';

			// 常量定义
			var LIST_QUERY_URL = "communityActivity/queryActivityList.do";//查询列表
			var EDIT_URL = "communityActivity/editCommunityClass.do";//发布或下架
			var DELETE_BATCH_URL = "communityActivity/deleteCommunityClass.do";//删除
			var ADMINMUMBER = "communityActivity/adminMumber.do";//删除
			var authCode = "";
			$scope.Dic = Dic;
			$scope.activityStateDescs = Dic.toObj(Dic.activityState);

			// init 变量定义
			$scope.queryParams = {
				    currentPage : 1,
				    itemsPerPage : 10
			};
			

			// 参数 首页，每页10条数据
			$scope.init = function() {
				$scope.getData();
			};

			// 状态描述
			$scope.statusDescs = {
				'0' : '已下架',
				'1' : '已发布'
			};

			// 字典值
			$scope.Dic = Dic;
			
			$scope.resCommunityDescs = {};

			/**
			 * 翻页加载数据
			 */
			$scope.getData = function() {
				$scope.checkAll = false;

				$http.post(LIST_QUERY_URL, angular.toJson($scope.queryParams))
						.success(function(result) {
							if (result.code == 0) {
								$scope.records = result.data.listObj;
								$scope.totalItems = result.data.total;
								angular.forEach(result.data, function(record, key) {
									$scope.resCommunityDescs[record.id] = record.activityName;//活动名称
									$scope.resCommunityDescs[record.id] = record.activityTime;//活动开始时间
									$scope.resCommunityDescs[record.id] = record.activityPlace;//活动地点
									$scope.resCommunityDescs[record.id] = record.registrationFee;//报名费
									$scope.resCommunityDescs[record.id] = record.timeInterval;//定时发布
									$scope.resCommunityDescs[record.id] = record.publishState;//发布状态
									$scope.resCommunityDescs[record.id] = record.activityState;//活动状态
							
								});
							}
						}).error(function(err) {
						});
			};
			
		
			
			$scope.reset = function() {
				$scope.queryParams = {
					currentPage : 1,
					itemsPerPage : 10
				};
			};

			/**
			 * 批量删除操作
			 */
			$scope.deleteBatchOption = function() {
				var selects = [];
				for (var i = 0; i < $scope.records.length; i++) {
					var record = $scope.records[i];
					if (record.checked) {
						selects.push(record.id);
						
					}
				}
				if (selects.length == 0) {
					return;
				}
				
				platformUtil.showConfirm("提示", "确认删除吗?", function() {
                	$http.post(DELETE_BATCH_URL, angular.toJson({
    					ids : selects
    				})).success(function(result) {
    					platformUtil.showAlert('提示', '操作成功');
    					$scope.getData();
    				}).error(function(err) {
    					console.log("HTTP访问失败");
    				});
               });

				

			};

			/**
			 * 新增操作
			 */
			$scope.addOption = function() {
				$state.go('communityActivityAdd');
			
			};
			
			/**
			 * 活动订单管理
			 */
			$scope.oderLookOption = function(record) {
				$state.go('communityActivityOder',
                 {
					
					id : record.id
				});
			
			};
			/**
			 * 编辑
			 */
			$scope.editOption = function(record) {
				$state.go('communityActivityEdit', {
					
					id : record.id
				});
			};
			
			
			/**
			 * 点击图片放大
			 * 
			 */
		  $scope.imageLarge = function(record) {
			  authCode = record.authCode;
			  $scope.image=authCode;
			  angular.element('#imageDiv').show();
			  
			  
		  }
			 /**
             * 活动组织者>>>弹窗
             */
			 $scope.selectedRecord = {};
            $scope.oderAdminOption = function(record) {
            	angular.element('#serviceDiv').show();
            	 $scope.selectedRecord['activityId']=record.id;
        		$http.post(ADMINMUMBER, angular.toJson({activityId:$scope.selectedRecord['activityId']}))
				.success(function(result) {
					if (result.code == 0) {
						$scope.resDescs= result.data;
						angular.forEach(result.data, function(record, key) {
							$scope.resCommunityDescs[record.adminHeadUrl] = record.adminHeadUrl;
							$scope.resCommunityDescs[record.adminName] = record.adminName;
							$scope.resCommunityDescs[record.adminPhone] = record.adminPhone;
						
					
						});
					}
				}).error(function(err) {
					console.log("HTTP访问失败");
				});
                
            }
            
            /**
             * 活动组织者>>>关闭弹窗
             */
            $scope.closeDialog = function() {
                angular.element('#serviceDiv').hide();
            }
            /**
             * 二维码者>>>关闭弹窗
             */
            $scope.closeDialogimage = function() {
                angular.element('#imageDiv').hide();
            }
            
			/**
			 * 列表全选
			 */
			$scope.selectAll = function(event) {
				angular.forEach($scope.records, function(record, key) {
					record.checked = !$scope.checkAll;
				});
			};

			/**
			 * 列表单选
			 */
			$scope.selectItem = function(record, event) {
				record.checked = !record.checked;
				$scope.checkAll = true;
				for (var i = 0; i < $scope.records.length; i++) {
					var r = $scope.records[i];
					if (!r.checked) {
						$scope.checkAll = false;
						break;
					}
				}
			};

			$scope.saveOption = function(formData) {
				$http.post(EDIT_URL, angular.toJson(formData)).success(
						function(result) {
							platformUtil.showAlert('提示', '操作成功');
							$scope.getData();
						}).error(function(err) {
					console.log("HTTP访问失败");
				});
			};

			/**
			 * state 1发布/state 0下架
			 */
			$scope.publishOption = function(record, state) {
				var formData = {
					id : record.id,
					publishState : state
				};
				var tip = "";
				if (state == 1) {
					tip = "发布";
				} else if (state == 0) {
					tip = "下架";
				}
				platformUtil.showConfirm("提示", "确认" + tip + "吗?", function() {
					$scope.saveOption(formData);
				});
			};

			$scope.init();
		}]);