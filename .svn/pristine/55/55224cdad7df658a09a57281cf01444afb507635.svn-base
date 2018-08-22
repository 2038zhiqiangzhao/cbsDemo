/*
 * 给js Date增加日期格式化功能
 * 例子：
 * (new Date()).Format('yyyy-MM-dd hh:mm:ss.S')    ==> 2000-01-01 01:00:00.001
 * (new Date()).Format('yyyy-MM-dd hh:mm:ss')      ==> 2000-01-01 01:00:00
 * (new Date()).Format('yyyy-M-d h:m:s.S')         ==> 2000-1-1 1:0:0.001
 */
Date.prototype.format = function(fmt) {
	var o = {
		'M+' : this.getMonth() + 1 // 月份(1-2占位符)
		,
		'd+' : this.getDate() // 日(1-2占位符)
		,
		'h+' : this.getHours() // 小时(1-2占位符)
		,
		'm+' : this.getMinutes() // 分(1-2占位符)
		,
		's+' : this.getSeconds() // 秒(1-2占位符)
		,
		'q+' : Math.floor((this.getMonth() + 3) / 3) // 季度(1-2占位符)
		,
		'S' : this.getMilliseconds()
	// 毫秒(1个占位符，1-3位数字)
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp('(' + k + ')').test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)));
		}
	}
	return fmt;
};
Date.prototype.defaultDateTimeFormat = function() {
	return this.format('yyyy-MM-dd hh:mm:ss');
};
Date.prototype.defaultDateFormat = function() {
	return this.format('yyyy-MM-dd');
};
Date.prototype.defaultTimeFormat = function() {
	return this.format('hh:mm:ss');
};
Date.prototype.digitDateTimeFormat = function() {
	return this.format('yyyyMMddhhmmss');
};