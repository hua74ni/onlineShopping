package com.biz.platform.web.service.impl;

import com.biz.platform.web.mapper.FeedBackMapper;
import com.biz.platform.web.mapper.OrderMapper;
import com.biz.platform.web.pojo.FeedBack;
import com.biz.platform.web.pojo.Order;
import com.biz.platform.web.service.BaseService;
import com.biz.platform.web.service.FeedBackService;
import com.biz.platform.web.utils.CollectionUtils;
import com.biz.platform.web.utils.StringUtils;
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

    @Autowired
    private FeedBackMapper feedBackMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public FeedBack getFeedBackByFeedBackId(FeedBack feedBack) {
        if(feedBack == null || StringUtils.isNullOrBlank(feedBack.getFeedBackId())){
            return null;
        }
        return feedBackMapper.selectByPrimaryKey(feedBack);
    }

    @Override
    public int addFeedBack(FeedBack feedBack) {
        if(feedBack == null){
            return 0;
        }
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
    public int checkUserIsFeedBack(String goodsId, String userId) {

        int result = 1;

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
            feedBackExample.createCriteria().andEqualTo("orederId",order.getOrderId());
            int feedBackCount = feedBackMapper.selectCountByExample(feedBackExample);

            if(feedBackCount == 0) {
                result = feedBackCount;
                break;
            }
        }

        return result;
    }

}
