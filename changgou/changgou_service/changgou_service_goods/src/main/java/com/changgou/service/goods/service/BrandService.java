package com.changgou.service.goods.service;

import com.changgou.goods.pojo.Brand;

import java.util.List;

public interface BrandService {
    /**
     * 品牌列表查询
     */
    List<Brand> findList();

    /**
     * 根据id查询品牌数据
     */
    Brand findById(Integer id);

    /**
     * 新增品牌
     * @param brand
     */
    void add(Brand brand);
    /**
     * 修改品牌
     * @param brand
     */
    void update(Brand brand);
    /**
     * 删除品牌
     * @param brand
     */
    void delById(Integer id);

}