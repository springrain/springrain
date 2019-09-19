package org.springrain.system.api;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.system.base.BaseController;
import org.springrain.system.entity.*;
import org.springrain.system.service.IUserRoleMenuService;
import org.springrain.system.service.IUserRoleOrgService;
import org.springrain.system.service.IUserService;
import org.springrain.rpc.sessionuser.SessionUser;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/system/user", method = RequestMethod.POST)
public class UserController extends BaseController {

    @Resource
    private IUserRoleMenuService userRoleMenuService;


    @Resource
    private IUserRoleOrgService userRoleOrgService;

    @Resource
    private IUserService userService;




    /**
     * 列表数据
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ReturnDatas<User> list(@RequestBody Page<User> page)
            throws Exception {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
        // ==构造分页请求
        // Page page = newPage(request);
        // ==执行分页查询
        List<User> datas=userService.findListDataByFinder(null,page,User.class,page.getData());
        returnObject.setQueryBean(page.getData());
        returnObject.setPage(page);
        returnObject.setResult(datas);
        return returnObject;
    }

    /**
     * 查看的Json格式数据
     */
    @RequestMapping(value = "/look", method = RequestMethod.POST)
    public ReturnDatas<User> look(java.lang.String id) throws Exception {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();

        if(StringUtils.isNotBlank(id)){
            User user = userService.findUserById(id);
            returnObject.setResult(user);
        }else{
            returnObject.setStatus(ReturnDatas.ERROR);
        }
        return returnObject;

    }

    /**
     * 保存 操作,返回json格式数据
     *
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ReturnDatas<User> save(@RequestBody User user) {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.SAVE_SUCCESS);
        try {

            java.lang.String id =user.getId();
            if(StringUtils.isBlank(id)){
                user.setId(null);
            }
            userService.save(user);

            returnObject.setResult(user);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.SAVE_ERROR);
        }
        return returnObject;

    }


    /**
     * 修改 操作,返回json格式数据
     *
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ReturnDatas<User> update(@RequestBody User user) {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
        try {

            java.lang.String id =user.getId();
            if(StringUtils.isBlank(id)){
                return ReturnDatas.getErrorReturnDatas(MessageUtils.UPDATE_NULL_ERROR);
            }
            userService.update(user,false);

            returnObject.setResult(user);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.UPDATE_ERROR);
        }
        return returnObject;

    }


    /**
     * 更新用户角色关系
     * @param map  userId,roleIds
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateuserrole", method = RequestMethod.POST)
    public  ReturnDatas<String> updateuserrole(@RequestBody Map map) throws Exception {
        String userId= (String) map.get("userId");
        List<String> roleIds=( List<String>) map.get("roleIds");
        String str= userRoleMenuService.updateUserRoles(userId,roleIds);
        if(StringUtils.isBlank(str)){
            return ReturnDatas.getSuccessReturnDatas();
        }else{
            return ReturnDatas.getErrorReturnDatas(str);
        }
    }

    /**
     *  用户获取部门
     * @param map  userId,userOrgs
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findOrgByUserId", method = RequestMethod.POST)
    public  ReturnDatas<List<Org>> findOrgByUserId(@RequestBody Map map) throws Exception {
        String userId= (String) map.get("id");
        List<UserOrg> orgs = userRoleOrgService.findUserOrgByUserId(userId, null);
        ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
         returnDatas.setResult(orgs);
         return  returnDatas;

    }


    /**
     *  用户根据角色获取部门
     * @param map  userId,userOrgs
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findOrgByRoleId", method = RequestMethod.POST)
    public  ReturnDatas<List<Org>> findOrgByRoleId(@RequestBody Map map) throws Exception {
        String roleId= (String) map.get("roleid");
        List<RoleOrg> orgs = userRoleOrgService.findOrgByRoleId(roleId, null);
        ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
        returnDatas.setResult(orgs);
        return  returnDatas;

    }


    /**
     * 更新用户部门关系
     * @param userOrg
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateuserorg", method = RequestMethod.POST)
    public  ReturnDatas<String> updateuserorg(@RequestBody  UserOrg userOrg) throws Exception {

        String str= userRoleOrgService.updateUserOrg(userOrg);
        if(StringUtils.isBlank(str)){
            return ReturnDatas.getSuccessReturnDatas();
        }else{
            return ReturnDatas.getErrorReturnDatas(str);
        }
    }



    /**
     * 删除操作
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public  ReturnDatas<User> delete( java.lang.String id) throws Exception {
        // 执行删除
        try {

            if(StringUtils.isNotBlank(id)){
                userService.deleteById(id,User.class);
                return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_SUCCESS);
            } else {
                return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_NULL_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
    }

    /**
     * 删除多条记录
     *
     */
    @RequestMapping(value = "/delete/more", method = RequestMethod.POST)
    public ReturnDatas deleteMore(@RequestBody java.lang.String[] ids) {

        if (ids == null || ids.length < 1) {
            return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_NULL_ERROR);
        }
        try {
            List<String> listIds = Arrays.asList(ids);
            userService.deleteByIds(listIds,User.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_ERROR);
        }
        return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);

    }



    /**
     * 获取用户的 权限菜单
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/menu", method = RequestMethod.POST)
	public ReturnDatas menuIds() throws Exception {
       // 获取当前登录人
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }
        ReturnDatas successReturnDatas = ReturnDatas.getSuccessReturnDatas();
        List<Menu> listMenu = userRoleMenuService.findMenuByUserId(userId);

        List<String> listMenuIds=new ArrayList<>();
        listMenuIds.add("default");
        successReturnDatas.setResult(listMenuIds);

        if (CollectionUtils.isEmpty(listMenu)){
            return successReturnDatas;
        }

        for (Menu menu:listMenu){
            listMenuIds.add(menu.getId());
        }

        //List<Menu> listMenu = userRoleMenuService.findMenuTreeByUsreId(userId);

       //List<Map<String,Object>> listMap=new ArrayList<>();
       // 包装成Vue使用的树形结构
        //userRoleMenuService.wrapVueMenu(listMenu,listMap);

       // successReturnDatas.setResult(listMap);

        return successReturnDatas;
    }




    /**
     * 获取用户的角色
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getRolesByUserId", method = RequestMethod.POST)
    public ReturnDatas<Menu> getRolesByUserId() throws Exception {
        // 获取当前登录人
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }
        ReturnDatas successReturnDatas = ReturnDatas.getSuccessReturnDatas();
        List<Role> roles = userRoleMenuService.findRoleByUserId(userId);

        successReturnDatas.setResult(roles);

        return successReturnDatas;
    }


    /**
     * 获取用户的 部门
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/info", method = RequestMethod.POST)
    public ReturnDatas<Menu> getinfo() throws Exception {
        // 获取当前登录人
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }
        ReturnDatas successReturnDatas = ReturnDatas.getSuccessReturnDatas();

        User user = userService.findUserById(userId);

        List<Role> roles = userRoleMenuService.findRoleByUserId(userId);

        user.setRoles( roles);

        successReturnDatas.setResult(user);

        return successReturnDatas;
    }


}
