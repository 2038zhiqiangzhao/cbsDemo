 app.controller("userInfoController", ["$scope", "$http", "$stateParams", "userUtil", "userInfoService",function($scope, $http, $stateParams, userUtil, orgService) {
      
	 var GET_CURRENT_USER_INFO='userManage/getUserInfo';
	 var SAVE_EDIT_PASSWORD='user/updatePassWord';
	 var SEND_EMAIL_CODE="userManage/sendEmailCode";
	 var SAVE_EDIT_EMAIL="userManage/saveEditEmail";
	 
	 var SEND_MOBILE_CODE="userManage/sendMobileCaptchas";
	 var CHECK_MOBILE_CAPTCHAS="userManage/checkMobileCaptchas";
	 
	 
	 $scope.email_error_tip=false;
	 $scope.code_error_tip=false;
	 $scope.email2_error_tip=false;
	 $scope.captchas_error_tip=false;
	 $scope.checkMobile_error_tip=false;
	 $scope.password_error_tip =false;
	 $scope.password1_error_tip =false;
	 $scope.password2_error_tip =false;
	 
	 
	 $scope.userInfo={};
	 $scope.editPassword={};
	 $scope.editEmail={};
	 $scope.checkCaptchas={};
	 
	 var param={};
	 
	 userUtil.postRequest(GET_CURRENT_USER_INFO, param, function(result){
		 if (result.code == 0) {// 获取数据成功
			 $scope.userInfo=result.data;
		 }else{
			 $.MsgBox.Alert("提示", "获取用户信息失败");
		 }
	 });
	 
	//查询用户信息
	$scope.query = function(){
		
		userUtil.postRequest(GET_CURRENT_USER_INFO, param, function(result){
			 if (result.code == 0) {// 获取数据成功
				 $scope.userInfo=result.data;
			 }else{
				 $.MsgBox.Alert("提示", "获取用户信息失败");
			 }
		 });
	}
	
	 //清空上次修改密码缓存信息
	 $scope.cleanEditPasswordMessage=function(){
		 $scope.editPassword.password='';
		 $scope.editPassword.password1='';
		 $scope.editPassword.password2='';
	 }
	 // 保存修改密码
	 $scope.saveEditPassword=function(){
		 
		 if(null==$scope.editPassword.password||''==null==$scope.editPassword.password){
			 $scope.password_error_tip=true;
			 $scope.password_error='原密码不能为空';
			 return ;
		 }
		 $scope.password_error_tip=false;
		 if(null==$scope.editPassword.password1||''==null==$scope.editPassword.password1){
			 $scope.password1_error_tip=true;
			 $scope.password1_error='新密码不能为空';
			 return;
		 }else{
			 
			 var reg2=/^[\da-zA-Z]{6,18}$/;
			 var r2 = reg2.test($scope.editPassword.password1);
			 if(r2==false){
				$scope.password1_error_tip=true;
				$scope.password1_error="密码需为6位-18位，包含字母或数字";
				return;
			}
			 
			if(null==$scope.editPassword.password2||''==null==$scope.editPassword.password2){
				 $scope.password2_error_tip=true;
				 $scope.password2_error='确认新密码不能为空';
				 return;
			}else{
				if($scope.editPassword.password1!=$scope.editPassword.password2){
					 $scope.password2_error_tip=true;
					 $scope.password2_error='确认新密码不匹配';
					 return;
				}
			}
		 }
		 $scope.password1_error_tip=false;
		 $scope.password2_error_tip=false;
		 		 
		 userUtil.postRequest(SAVE_EDIT_PASSWORD, $scope.editPassword, function(result){
			 if (result.code == 0) {// 获取数据成功
				 var code = result.data;
				 if(code == 0){
					 $.MsgBox.Alert("提示","密码修改成功");
					 
					 $('#formT').modal('hide');
				 }else if(code == 1){
					 $.MsgBox.Alert("提示","两次输入的密码不一致");
				 }else if(code == 2){
					 $.MsgBox.Alert("提示","原密码不正确");
				 }else if(code == 3){
					 $.MsgBox.Alert("提示","新密码不能为空");
				 }else if(code == 4){
					 $.MsgBox.Alert("提示","原密码不能为空");
				 }
			 }else{
				 $.MsgBox.Alert("提示", "用户密码修改异常");
			 }
		 });
				
	 }
	 
	 // 发送邮件验证码
	 $scope.sendEmailCode=function(){
		 if($scope.editEmail.email!=null && $scope.editEmail.email!=''){
			 var reg2=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
				var r2=$scope.editEmail.email.match(reg2);
				if(r2==null){
					$scope.email_error_tip=true;
					$scope.email_error="邮箱格式不正确";
					return;
				}
		 }else {
			 	$scope.email_error_tip=true;
				$scope.email_error="请先填写旧邮箱";
				return;
		 }
		 $scope.email_error_tip=false;
		 $scope.sendEmailCodeDisabled=true;
		 userUtil.postRequest(SEND_EMAIL_CODE, $scope.editEmail, function(result){
			 $scope.sendEmailCodeDisabled=false;
			 if (result.code == 0) {// 获取数据成功
				 var data=result.data;
				 if(0==data){
					 $.MsgBox.Alert("提示", "邮件验证码发送成功");
				 }else if(-1==data){
					 $scope.email_error_tip=true;
					 $scope.email_error="旧邮箱不正确";
				 }
			 }else{
				 $.MsgBox.Alert("提示", "邮件验证码发送失败");
			 }
		 });
	 }
	 //清除上次操作遗留的邮箱数据缓存
	 $scope.cleanEmail = function(){
		 $scope.editEmail={};
		 $scope.editEmail.email='';
		 $scope.editEmail.emailCode='';
		 $scope.editEmail.email2='';
	 }
	 
	 // 保存邮件修改
	 $scope.saveEditEmail=function(){
		 if($scope.userInfo.email!=null && $scope.userInfo.email!=''){// 有旧邮箱,需要验证验证码
			 if($scope.editEmail.emailCode==null||$scope.editEmail.emailCode==''){
				 $scope.code_error_tip=true;
				 $scope.code_error="请输入邮件验证码";
				 return;
			 }
		 }
		 $scope.code_error_tip=false;
		 
		 
		 if($scope.editEmail.email2!=null && $scope.editEmail.email2!=''){
			 var reg2=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
				var r2=$scope.editEmail.email2.match(reg2);
				if(r2==null){
					$scope.email2_error_tip=true;
					$scope.email2_error="邮箱格式不正确";
					return;
				}
		 }else {
			 	$scope.email2_error_tip=true;
				$scope.email2_error="请先填写新邮箱";
				return;
		 }
		 $scope.email2_error_tip=false;
		 
		 
		 userUtil.postRequest(SAVE_EDIT_EMAIL, $scope.editEmail, function(result){
			 if (result.code == 0) {// 获取数据成功
				 var data=result.data;
				 if(0==data){
					 $.MsgBox.Alert("提示", "邮箱修改成功"); 
					 $('#form').modal('hide');
					 $scope.query();
				 }else if(-1==data){
					 $scope.code_error_tip=true;
					 $scope.code_error="验证码错误";
					 return;
				 }else if(-2==data){
					 $scope.email2_error_tip=true;
					 $scope.email2_error="该邮箱已存在!";
					 return;
				 }
			 }else{
				 $.MsgBox.Alert("提示", "邮箱修改失败");
			 }
		 });
	 }
	 
	 $scope.sendMobileCode=function(event){
		 var interval = 60;//重试时间间隔
		 
		 userUtil.postRequest(SEND_MOBILE_CODE, {}, function(result){
			 if (result.code == 0) {// 获取数据成功
				 $.MsgBox.Alert("提示", "验证码发送成功");
			 }else{
				 $scope.captchas_error_tip=true;
				 $scope.captchas_error=result.message;
				 //$.MsgBox.Alert("提示", result.message);
			 }
		 });
		 
		//设置手机验证码重新获取倒计时
		var obj = event.currentTarget;
		var seconds = interval;
		$('#sendBtnText').html(seconds + '秒后重新获取');
		//obj.disabled = true;
		$scope.sendMobileCodeDisabled=true;
		var inter = setInterval(function () {
			seconds--;
			$('#sendBtnText').html(seconds + '秒后重新获取');
			if(seconds == 0) {
				//$scope.sendMobileCodeDisabled=false;
				obj.disabled=false;
				$('#sendBtnText').html('获取短信验证码');
				clearInterval(inter);
			}
		}, 1000);
	 }
	 
	//清除上次操作遗留的手机号数据缓存
	 $scope.cleanPhone = function(){
		 $scope.checkCaptchas={};
		 $scope.checkCaptchas.captchas='';
		 $scope.checkCaptchas.mobile='';
	 }
	 
	 $scope.checkMobileCaptchas=function(){
		 if($scope.userInfo.mobile2!=null && $scope.userInfo.mobile2!=''){
			 if($scope.checkCaptchas.captchas==''||$scope.checkCaptchas.captchas==null){
				 $scope.captchas_error_tip=true;
				 $scope.captchas_error="请输入手机验证码"; 
				 return;
			 } 
			 $scope.captchas_error_tip=false;
		 }	
				 
		 if($scope.checkCaptchas.mobile==''||$scope.checkCaptchas.mobile==null){
			 $scope.checkMobile_error_tip=true;
			 $scope.checkMobile_error="请输入新手机"; 
			 return;
		 }
		 $scope.checkMobile_error_tip=false;
		 
		 userUtil.postRequest(CHECK_MOBILE_CAPTCHAS, $scope.checkCaptchas,function(result){
			 if (result.code == 0) {// 获取数据成功			
				 $.MsgBox.Alert("提示", "手机号修改成功");
				 $('#phone').modal('hide');
				 $scope.query();
			 }else{
				 $scope.checkMobile_error_tip=true;
				 $scope.checkMobile_error=result.errorMessage;
			 }
		 });
	 }
			
     }]);