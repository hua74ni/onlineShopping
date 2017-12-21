package com.biz.platform.web.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by huangdonghua on 2017/12/13.
 */
@Table(name = "biz_platform_order")
public class Order {

    //订单编码
    @Id
    @GeneratedValue(generator="UUID")
    @Column(name = "order_id")
    private String orderId;

    //商品编码
    @Column(name = "goods_id")
    private String goodsId;

    //商品名
    @Column(name = "goods_name")
    private String goodsName;

    //商品图片路径
    @Column(name = "goods_logo_path")
    private String goodsLogoPath;

    //商品数量
    @Column(name = "goods_num")
    private int goodsNum;

    //商品价格
    @Column(name = "goods_price")
    private float goodsPrice;

    //商品总价格
    @Column(name = "order_total_price")
    private float orderTotalPrice;

    //买家编码
    @Column(name = "order_buyer_id")
    private String orderBuyerId;

    //买家名称
    @Column(name = "order_buyer_name")
    private String orderBuyerName;

    //买家状态:{0:初始化,1:确认订单,3:取消订单}
    @Column(name = "order_buyer_statu")
    private String orderBuyerStatu;

    //买家statu为3带上理由
    @Column(name = "buyer_cancel_msg")
    private String buyerCancelMsg;

    //卖家编码
    @Column(name = "order_shop_id")
    private String orderShopId;

    //卖家名称
    @Column(name = "order_shop_name")
    private String orderShopName;

    //卖家状态:{0:初始化,1:确认订单,3:取消订单}
    @Column(name = "order_shop_statu")
    private String orderShopStatu;

    //卖家statu为3带上理由
    @Column(name = "shop_cancel_msg")
    private String shopCancelMsg;

    //0:订单初始化;1:订单完成;3:订单取消
    @Column(name = "order_statu")
    private String orderStatu;

    //0:未付款,1:已付款
    @Column(name = "order_is_pay")
    private String orderIsPay;

    //快递起始地
    @Column(name = "delivery_start_addr")
    private String deliveryStartAddr;

    //快递目的地
    @Column(name = "delivery_end_addr")
    private String deliveryEndAddr;

    //物流信息
    @Column(name = "logistics")
    private String logistics;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //修改时间
    @Column(name = "last_modify_time")
    private long lastModifyTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public float getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(float orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getOrderBuyerId() {
        return orderBuyerId;
    }

    public void setOrderBuyerId(String orderBuyerId) {
        this.orderBuyerId = orderBuyerId;
    }

    public String getOrderBuyerName() {
        return orderBuyerName;
    }

    public void setOrderBuyerName(String orderBuyerName) {
        this.orderBuyerName = orderBuyerName;
    }

    public String getOrderBuyerStatu() {
        return orderBuyerStatu;
    }

    public void setOrderBuyerStatu(String orderBuyerStatu) {
        this.orderBuyerStatu = orderBuyerStatu;
    }

    public String getBuyerCancelMsg() {
        return buyerCancelMsg;
    }

    public void setBuyerCancelMsg(String buyerCancelMsg) {
        this.buyerCancelMsg = buyerCancelMsg;
    }

    public String getOrderShopId() {
        return orderShopId;
    }

    public void setOrderShopId(String orderShopId) {
        this.orderShopId = orderShopId;
    }

    public String getOrderShopName() {
        return orderShopName;
    }

    public void setOrderShopName(String orderShopName) {
        this.orderShopName = orderShopName;
    }

    public String getOrderShopStatu() {
        return orderShopStatu;
    }

    public void setOrderShopStatu(String orderShopStatu) {
        this.orderShopStatu = orderShopStatu;
    }

    public String getShopCancelMsg() {
        return shopCancelMsg;
    }

    public void setShopCancelMsg(String shopCancelMsg) {
        this.shopCancelMsg = shopCancelMsg;
    }

    public String getOrderStatu() {
        return orderStatu;
    }

    public void setOrderStatu(String orderStatu) {
        this.orderStatu = orderStatu;
    }

    public String getOrderIsPay() {
        return orderIsPay;
    }

    public void setOrderIsPay(String orderIsPay) {
        this.orderIsPay = orderIsPay;
    }

    public String getDeliveryStartAddr() {
        return deliveryStartAddr;
    }

    public void setDeliveryStartAddr(String deliveryStartAddr) {
        this.deliveryStartAddr = deliveryStartAddr;
    }

    public String getDeliveryEndAddr() {
        return deliveryEndAddr;
    }

    public void setDeliveryEndAddr(String deliveryEndAddr) {
        this.deliveryEndAddr = deliveryEndAddr;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsLogoPath() {
        return goodsLogoPath;
    }

    public void setGoodsLogoPath(String goodsLogoPath) {
        this.goodsLogoPath = goodsLogoPath;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsLogoPath='" + goodsLogoPath + '\'' +
                ", goodsNum=" + goodsNum +
                ", goodsPrice=" + goodsPrice +
                ", orderTotalPrice=" + orderTotalPrice +
                ", orderBuyerId='" + orderBuyerId + '\'' +
                ", orderBuyerName='" + orderBuyerName + '\'' +
                ", orderBuyerStatu='" + orderBuyerStatu + '\'' +
                ", buyerCancelMsg='" + buyerCancelMsg + '\'' +
                ", orderShopId='" + orderShopId + '\'' +
                ", orderShopName='" + orderShopName + '\'' +
                ", orderShopStatu='" + orderShopStatu + '\'' +
                ", shopCancelMsg='" + shopCancelMsg + '\'' +
                ", orderStatu='" + orderStatu + '\'' +
                ", orderIsPay='" + orderIsPay + '\'' +
                ", deliveryStartAddr='" + deliveryStartAddr + '\'' +
                ", deliveryEndAddr='" + deliveryEndAddr + '\'' +
                ", logistics='" + logistics + '\'' +
                ", createTime=" + createTime +
                ", lastModifyTime=" + lastModifyTime +
                '}';
    }
}
