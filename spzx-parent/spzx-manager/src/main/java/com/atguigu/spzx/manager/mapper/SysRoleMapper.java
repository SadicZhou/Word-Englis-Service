package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.DeletDto;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    //分页查询
    List<SysRole> findByPage(SysRoleDto sysRoleDto);
    //添加角色
   void saveSysRole(SysRoleDto sysRoleDto);
   //修改角色
    void updateSysRole(SysRoleDto sysRoleDto);
    //删除角色
    void deleteById(DeletDto deletDto);

}
