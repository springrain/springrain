//查看是否有违法记录

$(function(){
	$.ajax({
        url:_ctx+"/f/mp/122/"+host+"/"+siteId+"/ajax/drvendorse",
        type:"post",
        async:false,
        data:{page:"1",size:"10"},          
        success:function(data){
        if(data.status!="success"){
           	$("#drvendorse").html(data.message).attr("onclick",false).css("background","silver")
          } 
        }
    })
})