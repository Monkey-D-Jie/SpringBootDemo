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
//@Component
public interface UserDao {

    List<User> queryUsers();
}
