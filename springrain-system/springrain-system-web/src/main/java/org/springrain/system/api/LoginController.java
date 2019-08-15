package org.springrain.system.api;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springrain.frame.util.*;
import org.springrain.rpc.sessionuser.SessionUser;
import org.springrain.rpc.sessionuser.UserVO;
import org.springrain.system.base.BaseController;
import org.springrain.system.entity.User;
import org.springrain.system.service.IUserRoleMenuService;
import org.springrain.system.service.IUserService;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping(value = "/api", method = RequestMethod.POST)
public class LoginController extends BaseController {

    @Resource
    private IUserService userService;

    @Resource
    private IUserRoleMenuService userRoleMenuService;

    @Resource
    private CacheManager cacheManager;

    /**
     * 健康检查
     *
     * @return
     */
    @RequestMapping(value = "/checkHealth", method = RequestMethod.GET)
    public ReturnDatas<String> checkHealth() {
        return ReturnDatas.getSuccessReturnDatas();
    }


    /**
     * 生成验证码
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getCaptcha", method = RequestMethod.POST)
    public ReturnDatas getCaptcha(String captchaKey) throws IOException {

        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.IMAGE_JPEG);
        ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            StringBuilder code = new StringBuilder();
            BufferedImage image = CaptchaUtils.genRandomCodeImage(code);

            Cache cache = cacheManager.getCache(GlobalStatic.springranloginCaptchaKey);
            if (StringUtils.isNotBlank(captchaKey)) {
                cache.evict(captchaKey);
            } else {
                captchaKey = SecUtils.getUUID();
            }
            cache.put(captchaKey, code.toString());

            ImageIO.write(image, "JPEG", os);
            String imageBase64 = String.format("data:image/jpeg;base64,%s", SecUtils.encoderByBase64(os.toByteArray()));

            ConcurrentMap<String, String> concurrentMap = Maps.newConcurrentMap();
            concurrentMap.put("captchaKey", captchaKey);
            concurrentMap.put("imageBase64", imageBase64);
            returnDatas.setResult(concurrentMap);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }

        return returnDatas;
    }

    /**
     * 处理后台用户登录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/system/logout", method = RequestMethod.POST)
    public ReturnDatas systemLogout() throws Exception {


        // 获取当前登录人
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }


        ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();

        return returnDatas;

    }


    /**
     * 处理后台用户登录
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/system/login", method = RequestMethod.POST)
    public ReturnDatas systemLoginPost(@RequestBody UserVO userVO) throws Exception {

        if (StringUtils.isBlank(userVO.getAccount())) {
            return ReturnDatas.getErrorReturnDatas("用户不能为空");
        }

        if (StringUtils.isBlank(userVO.getPassword())) {
            return ReturnDatas.getErrorReturnDatas("密码不能为空");

        }


        ConcurrentMap resutltMap = Maps.newConcurrentMap();
        // 处理密码错误缓存
        Cache cache = cacheManager.getCache(GlobalStatic.springrainloginCacheKey);
        String errorLogincountKey = userVO.getAccount() + "_errorlogincount";
        Integer errorLogincount = cache.get(errorLogincountKey, Integer.class);

        if (errorLogincount != null && errorLogincount >= GlobalStatic.ERROR_LOGIN_COUNT) {// 密码连续错误10次以上

            String errorMessage = "密码连续错误超过" + GlobalStatic.ERROR_LOGIN_COUNT + "次,账号被锁定,请"
                    + GlobalStatic.ERROR_LOGIN_LOCK_MINUTE + "分钟之后再尝试登录!";

            Long endDateLong = cache.get(userVO.getAccount() + "_endDateLong", Long.class);
            Long now = System.currentTimeMillis() / 1000;// 秒
            if (endDateLong == null) {
                endDateLong = now + GlobalStatic.ERROR_LOGIN_LOCK_MINUTE * 60;// 秒
                cache.put(userVO.getAccount() + "_endDateLong", endDateLong);
                return ReturnDatas.getErrorReturnDatas(errorMessage);
            } else if (now > endDateLong) {// 过了失效时间
                cache.evict(errorLogincountKey);
                cache.evict(userVO.getAccount() + "_endDateLong");

            } else {
                return ReturnDatas.getErrorReturnDatas(errorMessage);
            }

        }


        User user = userService.findLoginUser(userVO.getAccount(), userVO.getPassword(), userVO.getUserType());
        if (user == null) {//登录失败
            if (errorLogincount == null) {
                errorLogincount = 0;
            }
            errorLogincount = errorLogincount + 1;
            cache.put(errorLogincountKey, errorLogincount);

            return ReturnDatas.getErrorReturnDatas("账号或密码错误");
        }

        Map<String, Object> jwtSignMap = new HashMap<>();
        jwtSignMap.put("userId", user.getId());
        jwtSignMap.put("account", user.getAccount());
        jwtSignMap.put("userName", user.getUserName());
        jwtSignMap.put("userType", user.getUserType());

        String jwtToken = JwtUtils.sign(jwtSignMap);
        // RSA 私钥加密
        jwtToken = SecUtils.encoderByRSAPrivateKey(jwtToken);


        resutltMap.put(GlobalStatic.jwtTokenKey, jwtToken);


        ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();


        //设置  权限菜单数据
        // List<Menu> listMenu = userRoleMenuService.findMenuTreeByUsreId(user.getId());
        //List<Map<String,Object>> listMap=new ArrayList<>();


        // List<String> roles = new ArrayList<>();

        //roles.add("admin");


        //包装成Vue使用的树形结构
        // userRoleMenuService.wrapVueMenu(listMenu,listMap);

        //  resutltMap.put("menus",listMap);
        // resutltMap.put("roles",roles);


        returnDatas.setResult(resutltMap);

        //登录成功,清空错误次数
        cache.evict(errorLogincountKey);
        cache.evict(userVO.getAccount() + "_endDateLong");


        return returnDatas;
    }


    /**
     * 处理前台用户登录
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ReturnDatas userLoginPost(@RequestBody UserVO userVO) throws Exception {
        return systemLoginPost(userVO);
    }


    /**
     * 员工登录地址
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/work/login", method = RequestMethod.POST)
    public ReturnDatas workLoginPost(@RequestBody UserVO userVO) throws Exception {
        return systemLoginPost(userVO);
    }


}
