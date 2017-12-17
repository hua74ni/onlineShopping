package com.biz.platform.web.service.impl;

import com.biz.platform.web.mapper.ShopMapper;
import com.biz.platform.web.pojo.Shop;
import com.biz.platform.web.service.BaseService;
import com.biz.platform.web.service.ShopService;
import com.biz.platform.web.utils.PropertiesUtil;
import com.biz.platform.web.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Service(value = "shopService")
public class ShopServiceImpl extends BaseService<Shop> implements ShopService {
    private Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);


    @Autowired
    private ShopMapper shopMapper;

    @Override
    public Shop getShopByShopId(Shop shop) {
        if(shop == null || StringUtils.isNullOrBlank(shop.getShopId())){
            return null;
        }
        return shopMapper.selectByPrimaryKey(shop);
    }

    @Override
    public int addShop(MultipartFile shopImage, Shop shop) {
        if(shop == null || shopImage.isEmpty()){
            return 0;
        }

        //获取shop图片保存路径
        String originalFilename = shopImage.getOriginalFilename();
        String shopImagePath = PropertiesUtil.getString("shop.image.path");
        //文件名： long时间+"-"+源文件名
        String fileName = (new Date()).getTime()/1000 + "-" +originalFilename;

        File folder = new File(shopImagePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        //将上传的图片保存到指定路径
        File image = new File(shopImagePath,fileName);
        try {
            shopImage.transferTo(image);
            shop.setShopLogoPath(fileName);
        } catch (IOException e) {
            logger.error("商家上传图片失败");
            logger.error(e.toString());
            e.printStackTrace();
            throw new RuntimeException();
        }

        shop.setCreateTime(new Date());
        return shopMapper.insertSelective(shop);
    }

    @Override
    public int updateShop(MultipartFile shopImage, Shop shop) {
        if(shop == null || StringUtils.isNullOrBlank(shop.getShopId())){
            return 0;
        }

        //如果图片为空就不上传 不替代之前的图片
        //如果不为空 替代之前的图片 并且删除之前的图片
        if(shopImage != null && !shopImage.isEmpty()){
            String originalFilename = shopImage.getOriginalFilename();
            String shopImagePath = PropertiesUtil.getString("shop.image.path");
            String fileName = (new Date()).getTime()/1000 + "-" +originalFilename;
            File image = new File(shopImagePath,fileName);
            try {
                shopImage.transferTo(image);
                shop.setShopLogoPath(fileName);
                //获取修改之前的shop信息
                Shop tmpShop = this.getShopByShopId(shop);
                //删除之前的图片
                deleteOldImage(tmpShop.getShopLogoPath());
            } catch (IOException e) {
                logger.error("商家上传图片失败");
                logger.error(e.toString());
                throw new RuntimeException();
            }
        }

        return shopMapper.updateByPrimaryKeySelective(shop);
    }

    @Override
    public int deleteShop(Shop shop) {
        if(shop == null || StringUtils.isNullOrBlank(shop.getShopId())){
            return 0;
        }

        shop = this.getShopByShopId(shop);

        //删除数据成功后 删除图片
        int result = shopMapper.deleteByPrimaryKey(shop);
        if(result != 0){
            deleteOldImage(shop.getShopLogoPath());
        }

        return result;
    }

    @Override
    public int checkShopName(String shopName) {
        return shopMapper.checkShopName(shopName);
    }

    //删除旧的图片
    private void deleteOldImage(String shopLogoPath){

        String shopImagePath = PropertiesUtil.getString("shop.image.path");
        String filePath = shopImagePath + "/" + shopLogoPath;
        File file = new File(filePath);

        try {
            FileUtils.forceDelete(file);
        } catch (IOException e) {
            logger.error("图片删除失败");
            e.printStackTrace();
        }

    }

}
