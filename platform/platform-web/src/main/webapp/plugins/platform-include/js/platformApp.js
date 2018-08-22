var app = angular.module("app", ['ui.router', 'ngAnimate', 'ngCookies', 'ngStorage', 'ui.load']);
app.config(["$stateProvider", "$urlRouterProvider", "$locationProvider", "$httpProvider",
    function($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider){
        'use strict';

        //默认进入配置页面
        $urlRouterProvider.otherwise("/blank");

        //登录页面
        $stateProvider.state("login", {
            url : "/login",
            cache:'false',
            templateUrl : "/plugins/platform-include/views/login.html"
        });

        //空白页面
        $stateProvider.state("blank", {
            url : "/blank",
            cache:'false',
            templateUrl : "/plugins/platform-include/views/blank.html"
        });
    }
]);