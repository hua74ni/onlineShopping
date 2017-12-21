package com.biz.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.biz.platform.web.pojo.*;
import com.biz.platform.web.service.GoodsService;
import com.biz.platform.web.service.OrderService;
import com.biz.platform.web.service.ShopService;
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
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ShopService shopService;

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
    public AjaxResult batchAddOder(@RequestBody ConfirmOrder confirmOrder, HttpServletRequest request){

//        List<Order> orders = JSONObject.parseArray(orderString, Order.class);

        int result = orderService.batchAddOder(confirmOrder);

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

    /**
     * 添加临时订单
     * @param order
     * @param request
     * @return
     */
    @RequestMapping("/addTempOrder.do")
    @ResponseBody
    public AjaxResult AddTempOrder(@RequestBody Order order,HttpServletRequest request){

//        List<Order> list = new ArrayList<Order>();

        LinkedBlockingDeque deque = new LinkedBlockingDeque();

        Goods goods = new Goods();
        goods.setGoodsId(order.getGoodsId());

        //从session 中获取临时订单
//        List<Order> tempList = (List<Order>) request.getSession().getAttribute("tempOrder");
        LinkedBlockingDeque<Order> tempDeque = (LinkedBlockingDeque<Order>) request.getSession().getAttribute("tempOrder");

        if(!CollectionUtils.isEmpty(tempDeque)){
            for (Order _order:
                    tempDeque) {
                if(_order.getGoodsId().equals(order.getGoodsId())){
                    int tmp = _order.getGoodsNum();
                    tempDeque.remove(_order);
                    _order.setGoodsNum(tmp + 1);
                    tempDeque.add(_order);
                }
            }
        }

        goods = goodsService.getGoodsByGoodsId(goods);

        Shop shop = new Shop();
        shop.setShopId(goods.getGoodsShopId());
        shop = shopService.getShopByShopId(shop);

        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderShopId(goods.getGoodsShopId());
        order.setOrderShopName(shop.getShopName());
        order.setGoodsId(goods.getGoodsId());
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsLogoPath(goods.getGoodsLogoPath());
        order.setGoodsNum(1);
        order.setGoodsPrice(goods.getGoodsPrice());

        if(CollectionUtils.isEmpty(tempDeque)){
            tempDeque = new LinkedBlockingDeque<Order>();
        }
        tempDeque.add(order);

        request.getSession().setAttribute("tempOrder",tempDeque);

        return new AjaxResult(AjaxResult.STATUS_SUCCESS,new ArrayList<Order>(tempDeque));
    }

    /**
     * 删除临时订单
     * @param request
     * @param order     需要的数据  orderId
     * @return
     */
    @RequestMapping("/deleteTempOrder.do")
    @ResponseBody
    public AjaxResult deleteTempOrder(HttpServletRequest request,@RequestBody Order order){

        //从session 中获取临时订单
//        List<Order> tempList = (List<Order>) request.getSession().getAttribute("tempOrder");
        LinkedBlockingDeque<Order> tempDeque = (LinkedBlockingDeque<Order>) request.getSession().getAttribute("tempOrder");


        if(CollectionUtils.isEmpty(tempDeque) || order == null || StringUtils.isNullOrBlank(order.getOrderId())) {
            return null;
        }

        for (Order _order:
                tempDeque) {
            if(_order != null && StringUtils.isNotNullAndBlank(_order.getOrderId())){
                if(_order.getOrderId().equals(order.getOrderId())){
                    tempDeque.remove(_order);
                }
            }
        }

        request.getSession().setAttribute("tempOrder",tempDeque);

        return new AjaxResult(AjaxResult.STATUS_SUCCESS,new ArrayList<Order>(tempDeque));
    }

    //展示临时订单
    @RequestMapping("/getTempOrder.do")
    @ResponseBody
    public AjaxResult getTempOrder(HttpServletRequest request){

        List<Order> list = new ArrayList<Order>();
        //从session 中获取临时订单
//        List<Order> tempList = (List<Order>) request.getSession().getAttribute("tempOrder");
        LinkedBlockingDeque<Order> tempDeque = (LinkedBlockingDeque<Order>) request.getSession().getAttribute("tempOrder");


        if(!CollectionUtils.isEmpty(tempDeque)){
            list.addAll(tempDeque);
        }

        return new AjaxResult(AjaxResult.STATUS_SUCCESS,list);
    }

    /**
     * 确认订单
     * @param order     需要的数据orderId
     * @param request
     * @return
     */
    @RequestMapping("/confirmOrder.do")
    @ResponseBody
    public AjaxResult confirmOrder(@RequestBody Order order, HttpServletRequest request){

        order = orderService.getOrderByOrderId(order);

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if("buyer".equals(loginUser.getUserType())){
            if(order.getOrderBuyerId().equals(loginUser.getUserId())){
                order.setOrderBuyerStatu("1");
            }
        }else if("shop".equals(loginUser.getUserType())){
            if(order.getOrderShopId().equals(loginUser.getUserId())){
                order.setOrderShopStatu("1");
            }
        }

        if("1".equals(order.getOrderBuyerStatu()) && "1".equals(order.getOrderShopStatu())){
            order.setOrderStatu("1");
        }

        int result = orderService.updateOrder(order);

        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    /**
     * 取消订单
     * @param order  需要的数据 orderId
     * @param request
     * @return
     */
    @RequestMapping("/cancelOrder.do")
    @ResponseBody
    public AjaxResult cancelOrder(@RequestBody Order order, HttpServletRequest request){

        order = orderService.getOrderByOrderId(order);

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if("buyer".equals(loginUser.getUserType())){
            if(order.getOrderBuyerId().equals(loginUser.getUserId())){
                order.setOrderStatu("3");
            }
        }else if("shop".equals(loginUser.getUserType())){
            if(order.getOrderShopId().equals(loginUser.getUserId())){
                order.setOrderShopStatu("3");
            }
        }

        if("3".equals(order.getOrderBuyerStatu()) && "3".equals(order.getOrderShopStatu())){
            order.setOrderStatu("3");
        }

        int result = orderService.updateOrder(order);

        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    //通过userId获取该用户的订单信息 进行分页
    @RequestMapping("/getOrderByUserId.do")
    @ResponseBody
    public AjaxResult getOrderByUserId(@RequestBody JSONObject jsonObject
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

        return new AjaxResult(AjaxResult.STATUS_SUCCESS,orderPage);
    }



}
