package com.biz.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.biz.platform.web.pojo.Order;
import com.biz.platform.web.pojo.User;
import com.biz.platform.web.service.OrderService;
import com.biz.platform.web.utils.AjaxResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/loadOrder.do")
    @ResponseBody
    public Order loadOrder(@RequestBody Order order){
        return orderService.getOrderByOrderId(order);
    }

    @RequestMapping("/addOrder.do")
    @ResponseBody
    public AjaxResult addOrder(@RequestBody Order order){

        int result = orderService.addOrder(order);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    @RequestMapping("/updateOrder.do")
    @ResponseBody
    public AjaxResult updateOrder(@RequestBody Order order){

        int result = orderService.updateOrder(order);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    @RequestMapping("/deleteOrder.do")
    @ResponseBody
    public AjaxResult deleteOrder(@RequestBody Order order){
        int result = orderService.deleteOrder(order);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

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
