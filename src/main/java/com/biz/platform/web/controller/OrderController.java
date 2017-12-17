package com.biz.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.biz.platform.web.pojo.Order;
import com.biz.platform.web.pojo.User;
import com.biz.platform.web.service.OrderService;
import com.biz.platform.web.utils.AjaxResult;
import com.biz.platform.web.utils.CollectionUtils;
import com.biz.platform.web.utils.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //通过 orderId获取order信息
    @RequestMapping("/loadOrder.do")
    @ResponseBody
    public Order loadOrder(@RequestBody Order order){
        return orderService.getOrderByOrderId(order);
    }


    //添加订单信息
    @RequestMapping("/addOrder.do")
    @ResponseBody
    public AjaxResult addOrder(@RequestBody Order order){

        int result = orderService.addOrder(order);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    //批量添加订单 未完善
    @RequestMapping("/batchAddOder.do")
    @ResponseBody
    public AjaxResult batchAddOder(@RequestBody String orderString,HttpServletRequest request){

        List<Order> orders = JSONObject.parseArray(orderString, Order.class);

        int result = orderService.batchAddOder(orders);

        //将临时的订单取消
        if(result == 1){
            request.getSession().setAttribute("tempOrder",new ArrayList<Order>());
        }

        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    //修改订单信息
    @RequestMapping("/updateOrder.do")
    @ResponseBody
    public AjaxResult updateOrder(@RequestBody Order order){

        int result = orderService.updateOrder(order);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    //通过orderId 删除订单
    @RequestMapping("/deleteOrder.do")
    @ResponseBody
    public AjaxResult deleteOrder(@RequestBody Order order){
        int result = orderService.deleteOrder(order);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    //添加临时订单
    @RequestMapping("/addTempOrder.do")
    @ResponseBody
    public List<Order> AddTempOrder(@RequestBody Order order,HttpServletRequest request){

        List<Order> list = new ArrayList<Order>();

        User user = (User) request.getSession().getAttribute("loginUser");

        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderBuyerId(user.getUserId());
        order.setOrderBuyerName(user.getUserName());
        list.add(order);

        //从session 中获取临时订单
        List<Order> tempList = (List<Order>) request.getSession().getAttribute("tempOrder");
        if(!CollectionUtils.isEmpty(tempList)){
            list.addAll(tempList);
        }

        request.getSession().setAttribute("tempOrder",list);

        return list;
    }

    //删除临时订单
    @RequestMapping("/deleteTempOrder.do")
    @ResponseBody
    public List<Order> deleteTempOrder(HttpServletRequest request,@RequestBody Order order){

        //从session 中获取临时订单
        List<Order> tempList = (List<Order>) request.getSession().getAttribute("tempOrder");

        if(CollectionUtils.isEmpty(tempList) || order == null || StringUtils.isNotNull(order.getOrderId())) {
            return null;
        }

        for (Order _order:
        tempList) {
            if(_order != null && StringUtils.isNotNullAndBlank(_order.getOrderId())){
                if(_order.getOrderId().equals(order.getOrderId())){
                    tempList.remove(_order);
                }
            }
        }

        request.getSession().setAttribute("tempOrder",tempList);

        return tempList;
    }

    //显示临时订单
    @RequestMapping("/getTempOrder.do")
    @ResponseBody
    public List<Order> getTempOrder(HttpServletRequest request){

        List<Order> list = new ArrayList<Order>();
        //从session 中获取临时订单
        List<Order> tempList = (List<Order>) request.getSession().getAttribute("tempOrder");

        if(!CollectionUtils.isEmpty(tempList)){
            list.addAll(tempList);
        }

        return list;
    }

    //通过userId获取该用户的订单信息 进行分页
    @RequestMapping("/getOrderByUserId.do")
    @ResponseBody
    public PageInfo<Order> getOrderByUserId(@RequestBody JSONObject jsonObject
                                , @RequestParam(name = "pageNum",defaultValue = "0") int pageNum
                                , @RequestParam(name = "pageSize",defaultValue = "10") int pageSize
                                , HttpServletRequest request){
        if(jsonObject != null && jsonObject.size() == 2){
            pageNum = jsonObject.getInteger("pageNum");
            pageSize = jsonObject.getInteger("pageSize");
        }

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if(loginUser == null){
            return null;
        }

        PageInfo<Order> orderPage = orderService.getOrderByUserId(pageNum,pageSize,loginUser);

        return orderPage;
    }

}
