package com.biz.platform.web.service.impl;

import com.biz.platform.web.mapper.FeedBackMapper;
import com.biz.platform.web.pojo.FeedBack;
import com.biz.platform.web.service.BaseService;
import com.biz.platform.web.service.FeedBackService;
import com.biz.platform.web.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Service(value = "feedBackService")
public class FeedBackServiceImpl extends BaseService<FeedBack> implements FeedBackService {

    @Autowired
    private FeedBackMapper feedBackMapper;

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

}
