;(function($){
	var _setting = null;
	var bannerUploader = null;
	$.banner = {
		setting:function(setting){
			_setting = setting;
			this.initBanner();
		},
		initBanner:function(){
			//添加div
			var htmlStr = '<div id="bannerEditDiv" class="larry-personal-body clearfix changepwd" style="display: none;">\
							    <input type="hidden" id="editBannerId" value="">\
						    <div class="layui-form-item col-lg-12" style="margin-top: 10px;">\
						        <label class="layui-form-label">图片说明</label>\
						        <div class="layui-inline col-lg-6">\
						            <input id="editBannerTitle" type="text" class="layui-input" value="">\
						        </div>\
						        <div class="layui-inline valid-info"></div>\
						    </div>\
						    <div class="layui-form-item col-lg-12" style="">\
						        <label class="layui-form-label">跳转链接</label>\
						        <div class="layui-inline col-lg-6">\
						            <input id="editBannerUrl" type="text" class="layui-input" value="">\
						        </div>\
						        <div class="layui-inline valid-info"></div>\
						    </div>\
						    <div class="layui-form-item col-lg-12">\
						        <label class="layui-form-label">排序</label>\
						        <div class="layui-inline col-lg-6">\
						            <input id="editBannerOrderby" type="number" class="layui-input" value="">\
						        </div>\
						        <div class="layui-inline valid-info"></div>\
						    </div>\
						    <div class="layui-form-item change-submit col-lg-12" style="text-align: center;">\
						        <div class="layui-inline">\
						            <button type="button" class="layui-btn" onclick="saveEditImg();" >保存</button>\
						            <button type="button" class="layui-btn layui-btn-primary" onclick="closeEditImg();" >关闭</button>\
						        </div>\
						    </div>\
						</div>';
			$("body").before(htmlStr);
			
			
			var uploader_banner = WebUploader.create(_setting);
			
			bannerUploader = uploader_banner;
			
			 var nowUploadImgId = "";
		        uploader_banner.on( 'fileQueued', function( file ) {
		            var timestamp = Date.parse(new Date());
		            var domId = file.id + timestamp;
		            nowUploadImgId = domId;
		            var $li = $('<div id="' + file.id + '" name="showbanner" class="file-item thumbnail layui-inline">' +
		                        '<img>' +
		                        '<div class="info">' + 
		                            '<button type="button" style="width:50px;" class="layui-btn layui-btn-mini" ' + 
		                                'onclick="editImg(\'' + domId + '\',this);" >编辑</button>' + 
		                            '<button type="button" style="width:50px;margin-left:0;float:right;" class="layui-btn layui-btn-danger layui-btn-mini" ' + 
		                            'onclick="deleteImgTips(\'' + domId + '\',this);" >删除</button>' + 
		                        '</div>' 
		                        // '<div class="info">' + file.name + '</div>' 
		                        +'</div>');
		            $img = $li.find('img');


		            // $list为容器jQuery实例
		            $list = $("#fileList_banner");
		            $list.append( $li );

		            // 创建缩略图
		            // 如果为非图片文件，可以不用调用此方法。
		            // thumbnailWidth x thumbnailHeight 为 100 x 100
		            var thumbnailWidth = thumbnailHeight = 100;
		            uploader_banner.makeThumb( file, function( error, src ) {
		                if ( error ) {
		                    $img.replaceWith('<span>不能预览</span>');
		                    return;
		                }

		                $img.attr( 'src', src );
		            }, thumbnailWidth, thumbnailHeight );
		        });
		        
		        // 文件上传过程中创建进度条实时显示。
		        uploader_banner.on( 'uploadProgress', function( file, percentage ) {
		            var $li = $( '#'+file.id ),
		            $percent = $li.find('.progress span');

		            // 避免重复创建
		            if ( !$percent.length ) {
		                $percent = $('<p class="progress"><span></span></p>')
		                        .appendTo( $li )
		                        .find('span');
		            }

		            $percent.css( 'width', percentage * 100 + '%' );
		        });
		        
		        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
		        uploader_banner.on( 'uploadSuccess', function( file,response ) {
		        	if(!!(_setting.hasOwnProperty("imgCrop"))){
	        			layer.open({
		  	                  type: 1,
		  	                  title: '编辑轮播图',
		  	                  //skin: 'layui-layer-demo', //样式类名
		  	                  closeBtn: 1, //不显示关闭按钮
		  	                  maxmin: true,
		  	                  anim: 2,
		  	                  area: ['778px','442px'],
		  	                  shadeClose: false, //开启遮罩关闭
		  	                  content:$("#cropImgDiv")
		                 });
		  				
		  				 
	  					var picpath=response.data[0].path;
	  					$("#picpath").val(picpath);
	  					var serverPicUrl = _setting.baseUrl+picpath;
	  					imgCrop = null;
	  					$("#sourceImg").html('');
	  					imgCrop = new ImageCrop({
	  		                  sourceContainer: $('#sourceImg')[0], // 必选，图片所在的容器元素
	  		                  src: serverPicUrl, // 必选，图片地址
	  		                  imgCls: 'img', // 可选，图片元素img的classname，默认 img
	  		                  //preImg: gtdom('prevImg'), // 可选，预览图片元素，已存在img元素
	  		                 // areaImg: gtdom('areaImg'), // 可选，当前移动框所包含的图片内容
	  		                  width: 333, // 可选，默认移动框的宽度，默认 100
	  		                  height: 177, // 可选，默认移动框的高度，默认 100
	  		                  lockWHScale: true, // 可选，是否锁定宽高比，默认false
	  		                  //defaultCenter: false, // 可选，是否默认出现在中心位置，默认true
	  		                  top: 0, // 可选，默认出现位置的top值（当defaultCenter为false时有效），默认0
	  		                  left:0, // 可选，默认出现位置的left值（当defaultCenter为false时有效），默认0
	  		                  
	  		                  minHeight: 50, // 可选，移动框的最小高度，默认20
	  		                  minWidth: 50, // 可选，移动框的最小宽度，默认20
	  		                  //minImgWidth: 124, // 可选，预览图片的最小宽度，默认150
	  		                 // minImgHeight: 124, // 可选，预览图片的最小高度，默认150
	  		                  
	  		                  // 可选，当移动的时候调用
	  		                  // 移动的概念是指 选择框的大小、位置 发生改变的时候
	  		                  onMove: function() {
	  		                  }
	  		              });
	        		}
		        	
		        	
		            var _images=jQuery("[name=banner]").val();
		            if(_images!=null&&_images!=""&&_images.length>0){
		               var _images=eval("("+_images+")");
		               var addObj = response.data[0];
		               addObj.id = nowUploadImgId;
		               addObj.orderby = _images.length + 1;
		               _images.push(addObj);
		               jQuery("[name=banner]").val(JSON.stringify(_images));
		            }else{
		                var addObj = response.data[0];
		                addObj.id = nowUploadImgId;
		                addObj.orderby = 1;
		                jQuery("[name=banner]").val(JSON.stringify(response.data));
		            }
		            $( '#'+file.id ).addClass('upload-state-done');
		        });
		        
		        // 文件上传失败，显示上传出错。
		        uploader_banner.on( 'uploadError', function( file ) {
		            var $li = $( '#'+file.id ),
		            $error = $li.find('div.error');

		            // 避免重复创建
		            if ( !$error.length ) {
		                $error = $('<div class="error"></div>').appendTo( $li );
		            }

		            $error.text('上传失败');
		        });
		        
		        // 完成上传完了，成功或者失败，先删除进度条。
		        uploader_banner.on( 'uploadComplete', function( file ) {
		            $( '#'+file.id ).find('.progress').remove();
		        });
		},
		
		getUploader:function(){
			return bannerUploader;
		}
	
	};

	
})($);


