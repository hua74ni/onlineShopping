package com.biz.platform.web.mapper;

import com.biz.platform.web.pojo.Goods;
import com.biz.platform.web.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by huangdonghua on 2017/12/14.
 */
public interface GoodsMapper extends Mapper<Goods>{

    /**
     * 通过商家userId获取对应商家的物品
     * @param userId
     * @return
     */
    public List<Goods> getGoodsByUserId(@Param("userId") String userId);

    /**
     * 首页遍历商品 可选择类型 搜索 已分页
     * @param goodsVo
     * @return
     */
    public List<Goods> queryGoodsHomePage(@Param("goodsVo") GoodsVo goodsVo);

    /**
     * 获取所有商品的分类
     * @return
     */
    public List<String> getGoodsAllType();
}
