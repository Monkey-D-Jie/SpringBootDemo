package com.jf.mydemo.druid;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-01 16:07
 * @Description: 设置druid的数据源监控设置
 * To change this template use File | Settings | File and Templates.
 */

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns="/druid/*",
        initParams={
@WebInitParam(name="allow",value="192.168.1.72,127.0.0.1"),// IP白名单(没有配置或者为空，则允许所有访问)
@WebInitParam(name="deny",value="192.168.1.73"),// IP黑名单 (存在共同时，deny优先于allow)
@WebInitParam(name="loginUsername",value="root"),// 用户名
@WebInitParam(name="loginPassword",value="123456"),// 密码
@WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
        }
                )
public class DruidStatViewServlet extends StatViewServlet {
    private static final long serialVersionUID = 1L;
}
