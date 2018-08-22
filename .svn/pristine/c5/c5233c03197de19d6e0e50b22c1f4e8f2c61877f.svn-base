'use strict';
app.controller("platformCtrl", ['$scope', '$localStorage', '$http', '$window', '$rootScope', function($scope, $localStorage, $http, $window, $rootScope){
	'use strict';

	$scope.init = function(){
		// add 'ie' classes to html 使用navigator.userAgent来判断浏览器类型 
		//正则表达式，navigator.userAgent浏览器信息是否含有MSIE字样，就是判断是否为IE浏览器。
		var isIE = !!navigator.userAgent.match(/MSIE/i);
		isIE && angular.element($window.document.body).addClass('ie');
		$scope.isSmartDevice($window) && angular.element($window.document.body).addClass('smart');

		// config
		$scope.app = {
			name: '后台登录',
			color: {
				primary: '#7266ba',
				info:    '#23b7e5',
				success: '#27c24c',
				warning: '#fad733',
				danger:  '#f05050',
				light:   '#e8eff0',
				dark:    '#3a3f51',
				black:   '#1c2b36'
			},
			settings: {
				themeID: 1,
				navbarHeaderColor: 'bg-black',
				navbarCollapseColor: 'bg-white-only',
				asideColor: 'bg-black',
				headerFixed: true,
				asideFixed: true,
				asideFolded: false
			}
		};

		// nav tab 导航栏
		$rootScope.tabs=[];
		$rootScope.tabs[0]=true;
		$scope.setTabs=function (i) {
			$rootScope.tabs[0]=false;
			$rootScope.tabs=[];
			$rootScope.tabs[i]=true;
			$scope.menuData = $scope.menuTreeData[i].childs;
		};

		if ( angular.isDefined($localStorage.settings) ) {
			$scope.app.settings = $localStorage.settings;
		} else {
			$localStorage.settings = $scope.app.settings;
		}
		$scope.$watch('app.settings', function(){ $localStorage.settings = $scope.app.settings; }, true);

		$scope.showMenuData();
		
		
	};

	$scope.isSmartDevice = function($window) {
		// Adapted from http://www.detectmobilebrowsers.com
		var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
		// Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
		return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
	};


	//菜单树数据加载
	$scope.showMenuData = function(){
		$http({
			method : 'POST',
			url: '/user-web/menu/queryUserMenu.do'
		}).success(function(result) {
			if(result.code == "0"){
				$scope.currentUser = result.data.user;
				$scope.username = result.data.user.username;
				$scope.menuTreeData = result.data.menu;
				if ($scope.menuTreeData && $scope.menuTreeData.length > 0) {
					$scope.menuData = $scope.menuTreeData[0].childs;
				}
				//$window.location.href = "/#/blank";
				$("#userMenuData").val(JSON.stringify(result));
				
				// 打开第一个菜单
				var firstUrl = $scope.menuTreeData[0].childs[0].url;
				if (firstUrl && firstUrl != '') {
					$scope.clickMenuURL($scope.menuTreeData[0].childs[0]);
				}
				
			}
			else{
				//$("#platformFrame").attr("src", "/plugins/platform-include/views/login.html");
				$window.location.href = "/login.html";
			}
			$('#platformFrame').css({
				height:document.body.scrollHeight - 50
			});
		});
	};

	$scope.getBackGuideSwitch = function(){
		if($scope.menuTreeData){
			$http({
				method : 'POST',
				url: '/getBackGuideSwitch.do'
			}).success(function(result) {
				if(result.code == 0){
					$scope.backGuideSwitch = result.resultData;
				}
				else{
					$scope.backGuideSwitch = true;
				}
			});
		}else{
			$scope.backGuideSwitch = false;
		}
	};

	$scope.getBoardSwitch = function(){
		if($scope.menuTreeData){
			$http({
				method : 'POST',
				url: '/getBoardSwitch.do'
			}).success(function(result) {
				if(result.code == 0 && result.resultData == "false"){
					$("#platformFrame").attr("src", "/plugins/platform-include/views/blank.html");
				}
				else{
					$("#platformFrame").attr("src", "/board.html");
				}
			});
		}
		else{
			$("#platformFrame").attr("src", "/plugins/platform-include/views/login.html");
		}
	};

	//用户退出
	$scope.logout = function(){
		$.ajax({
			type : "post",
			url : "/user-web/mobileLogin/exit.do",
			async:false
		});
		$scope.menuTreeData = null;
		$scope.username = null;
		//$("#platformFrame").attr("src", "/plugins/platform-include/views/login.html");
		$window.location.href = "/login.html";
	};

	//点击左侧菜单后
	$scope.clickMenuURL = function(menu){
		$scope.currentFunctionCode = menu.id;
		var id = menu.id;
		$.addtabs.add({
			id:id,
			url:menu.url,
			title:menu.name,
			refresh: true
		});
		$.addtabs.drop();
	};
	window.clickMenuURL = $scope.clickMenuURL;
	
	// 作废
	$scope.clickMenuURLBak = function(url){
		$(document.getElementById('platformFrame').contentWindow.document.body).find(".modal-backdrop").remove();
		$(document.getElementById('platformFrame').contentWindow.document.body).find(".bootstrap-dialog").remove();
		$(document.getElementById('platformFrame').contentWindow.document.body).find(".modal").modal("hide");
		$(document.getElementById('platformFrame').contentWindow.document.body).find(".modal-dialog").modal("hide");
		$(document.getElementById('platformFrame').contentWindow.document.body).find(".modal-dialog").remove();
		$scope.currentURL = url;
		$("#platformFrame").attr("src", url);
		$('#platformFrame').css({
			"height":document.body.scrollHeight - 50
		});
		window.scrollTo(0,0);
	};
}]);

