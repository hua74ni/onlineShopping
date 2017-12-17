package com.biz.platform.web.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by huangdonghua on 2017/12/13.
 */
@Table(name = "biz_platform_goods")
public class Goods {

    //商品ID
    @Id
    @GeneratedValue(generator="UUID")
    @Column(name = "goods_id")
    private String goodsId;

    //商品 依附于 商家编码
    @Column(name = "goods_shop_id")
    private String goodsShopId;

    //商品名称
    @Column(name = "goods_name")
    private String goodsName;

    //商品类型
    @Column(name = "goods_type")
    private String goodsType;

    //商品活动 目前不用
    @Column(name = "goods_activity")
    private String goodsActivity;

    //商品价格
    @Column(name = "goods_price")
    private float goodsPrice;

    //商品logo路径
    @Column(name = "goods_logo_path")
    private String goodsLogoPath;

    //商品详情
    @Column(name = "goods_detais")
    private String goodsDetails;

    //商品备注
    @Column(name = "goods_remarks")
    private String goodsRemarks;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //修改时间
    @Column(name = "last_modify_time")
    private long lastModofyTime;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsShopId() {
        return goodsShopId;
    }

    public void setGoodsShopId(String goodsShopId) {
        this.goodsShopId = goodsShopId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsActivity() {
        return goodsActivity;
    }

    public void setGoodsActivity(String goodsActivity) {
        this.goodsActivity = goodsActivity;
    }

    public float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsLogoPath() {
        return goodsLogoPath;
    }

    public void setGoodsLogoPath(String goodsLogoPath) {
        this.goodsLogoPath = goodsLogoPath;
    }

    public String getGoodsDetails() {
        return goodsDetails;
    }

    public void setGoodsDetails(String goodsDetails) {
        this.goodsDetails = goodsDetails;
    }

    public String getGoodsRemarks() {
        return goodsRemarks;
    }

    public void setGoodsRemarks(String goodsRemarks) {
        this.goodsRemarks = goodsRemarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getLastModofyTime() {
        return lastModofyTime;
    }

    public void setLastModofyTime(long lastModofyTime) {
        this.lastModofyTime = lastModofyTime;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsShopId='" + goodsShopId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", goodsActivity='" + goodsActivity + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsLogoPath='" + goodsLogoPath + '\'' +
                ", goodsDetails='" + goodsDetails + '\'' +
                ", goodsRemarks='" + goodsRemarks + '\'' +
                ", createTime=" + createTime +
                ", lastModofyTime=" + lastModofyTime +
                '}';
    }
}
