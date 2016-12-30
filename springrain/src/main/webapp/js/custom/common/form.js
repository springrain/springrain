var form;
layui.use('form', function(){
  form = layui.form();
});

function selectListener(filterId,callback){
	console.log(123);
	form.on('select('+filterId+')', function(data){
		  callback(data);
	});
}