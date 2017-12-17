package com.biz.platform.web.service;

import com.biz.platform.web.pojo.Shop;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by huangdonghua on 2017/12/14.
 */
public interface ShopService {
    /**
     * 通过shopId获取shop
     * @param shop
     * @return
     */
    public Shop getShopByShopId(Shop shop);

    /**
     * 新增shop
     *
     * @param shopImage
     * @param shop
     * @return
     */
    public int addShop(MultipartFile shopImage, Shop shop);

    /**
     * 修改shop信息
     *
     * @param shopImage
     * @param shop
     * @return
     */
    public int updateShop(MultipartFile shopImage, Shop shop);

    /**
     * 删除shop
     * @param shop
     * @return
     */
    public int deleteShop(Shop shop);

    /**
     * 校验shopName 是否已经存在
     * @param shopName
     * @return
     */
    public int checkShopName(String shopName);
}
