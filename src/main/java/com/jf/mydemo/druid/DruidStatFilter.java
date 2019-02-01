package com.jf.mydemo.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-01 16:10
 * @Description: druid的过滤器，忽略掉那些不用解析的静态类文件
 * To change this template use File | Settings | File and Templates.
 */

@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
        }
)
public class DruidStatFilter extends WebStatFilter {

}