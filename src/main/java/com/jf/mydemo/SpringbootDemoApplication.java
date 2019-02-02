package com.jf.mydemo;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
//@ComponentScan(basePackages = {"com.jf.mydemo"})
//引入druid的监控配置包
@ServletComponentScan(value = "com.jf.mydemo.druid")
public class SpringbootDemoApplication {




	public static void main(String[] args) {
		//默认的创建方式:SpringApplication.run(SpringbootdemoApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(SpringbootDemoApplication.class);
		//关闭banner
//        builder.bannerMode(Banner.Mode.OFF).run(args);
        //开启banner
        builder.bannerMode(Banner.Mode.CONSOLE).run(args);
    }

}
