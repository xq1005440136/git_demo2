package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsPageRepository extends MongoRepository<CmsPage,String> {//主键类型是String

    // 根据页面名称查询
    CmsPage findByPageName(String pageName);

    // 根据页面的名称，站点id，webpath查询
    CmsPage findByPageWebPathAndSiteIdAndPageName(String pageName,String siteId,String pageWebPath);






}
