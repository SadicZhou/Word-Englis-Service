package com.atguigu.spzx.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findByParentId(Long parentId) {
        // 根据父id查询分类
        List<Category> list = categoryMapper.findByParentId(parentId);
        for (Category category : list) {
           int count = categoryMapper.countByParentId(category.getId());
            category.setHasChildren(count > 0);
        }
        return list;
    }

}
