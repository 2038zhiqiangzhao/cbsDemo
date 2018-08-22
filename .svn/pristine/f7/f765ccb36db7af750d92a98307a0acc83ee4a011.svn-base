/**
 * 去除空格
 */
String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim = function(){
	return this.replace(/(^\s*)/g, "");
}
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/g, "");
}
/**
 * 导航初始化
 */
;(function ($) {
	function iconCls(n) {
		var icon = "icon-menu";
		if(n.iconCls && n.iconCls.trim() != "") {
			icon = n.iconCls;
		}
		if(n.children && n.children.length > 0) {
			icon = "icon-down-dir";
		}
		return icon;
	}
	function init(container) {
		var state = $.data(container, 'sidebarNav');
		var $nav = $('<ul class="sidebar-nav"></ul>');
		for(var i in state.options.data) {
			var n = state.options.data[i];
			var $li = $('<li><a href="javascript:void(0)"><i class="'+ iconCls(n) +'"></i> '+ n.text +'</a>');
			$li.data("node", n);
			if(i == 0) {
				$li.addClass('active');
			}
			if(n.children && n.children.length > 0) {
				var $ul = $('<ul class="sidebar-nav"></ul>');
				for(j in n.children) {
					var c = n.children[j];
					var $sli = $('<li><a href="javascript:void(0)"><i class="'+ iconCls(c) +'"></i> '+ c.text +'</a></li>');
					$sli.data("node", c);
					$ul.append($sli);
				}
				$li.append($ul);
			}
			$nav.append($li);
		}
		$(container).append($nav);
		bindEventClick(container);
	}
	function bindEventClick(container) {
		var state = $.data(container, 'sidebarNav');
		var $lis = $(container).find('li');
		$lis.click(function () {
			$(container).find('li.active').removeClass('active');
			$(this).addClass('active');
			var children = $(this).find('ul');
			if(children.length > 0) {
				if(!children.is(':visible') && !children.is(':animated')) {
			      children.fadeIn('fast');
			      $(this).addClass('cls').removeClass('active').find('.icon-down-dir').removeClass('icon-down-dir').addClass('icon-up-dir');;
			    }else {
			      children.fadeOut();
			      $(this).removeClass('cls').removeClass('active').find('.icon-up-dir').removeClass('icon-up-dir').addClass('icon-down-dir');;
			    }
			}
			children.click(function (e) {
				var event = e || window.event;
  				event.stopPropagation();
			});

			var node = $(this).data('node');
			state.options.onclick.call(this, node);
		})
	}
	$.fn.sidebarNav = function (options, param) {
		if (typeof options == 'string'){
			return $.fn.sidebarNav.methods[options](this, param);
		}
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'sidebarNav');
			if (state){
				$.extend(state.options, options);
			} else {
				if(options.url && options.url.trim() != "") {
					$.ajax({
						type: options.method,
						url: options.url,
						async: false,
						data: options.queryParams,
						success: function(data){
							options.data = data;
						}
					});
				}
				$.data(this, 'sidebarNav', {
					options: $.extend({}, $.fn.sidebarNav.defaults, options)
				});
				init(this);
			}
		});
	};
	$.fn.sidebarNav.defaults = {
		url:'',
		method: 'get',
		queryParams: {},
		data: [{
			text: '菜单1',
			children: [{
				text: '子菜单1'
			},{
				text: '子菜单2'
			}]
		},{
			text: '菜单2'
		}],
		onclick:function (node){}
	};
})(jQuery);
/**
 * 全选
 */
;(function ($) {
	$.fn.checkAllBox = function () {
		return this.each(function() {
			var $this = $(this);
			var selector = $this.attr('data-toggle');
			var $chks = $(selector);
			$this.click(function () {
				var html = $this.html();
				if(html == '全选') {
					$this.html('全不选');
					$chks.prop('checked', true);
				}else {
					$this.html('全选');
					$chks.prop('checked', false);
				}
			});
			$chks.click(function () {
				var slen = $(selector + ':checked').length;
				if(slen == $chks.length) {
					$this.html('全不选');
				}else {
					$this.html('全选');
				}
			});
		});
	};
})(jQuery);

$(function () {
	
});