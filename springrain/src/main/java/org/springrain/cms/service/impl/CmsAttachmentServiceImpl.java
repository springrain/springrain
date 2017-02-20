package org.springrain.cms.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springrain.cms.entity.CmsAttachment;
import org.springrain.cms.service.ICmsAttachmentService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.SecUtils;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:17
 * @see org.springrain.cms.entity.demo.service.impl.CmsAttachment
 */
@Service("cmsAttachmentService")
public class CmsAttachmentServiceImpl extends BaseSpringrainServiceImpl implements ICmsAttachmentService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      CmsAttachment cmsAttachment=(CmsAttachment) entity;
	       return super.save(cmsAttachment).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      CmsAttachment cmsAttachment=(CmsAttachment) entity;
		 return super.saveorupdate(cmsAttachment).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 CmsAttachment cmsAttachment=(CmsAttachment) entity;
	return super.update(cmsAttachment);
    }
    @Override
	public CmsAttachment findCmsAttachmentById(String id) throws Exception{
	 return super.findById(id,CmsAttachment.class);
	}
	
/**
 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
 * @param finder
 * @param page
 * @param clazz
 * @param o
 * @return
 * @throws Exception
 */
        @Override
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz,
			Object o) throws Exception{
			 return super.findListDataByFinder(finder,page,clazz,o);
			}
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param finder 为空则只查询 clazz表
	 * @param ftlurl 类表的模版宏
	 * @param page 分页对象
	 * @param clazz 要查询的对象
	 * @param o  querybean
	 * @return
	 * @throws Exception
	 */
		@Override
	public <T> File findDataExportExcel(Finder finder,String ftlurl, Page page,
			Class<T> clazz, Object o)
			throws Exception {
			 return super.findDataExportExcel(finder,ftlurl,page,clazz,o);
		}

	@Override
	public String saveAttachment(MultipartFile file, String siteId,
			String businessId) throws Exception {
		
		//保存附件对象
		String fileUrl = "/upload/"+siteId+"/"+businessId+"/"+SecUtils.getUUID()+file.getOriginalFilename();
		String filePath = GlobalStatic.rootdir+fileUrl;
		
		
		
		File destFile = new File(filePath);
		if(!destFile.getParentFile().exists())
			destFile.getParentFile().mkdirs();
		if(!destFile.exists())
			destFile.createNewFile();
		file.transferTo(destFile);
		return fileUrl;
	}

}
