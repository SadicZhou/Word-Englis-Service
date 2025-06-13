package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.DeletDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    //用户信息查询
   public abstract SysUser selectUserInfoByUserName(String userName);
    //用户列表
   public abstract List<SysUser> findByPage(SysUserDto sysUserDto);
   //按照用户名查找用户
    public abstract SysUser findByUserName(SysUser sysUser);
    //新增用户
    public abstract void saveSysUser(SysUser sysUser);
    //修改用户
    public abstract void updateSysUser(SysUser sysUser);
    //删除用户
    public abstract void deleteById(DeletDto deletDto);
}
