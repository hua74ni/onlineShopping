package com.biz.platform.web.controller;

import com.biz.platform.web.pojo.FeedBack;
import com.biz.platform.web.pojo.Goods;
import com.biz.platform.web.pojo.Shop;
import com.biz.platform.web.pojo.User;
import com.biz.platform.web.service.FeedBackService;
import com.biz.platform.web.utils.AjaxResult;
import com.biz.platform.web.utils.StringUtils;
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

    /**
     * 添加 买家反馈信息
     * @param feedBack  需要的数据：feedBackId、feedbackMsg、orderId
     * @param request
     * @return
     */
    @RequestMapping("/addFeedBack.do")
    @ResponseBody
    public AjaxResult addFeedBack(@RequestBody FeedBack feedBack,HttpServletRequest request){

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if(loginUser.getUserType().equals("shop")){
            return new AjaxResult(AjaxResult.STATUS_ERROR,"当前登录的用户是商家不可评论商品");
        }

        feedBack.setBuyerId(loginUser.getUserId());

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


    //判断 当前用户是否已经评论该商品 评论过或者没有资格评论 返回值为"" 有订单但未评论 返回值为orderId
    @RequestMapping("/checkUserIsFeedBack.do")
    @ResponseBody
    public String checkUserIsFeedBack(@RequestBody Goods goods, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("loginUser");

        String result = feedBackService.checkUserIsFeedBack(goods.getGoodsId(),user.getUserId());

        return result;
    }

    /**
     * 商家 回复买家的评论
     * @param feedBack  需要的数据 feedBackId、shopRevert
     * @param request
     * @return
     */
    @RequestMapping("/revertFeedBack.do")
    @ResponseBody
    public AjaxResult revertFeedBack(@RequestBody FeedBack feedBack,HttpServletRequest request){

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if(loginUser.getUserType().equals("buyer")){
            return new AjaxResult(AjaxResult.STATUS_ERROR,"当前登录的用户是买家不可回复评论");
        }

        feedBack.setShopId(loginUser.getUserId());

        int result = feedBackService.revertFeedBack(feedBack);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }


}