/** 编辑图片 **/
function editImg(domId,_this){
    var imgObj = getImgObj(domId);
    $("#editBannerId").val(imgObj.id);
    $("#editBannerTitle").val(imgObj.title);
    $("#editBannerUrl").val(imgObj.url);
    $("#editBannerOrderby").val(imgObj.orderby);
    layer.open({
       type: 1,
       area: ['500px','300px'],
       content: $('#bannerEditDiv') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
    });
}

/** 保存编辑的图片信息 **/
function saveEditImg(){
   var bannerId = $("#editBannerId").val();
   var bannerTitle = $("#editBannerTitle").val();
   var bannerUrl = $("#editBannerUrl").val();
   var bannerOrderby = parseInt($("#editBannerOrderby").val());

   var bannerJson = $("[name=banner]").val();
   var jsonObjArr = $.parseJSON(bannerJson);
   var oldOrderby = 0;
   if(jsonObjArr != null && jsonObjArr.length > 0){
       var jsonObj = null;
       for(var i=0;i<jsonObjArr.length;i++){
           jsonObj = jsonObjArr[i];
           if(jsonObj.id == bannerId){
               oldOrderby = jsonObj.orderby;
               jsonObj.title = bannerTitle;
               jsonObj.url = bannerUrl;
               jsonObj.orderby = bannerOrderby;
           }
           jsonObjArr[i] = jsonObj;
       }
   }
   
   // 变更的序号后面的序号依次+1
   for(var i=0;i<jsonObjArr.length;i++){
       var jsonObj = jsonObjArr[i];
       if(jsonObj.id != bannerId && jsonObj.orderby > bannerOrderby){
           jsonObj.orderby = parseInt(jsonObj.orderby) + 1;
       }else if(jsonObj.id != bannerId && jsonObj.orderby < bannerOrderby){
           jsonObj.orderby = parseInt(jsonObj.orderby) - 1;
       }else if(jsonObj.id != bannerId && jsonObj.orderby == bannerOrderby){
           if(oldOrderby > bannerOrderby){
               jsonObj.orderby = parseInt(jsonObj.orderby) + 1;
           }else{
               jsonObj.orderby = parseInt(jsonObj.orderby) - 1;
           }
       }
       jsonObjArr[i] = jsonObj;
   }
   
   // 排序
   jsonObjArr.sort(function(a,b){
       var c = a.orderby - b.orderby;
       return c;
   });
   
   // 再重新赋值排序
   for(var i=0;i<jsonObjArr.length;i++){
       jsonObjArr[i].orderby = i + 1;
   }
   
   // 重新生成html
   var html = "";
   for(var i=0;i<jsonObjArr.length;i++){
       var obj = jsonObjArr[i];
       html += '<div id="' + obj.id + '" name="showbanner" class="file-item thumbnail layui-inline" >' + 
                       '<img src="${ctx}' + obj.path + '" alt="" height="100" width="100" >' + 
                       '<div class="info">' + 
                           '<button type="button" style="width:50px;" class="layui-btn layui-btn-mini" ' + 
                               ' onclick="editImg(\'' + obj.id + '\',this);">编辑</button>' + 
                           '<button type="button" style="width:50px;margin-left: 0px;float: right;" ' + 
                               'class="layui-btn layui-btn-danger layui-btn-mini " ' + 
                               'onclick="deleteImgTips(\'' + obj.id + '\',this);" ' + 
                               'style="width:50px;margin-left:0;float:right;" >删除</button>' + 
                       '</div>' + 
               '</div>';
   }
   
   clearPic();
   $("#bannerList").prepend(html);
   $("[name=banner]").val(JSON.stringify(jsonObjArr));
   closeEditImg();
}

