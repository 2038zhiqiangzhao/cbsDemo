'use strict';

/* Controllers */

angular.module('app.controllers', ['pascalprecht.translate', 'ngCookies'])
  .controller('AppCtrl', ['$scope', '$translate', '$localStorage', '$window', '$rootScope',
    function(              $scope,   $translate,   $localStorage,   $window ,$rootScope) {
      // add 'ie' classes to html
      var isIE = !!navigator.userAgent.match(/MSIE/i);
      isIE && angular.element($window.document.body).addClass('ie');
      isSmartDevice( $window ) && angular.element($window.document.body).addClass('smart');

      // config
      $scope.app = {
        name: '后台登录',
        version: '1.1.1',
        // for chart colors
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
          asideFixed: false,
          asideFolded: false
        }
      }
      // nav tab
      $rootScope.tabs=[];
      $rootScope.tabs[0]=true;
      $scope.setTabs=function (i) {
        $rootScope.tabs[0]=false;
        $rootScope.tabs=[];
        $rootScope.tabs[i]=true;
      }
      // - nav tab

      // save settings to local storage
      if ( angular.isDefined($localStorage.settings) ) {
        $scope.app.settings = $localStorage.settings;
      } else {
        $localStorage.settings = $scope.app.settings;
      }
      $scope.$watch('app.settings', function(){ $localStorage.settings = $scope.app.settings; }, true);

      // angular translate
      $scope.lang = { isopen: false };
      $scope.langs = {en:'English', de_DE:'German', it_IT:'Italian'};
      $scope.selectLang = $scope.langs[$translate.proposedLanguage()] || "English";
      $scope.setLang = function(langKey, $event) {
        // set the current lang
        $scope.selectLang = $scope.langs[langKey];
        // You can change the language during runtime
        $translate.use(langKey);
        $scope.lang.isopen = !$scope.lang.isopen;
      };

      function isSmartDevice( $window )
      {
          // Adapted from http://www.detectmobilebrowsers.com
          var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
          // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
          return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
      }
      function Ctr($scope) {
        $scope.isActive = true;
      }

  }])
  // bootstrap controller
  .controller('AccordionDemoCtrl', ['$scope', function($scope) {
    $scope.oneAtATime = true;

    $scope.groups = [
      {
        title: 'Accordion group header - #1',
        content: 'Dynamic group body - #1'
      },
      {
        title: 'Accordion group header - #2',
        content: 'Dynamic group body - #2'
      }
    ];

    $scope.items = ['Item 1', 'Item 2', 'Item 3'];

    $scope.addItem = function() {
      var newItemNo = $scope.items.length + 1;
      $scope.items.push('Item ' + newItemNo);
    };

    $scope.status = {
      isFirstOpen: true,
      isFirstDisabled: false
    };
  }])
  .controller('AlertDemoCtrl', ['$scope', function($scope) {
    $scope.alerts = [
      { type: 'success', msg: 'Well done! You successfully read this important alert message.' },
      { type: 'info', msg: 'Heads up! This alert needs your attention, but it is not super important.' },
      { type: 'warning', msg: 'Warning! Best check yo self, you are not looking too good...' }
    ];

    $scope.addAlert = function() {
      $scope.alerts.push({type: 'danger', msg: 'Oh snap! Change a few things up and try submitting again.'});
    };

    $scope.closeAlert = function(index) {
      $scope.alerts.splice(index, 1);
    };
  }])
  .controller('ButtonsDemoCtrl', ['$scope', function($scope) {
    $scope.singleModel = 1;

    $scope.radioModel = 'Middle';

    $scope.checkModel = {
      left: false,
      middle: true,
      right: false
    };
  }])
  .controller('CarouselDemoCtrl', ['$scope', function($scope) {
    $scope.myInterval = 5000;
    var slides = $scope.slides = [];
    $scope.addSlide = function() {
      slides.push({
        image: 'img/c' + slides.length + '.jpg',
        text: ['Carousel text #0','Carousel text #1','Carousel text #2','Carousel text #3'][slides.length % 4]
      });
    };
    for (var i=0; i<4; i++) {
      $scope.addSlide();
    }
  }])
  .controller('DropdownDemoCtrl', ['$scope', function($scope) {
    $scope.items = [
      'The first choice!',
      'And another choice for you.',
      'but wait! A third!'
    ];

    $scope.status = {
      isopen: false
    };

    $scope.toggled = function(open) {
      //console.log('Dropdown is now: ', open);
    };

    $scope.toggleDropdown = function($event) {
      $event.preventDefault();
      $event.stopPropagation();
      $scope.status.isopen = !$scope.status.isopen;
    };
  }])
  .controller('ModalDemoCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log) {
    $scope.items = ['item1', 'item2', 'item3'];
    var ModalInstanceCtrl = function ($scope, $modalInstance, items) {
      $scope.items = items;
      $scope.selected = {
        item: $scope.items[0]
      };

      $scope.ok = function () {
        $modalInstance.close($scope.selected.item);
      };

      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
    };

    $scope.open = function (size) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalContent.html',
        controller: ModalInstanceCtrl,
        size: size,
        resolve: {
          items: function () {
            return $scope.items;
          }
        }
      });

      modalInstance.result.then(function (selectedItem) {
        $scope.selected = selectedItem;
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
  .controller('PaginationDemoCtrl', ['$scope', '$log', function($scope, $log) {
    $scope.totalItems = 64;
    $scope.currentPage = 4;

    $scope.setPage = function (pageNo) {
      $scope.currentPage = pageNo;
    };

    $scope.pageChanged = function() {
      $log.info('Page changed to: ' + $scope.currentPage);
    };

    $scope.maxSize = 5;
    $scope.bigTotalItems = 175;
    $scope.bigCurrentPage = 1;
  }])
  .controller('PopoverDemoCtrl', ['$scope', function($scope) {
    $scope.dynamicPopover = 'Hello, World!';
    $scope.dynamicPopoverTitle = 'Title';
  }])
  .controller('ProgressDemoCtrl', ['$scope', function($scope) {
    $scope.max = 200;

    $scope.random = function() {
      var value = Math.floor((Math.random() * 100) + 1);
      var type;

      if (value < 25) {
        type = 'success';
      } else if (value < 50) {
        type = 'info';
      } else if (value < 75) {
        type = 'warning';
      } else {
        type = 'danger';
      }

      $scope.showWarning = (type === 'danger' || type === 'warning');

      $scope.dynamic = value;
      $scope.type = type;
    };
    $scope.random();

    $scope.randomStacked = function() {
      $scope.stacked = [];
      var types = ['success', 'info', 'warning', 'danger'];

      for (var i = 0, n = Math.floor((Math.random() * 4) + 1); i < n; i++) {
          var index = Math.floor((Math.random() * 4));
          $scope.stacked.push({
            value: Math.floor((Math.random() * 30) + 1),
            type: types[index]
          });
      }
    };
    $scope.randomStacked();
  }])
  .controller('TabsDemoCtrl', ['$scope', function($scope) {
    $scope.tabs = [
      { title:'Dynamic Title 1', content:'Dynamic content 1' },
      { title:'Dynamic Title 2', content:'Dynamic content 2', disabled: true }
    ];
  }])
  .controller('RatingDemoCtrl', ['$scope', function($scope) {
    $scope.rate = 7;
    $scope.max = 10;
    $scope.isReadonly = false;

    $scope.hoveringOver = function(value) {
      $scope.overStar = value;
      $scope.percent = 100 * (value / $scope.max);
    };
  }])
  .controller('TooltipDemoCtrl', ['$scope', function($scope) {
    $scope.dynamicTooltip = 'Hello, World!';
    $scope.dynamicTooltipText = 'dynamic';
    $scope.htmlTooltip = 'I\'ve been made <b>bold</b>!';
  }])
  .controller('TypeaheadDemoCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.selected = undefined;
    $scope.states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Dakota', 'North Carolina', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];
    // Any function returning a promise object can be used to load values asynchronously
    $scope.getLocation = function(val) {
      return $http.get('http://maps.googleapis.com/maps/api/geocode/json', {
        params: {
          address: val,
          sensor: false
        }
      }).then(function(res){
        var addresses = [];
        angular.forEach(res.data.results, function(item){
          addresses.push(item.formatted_address);
        });
        return addresses;
      });
    };
  }])
  .controller('DatepickerDemoCtrl', ['$scope', function($scope) {
    $scope.today = function() {
      $scope.dt = new Date();
    };
    $scope.today();

    $scope.clear = function () {
      $scope.dt = null;
    };

    // Disable weekend selection
    $scope.disabled = function(date, mode) {
      return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
    };

    $scope.toggleMin = function() {
      $scope.minDate = $scope.minDate ? null : new Date();
    };
    $scope.toggleMin();

    $scope.open = function($event) {
      $event.preventDefault();
      $event.stopPropagation();

      $scope.opened = true;
    };

    $scope.dateOptions = {
      formatYear: 'yy',
      startingDay: 1,
      class: 'datepicker'
    };

    $scope.initDate = new Date('2016-15-20');
    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[0];
  }])
  .controller('TimepickerDemoCtrl', ['$scope', function($scope) {
    $scope.mytime = new Date();

    $scope.hstep = 1;
    $scope.mstep = 15;

    $scope.options = {
      hstep: [1, 2, 3],
      mstep: [1, 5, 10, 15, 25, 30]
    };

    $scope.ismeridian = true;
    $scope.toggleMode = function() {
      $scope.ismeridian = ! $scope.ismeridian;
    };

    $scope.update = function() {
      var d = new Date();
      d.setHours( 14 );
      d.setMinutes( 0 );
      $scope.mytime = d;
    };

    $scope.changed = function () {
      //console.log('Time changed to: ' + $scope.mytime);
    };

    $scope.clear = function() {
      $scope.mytime = null;
    };
  }])

  // Form controller
  .controller('FormDemoCtrl', ['$scope', function($scope) {
    $scope.notBlackListed = function(value) {
      var blacklist = ['bad@domain.com','verybad@domain.com'];
      return blacklist.indexOf(value) === -1;
    }

    $scope.val = 15;
    var updateModel = function(val){
      $scope.$apply(function(){
        $scope.val = val;
      });
    };
    angular.element("#slider").on('slideStop', function(data){
      updateModel(data.value);
    });

    $scope.select2Number = [
      {text:'First',  value:'One'},
      {text:'Second', value:'Two'},
      {text:'Third',  value:'Three'}
    ];

    $scope.list_of_string = ['tag1', 'tag2']
    $scope.select2Options = {
        'multiple': true,
        'simple_tags': true,
        'tags': ['tag1', 'tag2', 'tag3', 'tag4']  // Can be empty list.
    };

  }])

  // Flot Chart controller 
  .controller('FlotChartDemoCtrl', ['$scope', function($scope) {
    $scope.d = [ [1,6.5],[2,6.5],[3,7],[4,8],[5,7.5],[6,7],[7,6.8],[8,7],[9,7.2],[10,7],[11,6.8],[12,7] ];

    $scope.d0_1 = [ [0,7],[1,6.5],[2,12.5],[3,7],[4,9],[5,6],[6,11],[7,6.5],[8,8],[9,7] ];

    $scope.d0_2 = [ [0,4],[1,4.5],[2,7],[3,4.5],[4,3],[5,3.5],[6,6],[7,3],[8,4],[9,3] ];

    $scope.d1_1 = [ [10, 120], [20, 70], [30, 70], [40, 60] ];

    $scope.d1_2 = [ [10, 50],  [20, 60], [30, 90],  [40, 35] ];

    $scope.d1_3 = [ [10, 80],  [20, 40], [30, 30],  [40, 20] ];

    $scope.d2 = [];

    for (var i = 0; i < 20; ++i) {
      $scope.d2.push([i, Math.sin(i)]);
    }   

    $scope.d3 = [ 
      { label: "iPhone5S", data: 40 }, 
      { label: "iPad Mini", data: 10 },
      { label: "iPad Mini Retina", data: 20 },
      { label: "iPhone4S", data: 12 },
      { label: "iPad Air", data: 18 }
    ];

    $scope.getRandomData = function() {
      var data = [],
      totalPoints = 150;
      if (data.length > 0)
        data = data.slice(1);
      while (data.length < totalPoints) {
        var prev = data.length > 0 ? data[data.length - 1] : 50,
          y = prev + Math.random() * 10 - 5;
        if (y < 0) {
          y = 0;
        } else if (y > 100) {
          y = 100;
        }
        data.push(y);
      }
      // Zip the generated y values with the x values
      var res = [];
      for (var i = 0; i < data.length; ++i) {
        res.push([i, data[i]])
      }
      return res;
    }

    $scope.d4 = $scope.getRandomData();
  }])

  // jVectorMap controller
  .controller('JVectorMapDemoCtrl', ['$scope', function($scope) {
    $scope.world_markers = [
      {latLng: [41.90, 12.45], name: 'Vatican City'},
      {latLng: [43.73, 7.41], name: 'Monaco'},
      {latLng: [-0.52, 166.93], name: 'Nauru'},
      {latLng: [-8.51, 179.21], name: 'Tuvalu'},
      {latLng: [43.93, 12.46], name: 'San Marino'},
      {latLng: [47.14, 9.52], name: 'Liechtenstein'},
      {latLng: [7.11, 171.06], name: 'Marshall Islands'},
      {latLng: [17.3, -62.73], name: 'Saint Kitts and Nevis'},
      {latLng: [3.2, 73.22], name: 'Maldives'},
      {latLng: [35.88, 14.5], name: 'Malta'},
      {latLng: [12.05, -61.75], name: 'Grenada'},
      {latLng: [13.16, -61.23], name: 'Saint Vincent and the Grenadines'},
      {latLng: [13.16, -59.55], name: 'Barbados'},
      {latLng: [17.11, -61.85], name: 'Antigua and Barbuda'},
      {latLng: [-4.61, 55.45], name: 'Seychelles'},
      {latLng: [7.35, 134.46], name: 'Palau'},
      {latLng: [42.5, 1.51], name: 'Andorra'},
      {latLng: [14.01, -60.98], name: 'Saint Lucia'},
      {latLng: [6.91, 158.18], name: 'Federated States of Micronesia'},
      {latLng: [1.3, 103.8], name: 'Singapore'},
      {latLng: [1.46, 173.03], name: 'Kiribati'},
      {latLng: [-21.13, -175.2], name: 'Tonga'},
      {latLng: [15.3, -61.38], name: 'Dominica'},
      {latLng: [-20.2, 57.5], name: 'Mauritius'},
      {latLng: [26.02, 50.55], name: 'Bahrain'},
      {latLng: [0.33, 6.73], name: 'São Tomé and Príncipe'}
    ];

    $scope.usa_markers = [
      {latLng: [40.71, -74.00], name: 'New York'},
      {latLng: [34.05, -118.24], name: 'Los Angeles'},
      {latLng: [41.87, -87.62], name: 'Chicago'},
      {latLng: [29.76, -95.36], name: 'Houston'},
      {latLng: [39.95, -75.16], name: 'Philadelphia'},
      {latLng: [38.90, -77.03], name: 'Washington'},
      {latLng: [37.36, -122.03], name: 'Silicon Valley'}
    ];
  }])

  // signin controller
  .controller('SigninFormController', ['$scope', '$http', '$state', function($scope, $http, $state) {
    $scope.user = {};
    $scope.authError = null;
    $scope.login = function() {
      $scope.authError = null;
      // Try to login
      $http.post('api/login', {email: $scope.user.email, password: $scope.user.password})
      .then(function(response) {
        if ( !response.data.user ) {
          $scope.authError = 'Email or Password not right';
        }else{
          $state.go('app.dashboard');
        }
      }, function(x) {
        $scope.authError = 'Server Error';
      });
    };
  }])

  // signup controller
  .controller('SignupFormController', ['$scope', '$http', '$state', function($scope, $http, $state) {
    $scope.user = {};
    $scope.authError = null;
    $scope.signup = function() {
      $scope.authError = null;
      // Try to create
      $http.post('api/signup', {name: $scope.user.name, email: $scope.user.email, password: $scope.user.password})
      .then(function(response) {
        if ( !response.data.user ) {
          $scope.authError = response;
        }else{
          $state.go('app.dashboard');
        }
      }, function(x) {
        $scope.authError = 'Server Error';
      });
    };
  }])
  .controller('ProductsManageCtrl', ['$scope', '$http', '$state', function($scope, $http, $state) {
        $scope.today = function() {
            $scope.dt = new Date();
        };
        $scope.today();

        $scope.clear = function () {
            $scope.dt = null;
        };

        // Disable weekend selection
        $scope.disabled = function(date, mode) {
            return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
        };

        $scope.toggleMin = function() {
            $scope.minDate = $scope.minDate ? null : new Date();
        };
        $scope.toggleMin();

        $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1,
            class: 'datepicker'
        };

        $scope.initDate = new Date('2016-15-20');
        $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        $scope.format = $scope.formats[0];
}])
      
     // 时间选择
    .controller('timeCtrl', ['$scope', function ($scope) {
      $scope.close = function (t) {
        console.log(t);
      }
      $scope.options1={
//                    formatTime:'H:i:s',
        formatDate:'d.m.Y',
        timepickerScrollbar:false,
        step:1,
        onClose:$scope.close,
        timepicker:false
      }

      $scope.options2={
//                    mask:'9999/19/39 29:59',
        format:'Y-m-d H:i:s',
        lang:'zh',
        step:1,
        timepickerScrollbar:false
      }

      $scope.options3={
//                    mask:'9999/19/39 29:59',
        format:'Y-m-d H:i:s',
        lang:'zh',
        step:1,
        timepickerScrollbar:false
      }
    }])
    // 全选
    .controller('checkCtrl', ['$scope', function ($scope) {
      $scope.entities = [{
        "test1": "test",
        "test2": "test",
        "test3": "test",
        "id": 1
      }, {
        "test1": "test",
        "test2": "test",
        "test3": "test",
        "id": 2
      }, {
        "test1": "test",
        "test2": "test",
        "test3": "test",
        "id": 3
      }, {
        "test1": "test",
        "test2": "test",
        "test3": "test",
        "id": 4
      }, {
        "test1": "test",
        "test2": "test",
        "test3": "test",
        "id": 5
      }, {
        "test1": "test",
        "test2": "test",
        "test3": "test",
        "id": 6
      }];
      $scope.selected = [];
      var updateSelected = function (action, id) {
        if (action == 'add' & $scope.selected.indexOf(id) == -1) $scope.selected.push(id);
        if (action == 'remove' && $scope.selected.indexOf(id) != -1) $scope.selected.splice($scope.selected.indexOf(id), 1);
      }

      $scope.updateSelection = function ($event, id) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');
        updateSelected(action, id);
      };

      $scope.selectAll = function ($event) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');
        for (var i = 0; i < $scope.entities.length; i++) {
          var entity = $scope.entities[i];
          updateSelected(action, entity.id);
        }
      };

      $scope.getSelectedClass = function (entity) {
        return $scope.isSelected(entity.id) ? 'selected' : '';
      };

      $scope.isSelected = function (id) {
        return $scope.selected.indexOf(id) >= 0;
      };

      //something extra I couldn't resist adding :)
      $scope.isSelectedAll = function () {
        return $scope.selected.length === $scope.entities.length;
      };
    }])
