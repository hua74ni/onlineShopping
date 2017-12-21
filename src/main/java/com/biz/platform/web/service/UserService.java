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

    /**
     * 删除用户
     * @param user
     * @return
     */
    public int deleteUser(User user);

    /**
     * 校验userCode是否已经存在
     * @param userCode
     * @return
     */
    public int checkUserCode(String userCode);

    /**
     * 通过userCode获取user信息
     * @param user
     * @return
     */
    public User getUserByUserCode(User user);
}
