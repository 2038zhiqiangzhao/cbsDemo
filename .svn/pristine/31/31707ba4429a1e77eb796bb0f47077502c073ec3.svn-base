/**
 */
'use strict';
app.controller("communityActivityOderCtrl", [
		'$scope',
		'$state',
		'$stateParams',
		'$http',
		'platformUtil',
		'Dic',
		function($scope, $state, $stateParams, $http, platformUtil, Dic) {
			'use strict';

			// 常量定义
			var LIST_QUERY_URL = "communityActivity/queryActivityListOder.do";//查询列表
			 $scope.Dic = Dic;
			$scope.activityStatePayWayDec = Dic.toObj(Dic.payWays);
			$scope.activityStateDec = Dic.toObj(Dic.activityOrderState);
			if ($stateParams.id) {
  				$scope.communityActivityId = $stateParams.id;
  			}
			// init 变量定义
			$scope.queryParams = {
				    currentPage : 1,
				    itemsPerPage : 10,
				    communityActivityId:$scope.communityActivityId
			};

			// 参数 首页，每页10条数据
			$scope.init = function() {
				$scope.getData();
			};

			
			// 状态描述
			$scope.statusisEntryDescs = {
				'0' : '否',
				'1' : '是'
			};

			/**查看详情数据
			 * lookOption(record)
			 */
	
			$scope.lookOption = function(record) {
				$state.go('communityActivityOderDetails',  {
					id : record.id
					});
			
			};
			
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

								
							}
						}).error(function(err) {
						});
			};
			
			
			
			$scope.reset = function() {
				$scope.queryParams = {
					currentPage : 1,
					itemsPerPage : 10,
					community_activity_id:$scope.community_activity_id
				};
				$scope.getData();
			};


			$scope.init();
		}]);