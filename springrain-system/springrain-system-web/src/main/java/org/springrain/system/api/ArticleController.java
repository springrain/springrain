package org.springrain.system.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.system.base.BaseController;
import org.springrain.system.entity.Article;
import org.springrain.system.service.IArticleService;
import org.springrain.system.service.IUserRoleMenuService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2019-07-26 10:32:25
 */
@RestController
@RequestMapping(value="/api/system/article", method = RequestMethod.POST)
public class ArticleController  extends BaseController {
	@Resource
	private IArticleService articleService;

	@Resource
	private IUserRoleMenuService userRoleMenuService;
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 *
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ReturnDatas<Article> list(@RequestBody Page<Article> page)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		// Page page = newPage(request);
		// ==执行分页查询

//		Finder finder = Finder.getSelectFinder(Article.class).append(" WHERE 1=1 ");
//
//		if(page.getData().getCreateDate() != null){
//			page.getData().setCreateDate(null);
//		}
//
//		articleService.getFinderWhereByQueryBean(finder,page.getData());
//
//



		List<Article> datas=articleService.findListDataByFinder(null,page,Article.class,page.getData());


		returnObject.setQueryBean(page.getData());
		returnObject.setPage(page);
		returnObject.setResult(datas);
		return returnObject;
	}
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look", method = RequestMethod.POST)
	public ReturnDatas<Article> look(java.lang.String id) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();

		if(StringUtils.isNotBlank(id)){
			Article article = articleService.findArticleById(id);
			returnObject.setResult(article);
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
	public ReturnDatas<Article> save(@RequestBody Article article) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {

			java.lang.String id =article.getId();
			if(StringUtils.isBlank(id)){
				article.setId(null);
			}
			//article.setCreatePerson(SessionUser.getUserName());
			articleService.save(article);
			returnObject.setResult(article);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;

	}


	/**
	 * 修改 操作,返回json格式数据
	 *
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ReturnDatas<Article> update(@RequestBody Article article) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {

			java.lang.String id =article.getId();
			if(StringUtils.isBlank(id)){
				return ReturnDatas.getErrorReturnDatas("id不能为空.");
			}
			articleService.update(article);

			returnObject.setResult(article);


		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;

	}


	/**
	 * 删除操作
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public  ReturnDatas<Article> delete(@RequestBody JSONObject jsonObject) throws Exception {
		// 执行删除
		try {

			String id=jsonObject.get("id").toString();
			if(StringUtils.isNotBlank(id)){
				articleService.deleteById(id,Article.class);
				return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ERROR);
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
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ERROR);
		}
		try {
			List<String> listIds = Arrays.asList(ids);
			articleService.deleteByIds(listIds,Article.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_ERROR);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);

	}

}
