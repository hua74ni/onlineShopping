package com.biz.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.biz.platform.web.pojo.Goods;
import com.biz.platform.web.pojo.Order;
import com.biz.platform.web.pojo.Shop;
import com.biz.platform.web.pojo.User;
import com.biz.platform.web.service.GoodsService;
import com.biz.platform.web.utils.AjaxResult;
import com.biz.platform.web.utils.PropertiesUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    private Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/loadGoods.do")
    @ResponseBody
    public Goods loadGoods(@RequestBody Goods goods){
        return goodsService.getGoodsByGoodsId(goods);
    }

    @RequestMapping("/goGoodsRedirect.do")
    public ModelAndView goLogin(HttpServletRequest request, ModelAndView modelAndView){

        modelAndView.setViewName("redirect:/test.do");

        return modelAndView;
    }

    @RequestMapping("/addGoods.do")
    public String addGoods(MultipartFile goodsImage, Goods goods, HttpServletRequest request){

        int result = goodsService.addGoods(goodsImage,goods);

        return "redirect:goGoodsRedirect.do?result="+result;
    }

    @RequestMapping("/updateGoods.do")
    public String updateGoods(MultipartFile goodsImage, Goods goods, HttpServletRequest request){

        int result = goodsService.updateGoods(goodsImage,goods);

        return "redirect:goGoodsRedirect.do?result="+result;
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
