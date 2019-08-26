package org.springrain.weixin.sdk.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.frame.util.property.PropertyFile;

import java.util.HashMap;
import java.util.Map;

public class WxConsts {
    ///////////////////////
    // 微信推送过来的消息的类型，和发送给微信xml格式消息的消息类型
    ///////////////////////
    public static final String XML_MSG_TEXT = "text";
    public static final String XML_MSG_IMAGE = "image";
    public static final String XML_MSG_VOICE = "voice";
    public static final String XML_MSG_SHORTVIDEO = "shortvideo";
    public static final String XML_MSG_VIDEO = "video";
    public static final String XML_MSG_NEWS = "news";
    public static final String XML_MSG_MUSIC = "music";
    public static final String XML_MSG_LOCATION = "location";
    public static final String XML_MSG_LINK = "link";
    public static final String XML_MSG_EVENT = "event";
    public static final String XML_MSG_DEVICE_TEXT = "device_text";
    public static final String XML_MSG_DEVICE_EVENT = "device_event";
    public static final String XML_MSG_DEVICE_STATUS = "device_status";
    public static final String XML_MSG_HARDWARE = "hardware";
    public static final String XML_TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";
    ///////////////////////
    // 主动发送消息(即客服消息)的消息类型
    ///////////////////////
    public static final String CUSTOM_MSG_TEXT = "text";//文本消息
    public static final String CUSTOM_MSG_IMAGE = "image";//图片消息
    public static final String CUSTOM_MSG_VOICE = "voice";//语音消息
    public static final String CUSTOM_MSG_VIDEO = "video";//视频消息
    public static final String CUSTOM_MSG_MUSIC = "music";//音乐消息
    public static final String CUSTOM_MSG_NEWS = "news";//图文消息（点击跳转到外链）
    public static final String CUSTOM_MSG_MPNEWS = "mpnews";//图文消息（点击跳转到图文消息页面）
    public static final String CUSTOM_MSG_FILE = "file";//发送文件（CP专用）
    public static final String CUSTOM_MSG_WXCARD = "wxcard";//卡券消息
    public static final String CUSTOM_MSG_TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";
    public static final String CUSTOM_MSG_SAFE_NO = "0";
    public static final String CUSTOM_MSG_SAFE_YES = "1";
    ///////////////////////
    // 群发消息的消息类型
    ///////////////////////
    public static final String MASS_MSG_NEWS = "mpnews";
    public static final String MASS_MSG_TEXT = "text";
    public static final String MASS_MSG_VOICE = "voice";
    public static final String MASS_MSG_IMAGE = "image";
    public static final String MASS_MSG_VIDEO = "mpvideo";
    ///////////////////////
    // 群发消息后微信端推送给服务器的反馈消息
    ///////////////////////
    public static final String MASS_ST_SUCCESS = "send success";
    public static final String MASS_ST_FAIL = "send fail";
    public static final String MASS_ST_涉嫌广告 = "err(10001)";
    public static final String MASS_ST_涉嫌政治 = "err(20001)";
    public static final String MASS_ST_涉嫌社会 = "err(20004)";
    public static final String MASS_ST_涉嫌色情 = "err(20002)";
    public static final String MASS_ST_涉嫌违法犯罪 = "err(20006)";
    public static final String MASS_ST_涉嫌欺诈 = "err(20008)";
    public static final String MASS_ST_涉嫌版权 = "err(20013)";
    public static final String MASS_ST_涉嫌互推_互相宣传 = "err(22000)";
    public static final String MASS_ST_涉嫌其他 = "err(21000)";
    /**
     * 群发反馈消息代码所对应的文字描述
     */
    public static final Map<String, String> MASS_ST_2_DESC = new HashMap<>();
    ///////////////////////
    // 微信端推送过来的事件类型
    ///////////////////////
    public static final String EVT_SUBSCRIBE = "subscribe";
    public static final String EVT_UNSUBSCRIBE = "unsubscribe";
    public static final String EVT_SCAN = "SCAN";
    public static final String EVT_LOCATION = "LOCATION";
    public static final String EVT_CLICK = "CLICK";
    public static final String EVT_VIEW = "VIEW";
    public static final String EVT_MASS_SEND_JOB_FINISH = "MASSSENDJOBFINISH";
    public static final String EVT_SCANCODE_PUSH = "scancode_push";
    public static final String EVT_SCANCODE_WAITMSG = "scancode_waitmsg";
    public static final String EVT_PIC_SYSPHOTO = "pic_sysphoto";
    public static final String EVT_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    public static final String EVT_PIC_WEIXIN = "pic_weixin";
    public static final String EVT_LOCATION_SELECT = "location_select";
    public static final String EVT_TEMPLATESENDJOBFINISH = "TEMPLATESENDJOBFINISH";
    public static final String EVT_ENTER_AGENT = "enter_agent";
    public static final String EVT_CARD_PASS_CHECK = "card_pass_check";
    public static final String EVT_CARD_NOT_PASS_CHECK = "card_not_pass_check";
    public static final String EVT_USER_GET_CARD = "user_get_card";
    public static final String EVT_USER_DEL_CARD = "user_del_card";
    public static final String EVT_USER_CONSUME_CARD = "user_consume_card";
    public static final String EVT_USER_PAY_FROM_PAY_CELL = "user_pay_from_pay_cell";
    public static final String EVT_USER_VIEW_CARD = "user_view_card";
    public static final String EVT_USER_ENTER_SESSION_FROM_CARD = "user_enter_session_from_card";
    public static final String EVT_CARD_SKU_REMIND = "card_sku_remind"; // 库存报警
    public static final String EVT_KF_CREATE_SESSION = "kf_create_session"; // 客服接入会话
    public static final String EVT_KF_CLOSE_SESSION = "kf_close_session"; // 客服关闭会话
    public static final String EVT_KF_SWITCH_SESSION = "kf_switch_session"; // 客服转接会话
    public static final String EVT_POI_CHECK_NOTIFY = "poi_check_notify"; //门店审核事件推送
    /**
     * 资质认证成功
     */
    public static final String EVT_QUALIFICATION_VERIFY_SUCCESS = "qualification_verify_success";
    /**
     * 资质认证失败
     */
    public static final String EVT_QUALIFICATION_VERIFY_FAIL = "qualification_verify_fail";
    /**
     * 名称认证成功
     */
    public static final String EVT_NAMING_VERIFY_SUCCESS = "naming_verify_success";
    /**
     * 名称认证失败
     */
    public static final String EVT_NAMING_VERIFY_FAIL = "naming_verify_fail";
    /**
     * 年审通知
     */
    public static final String EVT_ANNUAL_RENEW = "annual_renew";
    /**
     * 认证过期失效通知
     */
    public static final String EVT_VERIFY_EXPIRED = "verify_expired";
    ///////////////////////
    // 上传多媒体文件的类型
    ///////////////////////
    public static final String MEDIA_IMAGE = "image";
    public static final String MEDIA_VOICE = "voice";
    //以下为微信认证事件
    public static final String MEDIA_VIDEO = "video";
    public static final String MEDIA_THUMB = "thumb";
    public static final String MEDIA_FILE = "file";
    ///////////////////////
    // 文件类型
    ///////////////////////
    public static final String FILE_JPG = "jpeg";
    public static final String FILE_MP3 = "mp3";
    public static final String FILE_AMR = "amr";
    public static final String FILE_MP4 = "mp4";
    /**
     * 点击推事件
     */
    public static final String BUTTON_CLICK = "click";
    /**
     * 跳转URL
     */
    public static final String BUTTON_VIEW = "view";
    /**
     * 扫码推事件
     */
    public static final String BUTTON_SCANCODE_PUSH = "scancode_push";
    /**
     * 扫码推事件且弹出“消息接收中”提示框
     */
    public static final String BUTTON_SCANCODE_WAITMSG = "scancode_waitmsg";
    /**
     * 弹出系统拍照发图
     */
    public static final String BUTTON_PIC_SYSPHOTO = "pic_sysphoto";
    /**
     * 弹出拍照或者相册发图
     */
    public static final String BUTTON_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    /**
     * 弹出微信相册发图器
     */
    public static final String BUTTON_PIC_WEIXIN = "pic_weixin";
    /**
     * 弹出地理位置选择器
     */
    public static final String BUTTON_LOCATION_SELECT = "location_select";
    /**
     * 下发消息（除文本消息）
     */
    public static final String BUTTON_MEDIA_ID = "media_id";


