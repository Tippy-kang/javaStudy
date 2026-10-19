package com.changgou.service.goods.service.impl;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.goods.dao.BrandMapper;
import com.changgou.service.goods.service.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 品牌列表查询
     * @return
     */
    @Override
    public List<Brand> findList() {
        return brandMapper.selectAll();
    }

    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void delById(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Brand> list(Map<String, Object> searchMap) {
        Example example = new Example(Brand.class);
        // 封装查询条件
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null&&!searchMap.isEmpty()){
            // 品牌名称（模糊查询） like
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }

            // 按照品牌首字母进行查询（精确）
            if(searchMap.get("letter")!=null && !"".equals(searchMap.get("letter"))){
                criteria.andEqualTo("letter",searchMap.get("letter"));
            }

        }
        List<Brand> brandList = brandMapper.selectByExample(example);
        return brandList;
    }

    @Override
    public Page<Brand> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        Page<Brand> pageList = (Page<Brand>) brandMapper.selectAll();
        return pageList;
    }

    @Override
    public Page<Brand> findPage(Map<String, Object> searchMap, int page, int size) {
        // 设置分页
        PageHelper.startPage(page, size);
        // 设置查询条件
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null&&!searchMap.isEmpty()){
            // 品牌名称（模糊查询） like
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }

            // 按照品牌首字母进行查询（精确）
            if(searchMap.get("letter")!=null && !"".equals(searchMap.get("letter"))){
                criteria.andEqualTo("letter",searchMap.get("letter"));
            }

        }
        Page<Brand> pageList = (Page<Brand>) brandMapper.selectByExample(example);
        return pageList;
    }
}