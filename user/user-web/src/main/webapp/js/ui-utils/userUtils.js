app.factory('userUtil', function ($location,$http) {
	var util = {};
	/**
	 * 方法定义
	 * */
	//通过字典获取中文名
	util.getDictionaryText = function(id,dictionary){
		   for(var i=0;i<dictionary.length;i++){
			   if(dictionary[i]['id'] == id){
				   if(!dictionary[i] || !dictionary[i]['id'] == undefined){
					   continue;
				   }
				   if(dictionary[i]['text']){
					   return dictionary[i]['text'];
				   }else{
					   return dictionary[i]['name'];
				   }
			   }
		   } 
	};
	//重定向
	util.redirectTo=function(path,params){
		var urlParam = '';
		if(params){
			for (var key in params) {
				urlParam += '/' + params[key];
			}
		}
		
		util.redirect(path + urlParam);
	};
	util.redirect=function(path){
		return window.location = bootPATH+path;
	};
	util.fromJson=function(jsonStr){
		return angular.fromJson(jsonStr);
	};
	util.toJson=function(obj){
		return angular.toJson(obj);
	};
	util.getCookie =function (sName) {//获取cookie
	    var aCookie = document.cookie.split("; ");
	    var lastMatch = null;
	    for (var i = 0; i < aCookie.length; i++) {
	        var aCrumb = aCookie[i].split("=");
	        if (sName == aCrumb[0]) {
	            lastMatch = aCrumb;
	        }
	    }
	    if (lastMatch) {
	        var v = lastMatch[1];
	        if (v === undefined) return v;
	        return unescape(v);
	    }
	    return null;
	};
	
	util.postRequest = function(url,param,success,failed,prefix){//发送httppost请求
		if(!prefix){
			prefix = '.do';
		}
		$http.post(bootPATH+url+prefix, angular.toJson(param)).success(function(result) {
			if($.isFunction(success)){
				success(result);
			}
		}).error(function(err) {
			if($.isFunction(failed)){
				failed(err);
			}
	    });
	};
	
	
	util.imgEnlarge = function (e){
		var offX=25,offY=25;
		var previewElement=$("#preview");
		if(previewElement.length==0){
			$("body").append("<div id='preview' class='previewShowWindow'><img id='pi' style='width:500px;height:500px;'  src='' alt='Now Loading'/></div>");
		} 
		$("#preview").css({
			'position':'absolute',
			'border':'1px solid #dadade',
			'background':'#95959d',
			'padding':'5px',
			'display':'none',
			'color':'#fff',
			'text-align':'center',
			'z-index':999
		});
		var src=e['currentTarget']['src'];
        $("#pi").attr("src",src);
        $("#pi").on('click',function(){
        	$("#preview").remove();
        });
        var o=$("#preview").width();
        var p=$("#preview").height();
        var a=$(window).width()+$(window).scrollLeft();
        var l=$(window).height()+$(window).scrollTop();
        var n;
        var b;
        if((e.pageX+offX+o)>a){
        	n=e.pageX-(o+offX)+"px";
        }else{
        	n=e.pageX+offX+"px";
        }if((e.pageY+offY+p)>l){
        	b=l-(p+offY)+"px";
        }else{
        	b=e.pageY+offY+"px";
        }
        $("#preview").css("top",b).css("left",n).fadeIn("fast");
      };
	/**end*/
	return util;
});