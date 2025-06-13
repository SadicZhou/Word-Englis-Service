package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.SeleteDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {
    //查询所有角色
    List<SysRole> findAllRoles();
    //查询当前用户角色
    List<Long> findSysUserRoleByUserId(SeleteDto seleteDto);
    //删除当前用户所有角色
    void deleteById(Long userid);
    //绑定角色
    void doAssign(Long userid,Long roleid);
}