app.directive('uiModule', ['MODULE_CONFIG','uiLoad', '$compile', function(MODULE_CONFIG, uiLoad, $compile) {
	return {
		restrict: 'A',
		compile: function (el, attrs) {
			var contents = el.contents().clone();
			return function(scope, el, attrs){
				el.contents().remove();
				uiLoad.load(MODULE_CONFIG[attrs.uiModule])
					.then(function(){
						$compile(contents)(scope, function(clonedElement, scope) {
							el.append(clonedElement);
						});
					});
			}
		}
	};
}]).directive('uiShift', ['$timeout', function($timeout) {
	return {
		restrict: 'A',
		link: function(scope, el, attr) {
			// get the $prev or $parent of this el
			var _el = $(el),
				_window = $(window),
				prev = _el.prev(),
				parent,
				width = _window.width()
				;

			!prev.length && (parent = _el.parent());

			function sm(){
				$timeout(function () {
					var method = attr.uiShift;
					var target = attr.target;
					_el.hasClass('in') || _el[method](target).addClass('in');
				});
			}

			function md(){
				parent && parent['prepend'](el);
				!parent && _el['insertAfter'](prev);
				_el.removeClass('in');
			}

			(width < 768 && sm()) || md();

			_window.resize(function() {
				if(width !== _window.width()){
					$timeout(function(){
						(_window.width() < 768 && sm()) || md();
						width = _window.width();
					});
				}
			});
		}
	};
}]).directive('uiToggleClass', ['$timeout', '$document', function($timeout, $document) {
	return {
		restrict: 'AC',
		link: function(scope, el, attr) {
			el.on('click', function(e) {
				e.preventDefault();
				var classes = attr.uiToggleClass.split(','),
					targets = (attr.target && attr.target.split(',')) || Array(el),
					key = 0;
				angular.forEach(classes, function( _class ) {
					var target = targets[(targets.length && key)];
					( _class.indexOf( '*' ) !== -1 ) && magic(_class, target);
					$( target ).toggleClass(_class);
					key ++;
				});
				$(el).toggleClass('active');

				function magic(_class, target){
					var patt = new RegExp( '\\s' +
					_class.
						replace( /\*/g, '[A-Za-z0-9-_]+' ).
						split( ' ' ).
						join( '\\s|\\s' ) +
					'\\s', 'g' );
					var cn = ' ' + $(target)[0].className + ' ';
					while ( patt.test( cn ) ) {
						cn = cn.replace( patt, ' ' );
					}
					$(target)[0].className = $.trim( cn );
				}
			});
		}
	};
}]).directive('uiNav', ['$timeout', function($timeout) {
	return {
		restrict: 'AC',
		link: function(scope, el, attr) {
			var _window = $(window),
				_mb = 768,
				wrap = $('.app-aside'),
				next,
				backdrop = '.dropdown-backdrop';
			// unfolded
			el.on('click', 'a', function(e) {
				next && next.trigger('mouseleave.nav');
				var _this = $(this);
				_this.parent().siblings( ".active" ).toggleClass('active');
				_this.next().is('ul') &&  _this.parent().toggleClass('active') &&  e.preventDefault();
				// mobile
				_this.next().is('ul') || ( ( _window.width() < _mb ) && $('.app-aside').removeClass('show off-screen') );
			});

			// folded & fixed
			el.on('mouseenter', 'a', function(e){
				next && next.trigger('mouseleave.nav');
				$('> .nav', wrap).remove();
				if ( !$('.app-aside-fixed.app-aside-folded').length || ( _window.width() < _mb )) return;
				var _this = $(e.target)
					, top
					, w_h = $(window).height()
					, offset = 50
					, min = 150;

				!_this.is('a') && (_this = _this.closest('a'));
				if( _this.next().is('ul') ){
					next = _this.next();
				}else{
					return;
				}

				_this.parent().addClass('active');
				top = _this.parent().position().top + offset;
				next.css('top', top);
				if( top + next.height() > w_h ){
					next.css('bottom', 0);
				}
				if(top + min > w_h){
					next.css('bottom', w_h - top - offset).css('top', 'auto');
				}
				next.appendTo(wrap);

				next.on('mouseleave.nav', function(e){
					$(backdrop).remove();
					next.appendTo(_this.parent());
					next.off('mouseleave.nav').css('top', 'auto').css('bottom', 'auto');
					_this.parent().removeClass('active');
				});

				$('.smart').length && $('<div class="dropdown-backdrop"/>').insertAfter('.app-aside').on('click', function(next){
					next && next.trigger('mouseleave.nav');
				});

			});

			wrap.on('mouseleave', function(e){
				next && next.trigger('mouseleave.nav');
				$('> .nav', wrap).remove();
			});
		}
	};
}]).directive('uiScroll', ['$location', '$anchorScroll', function($location, $anchorScroll) {
	return {
		restrict: 'AC',
		link: function(scope, el, attr) {
			el.on('click', function(e) {
				$location.hash(attr.uiScroll);
				$anchorScroll();
			});
		}
	};
}]).directive('uiFullscreen', ['uiLoad', function(uiLoad) {
	return {
		restrict: 'AC',
		template:'<i class="fa fa-expand fa-fw text"></i><i class="fa fa-compress fa-fw text-active"></i>',
		link: function(scope, el, attr) {
			el.addClass('hide');
			uiLoad.load('/plugins/common/js/screenfull.min.js').then(function(){
				if (screenfull.enabled) {
					el.removeClass('hide');
				}
				el.on('click', function(){
					var target;
					attr.target && ( target = $(attr.target)[0] );
					el.toggleClass('active');
					screenfull.toggle(target);
				});
			});
		}
	};
}]).directive('uiButterbar', ['$rootScope', '$anchorScroll', function($rootScope, $anchorScroll) {
	return {
		restrict: 'AC',
		template:'<span class="bar"></span>',
		link: function(scope, el, attrs) {
			el.addClass('butterbar hide');
			scope.$on('$stateChangeStart', function(event) {
				$anchorScroll();
				el.removeClass('hide').addClass('active');
			});
			scope.$on('$stateChangeSuccess', function( event, toState, toParams, fromState ) {
				event.targetScope.$watch('$viewContentLoaded', function(){
					el.addClass('hide').removeClass('active');
				})
			});
		}
	};
}]).directive('setNgAnimate', ['$animate', function ($animate) {
	return {
		link: function ($scope, $element, $attrs) {
			$scope.$watch( function() {
				return $scope.$eval($attrs.setNgAnimate, $scope);
			}, function(valnew, valold){
				$animate.enabled(!!valnew, $element);
			});
		}
	};
}]).directive('functionCodeCheck', [
	'$http',
	function($http) {
		window.globalFunction=false;
		window.queryGlobalFunctioning=false;
		//加载权限数据 这么实现是为了避免一个页面出现多个 需要按钮的标签，导致重复查询权限数据
		var checkCodeFun=function(eventName,scope){
			if(!globalFunction){
				if(!queryGlobalFunctioning){
					window.queryGlobalFunctioning=true;
					try {
						//这里是为了兼容，单独页面打开 没有嵌套iframe
						var userMenuData = JSON.parse(top.$("#userMenuData").val());
						window.globalFunctions=userMenuData.data.functions;
						if (!window.globalFunctions) {
							$http.post('/user-web/function/function.do').success(
									function(result) {
										console.log('加载权限数据!!');
										if (result.code == 0) {
											window.globalFunctions = result.data;
											if(eventName){
												scope.$broadcast(eventName,{});
											}
										}
									}).error(function(result){
										console.log('加载权限数据出错');
										console.error(result);
									}
							);
						}
					} catch (e) {
						console.log(e);
					}
				}
			}
			// 通知
			if(eventName){
				scope.$broadcast(eventName,{});
			}
		};
		//改变元素显示
		var changeDisplay=function(element, attrs,eventName,scope){
			scope.$on(eventName,function(){
				if (!(globalFunctions.indexOf("," + attrs.functionCodeCheck
					+ ",") > -1)) {
					element[0].style.display = 'none';
				}
			});
		};

		return {
			restrict : 'A',
			link : function(scope, element, attrs) {
				changeDisplay(element, attrs,'_functionCodeCheck',scope);
				checkCodeFun('_functionCodeCheck',scope);
			}
		};
}]).directive("toggleCondition", function(){
	return {
		restrict : 'E',
		replace : true,
		scope : {
			id : "@",
			datasetData : "="
		},
		template : "<div class='col-sm-12 text-right'><i class='glyphicon cursorPointer' ng-class=\"{true: 'glyphicon-chevron-up', false: 'glyphicon-chevron-down'}[datasetData]\" ng-click='datasetData = !datasetData'></i></div>",
		link : function(scope,elem,attrs) {

		}
	}
}).filter('fromNow', function() {
	return function(date) {
		return moment(date).fromNow();
	}
});

