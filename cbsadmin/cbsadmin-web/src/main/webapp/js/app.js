var app = angular.module("app", ['ui.router', 'ngAnimate', 'ngCookies',
		'ngStorage', 'ui.load', 'ui.bootstrap.pagination', 'angularFileUpload',
		'ngDatetimepicker', 'ui.bootstrap', 'ui.bootstrap.tree'// 树

]);

// 登录拦截
app.factory('commonInterceptor', [function($q) {
	var commonInterceptor = {
		response : function(resp) {
			var code = resp.data.code;
			if (code == '99') {
				parent.window.location.href = "/index.html";
			}
			return resp;
		}
	};
	return commonInterceptor;
}]);

app.config([
		"$stateProvider",
		"$urlRouterProvider",
		"$locationProvider",
		"$httpProvider",
		function($stateProvider, $urlRouterProvider, $locationProvider,
				$httpProvider) {
			'use strict';

			// 默认进入配置页面
			$urlRouterProvider.otherwise("/blank");

			//社区活动管理：列表
			$stateProvider.state("communityActivity", {
				url : "/communityActivity",
				cache : 'false',
				templateUrl : "views/communityActivity/list.html",
				controller : "communityActivityCtrl"
			});
			//社区活动管理：新增
			$stateProvider.state("communityActivityAdd", {
				url : "/communityActivityAdd",
				cache : 'false',
				templateUrl : "views/communityActivity/communityInfoListAdd.html",
				controller : "communityActivityAddCtrl"
			});
			//社区活动管理：编辑
			$stateProvider.state("communityActivityEdit", {
				url : "/communityActivityEdit:id",
				cache : 'false',
				templateUrl : "views/communityActivity/communityInfoListEdit.html",
				controller : "communityActivityEditCtrl"
			});
			//社区活动订单管理
			$stateProvider.state("communityActivityOder", {
				url : "/communityActivityOder:id",
				cache : 'false',
				templateUrl : "views/communityActivity/oderList.html",
				controller : "communityActivityOderCtrl"
			});
			//社区活动订详情
			$stateProvider.state("communityActivityOderDetails", {
				url : "/communityActivityOderDetails:id",
				cache : 'false',
				templateUrl : "views/communityActivity/oderDetails.html",
				controller : "communityActivityOderDetailsCtrl"
			});
			
		}]);