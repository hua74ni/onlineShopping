package com.biz.platform.web.service.impl;

import com.biz.platform.web.mapper.ShopMapper;
import com.biz.platform.web.pojo.Shop;
import com.biz.platform.web.service.BaseService;
import com.biz.platform.web.service.ShopService;
import com.biz.platform.web.utils.PropertiesUtil;
import com.biz.platform.web.utils.StringUtils;
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

        String originalFilename = shopImage.getOriginalFilename();
        String shopImagePath = PropertiesUtil.getString("shop.image.path");
        String fileName = new Date() + "" +originalFilename;

        File image = new File(shopImagePath,fileName);

        try {
            shopImage.transferTo(image);
        } catch (IOException e) {
            logger.error("商家上传图片失败");
            logger.error(e.toString());
            e.printStackTrace();
            throw new RuntimeException();
        }

        shop.setShopLogoPath(fileName);
        shop.setCreateTime(new Date());
        return shopMapper.insertSelective(shop);
    }

    @Override
    public int updateShop(Shop shop) {
        if(shop == null || StringUtils.isNullOrBlank(shop.getShopId())){
            return 0;
        }
        return shopMapper.updateByPrimaryKeySelective(shop);
    }

    @Override
    public int deleteShop(Shop shop) {
        if(shop == null || StringUtils.isNullOrBlank(shop.getShopId())){
            return 0;
        }
        return shopMapper.deleteByPrimaryKey(shop);
    }
}
