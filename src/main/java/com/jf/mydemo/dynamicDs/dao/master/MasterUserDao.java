package com.jf.mydemo.dynamicDs.dao.master;

import com.jf.mydemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-03 10:06
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */
@Mapper
public interface MasterUserDao/* extends UserBaseDao*/ {

    List<User> queryUsers();

    User selectUser(@Param("userId") String userId);
}
