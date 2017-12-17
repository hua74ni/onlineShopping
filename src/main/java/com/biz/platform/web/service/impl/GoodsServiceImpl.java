package com.biz.platform.web.service.impl;

import com.biz.platform.web.mapper.GoodsMapper;
import com.biz.platform.web.pojo.Goods;
import com.biz.platform.web.service.BaseService;
import com.biz.platform.web.service.GoodsService;
import com.biz.platform.web.utils.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Service(value = "goodsService")
public class GoodsServiceImpl extends BaseService<Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Goods getGoodsByGoodsId(Goods goods) {
        if(goods == null || StringUtils.isNullOrBlank(goods.getGoodsId())){
            return null;
        }
        return goodsMapper.selectByPrimaryKey(goods);
    }

    @Override
    public int addGoods(Goods goods) {
        if(goods == null){
            return 0;
        }
        goods.setCreateTime(new Date());
        return goodsMapper.insertSelective(goods);
    }

    @Override
    public int updateGoods(Goods goods) {
        if(goods == null || StringUtils.isNullOrBlank(goods.getGoodsId())){
            return 0;
        }
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public int deleteGoods(Goods goods) {
        if(goods == null || StringUtils.isNullOrBlank(goods.getGoodsId())){
            return 0;
        }
        return goodsMapper.deleteByPrimaryKey(goods);
    }

    @Override
    public PageInfo<Goods> getGoodsByUserId(int pageNum, int pageSize, String userId) {
        PageHelper.startPage(pageNum,pageSize);
        List<Goods> goods = goodsMapper.getGoodsByUserId(userId);
        return new PageInfo<Goods>();
    }

}
