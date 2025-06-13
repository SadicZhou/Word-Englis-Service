package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.dto.system.DeletDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysRoleUser;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "用户列表")
    @PostMapping(value = "/list/{current}/{limit}")
    public Result findBypage(@PathVariable Integer current, @PathVariable Integer limit, @RequestBody SysUserDto sysUserDto){
        //pageHelper插件实现分页
        PageInfo<SysUser> userList = sysUserService.findBypage(current,limit,sysUserDto);
        return Result.build(userList, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "新增用户")
    @PostMapping(value = "/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser){
         sysUserService.saveSysUser(sysUser);
         return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改用户")
    @PostMapping(value = "/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser){
        sysUserService.updateSysUser(sysUser);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除用户")
    @PostMapping(value = "/deleteById")
    public Result deleteById(@RequestBody DeletDto deletDto){
        sysUserService.deleteById(deletDto);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "分配角色")
    @PostMapping(value = "/doAssign")
    public Result doAssign(@RequestBody AssginRoleDto assginRoleDto){
        sysUserService.doAssign(assginRoleDto);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