//         //切换
//     .controller('Aaa',function($scope){
//   $scope.colors = [{
//     'id':'1',
//     'name':'red'
//   },{
//     'id':'2',
//     'name':'green'
//   },{
//     'id':'3',
//     'name':'blue'
//   }];
// })
//     .directive('toggleClass', function(){
//   return {
//     restrict: 'A',
//     scope: {
//       toggleClass: '@'
//     },
//     link: function($scope, $element){
//       $element.on('click', function(){
//         $element.toggleClass($scope.toggleClass);
//       });
//     }
//   };
// })
    // 分页yl
    /*.controller('yl_paginationCtrl',function($scope,$log,$http) {
      //用法一，数据只取一次
      ($scope.getData1 = function (currentPage, itemPerPage) {
        if (angular.isUndefined($scope.dataList)) {
          //接口参数设置
          var params = {
            //传入参数
          };
          $http.post('jsonTest/pagination_data.json', params).success(function (res) {
            //首次赋空数组是因为防止再向接口请求
            $scope.dataList = [];
            //$scope.dataListStore是数据备份，一次请求后不会再变化
            $scope.totalItems = ($scope.dataListStore = res.data.list).length;
            $scope.pageCount = Math.ceil($scope.totalItems / itemPerPage);
            //递规调用，按页码与每页数目给$scope.dataList填充内容。
            $scope.getData1(currentPage, itemPerPage)
          })
        } else {
          //按页码与每页数从备份的数据中截取items赋给$scope.dataList
          var start = itemPerPage * (currentPage - 1);
          var end = ($scope.totalItems < itemPerPage * currentPage) ? $scope.totalItems : itemPerPage * currentPage;
          $scope.dataList = ($scope.dataListStore.slice(start, end));
        }//$scope.pageSizes如果不传，就不会显示每页显示条目的设置，$scope.editPage不设置或设为false，就不会显示手动输入页码的输入框。
      })($scope.currentPage = 1, $scope.itemPerPage = 10, $scope.pageSizes = [10, 20, 50, 100], $scope.editPage = true);

      //同时运行两个分页
      ($scope.getData2 = function (cur, per) {
        if (angular.isUndefined($scope.dl)) {
          //接口参数设置
          var params = {
            //传入参数
          };
          $http.post('jsonTest/pagination_data.json', params).success(function (res) {
            //首次赋空数组是因为防止再向接口请求
            $scope.dl = [];
            //$scope.dataListStore是数据备份，一次请求后不会再变化
            $scope.ti = ($scope.dlStore = res.data.list).length;
            $scope.pc = Math.ceil($scope.ti / per);
            //递规调用，按页码与每页数目给$scope.dataList填充内容。
            $scope.getData2(cur, per)
          })
        } else {
          //按页码与每页数从备份的数据中截取items赋给$scope.dataList
          var start = per * (cur - 1);
          var end = ($scope.ti < per * cur) ? $scope.ti : per * cur;
          $scope.dl = ($scope.dlStore.slice(start, end));
          console.log($scope.dl);
        }//$scope.pageSizes如果不传，就不会显示每页显示条目的设置，$scope.editPage不设置或设为false，就不会显示手动输入页码的输入框。
      })($scope.cur = 1, $scope.per = 10, $scope.ps = [10, 20, 50, 100], $scope.ep = true);
      console.log('end',$scope.dl);
      //用法一结束

      //用法二，每次翻页都重新取数据（页码会变化）
      ($scope.getData = function (currentPage1, itemPerPage1) {
        if(currentPage1>0&&!$scope.progressing) {
          var params = {
            currentPage: currentPage1-0,
            itemPerPage: itemPerPage1
          };
          $scope.progressing=true;
          $http.post('jsonTest/pagination_data.json', angular.toJson(params)).success(function (res) {
            $scope.dataList1 = res.data.list;
            $scope.totalItems1 = res.data.list.length;
            $scope.pageCount1 = Math.ceil($scope.totalItems1 / itemPerPage1);
            $scope.progressing=false;
          })
        }
      })($scope.currentPage1 = 1, $scope.itemPerPage1 = 10, $scope.pageSizes1 = [10, 20, 50, 100], $scope.editPage1 = true);
      //用法二结束
    })*/
    // 文件上传
    .controller('yl_fileUploaderCtrl',['$scope','FileUploader',function($scope,FileUploader){
      var uploader = $scope.uploader = new FileUploader({
        url: 'data.json',
        autoUpload:true
      });
      uploader.onSuccessItem = function(fileItem, response, status, headers) {
        $scope.img=response.data.url;
      };

      //可配置的参数
      //                {
      //                    url: '/',
      //                    alias: 'file',
      //                    headers: {},
      //                    queue: [],
      //                    progress: 0,
      //                    autoUpload: false,
      //                    removeAfterUpload: false,
      //                    method: 'POST',
      //                    filters: [],
      //                    formData: [],
      //                    queueLimit: Number.MAX_VALUE,
      //                    withCredentials: false,
      //                    disableMultipart: false
      //                }
    }])
    // 颜色选择
    .controller('colorpickerCtrl',[function(){
      console.log('a')
    }])
    // 树
    .controller('treeCtrl', ['$scope','$timeout','$http',function($scope, $timeout,$http) {
      $scope.obj = {};
      $scope.my_data = [];
      $scope.selectData = function (branch, selections) {
        var dataArray;
        dataArray = selections ? selections.split(',') : [];
        if (branch.added && dataArray.indexOf(branch.label) < 0) {
          dataArray.push(branch.label);
        } else if (dataArray.indexOf(branch.label) >= 0) {
          dataArray.splice(dataArray.indexOf(branch.label), 1);
        }
        return dataArray.join();
      }

      $scope.add = function (branch) {
        branch.added = !branch.added;
        $scope.obj.selection = $scope.selectData(branch, $scope.obj.selection = $scope.obj.selection || '');
      }


      //当type为file的时候的选中效果
      $scope.select = function (branch) {
        //TODO
      };

      //数据获取
      ($scope.getData = function () {
        $http.post('jsonTest/tree_data.json', {})
            .success(function (res) {
              $scope.my_data = res;
              $scope.start=true;
            })
      })();

      //如果要手动操作树的展开等等
      var tree;
      $scope.my_tree = tree = {};
      return $scope.try_adding_a_branch = function () {
        var b;
        b = tree.get_selected_branch();
        return tree.add_branch(b, {
          label: 'New Branch',
          data: {
            something: 42,
            "else": 43
          }
        });
      };
    }])
    // zTree
    .controller('zTreeTestCtrl',['$scope','$http',function ($scope,$http) {



      $scope.getTreeNode_fn = function(){

        var config = {
          method: "get",
          url: 'jsonTest/zTree_data.json',
          data: {
            async:true
          }
        };
        $http(config).success(function (res) {
          if(res.code == 0) {
            $scope.zNodes = res.data;

            var setting = {
              view: {
                selectedMulti: false
              },
              check: {
                enable: true
              },
              data: {
                simpleData: {
                  enable: true
                }
              },
              callback: {
                // onCheck: onCheck
              }
            };
            var zTree = $.fn.zTree.init($("#treeDemo"),setting,$scope.zNodes);
            var zTreeTreeDemo = $.fn.zTree.getZTreeObj("treeDemo");
            zTreeTreeDemo.setting.check.chkboxType = { "Y" : "", "N" : "" };
            // 默认勾选
            var node = zTree.getNodeByParam("id", "110000");
            zTreeTreeDemo.selectNode(node);
          }else {
            // $rootScope.error.checkCode('提示', 'error');
          }
        });
      };
      $scope.getTreeNode_fn();


      //  数据
      // $scope.zNodes = [
      //   {id: 1, pId: 0, name: "中国", open: true},
      //   {id: 11, pId: 1, name: "上海 1-1", open: true},
      //   {id: 111, pId: 11, name: "静安 1-1-1"},
      //   {id: 112, pId: 11, name: "浦东 1-1-2"},
      //   {id: 12, pId: 1, name: "新疆 1-2", open: true},
      //   {id: 121, pId: 12, name: "新疆新疆 1-2-1"},
      //   {id: 122, pId: 12, name: "ASFDSD 1-2-2"},
      //   {id: 123, pId: 12, name: "BSDFSAFD 1-2-3"},
      //   {id: 124, pId: 12, name: "CSDFSADF 1-2-4"}
      // ];


      //  被选中节点的值
      $scope.selectDatas = [];
      $scope.$watch('selectDatas', function(newOne){
        console.log(newOne);
        // $scope.selectDatas.checked=true;
      },true);

    }])

    // 添加参与商家
    .controller('selectMerchantsCtrl', ['$scope', function ($scope) {
      $scope.siteList1=[{
        test:'上海'
      },{
        test:'南京'
      },{
        test:'安徽'
      },{
        test:'浙江'
      },{
        test:'江苏'
      },{
        test:'澳门'
      },];
      $scope.siteList2=[{
        test:'内蒙古'
      },{
        test:'云南'
      },{
        test:'新疆'
      },{
        test:'江西'
      }];
      $scope.siteList3=[{
        test:'四川省'
      },{
        test:'四川省城市2'
      },{
        test:'四川省城市3'
      },{
        test:'四川省城市4'
      },{
        test:'四川省城市4'
      },{
        test:'四川省城市4'
      }];
      $scope.siteList4=[{
        test:'词穷1'
      },{
        test:'词穷省城市2'
      },{
        test:'词穷省城市3'
      },{
        test:'词穷省城市4'
      }];

      // 已选地点
      $scope.delSite = function($index){
        $scope.siteList1.splice($index,1);
      }
      // 常用地点
      $scope.addSite = function(arr,$index){
        var item = arr[$index]
        $scope.siteList1.push(item);
        arr.splice($index,1)
      };
    }])

    // 广告管理添加
    .controller('admanageAddCtrl',['$scope',function ($scope) {
      // $scope.flag3=3;
      // $scope.flag5=5;
      // if($scope.flag3==3 ){
      //   $("#formT").modal('show');
      //   console.log("13143")
      // }
    }])
  ;