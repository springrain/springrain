package  org.springrain.demo.web;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.demo.entity.DicData;
import org.springrain.demo.service.IDicDataService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.CFReturnObject;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.MessageUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.SecUtils;


/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author 9iu.org<Auto generate>
 * @version  2013-07-31 15:56:45
 * @see org.springrain.demo.web.DicData
 */
@Controller
@RequestMapping(value="/dicdata")
public class DicDataController  extends BaseController {
	@Resource
	private IDicDataService dicDataService;
	
	private String listurl="/demo/dicdata/dicdataList";
	
	/** binder用于bean属性的设置 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {
	          super.initBinder(binder);
	}
	   
	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute("")
	public void init(Model model) {
		model.addAttribute("now", new Date());
	}
	
	/**
	* 进入dicdata Web页面后直接展现第一页数据
	*
	* @param request
	* @param model
	* @return
	* @author Mr.Hao<Auto generate>
	* @version  2013-07-31 15:56:45
	*/
	@RequestMapping("/{pathtypekey}/list")
	public String list(@PathVariable String pathtypekey,HttpServletRequest request, Model model,DicData dicData) throws Exception{
		model.addAttribute("typekey", pathtypekey);
		dicData.setTypekey(pathtypekey);
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<DicData> datas=dicDataService.findListDataByFinder(null,page,DicData.class,dicData);
		model.addAttribute("page", page);
		model.addAttribute("datas",datas);
		model.addAttribute("dicData",dicData);
	
		return listurl;
	}
	
	@RequestMapping("/{pathtypekey}/list/export")
	public void listexport(@PathVariable String pathtypekey,HttpServletRequest request,HttpServletResponse response, Model model,DicData dicData) throws Exception{
		model.addAttribute("typekey", pathtypekey);
		dicData.setTypekey(pathtypekey);
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = dicDataService.findDataExportExcel(null,listurl, page,DicData.class,dicData);
		String fileName="dicData"+GlobalStatic.excelext;
	
		downFile(response, file, fileName,true);
	
		return;
	}
	
	/**
	 * 查看操作
	 */
	@RequestMapping(value="/{pathtypekey}/look")
	public String look(@PathVariable String pathtypekey,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("typekey", pathtypekey);
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			DicData queryBean=new DicData();
			queryBean.setId(id);
			queryBean.setTypekey(pathtypekey);
		  DicData dicData = dicDataService.queryForObject(queryBean);
		   model.addAttribute("dicData",dicData);
		}
		return "/demo/dicdata/dicdataLook";
	}
	
	/**
	* 新增/修改save操作
	*
	* @param model
	* @param dicData
	* @param request
	* @param response
	* @return
	* @throws Exception
	* @author Mr.Hao<Auto generate>
	* @version  2013-07-31 15:56:45
	*/
	@RequestMapping("/{pathtypekey}/update")
	public String saveorupdate(@PathVariable String pathtypekey,Model model,DicData dicData,HttpServletRequest request,HttpServletResponse response) throws Exception{
		model.addAttribute("typekey", pathtypekey);
		dicData.setTypekey(pathtypekey);
		if(StringUtils.isBlank(dicData.getId())){// 新增
				dicData.setId(SecUtils.getUUID());
			try {
				dicDataService.save(dicData);
				model.addAttribute(message, MessageUtils.ADD_SUCCESS);
				return messageurl;
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			     model.addAttribute(message, MessageUtils.ADD_FAIL);
			     return messageurl;
		} else {// 修改
			try {
				dicData.setId(dicData.getId());
				dicDataService.update(dicData);
				model.addAttribute(message, MessageUtils.EDIT_SUCCESS);
				return messageurl;
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			model.addAttribute(message, MessageUtils.EDIT_WARING);
			return messageurl;
		}
		
	}
	
	/**
	 * 进入修改页面
	 */
	@RequestMapping(value="/{pathtypekey}/update/pre")
	public String edit(@PathVariable String pathtypekey,Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		model.addAttribute("typekey", pathtypekey);

		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			DicData queryBean=new DicData();
			queryBean.setId(id);
			queryBean.setTypekey(pathtypekey);
		   DicData dicData = dicDataService.queryForObject(queryBean);
		   model.addAttribute("dicData",dicData);
		}
		return "/demo/dicdata/dicdataCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/{pathtypekey}/delete")
	public @ResponseBody CFReturnObject destroy(@PathVariable String pathtypekey,HttpServletRequest request) throws Exception {
		// 执行删除
		try {
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			DicData queryBean=new DicData();
			queryBean.setId(id);
			queryBean.setTypekey(pathtypekey);
			dicDataService.deleteByEntity(queryBean);
			return new CFReturnObject(CFReturnObject.SUCCESS, MessageUtils.DELETE_SUCCESS);
			}else{
			    return new CFReturnObject(CFReturnObject.WARNING, MessageUtils.DELETE_WARNING);
			}
		} catch (Exception e) {
				logger.error(e.getMessage(),e);
		}
		return new CFReturnObject(CFReturnObject.WARNING, MessageUtils.DELETE_WARNING);
	}
	
	/**
	* 删除多条记录
	*
	* @param request
	* @param model
	* @return
	* @author Mr.Hao<Auto generate>
	* @version  2013-07-31 15:56:45
	*/
	@RequestMapping("/{pathtypekey}/delMulti")
	public @ResponseBody
	CFReturnObject delMultiRecords(@PathVariable String pathtypekey,HttpServletRequest request, Model model) {
		model.addAttribute("typekey", pathtypekey);
		String records = request.getParameter("records");
		String[] rs = records.split(",");
		if(rs==null||rs.length<1){
		return new CFReturnObject(CFReturnObject.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);
		}
		try {
			List<String> ids = Arrays.asList(rs);
			dicDataService.deleteByIds(ids, DicData.class);
			}
			catch (Exception e) {
				return new CFReturnObject(CFReturnObject.ERROR, MessageUtils.DELETE_ALL_FAIL);
			
			}
		return new CFReturnObject(CFReturnObject.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);
	}
	@RequestMapping("/{pathtypekey}/ajax/findDic")
	public @ResponseBody List<Map<String, Object>> findAjax(@PathVariable String pathtypekey)throws Exception{
		if(StringUtils.isBlank(pathtypekey)){
			return null;
		}
		List<String> list=dicDataService.findAjax(pathtypekey);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		List<Map<String, Object>> datas=new ArrayList<Map<String,Object>>();
		Map<String, Object> map=null;
		for(String str:list){
			map=new HashMap<String, Object>();
			map.put("text", str);
			datas.add(map);
		}
		return datas;
		
	}	
	
	@RequestMapping(value="/{pathtypekey}/ajax/findjson")
	public @ResponseBody List<DicData> findAjaxJSON(@PathVariable String pathtypekey)throws Exception{
		org.springrain.frame.util.Finder finder=new Finder("select * from t_dic_data where state=:state and typekey=:typekey ");
		finder.setParam("typekey", pathtypekey).setParam("state", "是");
		List<DicData> dicdatas=dicDataService.queryForList(finder, DicData.class);
		return dicdatas;
		
	}
	
	
	
	private Page newPage(HttpServletRequest request){
		//==获取分页信息
		int pageIndex = NumberUtils.toInt(request.getParameter("pageIndex"), 1);
		String order=request.getParameter("order");
		String sort=request.getParameter("sort");
		if(StringUtils.isBlank(order)){
		  order="id";
		}
		if(StringUtils.isBlank(sort)){
		  sort="desc";
		}
		
		Page page=new Page(pageIndex);
		page.setOrder(order);
		page.setSort(sort);
		return page;
	}
}
