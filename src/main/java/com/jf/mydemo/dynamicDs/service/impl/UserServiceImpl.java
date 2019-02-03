package com.jf.mydemo.dynamicDs.service.impl;


import com.jf.mydemo.dynamicDs.dao.cluster.ClusterUserDao;
import com.jf.mydemo.dynamicDs.dao.master.MasterUserDao;
import com.jf.mydemo.dynamicDs.service.UserService;
import com.jf.mydemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-30 16:08
 * @Description: 查询用户信息的实现类
 * To change this template use File | Settings | File and Templates.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private MasterUserDao masterUserDao;

    @Autowired
    private ClusterUserDao clusterUserDao;

    /**
     * 在这里，可以通过注解，手动的切换在spring-mybatis中注册的数据源。
     * 如果没有指定，则使用默认的数据源。
     * 指定后，以这里指定的为准
     */

    // 使用默认数据源
    @Override
    public List<User> getAllUsers() {
        return this.masterUserDao.queryUsers();
    }
    @Override
    public List<User> getAllUsers2(String dsCode) {
        return this.masterUserDao.queryUsers();
    }
}
