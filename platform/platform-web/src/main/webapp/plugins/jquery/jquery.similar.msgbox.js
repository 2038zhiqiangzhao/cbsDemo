document.write('<link href="/plugins/bootstrap-dialog/css/bootstrap-dialog.min.css' + '" rel="stylesheet" type="text/css" />');

function loadJS(url, success){
	var domScript = document.createElement('script');
	domScript.src = url;
	success = success || function(){};
	domScript.onload = domScript.onreadystatechange = function() {
		if (!this.readyState || 'loaded' === this.readyState || 'complete' === this.readyState) {
			if($.isFunction(success)){
				success();
			}
		    this.onload = this.onreadystatechange = null;
		    this.parentNode.removeChild(this);
		}
	};
	document.getElementsByTagName('head')[0].appendChild(domScript);
}


//执行加载外部 JS 文件
loadJS('/plugins/bootstrap-dialog/js/bootstrap-dialog.min.js', function (){
	/**
	 * 消息框初始化类型
	 */
	var msgType = {
		alert : "alert",
		confirm : "Confirm",
		init : "init"
	};
	
	/**
	 * 消息框调用方法
	 */
	$.MsgBox = {
		Alert : function(title, msg, callback) {
			if(BootstrapDialog){
				BootstrapDialog.show({
					title: title,
					message: msg,
					buttons: [{
						label: '确定',
						action: function(dialog) {
							dialog.close();
							if($.isFunction(callback)){
								callback(dialog);
							}
						}
					}]
				});
			}
		},
		Confirm : function(title, msg, callback, callBackNo) {
			if(BootstrapDialog){
				BootstrapDialog.show({
					title: title,
					message: msg,
					buttons: [{
						label: '确定',
						action: function(dialog) {
							dialog.close();
							if($.isFunction(callback)){
								callback(dialog);
							}
						}
					},{
						label: '取消',
						action: function(dialog) {
							dialog.close();
							if($.isFunction(callBackNo)){
								callBackNo(dialog);
							}
						}
					}]
				});
			}
		}
	};
});
