'use strict';
app.controller("detailCtrl", [
        '$scope',
        '$localStorage',
        '$http',
        '$window',
        '$rootScope',
        '$stateParams',
        function($scope, $localStorage, $http, $window, $rootScope,
                $stateParams) {
            'use strict';
            console.log("$stateParams===>>>" + $stateParams)
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

        } ]);