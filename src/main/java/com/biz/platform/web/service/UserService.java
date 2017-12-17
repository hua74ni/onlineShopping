package com.biz.platform.web.service;

import com.biz.platform.web.pojo.User;

/**
 * Created by huangdonghua on 2017/12/14.
 */
public interface UserService {

    /**
     * 登录
     * @param user
     * @return
     */
    public User getUserCode(User user);

    /**
     * 通过userId 获取user
     * @param user
     * @return
     */
    public User getUserByUserId(User user);

    /**
     * 新增user
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 修改user
     * @param user
     * @return
     */
    public int updateUser(User user);

    //
    public int deleteUser(User user);
}
