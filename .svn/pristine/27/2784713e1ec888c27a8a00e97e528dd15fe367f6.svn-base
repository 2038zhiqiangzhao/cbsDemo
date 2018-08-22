app.controller('loginController', ["$scope", "$http", "$stateParams", "userUtil", "loginService", "encryptService",
		function($scope, $http, $stateParams, userUtil, loginService, encryptService) {
	var name="";
	
	var pword="";
	
	var EncryptPassword=0;
	
	var fEncryptFlag=0;//前端加密算法启用开关
	
    $scope.init = function () {
        $scope.user = {};
    }
    $scope.qqLogin = function(){
        //获取登录参数
        var param = {};
        param.gateway = 1;//1、QQ,2、微信
        param.redirectUrl = "/unionlogin.html";
        userUtil.postRequest("unionLogin/getRelatedParams",param,function(result){
            if(result.code==0){
                window.open(result.data);
            }
        });
    };
    $scope.wxLogin = function(){
        var param = {};
        param.gateway = 2;//1、QQ,2、微信
        param.redirectUrl = "/unionlogin.html";
        userUtil.postRequest("unionLogin/getRelatedParams",param,function(result){
            if(result.code==0){
                //window.open("https://open.weixin.qq.com/connect/qrconnect?appid=" +result.data.appid
                //+"&redirect_uri=" + result.data.redirect_uri
                //+"&response_type=code&scope=snsapi_login"
                //+"&state=" + encodeURIComponent(result.data.state) + "#wechat_redirect");
                window.open(result.data);
            }
        });
    }
    $scope.usernameBlur=function(){//username失去焦点
    	if(typeof $scope.user.username!='undefined'&&$scope.user.username!=''){
    		if(name!=$scope.user.username){
    			$scope.disabled = true;
    			encryptService.queryEncryptInfo($scope.user,function (result){
                	name= $scope.user.username;
                    if (typeof result == 'undefined' || typeof result.code == 'undefined') {
                        $scope.errorMessage = '系统错误';
                        return;
                    }
                    var rstCode = result.code;
                    if(-1==rstCode){//获取加密算法信息失败
                    	
                    }else if(0==rstCode){//成功返回
                    	
                    	var data=result.data;
                    	$scope.user.oldFSalt=data.oldFSalt;
                    	$scope.user.oldFVersion=data.oldFVersion;
                    	$scope.user.newFSalt=data.newFSalt;
                    	$scope.user.newFVersion=data.newFVersion;
                    	
                    	fEncryptFlag=data.fEncryptFlag||0;
                    	
                    	if (typeof $scope.user.password != 'undefined' && $scope.user.password != '') {
                    		pword=$scope.user.password;
                    		if($scope.user.oldFSalt!=null&&$scope.user.oldFVersion!=null){
                    			EncryptPassword=passwordEncrypt.hexdigest($scope.user.password,$scope.user.oldFSalt,$scope.user.oldFVersion);
                    		}else{
                    			EncryptPassword=$scope.user.password;
                    		}
                    		
                    		if($scope.user.newFSalt!=null||$scope.user.newFVersion!=null){
                    			var salt=(null!=$scope.user.newFSalt)?$scope.user.newFSalt:$scope.user.oldFSalt;
                    			var version=(null!=$scope.user.newFVersion)?$scope.user.newFVersion:$scope.user.oldFVersion;
                    			
                    			if(1==fEncryptFlag){
                    				$scope.user.newEncryptPassword=passwordEncrypt.hexdigest($scope.user.password,salt,version);
                    			}
                    			
                    		}
                    	}
                    	
                    	
                    }
                    
                    $scope.disabled = false;
         
                },function (error){
                	 $.MsgBox.Alert("提示", "系统错误！");
        		});
    		}
    	
    	}
    }
    
    
    $scope.loginCheck = function () {
        if (typeof $scope.submitted == 'undefined') {
            return false;
        }
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
    }
    
    $scope.passwordBlur=function(){//密码失去焦点
    	if(typeof $scope.user.password!='undefined'&&$scope.user.password!=''){
    		if(pword!=$scope.user.password){   			
    			$scope.disabled = true;	
    			pword=$scope.user.password;

    			if($scope.user.oldFSalt!=null&&$scope.user.oldFVersion!=null){
        			EncryptPassword=passwordEncrypt.hexdigest($scope.user.password,$scope.user.oldFSalt,$scope.user.oldFVersion);
        		}else{
        			EncryptPassword=$scope.user.password;
        		}			

        		if($scope.user.newFSalt!=null||$scope.user.newFVersion!=null){
        			var salt=(null!=$scope.user.newFSalt)?$scope.user.newFSalt:$scope.user.oldFSalt;
        			var version=(null!=$scope.user.newFVersion)?$scope.user.newFVersion:$scope.user.oldFVersion;
        			
        			if(1==fEncryptFlag){
        				$scope.user.newEncryptPassword=passwordEncrypt.hexdigest($scope.user.password,salt,version);	
        			}
        			
        		}
        		$scope.disabled =false;
    		}
    	}
    }
    
    $scope.loginSubmit = function () {
        if (!$scope.loginCheck()) {
            return;
        }
//		$location.path('/merchant');
//		return;
        
        
        if(EncryptPassword!=null&&EncryptPassword!=''){ 
        	
        	if(1==fEncryptFlag){//前端算法开关
            	$scope.user.password=EncryptPassword;
        	}
        }
        
        $scope.disabled = true;
        
        
        //$scope.user.userPlatformId=1;
        loginService.login($scope.user,function (result){
            $scope.disabled = false;
            
            name="";
        	pword="";
        	EncryptPassword="";
        	
            if (typeof result == 'undefined' || typeof result.code == 'undefined') {
                $scope.errorMessage = '系统错误';
                return;
            }
            var bizErrorCode = result.code;

            if (bizErrorCode == 0) {
//				$location.path('/merchant');
                window.location.href = "home.html";
                return;
            }
            if (bizErrorCode == 1) {
            	$scope.user.password="";
                $scope.errorMessage = '请输入用户名';
                return;
            }
            if (bizErrorCode == 2) {
            	$scope.user.password="";
                $scope.errorMessage = '请输入密码';
                return;
            }
            if (bizErrorCode == 3) {
            	$scope.user.password="";
                $scope.errorMessage = '用户名或者密码错误';
                return;
            }
            if (bizErrorCode == 4) {
            	$scope.user.password="";
                $scope.errorMessage = '账号未注册';
                return;
            }
            if (bizErrorCode == 5) {
            	$scope.user.password="";
                $scope.errorMessage = '网络异常';
                return;
            }
            if (bizErrorCode == 6) {
            	$scope.user.password="";
                $scope.errorMessage = '用户已冻结';
                return;
            }
            if (bizErrorCode == 7) {
            	$scope.user.password="";
                $scope.errorMessage = '抱歉，该账号无权限登录此系统，请注册诸葛服务商账号，详情可联系：4008654321';
                return;
            }
            if (bizErrorCode == 8) {
            	$scope.user.password="";
                $scope.errorMessage = '抱歉，该账号无权限登录此系统，详情可联系：4008654321';
                return;
            }
        },function (error){
        	name="";
        	pword="";
        	EncryptPassword="";
        	$scope.user.password="";      	
            $scope.disabled = false;
            $.MsgBox.Alert("提示", "系统错误！");
		});
    };
}]);
//$(function(){
////联合登录
//
//})