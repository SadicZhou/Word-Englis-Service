package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.dto.system.DeletDto;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysRoleUser;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysUserService {
    //登录
    LoginVo login(LoginDto LoginDto);
    //查询用户信息
    SysUser getUserInfo(String token);
    //退出登录
    Void logout(String token);
    //用户列表(分页、模糊查询)
    PageInfo<SysUser> findBypage(Integer current, Integer limit, SysUserDto sysUserDto);
    //新增用户
    void saveSysUser(SysUser sysUser);
    //修改用户
    void updateSysUser(SysUser sysUser);
    //删除用户
    void deleteById(DeletDto deletDto);
    //分配角色
    void doAssign(AssginRoleDto assginRoleDto);
    //获取菜单接口
    List<SysMenuVo> menus();
}
