package com.biz.platform.web.service.impl;

import com.biz.platform.web.mapper.GoodsMapper;
import com.biz.platform.web.pojo.Goods;
import com.biz.platform.web.pojo.Shop;
import com.biz.platform.web.service.BaseService;
import com.biz.platform.web.service.GoodsService;
import com.biz.platform.web.utils.PropertiesUtil;
import com.biz.platform.web.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Service(value = "goodsService")
public class GoodsServiceImpl extends BaseService<Goods> implements GoodsService {

    private Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

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
    public int addGoods(MultipartFile goodsImage, Goods goods) {
        if(goods == null || goodsImage.isEmpty()){
            return 0;
        }

        //获取goods图片保存路径
        String originalFilename = goodsImage.getOriginalFilename();
        String goodsImagePath = PropertiesUtil.getString("goods.image.path");
        //文件名： long时间+"-"+源文件名
        String fileName = (new Date()).getTime()/1000 + "-" +originalFilename;

        File folder = new File(goodsImagePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        //将上传的图片保存到指定路径
        File image = new File(goodsImagePath,fileName);
        try {
            goodsImage.transferTo(image);
            goods.setGoodsLogoPath(fileName);
        } catch (IOException e) {
            logger.error("商品上传图片失败");
            logger.error(e.toString());
            e.printStackTrace();
            throw new RuntimeException();
        }

        goods.setCreateTime(new Date());
        return goodsMapper.insertSelective(goods);
    }

    @Override
    public int updateGoods(MultipartFile goodsImage, Goods goods) {
        if(goods == null || StringUtils.isNullOrBlank(goods.getGoodsId())){
            return 0;
        }

        //如果图片为空就不上传 不替代之前的图片
        //如果不为空 替代之前的图片 并且删除之前的图片
        if(goodsImage != null && !goodsImage.isEmpty()){
            String originalFilename = goodsImage.getOriginalFilename();
            String goodsImagePath = PropertiesUtil.getString("goods.image.path");
            String fileName = (new Date()).getTime()/1000 + "-" +originalFilename;
            File image = new File(goodsImagePath,fileName);
            try {
                goodsImage.transferTo(image);
                goods.setGoodsLogoPath(fileName);
                //获取修改之前的goods信息
                Goods tmpGoods = this.getGoodsByGoodsId(goods);
                //删除之前的图片
                deleteOldImage(tmpGoods.getGoodsLogoPath());
            } catch (IOException e) {
                logger.error("商品上传图片失败");
                logger.error(e.toString());
                throw new RuntimeException();
            }
        }

        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public int deleteGoods(Goods goods) {
        if(goods == null || StringUtils.isNullOrBlank(goods.getGoodsId())){
            return 0;
        }

        goods = this.getGoodsByGoodsId(goods);

        //删除数据成功后 删除图片
        int result = goodsMapper.deleteByPrimaryKey(goods);
        if(result != 0){
            deleteOldImage(goods.getGoodsLogoPath());
        }

        return result;
    }

    @Override
    public PageInfo<Goods> getGoodsByUserId(int pageNum, int pageSize, String userId) {
        PageHelper.startPage(pageNum,pageSize);
        List<Goods> goods = goodsMapper.getGoodsByUserId(userId);
        return new PageInfo<Goods>();
    }

    //删除旧的图片
    private void deleteOldImage(String goodsLogoPath){

        String shopImagePath = PropertiesUtil.getString("goods.image.path");
        String filePath = shopImagePath + "/" + goodsLogoPath;
        File file = new File(filePath);

        try {
            FileUtils.forceDelete(file);
        } catch (IOException e) {
            logger.error("图片删除失败");
            e.printStackTrace();
        }

    }

}