    ///////////////////////
    // 自定义菜单的按钮类型
    ///////////////////////
    /**
     * 跳转图文消息URL
     */
    public static final String BUTTON_VIEW_LIMITED = "view_limited";
    /**
     * 不弹出授权页面，直接跳转，只能获取用户openid
     */
    public static final String OAUTH2_SCOPE_BASE = "snsapi_base";
    /**
     * 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息
     */
    public static final String OAUTH2_SCOPE_USER_INFO = "snsapi_userinfo";
    /**
     * 网页应用登录授权作用域 snsapi_login
     */
    public static final String QRCONNECT_SCOPE_SNSAPI_LOGIN = "snsapi_login";
    ///////////////////////
    // 永久素材类型
    ///////////////////////
    public static final String MATERIAL_NEWS = "news";
    public static final String MATERIAL_VOICE = "voice";
    public static final String MATERIAL_IMAGE = "image";
    public static final String MATERIAL_VIDEO = "video";
    private static final Logger logger = LoggerFactory.getLogger(WxConsts.class);
    @SuppressWarnings("serial")
    private static final Map<Integer, String> errCodeToErrMsg = new HashMap<Integer, String>() {{
        put(-1, "系统繁忙");
        put(0, "请求成功");
        put(40001, "获取access_token时AppSecret错误，或者access_token无效");
        put(40002, "不合法的凭证类型");
        put(40003, "不合法的OpenID");
        put(40004, "不合法的媒体文件类型");
        put(40005, "不合法的文件类型");
        put(40006, "不合法的文件大小");
        put(40007, "不合法的媒体文件id");
        put(40008, "不合法的消息类型");
        put(40009, "不合法的图片文件大小");
        put(40010, "不合法的语音文件大小");
        put(40011, "不合法的视频文件大小");
        put(40012, "不合法的缩略图文件大小");
        put(40013, "不合法的APPID");
        put(40014, "不合法的access_token");
        put(40015, "不合法的菜单类型");
        put(40016, "不合法的按钮个数");
        put(40017, "不合法的按钮类型");
        put(40018, "不合法的按钮名字长度");
        put(40019, "不合法的按钮KEY长度");
        put(40020, "不合法的按钮URL长度");
        put(40021, "不合法的菜单版本号");
        put(40022, "不合法的子菜单级数");
        put(40023, "不合法的子菜单按钮个数");
        put(40024, "不合法的子菜单按钮类型");
        put(40025, "不合法的子菜单按钮名字长度");
        put(40026, "不合法的子菜单按钮KEY长度");
        put(40027, "不合法的子菜单按钮URL长度");
        put(40028, "不合法的自定义菜单使用用户");
        put(40029, "不合法的oauth_code");
        put(40030, "不合法的refresh_token");
        put(40031, "不合法的openid列表");
        put(40032, "不合法的openid列表长度,一次只能拉黑20个用户");
        put(40033, "不合法的请求字符，不能包含\\uxxxx格式的字符");
        put(40035, "不合法的参数");
        put(40037, "不合法的模板id");
        put(40038, "不合法的请求格式");
        put(40039, "不合法的URL长度");
        put(40050, "不合法的分组id");
        put(40051, "分组名字不合法");
        put(40053, "不合法的actioninfo，请开发者确认参数正确");
        put(40056, "不合法的Code码");
        put(40059, "不合法的消息id");
        put(40071, "不合法的卡券类型");
        put(40072, "不合法的编码方式");
        put(40078, "card_id未授权");
        put(40079, "不合法的时间");
        put(40080, "不合法的CardExt");
        put(40097, "参数不正确，请参考字段要求检查json字段");
        put(40099, "卡券已被核销");
        put(40100, "不合法的时间区间");
        put(40116, "不合法的Code码");
        put(40122, "不合法的库存数量");
        put(40124, "会员卡设置查过限制的 custom_field字段");
        put(40127, "卡券被用户删除或转赠中");
        put(40130, "不合法的openid列表长度, 长度至少大于2个");//invalid openid list size, at least two openid
        put(41001, "缺少access_token参数");
        put(41002, "缺少appid参数");
        put(41003, "缺少refresh_token参数");
        put(41004, "缺少secret参数");
        put(41005, "缺少多媒体文件数据");
        put(41006, "缺少media_id参数");
        put(41007, "缺少子菜单数据");
        put(41008, "缺少oauth code");
        put(41009, "缺少openid");
        put(41011, "缺少必填字段");
        put(41012, "缺少cardid参数");
        put(42001, "access_token超时");
        put(42002, "refresh_token超时");
        put(42003, "oauth_code超时");
        put(43001, "需要GET请求");
        put(43002, "需要POST请求");
        put(43003, "需要HTTPS请求");
        put(43004, "需要接收者关注");
        put(43005, "需要好友关系");
        put(43009, "自定义SN权限，请前往公众平台申请");
        put(43010, "无储值权限，请前往公众平台申请");
        put(43100, "修改模板所属行业太频繁");
        put(44001, "多媒体文件为空");
        put(44002, "POST的数据包为空");
        put(44003, "图文消息内容为空");
        put(44004, "文本消息内容为空");
        put(45001, "多媒体文件大小超过限制");
        put(45002, "消息内容超过限制");
        put(45003, "标题字段超过限制");
        put(45004, "描述字段超过限制");
        put(45005, "链接字段超过限制");
        put(45006, "图片链接字段超过限制");
        put(45007, "语音播放时间超过限制");
        put(45008, "图文消息超过限制");
        put(45009, "接口调用超过限制");
        put(45010, "创建菜单个数超过限制");
        put(45015, "回复时间超过限制");
        put(45016, "系统分组，不允许修改");
        put(45017, "分组名字过长");
        put(45018, "分组数量超过上限");
        put(45027, "模板与所选行业不符");//template conflict with industry
        put(45028, "没有群发配额");//has no masssend quota
        put(45030, "该cardid无接口权限");
        put(45031, "库存为0");
        put(45033, "用户领取次数超过限制get_limit");
        put(45056, "创建的标签数过多，请注意不能超过100个");
        put(45057, "该标签下粉丝数超过10w，不允许直接删除");
        put(45058, "不能修改0/1/2这三个系统默认保留的标签");
        put(45059, "有粉丝身上的标签数已经超过限制");
        put(45157, "标签名非法，请注意不能和其他标签重名");
        put(45158, "标签名长度超过30个字节");
        put(45159, "非法的tag_id");
        put(46001, "不存在媒体数据");
        put(46002, "不存在的菜单版本");
        put(46003, "不存在的菜单数据");
        put(46004, "不存在的用户");
        put(46005, "不存在的门店");
        put(47001, "解析JSON/XML内容错误");
        put(48001, "api功能未授权");
        put(48004, "api接口被封禁，请登录mp.weixin.qq.com查看详情");
        put(49003, "传入的openid不属于此AppID");
        put(50001, "用户未授权该api");
        put(50002, "用户受限，可能是违规后接口被封禁");
        put(61451, "参数错误(invalid parameter)");
        put(61452, "无效客服账号(invalid kf_account)");
        put(61453, "客服帐号已存在(kf_account exsited)");
        put(61454, "客服帐号名长度超过限制(仅允许10个英文字符，不包括@及@后的公众号的微信号)(invalid kf_acount length)");
        put(61455, "客服帐号名包含非法字符(仅允许英文+数字)(illegal character in kf_account)");
        put(61456, "客服帐号个数超过限制(10个客服账号)(kf_account count exceeded)");
        put(61457, "无效头像文件类型(invalid file type)");
        put(61450, "系统错误(system error)");
        put(61500, "日期格式错误");
        put(65104, "门店的类型不合法，必须严格按照附表的分类填写");
        put(65105, "图片url 不合法，必须使用接口1 的图片上传接口所获取的url");
        put(65106, "门店状态必须未审核通过");
        put(65107, "扩展字段为不允许修改的状态");
        put(65109, "门店名为空");
        put(65110, "门店所在详细街道地址为空");
        put(65111, "门店的电话为空");
        put(65112, "门店所在的城市为空");
        put(65113, "门店所在的省份为空");
        put(65114, "图片列表为空");
        put(65115, "poi_id 不正确");
        put(65301, "不存在此menuid对应的个性化菜单");
        put(65302, "没有相应的用户");
        put(65303, "没有默认菜单，不能创建个性化菜单");
        put(65304, "MatchRule信息为空");
        put(65305, "个性化菜单数量受限");
        put(65306, "不支持个性化菜单的帐号");
        put(65307, "个性化菜单信息为空");
        put(65308, "包含没有响应类型的button");
        put(65309, "个性化菜单开关处于关闭状态");
        put(65310, "填写了省份或城市信息，国家信息不能为空");
        put(65311, "填写了城市信息，省份信息不能为空");
        put(65312, "不合法的国家信息");
        put(65313, "不合法的省份信息");
        put(65314, "不合法的城市信息");
        put(65316, "该公众号的菜单设置了过多的域名外跳（最多跳转到3个域名的链接）");
        put(65317, "不合法的URL");
        put(65400, "API不可用，即没有开通/升级到新客服功能");
        put(65401, "无效客服帐号");
        put(65402, "帐号尚未绑定微信号，不能投入使用");
        put(65403, "客服昵称不合法");
        put(65404, "客服帐号不合法");
        put(65405, "帐号数目已达到上限，不能继续添加");
        put(65406, "已经存在的客服帐号");
        put(65407, "邀请对象已经是本公众号客服");
        put(65408, "本公众号已发送邀请给该微信号");
        put(65409, "无效的微信号");
        put(65410, "邀请对象绑定公众号客服数量达到上限（目前每个微信号最多可以绑定5个公众号客服帐号）");
        put(65411, "该帐号已经有一个等待确认的邀请，不能重复邀请");
        put(65412, "该帐号已经绑定微信号，不能进行邀请");
        put(65413, "不存在对应用户的会话信息");
        put(65414, "客户正在被其他客服接待");
        put(65415, "客服不在线");
        put(65416, "查询参数不合法");
        put(65417, "查询时间段超出限制");
        put(72015, "没有操作发票的权限，请检查是否已开通相应权限。");
        put(72017, "发票抬头不一致");
        put(72023, "发票已被其他公众号锁定");
        put(72024, "发票状态错误");
        put(72025, "wx_invoice_token无效");
        put(72028, "未设置微信支付商户信息");
        put(72029, "未设置授权字段");
        put(72030, "mchid无效");
        put(72031, "参数错误。可能为请求中包括无效的参数名称或包含不通过后台校验的参数值");
        put(72035, "发票已经被拒绝开票");
        put(72036, "发票正在被修改状态，请稍后再试");
        put(72038, "订单没有授权，可能是开票平台appid、商户appid、订单order_id不匹配");
        put(72039, "订单未被锁定");
        put(72040, "Pdf无效，请提供真实有效的pdf");
        put(72042, "发票号码和发票代码重复");
        put(72043, "发票号码和发票代码错误");
        put(72044, "发票抬头二维码超时");
        put(88000, "没有留言权限");
        put(9001001, "POST数据参数不合法");
        put(9001002, "远端服务不可用");
        put(9001003, "Ticket不合法");
        put(9001004, "获取摇周边用户信息失败");
        put(9001005, "获取商户信息失败");
        put(9001006, "获取OpenID失败");
        put(9001007, "上传文件缺失");
        put(9001008, "上传素材的文件类型不合法");
        put(9001009, "上传素材的文件尺寸不合法");
        put(9001010, "上传失败");
        put(9001020, "帐号不合法");
        put(9001021, "已有设备激活率低于50%，不能新增设备");
        put(9001022, "设备申请数不合法，必须为大于0的数字");
        put(9001023, "已存在审核中的设备ID申请");
        put(9001024, "一次查询设备ID数量不能超过50");
        put(9001025, "设备ID不合法");
        put(9001026, "页面ID不合法");
        put(9001027, "页面参数不合法");
        put(9001028, "一次删除页面ID数量不能超过10");
        put(9001029, "页面已应用在设备中，请先解除应用关系再删除");
        put(9001030, "一次查询页面ID数量不能超过50");
        put(9001031, "时间区间不合法");
        put(9001032, "保存设备与页面的绑定关系参数错误");
        put(9001033, "门店ID不合法");
        put(9001034, "设备备注信息过长");
        put(9001035, "设备申请参数不合法");
        put(9001036, "查询起始值begin不合法");
    }};

