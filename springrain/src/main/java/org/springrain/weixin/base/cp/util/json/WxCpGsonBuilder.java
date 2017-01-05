package org.springrain.weixin.base.cp.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springrain.weixin.base.common.bean.result.WxError;
import org.springrain.weixin.base.common.util.json.WxErrorAdapter;
import org.springrain.weixin.base.cp.bean.WxCpDepart;
import org.springrain.weixin.base.cp.bean.WxCpMessage;
import org.springrain.weixin.base.cp.bean.WxCpTag;
import org.springrain.weixin.base.cp.bean.WxCpUser;

public class WxCpGsonBuilder {

  public static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxCpMessage.class, new WxCpMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpDepart.class, new WxCpDepartGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpUser.class, new WxCpUserGsonAdapter());
    INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
    INSTANCE.registerTypeAdapter(WxCpTag.class, new WxCpTagGsonAdapter());
  }

  public static Gson create() {
    return INSTANCE.create();
  }

}
