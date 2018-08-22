'use strict';
app.controller("loginCtrl", ['$scope', '$localStorage', '$http', '$window', '$rootScope', function($scope, $localStorage, $http, $window, $rootScope){
    'use strict';

    $scope.init = function() {
        $scope.user = {};
        top.$('#platformFrame').css({
            height:document.body.scrollHeight - 50
        });
    };


    $scope.loginCheck = function() {
        if (typeof $scope.user.username == 'undefined' || $scope.user.username == '') {
            $scope.errorMessage = '请输入用户名';
            return false;
        } else {
            $scope.errorMessage = '';
        }
        if (typeof $scope.user.password == 'undefined' || $scope.user.password == '') {
            $scope.errorMessage = '请输入密码';
            return false;
        } else {
            $scope.errorMessage = '';
        }
        return true;
    };

    $scope.loginSubmit = function() {
        if (!$scope.loginCheck()) {
            return;
        }
        $("#loginBtn").button("loading");
        $scope.user.type = 1;
        $scope.user.companyId = $scope.companyId;
        //$scope.user.userPlatformId = 1;
        $http({
            method : 'POST',
            url: '/user-web/mobileLogin/login.do',
            data : $scope.user
        }).success(function(result) {
            $("#loginBtn").button("reset");
            if (typeof result == 'undefined' || typeof result.code == 'undefined') {
                $scope.errorMessage = '系统错误';
                return;
            }
            var bizErrorCode = result.code;
            if (bizErrorCode == 0) {
                top.window.location.href = "/";
                //$window.location.href = "/#/blank";
                return;
            }
            else if (bizErrorCode == 1) {
                $scope.errorMessage = '请输入用户名';
                return;
            }
            else if (bizErrorCode == 2) {
                $scope.errorMessage = '请输入密码';
                return;
            }
            else if (bizErrorCode == 3) {
                $scope.errorMessage = '用户名或者密码错误';
                return;
            }
            else{
                $scope.errorMessage = result.message;
                return ;
            }
        }).error(function() {
            $("#loginBtn").button("reset");
            $scope.errorMessage = '系统错误';
            return;
        });
    };

}]);