app.factory('platformUtil',['$http', function ($http) {
	var util={};

	/**
	 * 展示提示框
	 * @param title
	 * @param msg
	 * @param callback
	 */
	util.showAlert = function(title, msg, callback){
		BootstrapDialog.show({
			size: BootstrapDialog.SIZE_SMALL,
			title: title,
			message: msg,
			closable: true,
			closeByBackdrop: false,
			closeByKeyboard: false,
			buttons: [{
				label: "关闭",
				action: function(dialogRef){
					dialogRef.close();
				}
			}],
			onhide: function(dialogRef){
				if(callback){
					callback();
				}
			}
		});
	};

	/**
	 * 展示确认框
	 * @param title
	 * @param message
	 * @param callBackYes
	 * @param callBackNo
	 */
	util.showConfirm = function(title, message, callBackYes, callBackNo){
		BootstrapDialog.confirm({
			title: "<h4>" + title + "</h4>",
			message: '<h4 class="modal-body text-center">' + message + '</h4>',
			closable: false,
			draggable: true,
			btnOKLabel: '确定',
			btnCancelLabel: '取消',
			callback: function(result) {
				if(result) {
					if(callBackYes){
						callBackYes();
					}
				}else if(callBackNo){
					callBackNo();
				}
			}
		});
	};

	return util;
}]);