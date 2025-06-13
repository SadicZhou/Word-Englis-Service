package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.DeletDto;
import com.atguigu.spzx.model.dto.system.SysMenuDto;
import com.atguigu.spzx.model.entity.system.SysMenu;

import java.util.List;

public interface SysMenuService {
    //获取菜单列表
    List<SysMenu> findNodes();
    //新增菜单
    void save(SysMenu sysMenu);
    //修改菜单
    void updateById(SysMenuDto sysMenuDto);
    //删除菜单
    void deleteById(DeletDto deletDto);

}
