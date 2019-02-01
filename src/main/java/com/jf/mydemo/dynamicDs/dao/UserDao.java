package com.jf.mydemo.dynamicDs.dao;


import com.jf.mydemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-30 16:03
 * @Description: 获取到用户信息的数据库操作类
 * To change this template use File | Settings | File and Templates.
 */
@Mapper
//测试后也是可以用的
//@Component
//测试可行
//@Repository
/**
 * 那问题来了：
 * 这仨用来声明这个有啥区别哇？
 *
 */
public interface UserDao {

    List<User> queryUsers();
}
