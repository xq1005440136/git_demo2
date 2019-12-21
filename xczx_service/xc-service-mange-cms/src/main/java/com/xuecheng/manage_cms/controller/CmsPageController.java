package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.request.RequestData;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {
    @Autowired
    private CmsPageService cmsPageService;
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size") int size, RequestData requestData) {
//        QueryResult<CmsPage>  queryResult = new QueryResult<CmsPage>();
//        List<CmsPage> list  = new ArrayList<CmsPage>();
//        CmsPage cmsPage = new CmsPage();
//        cmsPage.setPageName("测试页面");
//        list.add(cmsPage);
//        queryResult.setList(list);
//        queryResult.setTotal(1);
//
//
//        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
//        return queryResponseResult;
       return cmsPageService.findList(page,size,requestData);


    }

    @Override
    @GetMapping("/conditionlist/{page}/{size}")

    public QueryResponseResult findListByConditionintn(@PathVariable("page") int page, @PathVariable("size") int size, String siteId, String pageAlise) {
        return cmsPageService.findListByCondition(page,size,siteId,pageAlise);
    }

    /**
     * 新增页面
     * @return
     */
    @PostMapping("/add")
    @Override
    public CmsPageResult addPage(@RequestBody CmsPage cmsPage) {
        return cmsPageService.addPage(cmsPage);
    }

    /**
     * 根据id查询页面
     * @param pageId
     * @return
     */
    @Override
    @GetMapping("/get/{id}")
    public CmsPage findById(@PathVariable("id") String pageId) {
        return cmsPageService.findById(pageId);
    }

    @Override
    @PutMapping("/edit/{id}")// 这里使用put，http请求中put表示更新；
    public CmsPageResult edit(@PathVariable("id") String id,@RequestBody CmsPage cmsPage) {
        return cmsPageService.update(id,cmsPage) ;
    }

    @Override
    @DeleteMapping("/delete/{id}") // 逻辑删除
    public ResponseResult delete(@PathVariable("id") String id) {
        return cmsPageService.delete(id);

    }


}
