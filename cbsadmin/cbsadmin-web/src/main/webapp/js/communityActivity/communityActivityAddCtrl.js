/**
 */
'use strict';
app.controller("communityActivityAddCtrl", [
		'$scope',
		'$state',
		'$stateParams',
		'$http',
		'FileUploader',
		'platformUtil',
		'Dic',
		function($scope, $state, $stateParams, $http, FileUploader,
				platformUtil, Dic) {
			'use strict';

			// 常量定义
			var ADD_URL = "communityActivity/addcommunityActivity.do";//表单提交数据
			var LOADING_PIC_PATH = "image/loading.gif";
			var DISH_CLASS_QUERY_URL = "community/querycommunityList.do";
			var LIST_QUERY_URL = "community/queryCategoryInfo.do";
			var PIC_SAVE_URL = "file/savePic.do";
			
			// 百度富文本编辑器配置
			window.UEDITOR_CONFIG.maximumWords = 9999;
			// 'editor' 为html 元素id
			UE.delEditor("editor");
			$scope.ue = UE.getEditor('editor');

  			// 表单数据
			$scope.formData = {
					isTimePublish : 0,
					type : 3,
					surfacePicture:""
			};
			
			//日期格式

			$scope.options0 = {
//                  mask:'9999/19/39 29:59',
//					 format: 'Y/m/d H:i:s',
		            format: 'Y-m-d',
		            lang: 'zh',
		            step: 1,
		            timepickerScrollbar: false,
		            datepicker:true,
		            timepicker:false
            };
            $scope.options1 = {
                    format : 'H:i',
                    lang : 'zh',
                    timepicker:true,
                    datepicker:false,
                    step:30
            };
            
            $scope.options2 = {
                    format : 'H:i',
                    lang : 'zh',
                    timepicker:true,
                    datepicker:false,
                    step:30
            };
        	$scope.options3= {
//                  mask:'9999/19/39 29:59',
//					 format: 'Y/m/d H:i:s',
		            format: 'Y-m-d',
		            lang: 'zh',
		            step: 1,
		            timepickerScrollbar: false,
		            datepicker:true,
		            timepicker:false
            };
        	 $scope.options4 = {
                     format : 'H:i',
                     lang : 'zh',
                     timepicker:true,
                     datepicker:false,
                     step:30
             };
        	 $scope.options5 = {
        			 format: 'Y-m-d',
 		            lang: 'zh',
 		            step: 1,
 		            timepickerScrollbar: false,
 		            datepicker:true,
 		            timepicker:false
             };
        	 $scope.options6 = {
                     format : 'H:i',
                     lang : 'zh',
                     timepicker:true,
                     datepicker:false,
                     step:30
             };
        	 /*图片上传*/
			$scope.uploader1 = new FileUploader({
				url : PIC_SAVE_URL,
				autoUpload : true,
				removeAfterUpload : true,
				onAfterAddingFile : function(fileItem) {
					// 图片格式仅限JPG、PNG
					if ("image/png" != fileItem._file.type
							&& "image/jpg" != fileItem._file.type
							&& "image/jpeg" != fileItem._file.type) {
						platformUtil.showAlert('提示', '图片格式不正确，只能上传JPG、PNG格式');
						$scope.canUpload = 1;
						return false;
					}
					// 不得大于500K
					if (fileItem._file.size > 512000) {
						platformUtil.showAlert('提示', '图片超出最大限制，图片最大限制为500KB');
						$scope.canUpload = 1;
						return false;
					}
					$scope.formData['lineMapUrl'] = LOADING_PIC_PATH;
				},
				onCompleteItem : function(fileItem, response, status, headers) {
					$scope.formData.surfacePicture = response.data;
				},
				filters : [ {
					name : 'customFilter',
					fn : function(item /* {File|FileLikeObject} */, options) {
						return this.queue.length < 10;
					}
				} ]
			});
		
			 //保存数据
            $scope.saveOption = function() {
          	  var richTextArea = $scope.ue.getContent();
          	  $scope.formData['richTextArea'] = richTextArea;
				$scope.addSubmit();
				

            };
            $scope.init=function(){
            	//定时发布默认隐藏
            	document.getElementById("div_timeInterva").style.display="none";//隐藏
            }
             //是否定时发布
            $scope.isTimePublish = function(result) {
          	  var isTimeState=result;
				if(isTimeState==0){
					//定时发布默认隐藏
	            	document.getElementById("div_timeInterva").style.display="none";//隐藏
				}else{
					//定时发布显示
	            	document.getElementById("div_timeInterva").style.display="block";//显示
				}

            };
           //新增表单数据提交
			$scope.addSubmit = function() {
				
				if($scope.formData.surfacePicture==null){
					return;
				}
				if ($scope.form.$invalid) {
					return;
				}
				var isTimePublish=$scope.formData.isTimePublish;
				if(isTimePublish==1){
					if($scope.formData.timeInterval==null||$scope.formData.timeIntervalStartTime==null)
						
						return;
				}
				$("#saveBtn").button("loading");
				$http.post(ADD_URL, angular.toJson($scope.formData)).success(
						function(result) {
							
							$("#saveBtn").button("reset");
							$scope.back();
						}).error(function(err) {
					console.log("HTTP访问失败");
				});
			};
			/**
			 * 返回
			 */
			$scope.back = function() {
				$state.go('communityActivity');
			}
			$scope.init();
		} ]);