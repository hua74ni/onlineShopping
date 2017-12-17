package com.biz.platform.web.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by huangdonghua on 2017/12/13.
 */
@Table(name = "biz_platform_feedback")
public class FeedBack {

    //反馈ID
    @Id
    @GeneratedValue(generator="UUID")
    @Column(name = "feedback_id")
    private String feedBackId;

    //订单ID
    @Column(name = "order_id")
    private String orederId;

    //商品ID
    @Column(name = "goods_id")
    private String goodsId;

    //买家ID
    @Column(name = "buyer_id")
    private String buyerId;

    //买家名字
    @Column(name = "buyer_name")
    private String buyerName;

    //商铺ID
    @Column(name = "shop_id")
    private String shopId;

    //买家反馈信息
    @Column(name = "feedback_msg")
    private String feedbackMsg;

    //商家评论
    @Column(name = "shop_revert")
    private String shopRevert;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //修改时间
    @Column(name = "last_modify_time")
    private long lastModifyTime;

    public String getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(String feedBackId) {
        this.feedBackId = feedBackId;
    }

    public String getOrederId() {
        return orederId;
    }

    public void setOrederId(String orederId) {
        this.orederId = orederId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getFeedbackMsg() {
        return feedbackMsg;
    }

    public void setFeedbackMsg(String feedbackMsg) {
        this.feedbackMsg = feedbackMsg;
    }

    public String getShopRevert() {
        return shopRevert;
    }

    public void setShopRevert(String shopRevert) {
        this.shopRevert = shopRevert;
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
        return "FeedBack{" +
                "feedBackId='" + feedBackId + '\'' +
                ", orederId='" + orederId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", shopId='" + shopId + '\'' +
                ", feedbackMsg='" + feedbackMsg + '\'' +
                ", shopRevert='" + shopRevert + '\'' +
                ", creatTime=" + createTime +
                ", lastModifyTime=" + lastModifyTime +
                '}';
    }
}
