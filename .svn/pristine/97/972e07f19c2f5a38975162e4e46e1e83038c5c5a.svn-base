/*var app = angular.module("app", [ 'ui.router', 'ngAnimate', 'ngCookies',
        'ngStorage', 'ui.load' ]);
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

            // 角色列表
            $stateProvider.state("role_list", {
                url : "/role_list",
                cache : 'false',
                templateUrl : "views/role/list.html"
            });

            // 角色查看
            $stateProvider.state("role_detail", {
                url : "/role_detail?id",
                cache : 'false',
                templateUrl : "views/role/detail.html"
            });

            // 角色新增
            $stateProvider.state("role_create", {
                url : "/role_create",
                cache : 'false',
                templateUrl : "views/role/create.html"
            });

            // 角色修改
            $stateProvider.state("role_update", {
                url : "/role_update",
                cache : 'false',
                templateUrl : "views/role/update.html"
            });

            // 角色删除
            $stateProvider.state("role_delete", {
                url : "/role_delete",
                cache : 'false',
                templateUrl : "views/role/delete.html"
            });
        } ]);*/
/**
 * user anglanjs 相关注册
 * */
var head = document.getElementsByTagName("head")[0];
var bootPATH = "";
(function() {
	var scripts = head.getElementsByTagName("script");
	for (var i = 0; i < scripts.length; i++) {
		var script = scripts[i];
		var regex = /boot\.js(\?v=.*)?/ig;
		result = regex.exec(script.src);
		if (result) {
			bootPATH = script.src.substring(0,script.src.lastIndexOf("scripts/boot.js"));
			if (result[1]) {
				version = result[1];
			}
			break;
		}
	}
})();

var app = angular.module("app", ['ngRoute','ui.router','ngAnimate','ngCookies','ngStorage',
                                   'ui.bootstrap',
                                   'ui.load',
                                   'ui.jq',
                                   'ui.validate',
                                   'pascalprecht.translate', 
                                   'app.directives',
                                   'app.controllers',                        
                                   'ngDatetimepicker',
                                   'ui.bootstrap.pagination',
                                   'angularFileUpload',
                                   'colorpicker.module',
                                   'ui.bootstrap.tree']
                                   ,function ($httpProvider) {  
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/json';  
    $httpProvider.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest'; 
});

app.directive(
		'multipleEmail',
		[ function() {
			return {
				require : "ngModel",
				link : function(scope, element, attr, ngModel) {
					if (ngModel) {
						var emailsRegexp = /^([a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9-]+(\.[a-z0-9-]+)*[;；]?)+$/i;
					}
					var customValidator = function(value) {
						var validity = ngModel.$isEmpty(value)
								|| emailsRegexp.test(value);
						ngModel.$setValidity("multipleEmail", validity);
						return validity ? value : undefined;
					};
					ngModel.$formatters.push(customValidator);
					ngModel.$parsers.push(customValidator);
				}
			};
		} ]);

app.directive('fileModel', [ '$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs, ngModel) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;
			element.bind('change', function(event) {
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				});
				// 附件预览
				scope.file = (event.srcElement || event.target).files[0];
				scope.getFile($(this).attr("id"));
			});
		}
	};
} ]);

app.factory('fileReader', [ "$q", "$log", function($q, $log) {
	
	var onLoad = function(reader, deferred, scope) {
		return function() {
			scope.$apply(function() {
				deferred.resolve(reader.result);
			});
		};
	};
	var onError = function(reader, deferred, scope) {
		return function() {
			scope.$apply(function() {
				deferred.reject(reader.result);
			});
		};
	};
	var getReader = function(deferred, scope) {
		var reader = new FileReader();
		reader.onload = onLoad(reader, deferred, scope);
		reader.onerror = onError(reader, deferred, scope);
		return reader;
	};
	var readAsDataURL = function(file, scope) {
		var deferred = $q.defer();
		var reader = getReader(deferred, scope);
		reader.readAsDataURL(file);
		return deferred.promise;
	};
	return {
		readAsDataUrl : readAsDataURL
	};
} ]);

app.config(["$stateProvider", "$urlRouterProvider", "$locationProvider", "$httpProvider",
         	function($stateProvider,$urlRouterProvider,$locationProvider,$httpProvider){
    $urlRouterProvider.otherwise('/login');
	$stateProvider
	//登录
	.state("login", {url : "/login",templateUrl : "module/login/login.html",controller : "loginController"})
	//注册
	.state("register",{url : "/register",templateUrl : "module/register/register.html",controller : "registerController"})
	//角色管理
	.state("roleList",{url : "/roleList",templateUrl : "module/role/roleList.html",controller : "roleListController"})
	//角色授权                                                                                                                                                     
	.state("roleFunction",{url : "/roleFunction",templateUrl : "module/role_function/role_function.html",controller : "roleFunctionController"})
	//功能管理
	.state("function",{url : "/function",templateUrl : "module/function/function.html",controller : "functionController"})
	//组织机构管理
	.state("org",{url : "/org",templateUrl : "module/org/org.html",controller : "orgController"})
	//组织人员管理
	.state("orgUser",{url : "/orgUser",templateUrl : "module/org_user/org_user.html",controller : "orgUserController"})

	
	
	//部门-员工
	.state("org-user",{url : "/org-user",templateUrl : "module/user_manager/department.html",controller : "departmentController"})
	//权限管理
	.state("function_manage",{url : "/function_manage",templateUrl : "module/user_manager/functionManage.html",controller : "functionManageController"})
	//角色管理
	.state("role",{url : "/role",templateUrl : "module/user_manager/roleManage.html",controller : "roleManageController"})
	//用户角色
	.state("userRole",{url : "/userRole",templateUrl : "module/user_role/user_role.html",controller : "userRoleController"})
	//员工详情
	.state("user-info",{url : "/user-info",templateUrl : "module/user_manager/userInfo.html",controller : "userInfoController"})
	//添加员工
	.state("yuangongAdd",{url : "/yuangongAdd",templateUrl : "module/user_manager/yuangongAdd.html",controller : "yuangongController"})//app.yuangongAdd
	//修改密码
	.state("editPassword",{url : "/editPassword",templateUrl : "module/user/user_password.html",controller : "userPasswordController"})
	//岗位管理
	.state("position",{url : "/position",templateUrl : "module/user_manager/position_list.html",controller : "positionController"})
	//平台管理
	.state("platform",{url : "/platform",templateUrl : "module/platform/platform_list.html",controller : "platformController"})
	//平台组管理
	.state("platformGroup",{url : "/platformGroup",templateUrl : "module/platform_group/platform_group_list.html",controller : "platformGroupController"});
	}
]);
