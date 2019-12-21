package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.request.RequestData;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;

@Api(value="cms页面管理接口",description="cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {


 @ApiOperation("分页查询页面列表")
  @ApiImplicitParams({@ApiImplicitParam(name="page",value="页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value="每页记录数",required=true,paramType="path",dataType="int")
})
    public QueryResponseResult findList(int page,int size,RequestData requestData);

    /**
     * 条件查询
     * @param page
     * @param size
     * @param siteId
     * @param pageAlise
     * @return
     */
    public  QueryResponseResult findListByConditionintn(int page ,int size,String siteId,String pageAlise);


    /**
     * 新增页面
     */
    @ApiOperation("新增页面")
    public CmsPageResult addPage(CmsPage cmsPage);


    @ApiOperation("根据页面id查询页面")
    public CmsPage findById(@PathVariable("id") String pageId);

    @ApiOperation("修改页面")
    public CmsPageResult edit(String id,CmsPage cmsPage);

    @ApiOperation("删除页面")
    public ResponseResult delete(String id);
}
