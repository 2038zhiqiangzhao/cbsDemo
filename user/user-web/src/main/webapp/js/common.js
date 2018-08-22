/*
 * @author fangqingyuan
 * @date 2014-09-22
 * @description
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
}
Date.prototype.defaultDateTimeFormat = function() {
	return this.format('yyyy-MM-dd hh:mm:ss');
}
Date.prototype.defaultDateFormat = function() {
	return this.format('yyyy-MM-dd');
}
Date.prototype.defaultTimeFormat = function() {
	return this.format('hh:mm:ss');
}
Date.prototype.digitDateTimeFormat = function() {
	return this.format('yyyyMMddhhmmss');
}

String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")), replaceWith);
	} else {
		return this.replace(reallyDo, replaceWith);
	}
}

if (!String.prototype.trim) {
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g, '');
	};
	String.prototype.ltrim = function() {
		return this.replace(/^\s+/, '');
	};
	String.prototype.rtrim = function() {
		return this.replace(/\s+$/, '');
	};
	String.prototype.fulltrim = function() {
		return this.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g, '').replace(/\s+/g, ' ');
	};
}

var patterns = new Object();
// 匹配ip地址
patterns.ip = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/;
// 匹配邮件地址
patterns.email = /^.+@.+\..+$/;
//匹配邮件地址
patterns.password = /^(\d{6,18}|\d{32})$/;
// 匹配java yyyy-mm-dd
patterns.date = /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/;
// 匹配java HH:mm:ss
patterns.time = /^([0-1]\d|2[0-3]):[0-5]\d:[0-5]\d$/;
// 匹配java yyyy-mm-dd HH:mm:ss
patterns.datetime = /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1]) ([0-1]\d|2[0-3]):[0-5]\d:[0-5]\d$/;
function verifyRegularExpression(str, pat) {
	var p = patterns[pat];
	if (p.test(str)) {
		return true;
	} else {
		return false;
	}
}
/*
 * 判断字符类型
 */
function charType(str) {
	if (str >= 48 && str <= 57) { // 数字
		return 1;
	} else if (str >= 65 && str <= 90) { // 大写字母
		return 2;
	} else if (str >= 97 && str <= 122) { // 小写
		return 4;
	} else {
		return 8; // 特殊字符
	}
}
/*
 * 统计字符类型
 */
function bitTotal(num) {
	var modes = 0;
	for (i = 0; i < 4; i++) {
		if (num & 1) {
			modes++;
		}
		num >>>= 1;
	}
	return modes;
}
/*
 * 返回密码的强度级别
 */
function checkStrong(password) {
	var modes = 0;
	for (i = 0; i < password.length; i++) {
		// 测试每一个字符的类别并统计一共有多少种模式.
		modes |= charType(password.charCodeAt(i));
	}
	return bitTotal(modes);
}

/*
 * iframe初始化
 */
function IFrameInit(iframename) {
	IFrameReSizeHeight(iframename);
	IFrameReSizeWidth(iframename);
}

/*
 * iframe自适应高度
 */
function IFrameReSizeHeight(iframename) {
	var pTar = document.getElementById(iframename);
	if (pTar) {
		if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight) {
			pTar.height = pTar.contentDocument.body.offsetHeight;
		} else if (pTar.Document && pTar.Document.body.scrollHeight) {
			pTar.height = pTar.Document.body.scrollHeight;
		}
	}
}

/*
 * iframe自适应宽度
 */
function IFrameReSizeWidth(iframename) {
	var pTar = document.getElementById(iframename);
	if (pTar) {
		if (pTar.contentDocument && pTar.contentDocument.body.offsetWidth) {
			pTar.width = pTar.contentDocument.body.offsetWidth;
		} else if (pTar.Document && pTar.Document.body.scrollWidth) {
			pTar.width = pTar.Document.body.scrollWidth;
		}
	}
}

function isEmptyObject(e) {  
    var t;  
    for (t in e)  
        return !1;  
    return !0  
}

function noLogin(e){//没有登录跳转
	if(null!=e&&""!=e){
		if(99==e.code){
			if(parent.location){
				parent.location.href = '/ouser-web/#/login';
			}
			
		}
	}
}