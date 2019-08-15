package org.springrain.system.service.impl;

import com.mysql.cj.Session;
import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.rpc.sessionuser.SessionUser;
import org.springrain.system.entity.Article;
import org.springrain.system.service.IArticleService;
import org.springrain.system.service.IUserRoleOrgService;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2019-07-24 14:55:40
 */

@Service("articleService")
public class ArticleServiceImpl extends BaseSpringrainServiceImpl implements IArticleService {



    @Resource
    private IUserRoleOrgService iUserRoleOrgService;

    @Override
    public String save(IBaseEntity entity) throws Exception {
        Article article = (Article) entity;
        return super.save(article).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        Article article = (Article) entity;
        return super.update(article);
    }

    @Override
    public Article findArticleById(String id) throws Exception {
        return super.findById(id, Article.class);
    }

    /**
     * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
     *
     * @param finder
     * @param page
     * @param clazz
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz,
                                            Object o) throws Exception {


//        Finder f = iUserRoleOrgService.wrapOrgIdFinderByUserId(SessionUser.getUserId());
//
//        finder.append("in(").appendFinder(f).append(")");


        return super.findListDataByFinder(finder, page, clazz, o);
    }

}
