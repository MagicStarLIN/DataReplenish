package com.lcl.datareplenish.controller;

import com.lcl.datareplenish.constant.ApiResult;
import com.lcl.datareplenish.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ModelController
 * @Description: controller
 * @date 2019/10/31 10:22 上午
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class ModelController {

    private static TestService testService;

    @RequestMapping("/foo")
    public static ApiResult fooController() {
        String msg = testService.testAutowired("this is a msg");
        System.err.println(msg);
        return ApiResult.success(msg);
    }


    public static void main(String[] args) {
        String s = "当处于逆时间的我发微博，在正向时间的你们看来其实是微博自动生成了这样的一串文字，然后我点击了删除罢了.";
        StringBuffer buffer = new StringBuffer(s);
        buffer.reverse();
        System.out.println(buffer.toString());

    }

}
