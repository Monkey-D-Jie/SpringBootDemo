只是作了代码的搬运工，如果想看更多说明，请移步下述具体链接，谢谢配合

1.动态数据源配置部分
Spring-boot mybatis 多数据源动态切换 - 简书
https://www.jianshu.com/p/e6bd1a736e3a

2.Controller和RestController部分，来自
Spring boot学习笔记之三：SpringBoot之Controller使用 - HeJD的博客 - CSDN博客
https://blog.csdn.net/eeeeasy/article/details/80808805

    2-1
    在使用注解声明装配各类的时候，要注意主启动程序文件(即项目中SpringbootDemoApplication)所在的位置，
    其他打了注解的文件必须得在它所在包或所在包的子包里的才能被扫描到。
    打注解这块儿，尤其要注意，Dao层的接口要用Mapper才行，用@Component OR @Repository也可以，它们分别有什么区别，还待验证和查阅


3.SpringMVC注解上的produces声明中，【application/json 和 text/plain】的区别
text/plain和application/json发送数据都一样的效果，为什么没看见使用text/plain的？ - kikong的回答 - SegmentFault 思否
https://segmentfault.com/q/1010000012601154/a-1020000012601835

4.freeMarker示例，更多的参考，请移步
https://blog.csdn.net/eeeeasy/article/details/80808805

5.druid数据源监控，参考自
SpringBoot(七)：集成DataSource 与 Druid监控配置 - cctext - 博客园
https://www.cnblogs.com/yy3b2007com/p/8763077.html


