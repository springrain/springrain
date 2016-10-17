package org.springrain.weixin.common.servcie;

public interface IWeiXinCommonService {
	
	
	
	/**
	 * 获取 accesstoken
	 * 文档地址:https://mp.weixin.qq.com/wiki/11/0e4b294685f817b95cbed85ba5e82b8f.html
	 * 文档地址:
	 * @param grantType
	 * @param appid
	 * @param secret
	 * @return
	 * @throws Exception
	 */
	public String getAccessToken(String grantType,String appid,String secret) throws Exception;
	
	/**
	 * 获取用户的openId
	 * 文档地址:https://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html
	 * @return
	 * @throws Exception
	 */
	public String getOpenid(String appid,String redirectUri,String scope,String state)throws Exception;
	
	/**
	 * 获取UnionID,默认中文:zh_CN 简体，zh_TW 繁体，en 英语
	 * 文档:https://mp.weixin.qq.com/wiki/14/bb5031008f1494a59c6f71fa0f319c66.html
	 * @return
	 */
	public String getUnionID(String accessToken,String openid)throws Exception;
	/**
	 * 获取UnionID
	 * 文档:https://mp.weixin.qq.com/wiki/14/bb5031008f1494a59c6f71fa0f319c66.html
	 * @return
	 */
	public String getUnionID(String accessToken,String openid,String lang)throws Exception;
	

}
