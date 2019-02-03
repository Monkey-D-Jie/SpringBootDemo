package com.jf.mydemo.dynamicDs.dao.cluster;

import com.jf.mydemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-03 10:06
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */
@Mapper
public interface ClusterUserDao /*extends UserBaseDao*/{
    Integer addUser(User user);

    Integer updateUserInfo(User user);

    Integer deleteUser(User user);
}
