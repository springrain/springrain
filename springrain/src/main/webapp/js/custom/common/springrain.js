/**
 * springrain   javascript 封装
 */
(function($){
	$.springrain={
			/*跳转
			 * _url 要跳转的URL
			 * */
			goTo:function(_url){
				if(!_url)return;
				location.href=_url;
			},
			/*删除
			 * _id要删除的ID
			 * _url删除URL
			 * _tips删除提示，默认“是否删除”
			 * */
			mydelete:function(_id,_url,_tips){
				if(!_url||!_id)return;
				var _pars={"id":_id};
				_tips=_tips?_tips:'是否删除?';
				layer.confirm(_tips, {icon: 3, title:'提示'}, function(index){
					  jQuery.ajax({
						  url:_url,
						  type:"post",
						  data:_pars,
						  dataType:"json",
						  async:false,
						  success:function(data){
							  if(data!=null&&"success"==data.status){
								  layer.msg(data.message, {
									  icon: 1,
									  time: 2000 //2秒关闭（如果不配置，默认是3秒）
									}, function(){
									   window.location.reload();
									}); 
							  }else{
								  layer.msg(data.message, {icon: 1,time: 1000}); 
							  }
						  }
					  });
					  layer.close(index);
				});
			},
			/*
			 * 初始化 验证表单
			 * 要求：
			 * 表单名为validForm，提交按钮为：smtbtn，还原按钮：rstbtn，提示：.valid-info
			 * 参数：
			 * _before:请求前处理函数 
			 * _after:请求后处理函数
			 * 
			 * */
			initValid:function(_before,_after){
				var index = null;
				jQuery("#validForm").Validform({
					btnSubmit:"#smtbtn", 
					btnReset:"#rstbtn",
					tiptype:4, 
					tiptype:function(msg,o,cssctl){
						var _obj=jQuery(o.obj).parents(".layui-form-item").find(".valid-info");
						cssctl(_obj,o.type);
						_obj.text(msg);
					},
					ignoreHidden:false,
					dragonfly:false,
					tipSweep:false,
					label:".label",
					showAllError:true,
					postonce:true,
					ajaxPost:true,
					datatype:{
						"*6-20": /^[^\s]{6,20}$/,
						"z2-4" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/,
						"username":function(gets,obj,curform,regxp){
							//参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
							var reg1=/^[\w\.]{4,16}$/,
								reg2=/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,8}$/;
				 
							if(reg1.test(gets)){return true;}
							if(reg2.test(gets)){return true;}
							return false;
				 
							//注意return可以返回true 或 false 或 字符串文字，true表示验证通过，返回字符串表示验证失败，字符串作为错误提示显示，返回false则用errmsg或默认的错误提示;
						},
						"phone":function(){
							// 5.0 版本之后，要实现二选一的验证效果，datatype 的名称 不 需要以 "option_" 开头;	
						}
					},
					usePlugin:{
						swfupload:{},
						datepicker:{},
						passwordstrength:{},
						jqtransform:{
							selector:"select,input"
						}
					},
					beforeCheck:function(curform){
						//在表单提交执行验证之前执行的函数，curform参数是当前表单对象。
						//这里明确return false的话将不会继续执行验证操作;	
					},
					beforeSubmit:function(curform){
						layer.load();
						if(_before!=null &&typeof(_before)=="function"){
							_before();
						}
						//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
						//这里明确return false的话表单将不会提交;	
					},
					callback:function(data){
						if(_after!=null &&typeof(_after)=="function"){
							_after();
						}
						layer.close(index);
						//返回数据data是json对象，{"info":"demo info","status":"y"}
						//info: 输出提示信息;
						//status: 返回提交数据的状态,是否提交成功。如可以用"y"表示提交成功，"n"表示提交失败，在ajax_post.php文件返回数据里自定字符，主要用在callback函数里根据该值执行相应的回调操作;
						//你也可以在ajax_post.php文件返回更多信息在这里获取，进行相应操作；
						//ajax遇到服务端错误时也会执行回调，这时的data是{ status:**, statusText:**, readyState:**, responseText:** }；
						//这里执行回调操作;
						//注意：如果不是ajax方式提交表单，传入callback，这时data参数是当前表单对象，回调函数会在表单验证全部通过后执行，然后判断是否提交表单，如果callback里明确return false，则表单不会提交，如果return true或没有return，则会提交表单。
					}
				});
			}
	}
})(window);