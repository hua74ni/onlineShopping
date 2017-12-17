package com.biz.platform.web.mapper;

import com.biz.platform.web.pojo.Goods;
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
}
