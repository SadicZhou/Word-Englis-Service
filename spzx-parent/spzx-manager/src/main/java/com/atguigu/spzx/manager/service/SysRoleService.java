package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.DeletDto;
import com.atguigu.spzx.model.dto.system.SeleteDto;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface SysRoleService {
    //分页查询方法
    PageInfo<SysRole> findByPage(Integer current, Integer limit, SysRoleDto sysRoleDto);
    //添加角色方法
    void saveSysRole(SysRoleDto sysRoleDto);
    //修改角色方法
    void updateSysRole(SysRoleDto sysRoleDto);
    //角色删除放方法
    void deleteById(DeletDto deletDto);
    //查询所有角色
    Map<String,Object> findAllRoles(SeleteDto seleteDto);
}
