package com.biz.platform.web.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Service
public abstract class BaseService<T> {

    @Autowired
    protected Mapper<T> mapper;

    /**
     * 新增entity
     * @param entity
     * @return
     */
    public int save(T entity){
        return mapper.insertSelective(entity);
    }

    /**
     * 修改entity
     * @param entity
     * @return
     */
    public int update(T entity){
        return mapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 删除entity
     * @param entity
     * @return
     */
    public int delete(T entity){
        return mapper.deleteByPrimaryKey(entity);
    }

    /**
     * 通过entityId获取entity
     * @param entity
     * @return
     */
    public T load(T entity){
        return mapper.selectByPrimaryKey(entity);
    }
    /**
     * 单表分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<T> selectPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        //Spring4支持泛型注入
        return mapper.select(null);
    }

}