/** 关闭编辑图片信息的框 **/
function closeEditImg(){
   $("#editBannerId").val("");
   $("#editBannerTitle").val("");
   $("#editBannerUrl").val("");
   $("#editBannerOrderby").val("");
   layer.closeAll();
}

/** 询问是否删除图片 **/
function deleteImgTips(domId,_this){
	var uploader = $.banner.getUploader();
	var filesArr = uploader.getFiles();
	for (var i = 0; i < filesArr.length; i++) {
		if(domId.indexOf(filesArr[i].id) >= 0){
			uploader.removeFile(filesArr[i]);
		}
	}
	
	
    layer.confirm('确定要删除该图片？', {
         btn: ['确认', '关闭'] //可以无限个按钮
       }, function(index, layero){
           deleteImg(domId,_this);
           layer.closeAll();
       }, function(index){
         
       });
}

/** 删除图片 **/
function deleteImg(domId,_this){
   $($(_this).parent().parent()).remove()
   
   var bannerJson = $("[name=banner]").val();
   var jsonObjArr = $.parseJSON(bannerJson);

   var index = 0;
   var deleteNum = 1;
   if(jsonObjArr != null && jsonObjArr.length > 0){
       var jsonObj = null;
       for(var i=0;i<jsonObjArr.length;i++){
           jsonObj = jsonObjArr[i];
           if(jsonObj.id == domId){
               index = i;
               break;
           }
       }
       jsonObjArr.splice(index,1);
   }
   
   // 再重新赋值排序
   for(var i=0;i<jsonObjArr.length;i++){
       jsonObjArr[i].orderby = i + 1;
   }
   $("[name=banner]").val(JSON.stringify(jsonObjArr));
}

/** 从bannber中根据id获取某个img对象 **/
function getImgObj(id){
   var bannerJson = $("[name=banner]").val();
   var jsonObjArr = $.parseJSON(bannerJson);
   if(jsonObjArr != null && jsonObjArr.length > 0){
       var jsonObj = null;
       for(var i=0;i<jsonObjArr.length;i++){
           jsonObj = jsonObjArr[i];
           if(jsonObj.id == id){
               return jsonObj;
           }
       }
   }
}

/** 询问是否清除所有图片 **/
function clearPicTips(){
    layer.confirm('确定要删除所有图片？', {
         btn: ['确认', '关闭'] //可以无限个按钮
       }, function(index, layero){
           clearPic();
           layer.closeAll();
       }, function(index){
         
       });
}

/** 清除图片 **/
function clearPic(){
    jQuery("[name=banner]").val("");
    $("#bannerList [name=showbanner]").remove();
}

