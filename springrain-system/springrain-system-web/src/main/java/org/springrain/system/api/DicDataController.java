package org.springrain.system.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.system.base.BaseController;
import org.springrain.system.entity.DicData;
import org.springrain.system.service.IDicDataService;

/**
 * TODO 在此加入类描述
 *
 * @author 9iu.dicData<Auto generate>
 * @version 2013-07-31 15:56:45
 */
@RestController
@RequestMapping(value = "/api/system/dicdata")
public class DicDataController extends BaseController {
	@Resource
	private IDicDataService dicDataService;

	/**
	 * 查询所有字典类型信息
	 *
	 * @param request
	 * @param model
	 * @param dicData
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/type/list")
	public ReturnDatas<List<DicData>> list(HttpServletRequest request, Model model, Page<DicData> page) {
		ReturnDatas<List<DicData>> returnObject = ReturnDatas.getSuccessReturnDatas();
		List<DicData> datas = null;
		try {
			datas = dicDataService.findAllRootList(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("查询失败");
		}
		returnObject.setResult(datas);
		returnObject.setPage(page);
		return returnObject;
	}

	/**
	 * 根据类型查询类型下的字典列表
	 *
	 * @param request
	 * @param model
	 * @param dicData
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/type/{typeName}")
	@ResponseBody
	public ReturnDatas<List<DicData>> listjson(HttpServletRequest request, Model model, Page<DicData> page,
			@PathVariable String typeName) {
		ReturnDatas<List<DicData>> returnObject = ReturnDatas.getSuccessReturnDatas();
		List<DicData> datas = null;
		try {
			datas = dicDataService.findTypeListByTypeName(page, typeName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("查询失败");
		}
		returnObject.setResult(datas);
		returnObject.setPage(page);
		return returnObject;
	}

	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@PostMapping(value = "/type/update")
	public ReturnDatas<String> typeUpdate(@RequestBody DicData dicData) {
		ReturnDatas<String> returnObject = ReturnDatas.getSuccessReturnDatas();

		String dicDataId = null;
		try {
			if (StringUtils.isBlank(dicData.getId())) {
				// 保存新的字典类型
				dicDataId = dicDataService.saveDicDataType(dicData);
			} else {
				// 修改字典类型
				dicDataId = dicData.getId();
				dicDataService.update(dicData, true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("保存失败");
		}
		returnObject.setResult(dicDataId);
		return returnObject;
	}

	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	@ResponseBody
	public ReturnDatas<DicData> lookjson(@PathVariable String pathtypekey, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnDatas<DicData> returnObject = ReturnDatas.getSuccessReturnDatas();
		java.lang.String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			DicData dicData = dicDataService.findDicDataById(id, pathtypekey);
			returnObject.setResult(dicData);
		} else {
			returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
	}

	/**
	 * 新增/修改 操作吗,返回json格式数据
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ReturnDatas<?> saveorupdate(@PathVariable String pathtypekey, Model model, DicData dicData,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnDatas<?> returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String id = dicData.getId();
			String pid = dicData.getPid();
			if (StringUtils.isBlank(id)) {
				dicData.setId(null);
			}
			if (StringUtils.isBlank(pid)) {
				dicData.setPid(null);
			}
			dicData.setTypekey(pathtypekey);
			// dicDataService.save(dicData, pathtypekey);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;

	}

	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String edit(@PathVariable String pathtypekey, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnDatas<?> returnObject = lookjson(pathtypekey, model, request, response);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typekey", pathtypekey);
		returnObject.setMap(map);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/dicdata/dicdataCru";
	}

	/**
	 * 删除操作
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ReturnDatas<?> destroy(@PathVariable String pathtypekey, HttpServletRequest request) throws Exception {

		// 执行删除
		try {
			java.lang.String id = request.getParameter("id");
			if (StringUtils.isNotBlank(id)) {
				dicDataService.deleteDicDataById(id, pathtypekey);
				return new ReturnDatas<Object>(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas<Object>(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas<Object>(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
	}

	/**
	 * 删除多条记录
	 */
	@RequestMapping("/delete/more")
	@ResponseBody
	public ReturnDatas<?> delMultiRecords(@PathVariable String pathtypekey, HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		if (StringUtils.isBlank(records)) {
			return new ReturnDatas<Object>(ReturnDatas.ERROR, MessageUtils.DELETE_ALL_ERROR);
		}
		String[] rs = records.split(",");
		if (rs == null || rs.length < 1) {
			return new ReturnDatas<Object>(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
		}
		try {
			List<String> ids = Arrays.asList(rs);
			dicDataService.deleteDicDataByIds(ids, pathtypekey);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas<Object>(ReturnDatas.ERROR, MessageUtils.DELETE_ALL_ERROR);
		}
		return new ReturnDatas<Object>(ReturnDatas.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);
	}

}
