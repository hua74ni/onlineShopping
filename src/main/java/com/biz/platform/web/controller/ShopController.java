package com.biz.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.biz.platform.web.pojo.Shop;
import com.biz.platform.web.pojo.User;
import com.biz.platform.web.service.ShopService;
import com.biz.platform.web.utils.AjaxResult;
import com.biz.platform.web.utils.PropertiesUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

    private Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private ShopService shopService;

    @RequestMapping("/loadShop.do")
    @ResponseBody
    public Shop loadShop(@RequestBody Shop shop){
        return shopService.getShopByShopId(shop);
    }

    @RequestMapping("/goShopRedirect.do")
    public ModelAndView goLogin(HttpServletRequest request,ModelAndView modelAndView){

        modelAndView.setViewName("redirect:/test.do");

        return modelAndView;
    }

    @RequestMapping("/addShop.do")
    public String addShop(MultipartFile shopImage,Shop shop, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("loginUser");

        if(user == null){
            logger.error("用户未登录");
        }

        shop.setShopUserId(user.getUserId());
        int result = shopService.addShop(shopImage,shop);
        return "redirect:goShopRedirect.do?result="+result;
    }

    @RequestMapping("/updateShop.do")
    public String updateShop(MultipartFile shopImage,Shop shop, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("loginUser");

        if(user == null){
            logger.error("用户未登录");
        }

        shop.setShopUserId(user.getUserId());
        int result = shopService.updateShop(shopImage,shop);
        return "redirect:goShopRedirect.do?result="+result;
    }

    @RequestMapping("/deleteShop.do")
    @ResponseBody
    public AjaxResult deleteShop(@RequestBody Shop shop){
        int result = shopService.deleteShop(shop);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    @RequestMapping("/checkShopName.do")
    @ResponseBody
    public int checkShopName(@RequestBody JSONObject jsonObject){
        String shopName = jsonObject.getString("shopName");
        return shopService.checkShopName(shopName);
    }

    @RequestMapping("/loadImage.do")
    public void loadImage(String shopLogoPath,String type,HttpServletResponse response){

        String shopImagePath = "";

        if("goods".equals(type)){
            shopImagePath = PropertiesUtil.getString("goods.image.path");
        }else if("shop".equals(type)){
            shopImagePath = PropertiesUtil.getString("shop.image.path");
        }

        File file = new File(shopImagePath+"/"+shopLogoPath);
        if(!file.isFile() || !file.exists()){
            logger.error("不是文件或者文件不存在");
        }

        OutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
            FileUtils.copyFile(file,outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
