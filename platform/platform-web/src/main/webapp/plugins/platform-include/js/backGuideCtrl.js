/**
 * Created by ody on 2016/10/10.
 */
//var backGuideApp = angular.module("backGuideApp", []);
app.controller("backGuideCtrl", ['$scope', '$http', function($scope, $http){
    'use strict';
    //初始化
    $scope.init = function(){
        /*$http({
            url : 'guide/getBackGuideSetting.do',
            method : 'POST',
            dataType : "json"
        }).success(function(result){
            if("0" == result.code){
                $scope.commonSettingDTOList = result.resultData.commonSettingDTOList;
                $scope.finishSettingList = result.resultData.finishSettingList;
                $scope.isNoShowBackGuide = result.resultData.isNoShowBackGuide;
                if(!$scope.isNoShowBackGuide){
                    $('#guide-modal').modal('show');
                }
            }
	        else{
	          alert("获取网站向导信息失败");
	        }
        });*/
    };


    //点击现在就去
    $scope.clickToGo = function(item){
        $('#guide-modal').modal('hide');
        //与左侧菜单联动
        //$scope.clickToGoWithMenu(item);
        var currentUrlMenu = $("li[definedattr ='" + item.keyLink + "']");
        //未点击过任何一级菜单或一级菜单不是当前导航链接对应的一级菜单,同时处理非网站导航展开的一级菜单
        if($scope.menuRootHtml == null && currentUrlMenu.parent().parent()[0].className.indexOf("active") == -1){
            $scope.menuRootHtml = currentUrlMenu.parent().prev()[0];
            currentUrlMenu.parent().prev().click();
        }else if ($scope.menuRootHtml = null && $scope.menuRootHtml != currentUrlMenu.parent().prev()[0] && currentUrlMenu.parent().parent()[0].className.indexOf("active") == -1){
            $scope.menuRootHtml = currentUrlMenu.parent().prev()[0];
            currentUrlMenu.parent().prev().click();
        }


        $scope.clickMenuURL(item.keyLink);

        //更新现在就去的状态
        if(item.configKey != null && "" != item.configKey && !item.linkStauts){
            $http({
                url : 'guide/updateSettingStatus.do',
                method : 'POST',
                dataType : "json",
                params : {
                    configKey : item.configKey
                }
            }).success(function(result){
                if("0" == result.code){
                    item.linkStauts = true;
                }
            });
        }
    };

    //修改下次不再显示向导的勾选状态
    $scope.changeNoShowStatus = function(){
        $http({
            url : 'guide/updateShowGuideStatus.do',
            method : 'POST',
            dataType : "json",
            params : {
                isNoShowBackGuide : !$scope.isNoShowBackGuide
            }
        }).success(function(result){
            if("0" == result.code){
                //如果原来是勾选的 那么现在为不勾选
                if($scope.isNoShowBackGuide){
                    $('#isNoShowBackGuide').removeClass('glyphicon-check').addClass('glyphicon-unchecked');
                    $scope.isNoShowBackGuide = false;
                }
                //原来是不勾选 现在则为勾选
                else{
                    $('#isNoShowBackGuide').removeClass('glyphicon-unchecked').addClass('glyphicon-check');
                    $scope.isNoShowBackGuide = true;
                }
            }
        });
    };

    //点击上一步
    $scope.clickPrev = function(index){
        $('.guide-tab a:eq(' + (index - 1) + ')').tab('show');
    };

    //点击下一步
    $scope.clickNext = function(index){
        $('.guide-tab a:eq(' + (index + 1) + ')').tab('show');
    };
}]);