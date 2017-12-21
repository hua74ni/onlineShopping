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
    public AjaxResult addUser(@RequestBody User user,HttpServletRequest request){

        if (user == null){
            return new AjaxResult(AjaxResult.STATUS_ERROR,"请输入必填数据");
        }
        if (StringUtils.isNullOrBlank(user.getUserCode())){
            return new AjaxResult(AjaxResult.STATUS_ERROR,"请输入账号");
        }
        if (StringUtils.isNullOrBlank(user.getUserName())){
            return new AjaxResult(AjaxResult.STATUS_ERROR,"请输入账号");
        }
        if (StringUtils.isNullOrBlank(user.getUserPassword())){
            return new AjaxResult(AjaxResult.STATUS_ERROR,"请输入密码");
        }
        if (StringUtils.isNullOrBlank(user.getUserType())){
            return new AjaxResult(AjaxResult.STATUS_ERROR,"请选中用户类型");
        }
        if (StringUtils.isNullOrBlank(user.getUserAddr())){
            return new AjaxResult(AjaxResult.STATUS_ERROR,"请输入地址");
        }

        int result = userService.addUser(user);

        if(result > 0){
            User loginUser = userService.getUserByUserCode(user);
            request.getSession().setAttribute("loginUser",loginUser);
        }

        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    //修改用户信息
    @RequestMapping("/updateUser.do")
    @ResponseBody
    public AjaxResult updateUser(@RequestBody User user,HttpServletRequest request){

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        loginUser = userService.getUserByUserId(loginUser);

        if(StringUtils.isNotNullAndBlank(user.getUserPhone())){
            loginUser.setUserPhone(user.getUserPhone());
        }
        if(StringUtils.isNotNullAndBlank(user.getUserName())){
            loginUser.setUserName(user.getUserName());
        }
        if(StringUtils.isNotNullAndBlank(user.getUserPassword())){
            loginUser.setUserPassword(user.getUserPassword());
        }

        int result = userService.updateUser(loginUser);

        if(result > 0){
            loginUser.setUserPassword("");
            request.getSession().setAttribute("loginUser",loginUser);
        }

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
