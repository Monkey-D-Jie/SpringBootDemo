package com.jf.mydemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-01 14:45
 * @Description:
    1.关于Controller的回顾
    @RestController 	Spring4之后新加的注解
    常用于接受页面局部刷新的请求
    @RestController就是ajax提交，一般返回json格式，相当于我们经常使用的@ResponseBody配合@Controller组合
 * To change this template use File | Settings | File and Templates.
 */
@RestController
public class SpringBootRestController {

    @RequestMapping("/ajax/hello")
    public String say(){
        return "{'message1': 'SpringBoot你大爷','message2','SpringBoot你大爷2'}";
    }
}
