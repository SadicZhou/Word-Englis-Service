package com.atguigu.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.atguigu.spzx.model.entity.product.Category;
import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> findByParentId(Long parentId);
    int countByParentId(Long parentId);
}