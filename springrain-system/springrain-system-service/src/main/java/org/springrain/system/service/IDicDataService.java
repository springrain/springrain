package org.springrain.system.service;

import org.springrain.frame.util.Page;
import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.system.entity.DicData;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author weicms.net<Auto generate>
 * @version 2013-07-31 15:56:45
 * @copyright {@link springrain}
 * @see org.springrain.springrain.service.DicData
 */
@RpcServiceAnnotation
public interface IDicDataService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    DicData findDicDataById(Object id) throws Exception;

    /**
     * 根据pathKey和Id查找对象
     *
     * @param id
     * @param pathtypekey
     * @return
     * @throws Exception
     */
    DicData findDicDataById(String id, String pathtypekey) throws Exception;

    /**
     * 根据pathKey查询所有字典
     *
     * @param pathtypekey
     * @return
     * @throws Exception
     */
    List<DicData> findListDicData(String pathtypekey, Page page, DicData dicData) throws Exception;

    /**
     * 根据Id和pathKey删除字典
     *
     * @param id
     * @param pathtypekey
     * @throws Exception
     */
    void deleteDicDataById(String id, String pathtypekey) throws Exception;

    /**
     * 根据Ids和pathKey删除字典
     *
     * @param ids
     * @param pathtypekey
     * @throws Exception
     */
    void deleteDicDataByIds(List<String> ids, String pathtypekey) throws Exception;


    /**
     * 从缓存中查询name
     *
     * @param id
     * @param pathtypekey
     * @return
     * @throws Exception
     */
    String findCacheNameById(String id, String pathtypekey) throws Exception;

    /**
     * 根据pathtypekey查询字典列表
     *
     * @param pathtypekey
     * @param sort        排序
     * @return
     * @throws Exception
     */
    List<DicData> findDisDataByPathtypeKey(String pathtypekey, String sort, Integer active) throws Exception;

    /**
     * 根据Code,typeKey获取字典
     *
     * @param code
     * @param typeKey
     * @return
     * @throws Exception
     */
    DicData findByCodeAndTypeKey(String code, String typeKey) throws Exception;

}