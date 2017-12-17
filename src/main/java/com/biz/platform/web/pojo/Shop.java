package com.biz.platform.web.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by huangdonghua on 2017/12/13.
 */
@Table(name = "biz_platform_shop")
public class Shop {

    //商铺编码
    @Id
    @GeneratedValue(generator="UUID")
    @Column(name = "shop_id")
    private String shopId;

    //商铺名称
    @Column(name = "shop_name")
    private String shopName;

    //商家的用户编码
    @Column(name = "shop_user_id")
    private String shopUserId;

    //商铺图标路径
    @Column(name = "shop_logo_path")
    private String shopLogoPath;

    //商铺地址
    @Column(name = "shop_addr")
    private String shopAddr;

    //商铺电话
    @Column(name = "shop_phone")
    private String shopPhone;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //修改时间
    @Column(name = "last_modify_time")
    private long lastModifyTime;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopUserId() {
        return shopUserId;
    }

    public void setShopUserId(String shopUserId) {
        this.shopUserId = shopUserId;
    }

    public String getShopLogoPath() {
        return shopLogoPath;
    }

    public void setShopLogoPath(String shopLogoPath) {
        this.shopLogoPath = shopLogoPath;
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopUserId='" + shopUserId + '\'' +
                ", shopLogoPath='" + shopLogoPath + '\'' +
                ", shopAddr='" + shopAddr + '\'' +
                ", shopPhone='" + shopPhone + '\'' +
                ", createTime='" + createTime + '\'' +
                ", lastModifyTime='" + lastModifyTime + '\'' +
                '}';
    }
}
