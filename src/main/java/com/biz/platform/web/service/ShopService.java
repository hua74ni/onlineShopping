package com.biz.platform.web.service;

import com.biz.platform.web.pojo.Shop;
import com.github.pagehelper.PageInfo;
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

    /**
     * 获取所有的商家数据 进行分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Shop> queryShopPage(int pageNum, int pageSize);

    /**
     * 通过userId获取对应的商家信息
     * @param userId
     * @return
     */
    public Shop getShopByUserId(String userId);
}
