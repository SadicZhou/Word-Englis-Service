package com.atguigu.spzx.manager.service;

import java.util.List;

import com.atguigu.spzx.model.entity.product.Category;

public interface CategoryService {
    // 根据父id查询分类
    public List<Category> findByParentId(Long parentId);
}
