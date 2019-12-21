package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.model.request.RequestData;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import com.xuecheng.manage_cms.dao.CmsSiteRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsSiteService {

    @Autowired
    private CmsSiteRepository cmssiteRepository;




//    /**
//     * 页面查询方法,页码从1开始计数
//     * @param page
//     * @param size
//     * @param requestData
//     * @return
//     */
//    public QueryResponseResult findSiteList(int page,int size,RequestData requestData) {
//        if(requestData == null){
//            requestData = new RequestData();
//        }
//        // 自定义条件查询
//        // 自定义条件匹配器
//        ExampleMatcher  exampleMatcher =  ExampleMatcher.matching();
//        CmsPage cmsPage = new CmsPage();
//
//        // 设置条件值
//        if(StringUtils.isNotEmpty(requestData.getSiteId())){
//            cmsPage.setSiteId(requestData.getSiteId());
//        }
//        // 设置模板id
//        if(StringUtils.isNotEmpty(requestData.getTemplateId())){
//            cmsPage.setTemplateId(requestData.getTemplateId());
//        }
//        // 设置页面别名
//        if(StringUtils.isNotEmpty(requestData.getPageAliseName())){
//
//            cmsPage.setPageAliase(requestData.getPageAliseName());
//        }
//        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
//
//
//        if(page<=0){
//
//            page=0;
//        }
//        if(size<=0){
//            size=10;
//
//        }
//        page =page-1;
//
//        PageRequest pageRequest = PageRequest.of(page, size);
//        Page<CmsPage> page1 = cmssiteRepository.findAll(example,pageRequest);
//        QueryResult queryResult = new QueryResult();
//        queryResult.setList(page1.getContent());// 设置数据列表
//        queryResult.setTotal(page1.getTotalElements());// 数据总记录数
//        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
//
//        return queryResponseResult;
//
//
//
//    }

    /**
     *查询站点列表
     * @return
     */
  public List<CmsSite> findSiteList(){

      List<CmsSite> siteList = cmssiteRepository.findAll();

      return  siteList;

  }






}