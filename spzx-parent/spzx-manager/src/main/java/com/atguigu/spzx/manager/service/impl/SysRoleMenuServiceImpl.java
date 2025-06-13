package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.service.SysRoleMenuService;
import com.atguigu.spzx.model.dto.system.AssginMenuDto;
import com.atguigu.spzx.model.dto.system.SeleteDto;
import com.atguigu.spzx.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysMenuService sysMenuService;
    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(SeleteDto seleteDto) {
       List<SysMenu> menus = sysMenuService.findNodes();
       List<Long> menusIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(seleteDto);
       Map<String, Object> map = new HashMap<>();
       map.put("menus",menus);
       map.put("menusIds",menusIds);
       return  map;
    }

    @Override
    public void assignMenu(AssginMenuDto assginMenuDto) {
        //1.删除旧的绑定关系
        sysRoleMenuMapper.deleteById(assginMenuDto.getRoleId());
        //2.重新绑定菜单
       List<Map<String,Number>> menuInfo = assginMenuDto.getMenuIdList();
        if(menuInfo != null&& menuInfo.size()>0){
                sysRoleMenuMapper.assignMenu(assginMenuDto);
        }
    }
}
