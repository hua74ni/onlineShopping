package com.biz.platform.web.service;

import com.biz.platform.web.pojo.Order;
import com.biz.platform.web.pojo.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by huangdonghua on 2017/12/14.
 */
public interface OrderService {
    /**
     * 通过orderId获取order
     * @param order
     * @return
     */
    public Order getOrderByOrderId(Order order);

    /**
     * 新增order
     * @param order
     * @return
     */
    public int addOrder(Order order);

    /**
     * 修改order
     * @param order
     * @return
     */
    public int updateOrder(Order order);

    /**
     * 删除order
     * @param order
     * @return
     */
    public int deleteOrder(Order order);

    /**
     * 通过userId获取该买家用户的所有订单 进行分页
     *
     * @param pageNum
     * @param pageSize
     * @param loginUser
     * @return
     */
    public PageInfo<Order> getOrderByUserId(int pageNum, int pageSize, User loginUser);

}
