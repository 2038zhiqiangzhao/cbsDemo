app.controller('registerController', ["$scope", "$http", "$stateParams", "userUtil", "loginService","registerService", "encryptService",
		function($scope, $http, $stateParams, userUtil, loginService,registerService, encryptService) {
	 var pword="";//存放明文
	 var encryptPassword=""; //存放密文
	 var fEncryptFlag=0;//前端加密算法启用开关

	 
	 $scope.init = function () {
		 
		 	$scope.user = {};
		 	
		 	encryptService.queryEncryptInfo($scope.user,function (result){
	            // $scope.disabled = false;
	        
	            var rstCode = result.code;
	            if(-1==rstCode){//获取加密算法信息失败
	            	
	            }else if(0==rstCode){//成功返回
	            	
	            	var data=result.data;
	            	//$scope.user.oldFSalt=data.oldFSalt;
	            	//$scope.user.oldFVersion=data.oldFVersion;
	            	$scope.user.newFSalt=data.newFSalt;
	            	$scope.user.newFVersion=data.newFVersion;  
	            	fEncryptFlag=data.fEncryptFlag||0;
	            }
	            
	         
	 
	        },function (error){
	        	 $.MsgBox.Alert("提示", "系统错误！");
			});
	 }
	
	$scope.registerCheck = function() {
		var minLength, maxLength;
		/*if (typeof $scope.submitted == 'undefined') {
			return false;
		}*/
		var flag = true;
		if (typeof $scope.user == 'undefined') {
			$scope.user = {};
		}
		if (typeof $scope.user.username == 'undefined' || $scope.user.username == '') {
			$scope.username_error = '请输入用户名';
			flag = false;
		} else {
			if ($scope.user.username.indexOf(' ') >= 0) {
				$scope.username_error = '用户名不能包含空格';
				flag = false;
			} else {
				minLength = 6, maxLength = 20;
				if ($scope.user.username.trim().length < minLength || $scope.user.username.trim().length > maxLength) {
					$scope.username_error = '用户名的长度是' + minLength + '-' + maxLength + '个字符';
					flag = false;
				} else {
					$scope.username_error = '';
				}
			}
		}
		if (typeof $scope.user.password == 'undefined' || $scope.user.password == '') {
			$scope.password_error = '请输入密码';
			flag = false;
		} else {
			minLength = 6, maxLength = 20;
			if ($scope.user.password.trim().length < minLength || $scope.user.password.trim().length > maxLength) {
				$scope.password_error = '密码的长度是' + minLength + '-' + maxLength + '个字符';
				flag = false;
			} else {
				$scope.password_error = '';
			}
		}
		if (typeof $scope.user.confirmPassword == 'undefined' || $scope.user.confirmPassword == '') {
			$scope.confirmPassword_error = '请输入确认密码';
			flag = false;
		} else {
			if ($scope.user.password != $scope.user.confirmPassword) {
				$scope.confirmPassword_error = '两次输入的密码不相同';
				flag = false;
			} else {
				$scope.confirmPassword_error = '';
			}
		}
		if (typeof $scope.user.companyName == 'undefined' || $scope.user.companyName == '') {
			$scope.companyName_error = '请输入“企业营业执照”中的“名称”';
			flag = false;
		} else {
			minLength = 4, maxLength = 40;
			if ($scope.user.companyName.trim().length < minLength || $scope.user.companyName.trim().length > maxLength) {
				$scope.companyName_error = '公司名称的长度是' + minLength + '-' + maxLength + '个字符';
				flag = false;
			} else {
				$scope.companyName_error = '';
			}
		}
		if (typeof $scope.user.contactPerson == 'undefined' || $scope.user.contactPerson == '') {
			$scope.contactPerson_error = '请输入联系人姓名';
			flag = false;
		} else {
			minLength = 2, maxLength = 20;
			if ($scope.user.contactPerson.trim().length < minLength || $scope.user.contactPerson.trim().length > maxLength) {
				$scope.contactPerson_error = '联系人姓名的长度是' + minLength + '-' + maxLength + '个字符';
				flag = false;
			} else {
				$scope.contactPerson_error = '';
			}
		}
		if (typeof $scope.user.mobile == 'undefined' || $scope.user.mobile == '') {
			$scope.mobile_error = '请输入手机号码';
			flag = false;
		} else {
			minLength = 11, maxLength = 11;
			if ($scope.user.mobile.trim().length < minLength || $scope.user.mobile.trim().length > maxLength) {
				$scope.mobile_error = '手机号码的长度是' + minLength + '个字符';
				flag = false;
			} else {
				$scope.mobile_error = '';
			}
		}
		if (typeof $scope.user.smsCode == 'undefined' || $scope.user.smsCode == '') {
			$scope.smsCode_error = '请输入短信验证码';
			flag = false;
		} else {
			minLength = 6, maxLength = 6;
			if ($scope.user.smsCode.trim().length < minLength || $scope.user.smsCode.trim().length > maxLength) {
				$scope.smsCode_error = '短信验证码的长度是' + minLength + '个字符';
				flag = false;
			} else {
				$scope.smsCode_error = '';
			}
		}
		if (typeof $scope.user.email == 'undefined' || $scope.user.email == '') {
			$scope.email_error = '请输入联系人邮箱';
			flag = false;
		} else {
			if (!verifyRegularExpression($scope.user.email, 'email')) {
				$scope.email_error = '联系人邮箱格式错误';
				flag = false;
			} else {
				maxLength = 100;
				if ($scope.user.email.trim().length > maxLength) {
					$scope.email_error = '联系人邮箱长度必须小于等于' + maxLength + '个字符';
					flag = false;
				} else {
					$scope.email_error = '';
				}
			}
		}
		return flag;
	}
	
	$scope.userNameCheck = function() {
		var flag = true;
		if (typeof $scope.user == 'undefined') {
			$scope.user = {};
		}
		if (typeof $scope.user.username == 'undefined' || $scope.user.username == '') {
			$scope.username_error = '请输入用户名';
			flag = false;
		} else {
			if ($scope.user.username.indexOf(' ') >= 0) {
				$scope.username_error = '用户名不能包含空格';
				flag = false;
			} else {
				minLength = 6, maxLength = 20;
				if ($scope.user.username.trim().length < minLength || $scope.user.username.trim().length > maxLength) {
					$scope.username_error = '用户名的长度是' + minLength + '-' + maxLength + '个字符';
					flag = false;
				} else {
					$scope.username_error = '';
				}
			}
		}
		var userObj = new Object;
		userObj.username=$scope.user.username;
		userObj.userPlatformId=2;
		registerService.checkUserName(userObj,function (result){
					if (typeof result == 'undefined' || typeof result.code == 'undefined') {
						$scope.username_error = '用户名校验出错';
						flag=false;
						return flag;
					}
					var bizErrorCode = result.code;
					if (bizErrorCode == 0) {
						$scope.username_error = '';
						return flag;
					}
					switch (bizErrorCode) {
					case -1:
						$scope.username_error = '校验参数信息错误';
						flag=false;
						break;
					case -2:
						$scope.username_error = '用户名已存在';
						flag=false;
						break;
					default:
						$scope.username_error = '用户名校验出错';
						flag=false;
						break;
					}
				});
		return flag;
	}
	$scope.getCaptchas = function() {
		$scope.btndisabled = true;
		var maxTimeCount = 60;
		var timeCount = 0;
		var int = setInterval(function() {
			$scope.$apply(function() {
				$("#btn").text((maxTimeCount - timeCount) + '秒后重试');
				timeCount++;
				if (timeCount == maxTimeCount) {
					$("#btn").text('获取短信验证码');
					clearInterval(int);
					$scope.btndisabled = false;
				}
			});
		}, 1000);
		var captchasObj = new Object;
		captchasObj.mobile=$scope.user.mobile;
		captchasObj.userPlatformId=2;
		registerService.sendCaptchas(captchasObj,function (result){
					if (typeof result == 'undefined' || typeof result.code == 'undefined') {
						$scope.errorMessage = '系统错误';
						return;
					}
					var bizErrorCode = result.code;
					if (bizErrorCode == 0) {
						$scope.mobile_error = '短信验证码已发送，请查收';
						return;
					}
					switch (bizErrorCode) {
					case -1:
						$scope.mobile_error = '参数不完整';
						break;
					default:
						$scope.mobile_error = '手机号已存在';
						break;
					}
					$scope.disabled = false;
				});
	};
	
	$scope.confirmPasswordBlur=function(){
		if(typeof $scope.user.newFSalt != 'undefined' && $scope.user.username != ''){
			if(typeof $scope.user.newFVersion != 'undefined' && $scope.user.newFVersion != ''){
				if ($scope.user.password == $scope.user.confirmPassword) {//密码相同才加密
					if(pword!=$scope.user.password){
						pword=$scope.user.password;
						encryptPassword=passwordEncrypt.hexdigest($scope.user.password,$scope.user.newFSalt,$scope.user.newFVersion);
					}
				}
			}
		}
				
	};
	
	$scope.registerSubmit = function() {
		if (!$scope.registerCheck()) {
			return;
		}
		if (!$scope.userNameCheck()) {
			return;
		}
		if (!$scope.iagree) {
			$scope.showAgreement();
			return;
		}
		$scope.user.userPlatformId=2;
		
		if(""!=encryptPassword){//提交的是密文
			
			if(1==fEncryptFlag){//前端算法开关
				
				$scope.user.password=encryptPassword;
				$scope.user.confirmPassword=encryptPassword;
			}
			
		}
		
		$scope.disabled = true;
		registerService.register($scope.user,function (result){
			$scope.disabled = false;
			if (typeof result == 'undefined' || typeof result.code == 'undefined') {
				$scope.errorMessage = '系统错误';
				return;
			}
			var bizErrorCode = result.code;
			if (bizErrorCode == 0) {
				$scope.disabled = true;
				var maxTimeCount = 3;
				var timeCount = 0;
				$scope.errorMessage = '注册成功，' + (maxTimeCount - timeCount) + '秒后返回登录页面。';
//				location.href = '/login.do';
				
				var intervalProcess = $interval(
				  function () {
				  	timeCount++;
				  	// alert('timeCount = ' + timeCount + ', message = ' + $scope.errorMessage);
				  	if (timeCount == maxTimeCount) {
				  		if(angular.isDefined(intervalProcess)) {
		            		$interval.cancel(intervalProcess);
		            		intervalProcess = undefined;
		          		}
//				  		location.href = '/login.do';
          				$location.path('/login');
				  		return;
				  	} else {
				  		$scope.errorMessage = '注册成功，' + (maxTimeCount - timeCount) + '秒后返回登录页面。';
//				  		$scope.$apply();
				  	}
			    }, 1000);
				return;
			}
			switch(bizErrorCode) {
				case 1:
					$scope.errorMessage = '请输入用户名'
					break;
				case 2:
					$scope.errorMessage = '用户名长度错误';
					break;
				case 3:
					$scope.errorMessage = '请输入密码';
					break;
				case 4:
					$scope.errorMessage = '密码长度错误';
					break;
				case 5:
					$scope.errorMessage = '请输入公司名称';
					break;
				case 6:
					$scope.errorMessage = '公司名称长度错误';
					break;
				case 7:
					$scope.errorMessage = '请输入联系人姓名';
					break;
				case 8:
					$scope.errorMessage = '联系人姓名长度错误';
					break;
				case 9:
					$scope.errorMessage = '请输入手机号码';
					break;
				case 10:
					$scope.errorMessage = '手机号码长度错误';
					break;
				case 11:
					$scope.errorMessage = '请输入联系人邮箱';
					break;
				case 12:
					$scope.errorMessage = '联系人邮箱长度错误';
					break;
				case 13:
					$scope.errorMessage = '联系人邮箱格式错误';
					break;
				case 14:
					$scope.errorMessage = '用户所属平台不存在';
					break;
				case 15:
					$scope.errorMessage = '用户所属平台不存在';
					break;
				case 16:
					$scope.errorMessage = '该用户已注册';
					break;
				case 17:
					$scope.errorMessage = '用户没有找到对应平台组';
					break;
				case 18:
					$scope.errorMessage = '验证码错误';
					break;
				default:
					$scope.errorMessage = '';
					break;
			}
		},function (error){
			 $.MsgBox.Alert("提示", "系统错误！");
		});
	};
	$scope.showAgreement = function(func) {
		$('#agreementDialog').off('hidden.bs.modal');
		$('#agreementDialog').on('hidden.bs.modal', function () {
		  if ($scope.iagree) {
		  	$('#agreeCheckbox').prop('checked', true);
		  } else {
		  	$('#agreeCheckbox').prop('checked', false);
		  }
		});
		$('#agreementDialog').modal('show');
	}
}]);