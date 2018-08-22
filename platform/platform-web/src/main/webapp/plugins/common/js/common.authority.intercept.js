/**
 * 权限拦截服务
 */
angular.module('authorityInterceptApp', []).
factory('ouser', ['$http',
  function($http) {
	var globalFunctions = "";
    function OuserFunction() {
    	$http.post('/ouser-web/function/function.do').success(function(result) {
    		if(result.code ==0){
				globalFunctions = result.data;
			}else {
					return "0";
			}
			if(result.code.indexOf(CommonConsta.getConstant("NOT_AUTHORITY")) > 0){
				// 没有权限
				return "0";
			} else if (result.code.indexOf(CommonConsta.getConstant("SESSION_OVERDUE")) > 0 ){
				// session过期
				window.location="/index.html";
			}
		});
    }
    OuserFunction.prototype.checkFunction = function (code) {
    	if(globalFunctions == null || globalFunctions == ''){
			return false;
		}
        if(globalFunctions.indexOf(","+code+",") > -1){
			return true;
		}else{
			return false;
		}
    };
    return new OuserFunction();
 }
 ]);