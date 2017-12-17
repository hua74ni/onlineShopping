package com.biz.platform.web.controller;

import com.biz.platform.web.pojo.FeedBack;
import com.biz.platform.web.pojo.Goods;
import com.biz.platform.web.pojo.Shop;
import com.biz.platform.web.pojo.User;
import com.biz.platform.web.service.FeedBackService;
import com.biz.platform.web.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Controller
@RequestMapping("/feedBack")
public class FeedBackController {

    @Autowired
    private FeedBackService feedBackService;

    //通过feedBackId 获取 反馈(feedBack)信息
    @RequestMapping("/loadFeedBack.do")
    @ResponseBody
    public FeedBack loadFeedBack(@RequestBody FeedBack feedBack){
        return feedBackService.getFeedBackByFeedBackId(feedBack);
    }

    //添加 反馈信息
    @RequestMapping("/addFeedBack.do")
    @ResponseBody
    public AjaxResult addFeedBack(@RequestBody FeedBack feedBack){

        int result = feedBackService.addFeedBack(feedBack);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    //通过feedBackId 进行修改 反馈信息
    @RequestMapping("/updateFeedBack.do")
    @ResponseBody
    public AjaxResult updateFeedBack(@RequestBody FeedBack feedBack){

        int result = feedBackService.updateFeedBack(feedBack);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    //通过 feedBackId 删除 反馈信息
    @RequestMapping("/deleteFeedBack.do")
    @ResponseBody
    public AjaxResult deleteFeedBack(@RequestBody FeedBack feedBack){
        int result = feedBackService.deleteFeedBack(feedBack);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }


    //判断 当前用户是否已经评论该商品 评论过1 未评论为0
    @RequestMapping("/checkUserIsFeedBack.do")
    @ResponseBody
    public int checkUserIsFeedBack(@RequestBody Goods goods, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("loginUser");

        int result = feedBackService.checkUserIsFeedBack(goods.getGoodsId(),user.getUserId());

        return result;
    }


}
