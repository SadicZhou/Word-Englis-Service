package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.AssginMenuDto;
import com.atguigu.spzx.model.dto.system.SeleteDto;

import java.util.List;
import java.util.Map;

public interface SysRoleMenuService {
    //查询角色菜单
    Map<String,Object> findSysRoleMenuByRoleId(SeleteDto seleteDto);
    //分配菜单
    void assignMenu(AssginMenuDto assginMenuDto);
}
