package com.biz.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.biz.platform.web.pojo.Goods;
import com.biz.platform.web.pojo.Shop;
import com.biz.platform.web.pojo.User;
import com.biz.platform.web.service.GoodsService;
import com.biz.platform.web.service.ShopService;
import com.biz.platform.web.utils.AjaxResult;
import com.biz.platform.web.vo.GoodsVo;
import com.github.pagehelper.PageInfo;
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

    @Autowired
    private ShopService shopService;

    @RequestMapping("/loadGoods.do")
    @ResponseBody
    public AjaxResult loadGoods(@RequestBody Goods goods){
        return new AjaxResult(AjaxResult.STATUS_SUCCESS,goodsService.getGoodsByGoodsId(goods));
    }

    @RequestMapping("/goGoodsRedirect.do")
    public ModelAndView goLogin(HttpServletRequest request, ModelAndView modelAndView){

        modelAndView.setViewName("redirect:/test.do");

        return modelAndView;
    }

    @RequestMapping("/addGoods.do")
    @ResponseBody
    public AjaxResult addGoods(MultipartFile goodsImage, Goods goods, HttpServletRequest request){

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        Shop shop = shopService.getShopByUserId(loginUser.getUserId());

        goods.setGoodsShopId(shop.getShopId());
        int result = goodsService.addGoods(goodsImage,goods);

        return result > 0?AjaxResult.SUCCESS:AjaxResult.ERROR;
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


    //首页遍历商品 可选择类型 搜索 分页
    @RequestMapping("/queryGoodsHomePage.do")
    @ResponseBody
    public AjaxResult queryGoodsHomePage(@RequestBody GoodsVo goodsVo){

        PageInfo<Goods> goodsPage = goodsService.queryGoodsHomePage(goodsVo);

        return new AjaxResult(AjaxResult.STATUS_SUCCESS,goodsPage);
    }

    //获取当前商家对应商家的商品 进行分页
    @RequestMapping("/getGoodsByUserId.do")
    @ResponseBody
    public AjaxResult getGoodsByUserId(@RequestBody JSONObject jsonObject,
                                        @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(name = "pageSize",defaultValue = "5") int pageSize,
                                        HttpServletRequest request){

        if(jsonObject != null && jsonObject.size() == 1){
            pageNum = jsonObject.getInteger("pageNum");
//            pageSize = jsonObject.getInteger("pageSize");
        }

        User user = (User) request.getSession().getAttribute("loginUser");
        PageInfo<Goods> goodsPage = goodsService.getGoodsByUserId(pageNum,pageSize,user.getUserId());

        return new AjaxResult(AjaxResult.STATUS_SUCCESS,goodsPage);
    }

    /**
     * 获取选中的商家展示对应的商品 进行分页
     * @param jsonObject  pageNum、pageSize、shopId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getGoodsByShopId.do")
    @ResponseBody
    public AjaxResult getGoodsByShopId(@RequestBody JSONObject jsonObject,
                                            @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                                            @RequestParam(name = "pageSize",defaultValue = "8") int pageSize){

        if(jsonObject != null && jsonObject.size() == 2){
            pageNum = jsonObject.getInteger("pageNum");
            pageSize = jsonObject.getInteger("pageSize");
        }
        String shopId = jsonObject.getString("shopId");

        PageInfo<Goods> goodsPage = goodsService.getGoodsByUserId(pageNum,pageSize,shopId);

        return new AjaxResult(AjaxResult.STATUS_SUCCESS,goodsPage);
    }

    /**
     * 获取所有商品分类
     * @return
     */
    @RequestMapping("/getGoodsAllType.do")
    @ResponseBody
    public AjaxResult getGoodsAllType(){

        List<String> orderTypes = goodsService.getGoodsAllType();

        return new AjaxResult(AjaxResult.STATUS_SUCCESS,orderTypes);
    }

}
