/**
 * Created by Roy on 16/8/12.
 */
angular.module('ui.bootstrap.modal.transform',['ui.bootstrap.modal'])
    .run(['$rootScope','$modal','$templateCache',function($rootScope,$modal,$templateCache){
        $templateCache.put('baseModal.html','<div><div class="modal-header"><h3 class="modal-title">{{msg.title}}</h3></div>'+
        '<div class="modal-body" ng-if="!msg.userTemplateUrl">{{msg.message}}</div>'+
        '<div class="modal-body" ng-if="msg.userTemplateUrl" ng-include="msg.userTemplateUrl"></div>'+
        '<div class="modal-footer">'+
        '<button class="btn btn-info" ng-click="close()" ng-if="msg.isOK">{{msg.OKText}}</button>'+
        '<button class="btn btn-default" ng-click="cancel()" ng-if="msg.isCancel">{{msg.cancelText}}</button>'+
        '</div></div>');
        $rootScope.open = function (msg) {
            var msg = msg;
            if (angular.isString(msg))
                msg = {message: msg}
            else if (!angular.isObject(msg))
                msg = {message: '格式不对！'}
            $rootScope.msg = angular.extend({
                code: 0,
                title: '信息提示', //提示框标题
                message: '',   //提示框内容
                isCancel: true,  //是否需要取消功能
                cancelText: '取消', //取消按钮文字
                isOK: true,        //是否需要确定功能
                OKText: '确定',       //确定按钮文字
                size: 'sm',          //模态框size,(sm:小，md:中，lg:大)
                callback: angular.noop,  //关闭确定按钮后触发的事件
                template: $templateCache.get('baseModal.html'), //弹框模板
                templateUrl:'',
                userTemplateUrl: false,
                scope: $rootScope    //作用域
            }, msg);
            $modal.open({
                template: $rootScope.msg.template,
                templateUrl: $rootScope.msg.template,
                scope: $rootScope.msg.scope,
                size: $rootScope.msg.size
            });
        };
        $rootScope.close=function(){
            this.$close();
            $rootScope.msg.callback();
        }
        $rootScope.cancel=function(result){
            this.$dismiss(result);
        }
    }])