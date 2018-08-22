app.controller("userPasswordController", ["$scope", "$http", "$stateParams", "userUtil", "userService","encryptService",
                                   		function($scope, $http, $stateParams, userUtil, userService, encryptService) {
	var pword="";
	var pword2="";
	var EncryptPassword="";
	var EncryptPassword2="";
	
	var fEncryptFlag=0;//前端加密算法启用开关
	
	
	var reg=/^[A-Za-z0-9]{6,18}$/;//密码正则
	
	$scope.user = {};
	
    $scope.init = function () {
    	encryptService.queryEncryptInfoById($scope.user,function (result){
            var rstCode = result.code;
            if(-1==rstCode){//获取加密算法信息失败
            	
            }else if(0==rstCode){//成功返回
            	
            	var data=result.data;
            	$scope.user.oldFSalt=data.oldFSalt;
            	$scope.user.oldFVersion=data.oldFVersion;
            	$scope.user.newFSalt=data.newFSalt;
            	$scope.user.newFVersion=data.newFVersion;   
            	
            	fEncryptFlag=data.fEncryptFlag||0;
            }
 
        })
    }
    
    $scope.passwordBlur=function(){
   
    	if(pword!=$scope.user.password){
    		pword=$scope.user.password;
    		if(typeof $scope.user.oldFSalt!='undefined'&&$scope.user.oldFSalt!=''){
    			if(typeof $scope.user.oldFVersion!='undefined'&&$scope.user.oldFVersion!=''){
    				EncryptPassword=passwordEncrypt.hexdigest($scope.user.password,$scope.user.oldFSalt,$scope.user.oldFVersion);
    			}
    		}
    	}
    }
    
    $scope.password2Blur=function(){
    	if(pword2!=$scope.user.password2){
    		pword2!=$scope.user.password2;
    		var salt=($scope.user.newFSalt!='undefined'&&null!=$scope.user.newFSalt)?$scope.user.newFSalt:$scope.user.oldFSalt;
			var version=($scope.user.newFVersion!='undefined'&&null!=$scope.user.newFVersion)?$scope.user.newFVersion:$scope.user.oldFVersion;
			EncryptPassword2=passwordEncrypt.hexdigest($scope.user.password2,salt,version);
    	}
    }
       
    $scope.passwordCheck=function(){
    	var flag = true;
    	if(typeof $scope.user.password == 'undefined' || $scope.user.password == ''){
    		$scope.password_error = '请输入原密码';
    		flag=false;
    	}else{
    		var r2=$scope.user.password.match(reg);
    		if(r2==null){
    			$scope.password_error ='密码是6-18个字符,字母跟数字组合';
    			flag=false;
    		}else $scope.password_error = '';
    	}
    	return flag;
    }
    
    
    $scope.password1Check=function(){
    	var flag = true;
    	if(typeof $scope.user.password1 == 'undefined' || $scope.user.password1 == ''){
    		$scope.password1_error = '请输入新密码';
    		flag=false;
    	}else{
    		if($scope.user.password1 == $scope.user.password){
    			$scope.password1_error ='新密码不能与原密码相同';
    			flag=false;
    		}else{
    			var r2=$scope.user.password1.match(reg);
        		if(r2==null){
        			$scope.password1_error ='密码是6-18个字符,字母跟数字组合';
        			flag=false;
        		}else $scope.password1_error = '';
    		}
    		
    	}
    	return flag;
    }
    
    
    $scope.password2Check=function(){
    	var flag = true;
    	if(typeof $scope.user.password2 == 'undefined' || $scope.user.password2 == ''){
    		$scope.password2_error = '请输入确认新密码';
    		flag=false;
    	}else{
    		if($scope.user.password1 != $scope.user.password2){
    			$scope.password2_error = '两次输入的密码不一致';
    			flag=false;
    		}else{
    			var r2=$scope.user.password2.match(reg);
        		if(r2==null){
        			$scope.password2_error = '密码是6-18个字符,字母跟数字组合';
        			flag=false;
        		}else $scope.password2_error = '';
    		}		
    	}
    	
    	return flag;
    }
    
    
    $scope.modifyPasswordCheck=function(){
    	var flag = true;
    	
    	if(typeof $scope.user.password == 'undefined' || $scope.user.password == ''){
    		$scope.password_error = '请输入原密码';
    		flag=false;
    	}else{
    		var r2=$scope.user.password.match(reg);
    		if(r2==null){
    			$scope.password_error ='密码是6-18个字符,字母跟数字组合';
    			flag=false;
    		}else $scope.password_error = '';
    	}
    	
    	
    	if(typeof $scope.user.password1 == 'undefined' || $scope.user.password1 == ''){
    		$scope.password1_error = '请输入新密码';
    		flag=false;
    	}else{
    		var r2=$scope.user.password1.match(reg);
    		if(r2==null){
    			$scope.password1_error ='密码是6-18个字符,字母跟数字组合';
    			flag=false;
    		}else $scope.password1_error = '';
    	}
    	   	   	
    	if(typeof $scope.user.password2 == 'undefined' || $scope.user.password2 == ''){
    		$scope.password2_error = '请输入确认新密码';
    		flag=false;
    	}else{
    		if($scope.user.password1 != $scope.user.password2){
    			$scope.password2_error = '两次输入的密码不一致';
    			flag=false;
    		}else{
    			var r2=$scope.user.password2.match(reg);
        		if(r2==null){
        			$scope.password2_error = '密码是6-18个字符,字母跟数字组合';
        			flag=false;
        		}else $scope.password2_error = '';
    		}		
    	}
    	
    	return flag;
    }
    
	//保存
	$scope.save = function(){
		if (!$scope.passwordCheck()) {
			return;
		}
		if (!$scope.password1Check()) {
			return;
		}
		if (!$scope.password2Check()) {
			return;
		}
		if(""!=EncryptPassword){
			
			if(1==fEncryptFlag){
				$scope.user.password=EncryptPassword;
			}
		}
		if(""!=EncryptPassword2){
			
			if(1==fEncryptFlag){
				$scope.user.password1=EncryptPassword2;
				$scope.user.password2=EncryptPassword2;			
			}
		}
		userService.updateUserPassword($scope.user,function (result){
				if(result.code == 0){//获取数据成功
					var code = result.data;
					if(code == 0){
						//alert("密码修改成功");
						$.MsgBox.Alert("提示","密码修改成功");
					}else if(code == 1){
						//alert("两次输入的密码不一致");
						$.MsgBox.Alert("提示","两次输入的密码不一致");
					}else if(code == 2){
						//alert("原密码不正确");
						$.MsgBox.Alert("提示","原密码不正确");
					}else if(code == 3){
						//alert("新密码不能为空");
						$.MsgBox.Alert("提示","新密码不能为空");
					}else if(code == 4){
						//alert("原密码不能为空");
						$.MsgBox.Alert("提示","原密码不能为空");
					}
				}
			})
	}
}])