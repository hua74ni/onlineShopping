package com.biz.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.biz.platform.web.pojo.Goods;
import com.biz.platform.web.pojo.Order;
import com.biz.platform.web.pojo.User;
import com.biz.platform.web.service.GoodsService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Controller
@RequestMapping("/order")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/loadGoods.do")
    @ResponseBody
    public Goods loadGoods(@RequestBody Goods goods){
        return goodsService.getGoodsByGoodsId(goods);
    }

    @RequestMapping("/addGoods.do")
    @ResponseBody
    public AjaxResult addGoods(@RequestBody Goods goods){

        int result = goodsService.addGoods(goods);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    @RequestMapping("/updateGoods.do")
    @ResponseBody
    public AjaxResult updateGoods(@RequestBody Goods goods){

        int result = goodsService.updateGoods(goods);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    @RequestMapping("/deleteGoods.do")
    @ResponseBody
    public AjaxResult deleteGoods(@RequestBody Goods goods){
        int result = goodsService.deleteGoods(goods);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    @RequestMapping("/getGoodsByUserId.do")
    @ResponseBody
    public PageInfo<Goods> getGoodsByUserId(@RequestBody JSONObject jsonObject,
                                        @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                                        @RequestParam(name = "pageSize",defaultValue = "10") int pageSize,
                                        HttpServletRequest request){

        if(jsonObject != null && jsonObject.size() == 2){
            pageNum = jsonObject.getInteger("pageNum");
            pageSize = jsonObject.getInteger("pageSize");
        }

        User user = (User) request.getSession().getAttribute("loginUser");
        PageInfo<Goods> goodsPage = goodsService.getGoodsByUserId(pageNum,pageSize,user.getUserId());

        return goodsPage;
    }

}
