package com.biz.platform.web.service.impl;

import com.biz.platform.web.mapper.FeedBackMapper;
import com.biz.platform.web.mapper.OrderMapper;
import com.biz.platform.web.mapper.ShopMapper;
import com.biz.platform.web.pojo.FeedBack;
import com.biz.platform.web.pojo.Order;
import com.biz.platform.web.pojo.Shop;
import com.biz.platform.web.service.BaseService;
import com.biz.platform.web.service.FeedBackService;
import com.biz.platform.web.utils.CollectionUtils;
import com.biz.platform.web.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Service(value = "feedBackService")
public class FeedBackServiceImpl extends BaseService<FeedBack> implements FeedBackService {

    private Logger logger = LoggerFactory.getLogger(FeedBackServiceImpl.class);

    @Autowired
    private FeedBackMapper feedBackMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public FeedBack getFeedBackByFeedBackId(FeedBack feedBack) {
        //feedBack不能为空 feedBackId和feedBackMsg不能为空也不能为空值
        if(feedBack == null || StringUtils.isNullOrBlank(feedBack.getFeedBackId())){
            return null;
        }
        return feedBackMapper.selectByPrimaryKey(feedBack);
    }

    @Override
    public int addFeedBack(FeedBack feedBack) {
        if(feedBack == null || StringUtils.isNullOrBlank(feedBack.getOrderId()) || StringUtils.isNullOrBlank(feedBack.getFeedbackMsg()) ){
            return 0;
        }

        String orderId = feedBack.getOrderId();
        Order order = orderMapper.selectByPrimaryKey(orderId);

        //买家和当前登录用户编码不一样不可评论
        if(!order.getOrderBuyerId().equals(feedBack.getBuyerId())){
            logger.error("买家编码和当前登录用户编码不一样不可评论");
            return 0;
        }

        feedBack.setGoodsId(order.getGoodsId());
        feedBack.setShopId(order.getOrderShopId());
        feedBack.setCreateTime(new Date());

        return feedBackMapper.insertSelective(feedBack);
    }

    @Override
    public int updateFeedBack(FeedBack feedBack) {
        if(feedBack == null || StringUtils.isNullOrBlank(feedBack.getFeedBackId())){
            return 0;
        }
        return feedBackMapper.updateByPrimaryKeySelective(feedBack);
    }

    @Override
    public int deleteFeedBack(FeedBack feedBack) {
        if(feedBack == null || StringUtils.isNullOrBlank(feedBack.getFeedBackId())){
            return 0;
        }
        return feedBackMapper.deleteByPrimaryKey(feedBack);
    }

    @Override
    public String checkUserIsFeedBack(String goodsId, String userId) {

        String result = "";

        Example example = new Example(Order.class);
        example.createCriteria()
                .andEqualTo("orderBuyerId",userId)
                .andEqualTo("goodsId",goodsId)
                .andEqualTo("orderStatu","1");

        List<Order> orders = orderMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(orders)){
            return result;
        }

        Example feedBackExample = null;

        //一旦 result 等于1 说明该用户可评论该商品
        for (Order order:
        orders) {
            feedBackExample = new Example(FeedBack.class);
            feedBackExample.createCriteria().andEqualTo("orderId",order.getOrderId());
            int feedBackCount = feedBackMapper.selectCountByExample(feedBackExample);

            if(feedBackCount == 0) {
                result = order.getOrderId();
                break;
            }
        }

        return result;
    }

    @Override
    public int revertFeedBack(FeedBack feedBack) {

        if(feedBack == null || StringUtils.isNullOrBlank(feedBack.getFeedBackId()) || StringUtils.isNullOrBlank(feedBack.getShopRevert()) ){
            return 0;
        }

        //当前登录用户的userId
        String userId = feedBack.getShopId();

        String msg = feedBack.getShopRevert();

        feedBack = feedBackMapper.selectByPrimaryKey(feedBack.getFeedBackId());

        feedBack.setShopRevert(msg);

        Example example1 = new Example(Shop.class);
        example1.createCriteria().andEqualTo("shopUserId",userId);

        List<Shop> shops = shopMapper.selectByExample(example1);

        Shop shop = shops.get(0);

        if(!feedBack.getShopId().equals(shop.getShopId())){
            logger.error("商家编码和当前登录用户编码不一样不可回复");
            return 0;
        }

        return feedBackMapper.updateByPrimaryKey(feedBack);
    }

    @Override
    public List<FeedBack> getFeedBackByGoodsId(FeedBack feedBack) {

        Example example = new Example(FeedBack.class);
        example.createCriteria().andEqualTo("goodsId",feedBack.getGoodsId());

        return feedBackMapper.selectByExample(example);
    }

}
