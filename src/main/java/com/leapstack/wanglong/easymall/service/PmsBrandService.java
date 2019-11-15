package com.leapstack.wanglong.easymall.service;

import com.github.pagehelper.PageHelper;
import com.leapstack.wanglong.easymall.dao.domain.PmsBrand;
import com.leapstack.wanglong.easymall.dao.mapper.PmsBrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: easy-mall
 * @description: 品牌管理service
 * @author: Wang Long
 * @create: 2019-11-15
 **/

@Service
public class PmsBrandService {

    @Autowired
    private PmsBrandMapper brandMapper;

    public List<PmsBrand> listAllBrand() {
        return brandMapper.selectByExample(new Example(PmsBrand.class));
    }

    public int createBrand(PmsBrand brand) {
        return brandMapper.insertSelective(brand);
    }

    public int updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return brandMapper.selectByExample(new Example(PmsBrand.class));
    }

    public PmsBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

}
