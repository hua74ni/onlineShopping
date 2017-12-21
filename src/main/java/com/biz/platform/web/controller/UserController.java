package com.biz.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.biz.platform.web.pojo.Order;
import com.biz.platform.web.pojo.User;
import com.biz.platform.web.service.UserService;
import com.biz.platform.web.utils.AjaxResult;
import com.biz.platform.web.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huangdonghua on 2017/12/13.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //测试案例
    @RequestMapping(value = "/test.do")
    public String test(){
        return "index";
    }


    //通过userId获取用户信息
    @RequestMapping("/loadUser.do")
    @ResponseBody
    public AjaxResult load(@RequestBody User user){
        return new AjaxResult(AjaxResult.STATUS_SUCCESS,userService.getUserByUserId(user));
    }

    //添加用户
    @RequestMapping("/addUser.do")
    @ResponseBody
    public AjaxResult addUser(@RequestBody User user){

        int result = userService.addUser(user);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    //修改用户信息
    @RequestMapping("/updateUser.do")
    @ResponseBody
    public AjaxResult updateUser(@RequestBody User user){

        int result = userService.updateUser(user);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    //删除用户
    @RequestMapping("/deleteUser.do")
    @ResponseBody
    public AjaxResult deleteUser(@RequestBody User user){
        int result = userService.deleteUser(user);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    //校验userCode是否已经存在
    @RequestMapping("/checkUserCode.do")
    @ResponseBody
    public AjaxResult checkUserCode(@RequestBody JSONObject jsonObject){
        String userCode = jsonObject.getString("userCode");
        return new AjaxResult(AjaxResult.STATUS_SUCCESS,userService.checkUserCode(userCode));
    }

}
