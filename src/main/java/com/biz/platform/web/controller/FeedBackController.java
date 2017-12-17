package com.biz.platform.web.controller;

import com.biz.platform.web.pojo.FeedBack;
import com.biz.platform.web.service.FeedBackService;
import com.biz.platform.web.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Controller
@RequestMapping("/feedBack")
public class FeedBackController {

    @Autowired
    private FeedBackService feedBackService;

    @RequestMapping("/loadFeedBack.do")
    @ResponseBody
    public FeedBack loadFeedBack(@RequestBody FeedBack feedBack){
        return feedBackService.getFeedBackByFeedBackId(feedBack);
    }

    @RequestMapping("/addFeedBack.do")
    @ResponseBody
    public AjaxResult addFeedBack(@RequestBody FeedBack feedBack){

        int result = feedBackService.addFeedBack(feedBack);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    @RequestMapping("/updateFeedBack.do")
    @ResponseBody
    public AjaxResult updateFeedBack(@RequestBody FeedBack feedBack){

        int result = feedBackService.updateFeedBack(feedBack);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    @RequestMapping("/deleteFeedBack.do")
    @ResponseBody
    public AjaxResult deleteFeedBack(@RequestBody FeedBack feedBack){
        int result = feedBackService.deleteFeedBack(feedBack);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }


}
