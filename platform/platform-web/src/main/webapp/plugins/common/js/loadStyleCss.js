function loadCssJS(url, success){
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
loadJS('/plugins/jquery/jquery.min.js', function (){
	//动态加载style.css
	$.ajax({
		type : "post",
		url : "/getIndexCms.do",
		async:false,
		dataType: 'json',
		success : function(data) {
			var companyId = data.data.companyId;
			if(companyId == 5){
				/**注册当前项目css*/
				document.write('<link href="/plugins/common/css/zhuGe/style.css' + '" rel="stylesheet" type="text/css" />');
			}else{
				/**注册当前项目css*/
				document.write('<link href="/plugins/common/css/style.css' + '" rel="stylesheet" type="text/css" />');
			}
		}
	});	
});