    ///////////////////////
    // oauth2网页授权的scope
    ///////////////////////
    //微信API的访问协议,为了以后方便处理特殊情况
    public static String mpapiurl = "https://api.weixin.qq.com";
    //微信API的访问协议,为了以后方便处理特殊情况
    public static String mpweixinurl = "https://mp.weixin.qq.com";
    //微信API的访问协议,为了以后方便处理特殊情况
    public static String mpopenurl = WxConsts.mpopenurl + "";
    //微信payBaseURL
    public static String mppaybaseurl = WxConsts.mppaybaseurl + "";
    //企业号API的访问协议,为了以后方便处理特殊情况
    public static String qyapiurl = "https://qyapi.weixin.qq.com";

    static {


        try {
            //处理配置文件默认值
            PropertyFile wechat = new PropertyFile("wechat");

            String _mpapiurl = wechat.getString("mpapiurl");
            if (_mpapiurl != null) {
                mpapiurl = _mpapiurl;
            }

            String _mpweixinurl = wechat.getString("mpweixinurl");
            if (_mpweixinurl != null) {
                mpweixinurl = _mpweixinurl;
            }

            String _mpopenurl = wechat.getString("mpopenurl");
            if (_mpopenurl != null) {
                mpopenurl = _mpopenurl;
            }

            String _mppaybaseurl = wechat.getString("mppaybaseurl");
            if (_mppaybaseurl != null) {
                mppaybaseurl = _mppaybaseurl;
            }

            String _qyapiurl = wechat.getString("qyapiurl");
            if (_qyapiurl != null) {
                qyapiurl = _qyapiurl;
            }


        } catch (Exception e) {
            logger.error("wechat配置文件加载失败:", e);
        }
    }

    private WxConsts() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    /**
     * 通过返回码获取返回信息
     *
     * @param errCode 错误码
     * @return {String}
     */
    public static String get(int errCode) {
        return errCodeToErrMsg.get(errCode);
    }


}
