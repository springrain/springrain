function myreloadpage(){
	  setTimeout(function(){location.reload();},1000);
}

function submitForm(formName){
	document[formName].submit();
}


function f_newTab(tabTitle,tabHref){
	var now=new Date();
	var tabId=now.getTime();
	var iframeId="_iframe_"+tabId;
	var _fid=getCurrIframeId();
     jQuery.cookie(iframeId,_fid);
	top.parent.newTab(tabId,iframeId,tabTitle,tabHref);
}

function getCurrIframeId(){
	var id=null;
	   jQuery.each(jQuery("iframe",jQuery(self.parent.document.getElementById("easyui_center_tabs"))),function(i,_iframe){
	            if(window==_iframe.contentWindow){
	            	id= _iframe.id;
	             return false;
	            }
	           });
	   
	   return id;
	}


function reloadParentFrame(){
	try{
	var _fid=getCurrIframeId();
	var pid=  jQuery.cookie(_fid);
	//alert(pid);
	
	var _iframe=  jQuery("iframe[id='"+pid+"']",jQuery(self.parent.document.getElementById("easyui_center_tabs"))).get(0);
          _iframe.contentWindow.location.reload();
          
	}catch(e){
		alert(e);
	}
		
    
}