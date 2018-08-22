'use strict';
app.controller("listCtrl", [ '$scope', '$localStorage', '$http', '$window',
        '$rootScope', '$state',
        function($scope, $localStorage, $http, $window, $rootScope, $state) {
            'use strict';
            $scope.init = function() {
                console.log("初始化开始init");
                // 初始化方法
                $http({
                    method : 'POST',
                    url : 'role/queryAllRoles.do'
                }).then(function successCallback(response) {
                    // 请求成功执行代码
                    console.log("===>>>" + response.data.data.roles);
                    $scope.roles = response.data.data.roles;
                }, function errorCallback(response) {
                    // 请求失败执行代码
                    console.log("HTTP访问失败");
                });
            }

            // 根据名称查询
            $scope.loadDataByRoleName = function() {
                if (!$('#query_roleName').val()) {
                    $scope.init();
                    return;
                }
                $http({
                    method : 'POST',
                    url : 'role/loadDataByRoleName.do',
                    params : {
                        roleName : $('#query_roleName').val()
                    }
                }).then(function successCallback(response) {
                    // 请求成功执行代码
                    console.log("查询成功");
                    $scope.roles = response.data.data.roles;
                }, function errorCallback(response) {
                    // 请求失败执行代码
                    console.log("HTTP访问失败");
                });
            }

            // 删除角色
            $scope.deleteRole = function(id, $state) {
                $http({
                    method : 'POST',
                    url : 'role/deleteRole.do',
                    params : {
                        id : id
                    }
                }).then(function successCallback(response) {
                    // 请求成功执行代码
                    console.log("删除成功");
                    // $state.go('role_list');
                    $scope.init();
                }, function errorCallback(response) {
                    // 请求失败执行代码
                    console.log("HTTP访问失败");
                });
            }

            // 回车事件绑定：监听角色名称的输入框
            $('#query_roleName').bind('keyup', function(event) {
                if (event.keyCode == "13") {
                    // 回车执行查询
                    $('#query').click();
                }
            });
        } ]);