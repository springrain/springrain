//#error		错误提示的id
//#neirong		内容id
//#captchaImg	验证码的id
/**
 * 提交
 */
function submit(){
	var hpzl=$("[name='hpzl']").val().trim();
	var hphm2b=$("[name='hphm2b']").val().trim();
	var captcha=$("[name='captcha']").val().trim();
	var hphm=hphm2a+hphm2b
	if(hphm2b==""||captcha==""){
		$("#error").show().html("请填写表单信息！")
        return;
	}
	
	var datas={
			hpzl:hpzl,
			hphm2a:"粤",
			hphm2b:hphm2b,
			hphm:hphm,
			captcha:captcha,
			qm:"wf",
			page:"1"
	};
    $.ajax({
        url:_ctx+"/f/mp/122/"+host+"/"+siteId+"/ajax/discard",
        data:datas,
        async:false,
        type:"post",
        success:function(data){
        	$("#error").hide()
        	reloadValidateCode122();
            if(data.status=="success"){
                data.data.sfbf==null?noLife(data.data.yqjyqzbfqz):life(data.data.qzbfqz);
                $(".scrapin_main").hide()
                $(".vtsresult_main").show()
            }else{
                $("#error").html(data.message).show();
            }
        }
    })
}
/**
 * 刷新验证码
 */
function reloadValidateCode122(){
    $("#captchaImg").css("background-image","url('"+_ctx+"/f/mp/122/"+host+"/"+siteId+"/getCaptcha122?loginCaptcha=false&date="+ new Date().getTime()+"')");
}


