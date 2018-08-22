/**
 */
'use strict';
app.controller("communityActivityOderDetailsCtrl", [
		'$scope',
		'$state',
		'$stateParams',
		'$http',
		'platformUtil',
		'Dic',
		function($scope, $state, $stateParams, $http, platformUtil, Dic) {
			'use strict';

			// 常量定义
			var LIST_QUERY_URL = "communityActivity/queryActivityListDetailsOder.do";//查询列表
			 $scope.Dic = Dic;
			$scope.activityStatePayWayDec = Dic.toObj(Dic.payWays);
			$scope.activityStateDec = Dic.toObj(Dic.activityOrderState);
			$scope.orderSourceDescs = Dic.toObj(Dic.orderSources);
			  /** 变量定义 **/
			if ($stateParams.id) {
  				$scope.id = $stateParams.id;
  			}
			// 表单数据
			$scope.formData = {
					
			};
			
			// 状态描述
			$scope.statusisEntryDescs = {
				'0' : '否',
				'1' : '是'
			};

			 /**
             * 页面初始化
             */
            $scope.init = function(){
          	  $scope.getData();
            };
            
			
			$scope.getData = function() {

				$http.post(LIST_QUERY_URL, angular.toJson({
					id : $scope.id
					}))
						.success(function(result) {
							if (result.code == 0) {
								$scope.formData = result.data;

								
							}
						}).error(function(err) {
						});
			};
			
			
			
			$scope.reset = function() {
				$scope.getData();
			};


			$scope.init();
		}]);