package org.springrain.system.service.impl;

import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.system.entity.Attachment;
import org.springrain.system.service.IAttachmentService;
import org.springrain.system.service.IUserRoleOrgService;

import java.util.List;


/**
 * 附件ServiceImpl
 *
 * @author springrain<Auto generate>
 * @version 2021-06-09 11:55:02
 */

@Service("attachmentService")
public class AttachmentServiceImpl extends BaseSpringrainServiceImpl implements IAttachmentService {

    @Resource
    @Lazy
    private IUserRoleOrgService userRoleOrgService;

    @Override
    public String save(IBaseEntity entity) throws Exception {
        Attachment attachment = (Attachment) entity;
        return super.save(attachment).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        Attachment attachment = (Attachment) entity;
        return super.update(attachment);
    }

    @Override
    public Attachment findAttachmentById(String id) throws Exception {
        return super.findById(id, Attachment.class);
    }

    @Override
    public void deleteByPolicyId(String policyId) throws Exception {
        Finder deleteFinder = Finder.getDeleteFinder(Attachment.class)
                .append(" where businessId=:policyId ")
                .setParam("policyId", policyId);
        super.update(deleteFinder);
    }

    @Override
    public void deleteByListId(String policyId, List<String> attachmentIdList) throws Exception {

        Finder deleteFinder = Finder.getDeleteFinder(Attachment.class)
                .append("where attachmentType=1 AND businessId=:businessId ").setParam("businessId", policyId);
        if (CollectionUtils.isNotEmpty(attachmentIdList)) {
            deleteFinder.append(" AND id not in (:attachmentIdList) ").setParam("attachmentIdList", attachmentIdList);
        }

        super.update(deleteFinder);
    }

    @Override
    public List<Attachment> findAttachmentByBusinessId(String businessId, Integer attachmentType) throws Exception {
        Finder selectFinder = Finder.getSelectFinder(Attachment.class)
                .append(" where businessId=:businessId")
                .append(" AND attachmentType=:attachmentType")
                .setParam("businessId", businessId)
                .setParam("attachmentType", attachmentType);
        Finder finder = userRoleOrgService.wrapOrgIdFinderByFinder(selectFinder, "orgId", "createUser");
        if (finder == null) {
            logger.error("wrapOrgIdFinderByFinder({})", selectFinder);
            throw new RuntimeException("当前用户的数据权限异常!");
        }
        return super.queryForList(selectFinder, Attachment.class);
    }

}
