package com.xuecheng.manage_cms.test;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.manage_cms.ManageCmsApplication;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = ManageCmsApplication.class)
@RunWith(SpringRunner.class)
public class CmsPageReposiroryTest {


    @Autowired
    private CmsPageRepository cmsPageRepository;


    @Test
    /**
     * 查询所有
     */
    public void findAll() {

        List<CmsPage> list = cmsPageRepository.findAll();

        for (CmsPage cmsPage : list) {
            System.out.println(cmsPage);
        }
    }

    @Test
    /**
     * 分页查询
     */
    public void findPage() {
        int page = 0;
        int pageSize = 10;

        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<CmsPage> list = cmsPageRepository.findAll(pageRequest);
        int i = 1;
        for (CmsPage cmsPage : list) {

            System.out.println(cmsPage + ":i:" + i);
            i++;
        }


    }

    /**
     * 修改
     */
    @Test
    public void update() {
        // 返回optional 是为了防止空指针异常
        Optional<CmsPage> optional = cmsPageRepository.findById("5a754adf6abb500ad05688d9");
        if (optional.isPresent()) {// 如果optional不为空;


            CmsPage cmsPage = optional.get();

            cmsPage.setPageAliase("哈哈哈");


            cmsPageRepository.save(cmsPage);

            Optional<CmsPage> op = cmsPageRepository.findById("5a754adf6abb500ad05688d9");

            if (op.isPresent()) {
                CmsPage cmsPage1 = op.get();


                System.out.println(cmsPage1);


            }


        }


    }

    /**
     * 自定义查询方法测试;
     */

    @Test
    public void findByPageNameTest() {
        CmsPage cmsPage = cmsPageRepository.findByPageName("index.html");


        System.out.println(cmsPage);

        int a = 3;

        boolean flag = true;


    }


    /**
     * 条件查询分页
     */
    @Test

    public void testFindAllByExample() {
        int page = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);
        // 条件值对象


        CmsPage cmsPage = new CmsPage();


//cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
//
//cmsPage.setTemplateId("5a962b52b00ffc514038faf7");
//        cmsPage.setPageName("index.html");
        cmsPage.setPageAliase("轮播");

        System.out.println(cmsPage.getTemplateId());



        // 创建条件匹配器
        ExampleMatcher exampleMatcher =ExampleMatcher.matching();

        exampleMatcher=exampleMatcher.withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
        // 定义example

        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);


        Page<CmsPage> page1 = cmsPageRepository.findAll(example, pageable);

        List<CmsPage> content = page1.getContent();

        System.out.println(content);

        System.out.println(content.size());


    }
    @Test
    public void testDemo(){


        System.out.println("hellalkdlaklfka");

    }

}
