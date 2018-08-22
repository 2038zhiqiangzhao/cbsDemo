'use strict';
app.controller("updateCtrl", [
        '$scope',
        '$localStorage',
        '$http',
        '$window',
        '$rootScope',
        '$stateParams',
        '$location',
        function($scope, $localStorage, $http, $window, $rootScope,
                $stateParams, $location) {
            'use strict';

            // 初始化加载数据
            $scope.init = function() {
                $http({
                    method : 'POST',
                    url : 'role/queryRoleById.do',
                    dataType : "json",
                    params : {
                        id : $stateParams.id
                    }
                }).then(function successCallback(response) {
                    // 请求成功执行代码
                    console.log("数据返回成功===>>>" + response.data.data.role);
                    $scope.role = response.data.data.role;
                }, function errorCallback(response) {
                    // 请求失败执行代码
                    console.log("HTTP访问失败");
                });
            }

            // 保存数据
            $scope.saveSubmit = function() {
                $("#saveSubmitBtn").button("loading");
                $http({
                    method : 'POST',
                    url : 'role/updateRoleById.do',
                    dataType : "json",
                    params : {
                        id : $stateParams.id,
                        roleName : $("*[name='roleName']").val(),
                        remarks : $("*[name='remarks']").val(),
                        status : $("*[name='status']").val()
                    }
                }).success(
                        function(result) {
                            $("#loginBtn").button("reset");
                            if (typeof result == 'undefined'
                                    || typeof result.code == 'undefined') {
                                alert("系统错误!");
                                return;
                            }
                            // 如何利用路由跳转？
                            window.location ="/user-web/index.html#/role_list";
                            // $state.go('role_list');
                        }).error(function() {
                    $("#loginBtn").button("reset");
                    $scope.errorMessage = '系统错误';
                    return;
                });
            };

        } ]);