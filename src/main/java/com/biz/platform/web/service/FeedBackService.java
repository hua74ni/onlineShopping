package com.biz.platform.web.service;

import com.biz.platform.web.pojo.FeedBack;

/**
 * Created by huangdonghua on 2017/12/14.
 */
public interface FeedBackService {
    /**
     * 通过FeedBackId获得 FeedBack
     * @param feedBack
     * @return
     */
    public FeedBack getFeedBackByFeedBackId(FeedBack feedBack);

    /**
     * 新增 feedBack
     * @param feedBack
     * @return
     */
    public int addFeedBack(FeedBack feedBack);

    /**
     * 修改feedBack
     * @param feedBack
     * @return
     */
    public int updateFeedBack(FeedBack feedBack);

    /**
     * 删除feedBack
     * @param feedBack
     * @return
     */
    public int deleteFeedBack(FeedBack feedBack);

    /**
     * 判断当前用户是否可评论 该物品
     * @param goodsId
     * @param userId
     * @return
     */
    public String checkUserIsFeedBack(String goodsId, String userId);

    /**
     * 商家回复买家评论
     * @param feedBack
     * @return
     */
    public int revertFeedBack(FeedBack feedBack);
}
