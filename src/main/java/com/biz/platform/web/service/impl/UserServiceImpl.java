package com.biz.platform.web.service.impl;

import com.biz.platform.web.mapper.UserMapper;
import com.biz.platform.web.pojo.User;
import com.biz.platform.web.service.BaseService;
import com.biz.platform.web.service.UserService;
import com.biz.platform.web.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Service(value = "userService")
public class UserServiceImpl extends BaseService<User> implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUserCode(User user) {

        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userCode",user.getUserCode());
        List<User> users = userMapper.selectByExample(example);
        if(users.size() == 0){
            return null;
        }

        if(StringUtils.isNullOrBlank(user.getUserPassword())){
            return null;
        }
        String password = user.getUserPassword();
        user = users.get(0);

        if(!password.equals(user.getUserPassword())){
            return null;
        }

        return user;
    }

    @Override
    public User getUserByUserId(User user) {
        if(user == null || StringUtils.isNullOrBlank(user.getUserId())){
            return null;
        }
        return super.load(user);
    }

    @Override
    public int addUser(User user) {
        if(user == null){
            return 0;
        }
        user.setCreateTime(new Date());
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        if(user == null || StringUtils.isNullOrBlank(user.getUserId())){
            return 0;
        }
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int deleteUser(User user) {
        if(user == null || StringUtils.isNullOrBlank(user.getUserId())){
            return 0;
        }
        return userMapper.deleteByPrimaryKey(user.getUserId());
    }

}
