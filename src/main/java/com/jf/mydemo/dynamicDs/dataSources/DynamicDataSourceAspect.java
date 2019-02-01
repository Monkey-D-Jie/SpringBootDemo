package com.jf.mydemo.dynamicDs.dataSources;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-01 10:22
 * @Description: 指明数据源切换的时机
 * To change this template use File | Settings | File and Templates.
 */
@Order(1)
@Aspect
@Repository
public class DynamicDataSourceAspect {

    @Pointcut("execution(* com..service..*Impl.*(..))")
    private void anyMethod() {
    }
    @Before(value="anyMethod()")
    public void beforeSwitchDS(JoinPoint point){

        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();

        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        String dataSource = DataSourceContextHolder.DEFAULT_DS;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);

            // 判断是否存在@DS注解
            if (method.isAnnotationPresent(DBSource.class)) {
                DBSource annotation = method.getAnnotation(DBSource.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }else{
                dataSource = (String) point.getArgs()[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //没有的就使用默认的数据源
        // 切换数据源
        DataSourceContextHolder.setDB(dataSource);
    }


//    @After("@annotation(com.levy.mysql.DBSource)")
    @AfterReturning(value = "anyMethod()", returning = "result")
    public void afterSwitchDS(JoinPoint point){
        DataSourceContextHolder.clearDB();
    }
}
