package com.biz.platform.web.service;

import com.biz.platform.web.pojo.Goods;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by huangdonghua on 2017/12/14.
 */
public interface GoodsService {
    /**
     * 通过goodsId获取goods
     * @param goods
     * @return
     */
    public Goods getGoodsByGoodsId(Goods goods);

    /**
     * 新增goods
     * @param goods
     * @return
     */
    public int addGoods(Goods goods);

    /**
     * 修改goods
     * @param goods
     * @return
     */
    public int updateGoods(Goods goods);

    /**
     * 删除goods
     * @param goods
     * @return
     */
    public int deleteGoods(Goods goods);

    /**
     * 通过商家userId获取对应商家的订单 进行分页
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    public PageInfo<Goods> getGoodsByUserId(int pageNum, int pageSize, String userId);
}
