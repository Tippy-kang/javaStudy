package com.changgou.goods.dao;

import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface BrandMapper extends Mapper<Brand> {
    @Select("SELECT name,image FROM tb_brand WHERE id in(SELECT brand_id FROM tb_category_brand WHERE category_id in(SELECT id FROM tb_category WHERE name=#{categoryName}))")
    public List<Map> findBrandListByCategoryName(@Param("categoryName") String categoryName);
}
