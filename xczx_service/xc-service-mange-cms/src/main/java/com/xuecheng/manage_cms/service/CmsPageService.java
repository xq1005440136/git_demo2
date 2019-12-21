package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.CustomException;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.exception.ExceptionCatch;
import com.xuecheng.framework.model.request.RequestData;
import com.xuecheng.framework.model.response.*;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CmsPageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;


    /**
     * 页面查询方法,页码从1开始计数
     *
     * @param page
     * @param size
     * @param requestData
     * @return
     */
    public QueryResponseResult findList(int page, int size, RequestData requestData) {
        if (requestData == null) {
            requestData = new RequestData();
        }
        System.out.println(requestData.getPageAliase());
        // 自定义条件查询
        // 自定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        CmsPage cmsPage = new CmsPage();

        // 设置条件值
        if (StringUtils.isNotEmpty(requestData.getSiteId())) {
            cmsPage.setSiteId(requestData.getSiteId());
        }
        // 设置模板id
        if (StringUtils.isNotEmpty(requestData.getTemplateId())) {
            cmsPage.setTemplateId(requestData.getTemplateId());
        }
        // 设置页面别名
        if (StringUtils.isNotEmpty(requestData.getPageAliase())) {

            cmsPage.setPageAliase(requestData.getPageAliase());
        }
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);


        if (page <= 0) {

            page = 0;
        }
        if (size <= 0) {
            size = 10;

        }
        page = page - 1;

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CmsPage> page1 = cmsPageRepository.findAll(example, pageRequest);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(page1.getContent());// 设置数据列表
        queryResult.setTotal(page1.getTotalElements());// 数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);

        return queryResponseResult;


    }


    public QueryResponseResult findListByCondition(int page, int size, String siteId, String pageAlise) {
        // 自定义条件查询
        // 自定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        CmsPage cmsPage = new CmsPage();
        // 设置站点id；
        if (StringUtils.isNotEmpty(siteId)) {
            cmsPage.setSiteId(siteId);

        } else {
            cmsPage.setSiteId(null);
        }
        // 设置页面别名
        if (StringUtils.isNotEmpty(pageAlise)) {

            cmsPage.setPageAliase(pageAlise);
        } else {
            cmsPage.setPageAliase(null);
        }
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            size = 10;
        }
        page = page - 1;

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CmsPage> page1 = cmsPageRepository.findAll(example, pageRequest);
        QueryResult<CmsPage> queryResult = new QueryResult<CmsPage>();
        queryResult.setList(page1.getContent());
        queryResult.setTotal(page1.getTotalElements());

        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);

        return queryResponseResult;


    }

    /**
     * 添加页面
     *
     * @param cmsPage
     * @return
     */
    public CmsPageResult addPage(CmsPage cmsPage) {
        // 校验页面名称，站点id，页面的webpath来验证页面的唯一性；

        if(null==cmsPage){
            ExceptionCast.cast(CmsCode.CMS_ILLEGAL_PARAMETERS);
        }

            CmsPage cmsPage1 = cmsPageRepository.findByPageWebPathAndSiteIdAndPageName(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
            if (cmsPage1 != null) {
                // 页面已经存在
                // 抛出异常，页面已经存在；
                ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);


            }
        cmsPage.setPageId(null);
        // 调用dao新增页面
        CmsPage cmsPage2 = cmsPageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS, cmsPage2);


    }

    // 根据页面id查询页面
    public CmsPage findById(String pageId) {
        // 根据主键id
        Optional<CmsPage> optionalCmsPage = cmsPageRepository.findById(pageId);
        if (optionalCmsPage.isPresent()) { // 如果optionCmsPage不为null；

            CmsPage cmsPage = optionalCmsPage.get();


            return cmsPage;


        }
        return null;


    }

    /**
     * 修改页面
     *
     * @param cmsPage
     * @return
     */
    public CmsPageResult update(String pageId, CmsPage cmsPage) {
        CmsPage cmsPage1 = this.findById(pageId);

        if (cmsPage1 != null) {
            // 更新頁面模板id
            cmsPage1.setTemplateId(cmsPage.getTemplateId());
            // 更新頁面別名
            cmsPage1.setPageAliase(cmsPage.getPageAliase());
            // 更新页面站点id；
            cmsPage1.setSiteId(cmsPage.getSiteId());
            // 更新页面名称
            cmsPage1.setPageName(cmsPage.getPageName());
            // 更新访问路径
            cmsPage1.setPageWebPath(cmsPage.getPageWebPath());
            // 更新物理路径
            cmsPage1.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            cmsPageRepository.save(cmsPage1);

            return new CmsPageResult(CommonCode.SUCCESS, cmsPage1);


        }
        return new CmsPageResult(CommonCode.FAIL,null);// 如果cmsPage为null；
    }

    public ResponseResult delete(String id) {

        // 根据id查询页面
        CmsPage cmsPage = findById(id);
        if(cmsPage!=null){

            cmsPageRepository.deleteById(id);

          return new ResponseResult(CommonCode.SUCCESS);


        }

        return new ResponseResult(CommonCode.FAIL);


    }
}