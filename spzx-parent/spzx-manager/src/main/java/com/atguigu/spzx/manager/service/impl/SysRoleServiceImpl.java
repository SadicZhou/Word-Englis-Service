package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysRoleMapper;
import com.atguigu.spzx.manager.mapper.SysUserRoleMapper;
import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.model.dto.system.DeletDto;
import com.atguigu.spzx.model.dto.system.SeleteDto;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    //查询当前用户角色mapper
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    //分页查询角色列表
    @Override
    public PageInfo<SysRole> findByPage(Integer current, Integer limit, SysRoleDto sysRoleDto) {
        //设置分页参数
        PageHelper.startPage(current,limit);
        //根据条件查询所有数据
        List<SysRole> list = sysRoleMapper.findByPage(sysRoleDto);
        //封装pageInfo对象
        PageInfo<SysRole> pageInfo= new PageInfo<>(list);
        return pageInfo;
    }
    //添加角色
    @Override
    public void saveSysRole(SysRoleDto sysRoleDto) {
        sysRoleMapper.saveSysRole(sysRoleDto);
    }
    //修改角色
    @Override
    public void updateSysRole(SysRoleDto sysRoleDto) {
        sysRoleMapper.updateSysRole(sysRoleDto);
    }
    //删除角色
    @Override
    public void deleteById(DeletDto deletDto) {
        sysRoleMapper.deleteById(deletDto);
    }

    @Override
    public Map<String, Object> findAllRoles(SeleteDto seleteDto) {
        List<SysRole> sysRoleList = sysUserRoleMapper.findAllRoles();
        List<Long> userRoleList = sysUserRoleMapper.findSysUserRoleByUserId(seleteDto);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("roleList",sysRoleList);
        resultMap.put("userRoleList",userRoleList);
        return resultMap;
    }

}
