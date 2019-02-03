package com.jf.mydemo.controller;

import com.jf.mydemo.dynamicDs.service.UserService;
import com.jf.mydemo.entity.BookBean;
import com.jf.mydemo.entity.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-01 14:30
 * @Description:
 * 1.关于Controller的回顾
 * Controller---处理http请求
 常用于接收页面的整体性请求
 @Controller就是整体页面刷新提交的处理注解
 * To change this template use File | Settings | File and Templates.
 */

@Controller
public class SpringBootController {

    @Value(value = "${book.author}")
    private String bookAuthor;
    @Value("${book.name}")
    private String bookName;
    @Value("${book.pinyin}")
    private String bookPinYin;

    @Autowired
    private BookBean bookBean;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/index",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String index(){
        return "Hello Spring Boot!"+"通过properties文件注入的值:"+bookAuthor+","+bookName+","+bookPinYin;
    }

    /**
     * 这里如果还要加上
     * 【,produces = "text/plain;charset=UTF-8"】
     * 的声明，请求进来后，会报错、、、
     *  //返回值为实体类时，‘产出’text/plain类型的返回值有错误，考虑是没有跟请求中的Accept能接受的类型匹配上
     关于【application/json 和 text/plain】的区别
     两个都是能用来作返回数据的声明嘞。。。
     只是呢 json的那个能更清楚的告诉浏览器：我就是json格式的数据，你可以直接用json的方式来接收我；
     而plain的方式：我给你的数据是什么格式的不太确定，你得自己再重新再解析下噢(相比Json 多了一层自行解析的过程，这应该是老技术中使用的方式了)
     现在升级了后，一般都是用的json那种类型的。
     */
    //标准的请求URI声明
    @RequestMapping(value = "/book",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
//    @RequestMapping(value = "/book",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public ResponseBean book() {
        return new ResponseBean(bookBean);
    }
    @RequestMapping("/addUser")
    @ResponseBody
    public ResponseBean addUser() {
        //在从库中插入数据-mytest2
        return new ResponseBean(this.userService.addUser("dbCluster"));
    }

    @RequestMapping("/getAllUsers")
    @ResponseBody
    public ResponseBean getAllUsers() {
        //从主库中读取数据-mytest1
        return new ResponseBean(this.userService.getAllUsers2("dbMaster"));
    }

    /*Freemarker测试-视图跳转，SpringBoot并不推荐使用JSP来作跳转的页面*/
    /**
         * 设置数据，返回到freemarker视图
         * @return
     来源：CSDN
     原文：https://blog.csdn.net/eeeeasy/article/details/80808805
     */
    @RequestMapping("/freemarker/say")
    public ModelAndView say(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("message", "SpringBoot 大爷你好！");
        //跳转至指定的freemarker界面去
        mav.setViewName("helloWorld");
        return mav;
   }
}
