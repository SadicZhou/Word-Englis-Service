package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.DeletDto;
import com.atguigu.spzx.model.dto.system.SysMenuDto;
import com.atguigu.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {
    //查询所有菜单列表按照sort_value排序
    List<SysMenu> findNodes();
    //新增菜单
    void save(SysMenu sysMenu);
    //修改菜单
    void updateById(SysMenuDto sysMenu);
    //查找子菜单数量
    Integer selectCountById(DeletDto deletDto);
    //删除菜单
    void deleteById(DeletDto deletDto);
    //查找菜单
    SysMenu selectById(Long id);
    //根据用户ID查找用户的所有菜单
   List<SysMenu> selectByUserId(Long userId);
}
