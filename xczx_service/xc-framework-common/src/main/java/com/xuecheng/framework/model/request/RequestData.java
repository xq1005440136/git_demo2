package com.xuecheng.framework.model.request;

import lombok.Data;
import lombok.ToString;

/**
 * Created by mrt on 2018/3/5.
 */
@Data
@ToString
public class RequestData {

    //接收页面的查询条件;

    private  String siteId;// 站点id;


    private  String  pageId;// 页面id;


    private String  pageName;// 页面名称

    private  String pageAliase;// 页面别名

    private String templateId; // 模板id;




}
