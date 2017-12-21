package com.biz.platform.web.service;

import com.biz.platform.web.pojo.Goods;
import com.biz.platform.web.vo.GoodsVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

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
     *
     * @param goodsImage
     * @param goods
     * @return
     */
    public int addGoods(MultipartFile goodsImage, Goods goods);

    /**
     * 修改goods
     *
     * @param goodsImage
     * @param goods
     * @return
     */
    public int updateGoods(MultipartFile goodsImage, Goods goods);

    /**
     * 删除goods
     * @param goods
     * @return
     */
    public int deleteGoods(Goods goods);

    /**
     * 通过商家userId获取对应商家的商品 进行分页
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    public PageInfo<Goods> getGoodsByUserId(int pageNum, int pageSize, String userId);

    /**
     * 首页遍历商品 可选择类型 搜索 分页
     * @param goodsVo
     * @return
     */
    public PageInfo<Goods> queryGoodsHomePage(GoodsVo goodsVo);

    /**
     * 等到所有商品类型
     * @return
     */
    public List<String> getGoodsAllType();
}
