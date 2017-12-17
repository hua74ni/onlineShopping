package com.biz.platform.web.controller;

import com.biz.platform.web.pojo.User;
import com.biz.platform.web.service.UserService;
import com.biz.platform.web.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by huangdonghua on 2017/12/13.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test.do")
    public String test(){
        return "index";
    }



    @RequestMapping("/loadUser.do")
    @ResponseBody
    public User load(@RequestBody User user){
        return userService.getUserByUserId(user);
    }

    @RequestMapping("/addUser.do")
    @ResponseBody
    public AjaxResult addUser(@RequestBody User user){

        int result = userService.addUser(user);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    @RequestMapping("/updateUser.do")
    @ResponseBody
    public AjaxResult updateUser(@RequestBody User user){

        int result = userService.updateUser(user);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    @RequestMapping("/deleteUser.do")
    @ResponseBody
    public AjaxResult deleteUser(@RequestBody User user){
        int result = userService.deleteUser(user);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

}
