package com.biz.platform.web.controller;

import com.biz.platform.web.pojo.Shop;
import com.biz.platform.web.service.ShopService;
import com.biz.platform.web.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping("/loadShop.do")
    @ResponseBody
    public Shop loadShop(@RequestBody Shop shop){
        return shopService.getShopByShopId(shop);
    }

    @RequestMapping("/addShop.do")
    @ResponseBody
    public AjaxResult addShop(MultipartFile shopImage, @RequestBody Shop shop){

        int result = shopService.addShop(shopImage,shop);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    @RequestMapping("/updateShop.do")
    @ResponseBody
    public AjaxResult updateShop(@RequestBody Shop shop){

        int result = shopService.updateShop(shop);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }

    @RequestMapping("/deleteShop.do")
    @ResponseBody
    public AjaxResult deleteShop(@RequestBody Shop shop){
        int result = shopService.deleteShop(shop);
        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
    }
}
