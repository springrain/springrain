package org.springrain.weixin.common.dto;

import java.io.Serializable;

/**
 * 返回
 * @author caomei
 *
 */

public abstract class WeiXinReponseDataDto implements Serializable {

	private static final long serialVersionUID = 1L;
	//错误代码,为 nul 代表成功
    private String errcode;
    //错误详情
    private String errmsg;
   
    //返回结果的集合类型,为null 则是返回一个java对象
    private Class collectionType;
    
    //返回的java对象,如果为空 返回Map
    private Class clazz;
    
    //返回的数据类型,默认 json格式
    private String dataType="JSON";

    
    //返回的对象数据
    private Object data;
    
    
    /**
     * 子类实现,根据得到的json字符串,封装返回的数据
     * @param result
     * @return
     * @throws Exception
     */
    public abstract Object wrapResult(String result) throws Exception;
    
    


	public String getErrcode() {
		return errcode;
	}


	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}


	public String getErrmsg() {
		return errmsg;
	}


	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}


	public Class getCollectionType() {
		return collectionType;
	}


	public void setCollectionType(Class collectionType) {
		this.collectionType = collectionType;
	}


	public Class getClazz() {
		return clazz;
	}


	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}
	
	

}
