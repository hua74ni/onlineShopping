package com.biz.platform.web.service.impl;

import com.biz.platform.web.mapper.OrderMapper;
import com.biz.platform.web.mapper.ShopMapper;
import com.biz.platform.web.pojo.ConfirmOrder;
import com.biz.platform.web.pojo.Order;
import com.biz.platform.web.pojo.Shop;
import com.biz.platform.web.pojo.User;
import com.biz.platform.web.service.BaseService;
import com.biz.platform.web.service.OrderService;
import com.biz.platform.web.utils.CollectionUtils;
import com.biz.platform.web.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Service(value = "orderService")
public class OrderServiceImpl extends BaseService<Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public Order getOrderByOrderId(Order order) {
        if(order == null || StringUtils.isNullOrBlank(order.getOrderId())){
            return null;
        }
        return orderMapper.selectByPrimaryKey(order);
    }

    @Override
    public int addOrder(Order order) {
        if(order == null){
            return 0;
        }
        order.setCreateTime(new Date());
        return orderMapper.insertSelective(order);
    }

    @Override
    public int updateOrder(Order order) {
        if(order == null || StringUtils.isNullOrBlank(order.getOrderId())){
            return 0;
        }
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int deleteOrder(Order order) {
        if(order == null || StringUtils.isNullOrBlank(order.getOrderId())){
            return 0;
        }
        return orderMapper.deleteByPrimaryKey(order);
    }

    @Override
    public PageInfo<Order> getOrderByUserId(int pageNum, int pageSize, User user) {

        PageHelper.startPage(pageNum,pageSize);
        Example example = new Example(Order.class);
        if("buyer".equals(user.getUserType())){
            example.createCriteria().andEqualTo("orderBuyerId",user.getUserId());
        }else if ("shop".equals(user.getUserType())){
            Example example1 = new Example(Shop.class);
            example1.createCriteria().andEqualTo("shopUserId",user.getUserId());
            List<Shop> shops = shopMapper.selectByExample(example1);
            if (CollectionUtils.isEmpty(shops)){
                return new PageInfo<Order>(null);
            }
            Shop shop = shops.get(0);
            example.createCriteria().andEqualTo("orderShopId",shop.getShopId());
        }

        List<Order> orders = orderMapper.selectByExample(example);

        return new PageInfo<Order>(orders);
    }

    //未完善 依据实际修改
    @Override
    public int batchAddOder(ConfirmOrder confirmOrder) {

        List<Order> orders = confirmOrder.getOrders();
        User user = confirmOrder.getUser();

        if(CollectionUtils.isEmpty(orders)){
            return 0;
        }

        try{
            for (Order order:
                    orders) {

                order.setOrderId(null);
                order.setDeliveryEndAddr(user.getUserAddr());
                order.setOrderTotalPrice(order.getGoodsPrice() * order.getGoodsNum());
                order.setOrderBuyerId(user.getUserId());
                order.setOrderBuyerName(user.getUserName());
                //默认用户下订单就已经付款
                order.setOrderIsPay("1");
                order.setCreateTime(new Date());
                orderMapper.insertSelective(order);
            }
        }catch (Exception e){
            return 0;
        }

        return 1;
    }
}
