package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.model.dto.system.DeletDto;
import com.atguigu.spzx.model.dto.system.SeleteDto;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name="角色管理")
@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    //角色列表方法
    //current:当前页 limit:每页数量
    //sysRoleDto 查询参数对象
    @Operation(summary="角色列表")
    @PostMapping("/findByPage/{current}/{limit}")

    public Result findByPage(@PathVariable Integer current, @PathVariable Integer limit, @RequestBody SysRoleDto sysRoleDto){

        //pageHelper插件实现分页
       PageInfo<SysRole> pageInfo = sysRoleService.findByPage(current,limit,sysRoleDto);
       return Result.build(pageInfo,ResultCodeEnum.SUCCESS);
    }

    //角色添加
    @Operation(summary = "角色添加")
    @PostMapping("/saveSysRole")
    public Result saveSysRole(@RequestBody SysRoleDto sysRoleDto){
        sysRoleService.saveSysRole(sysRoleDto);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //角色修改
    @Operation(summary = "角色修改")
    @PostMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRoleDto sysRoleDto){
        sysRoleService.updateSysRole(sysRoleDto);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //角色删除
    @Operation(summary = "角色删除")
    @PostMapping("/deleteById")
    public Result deleteById(@RequestBody DeletDto deletDto){
        sysRoleService.deleteById(deletDto);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //查找所有角色
    @Operation(summary = "查找所有角色")
    @PostMapping(value = "/findAllRoles")
    public Result findAllRoles(@RequestBody SeleteDto seleteDto){
        Map<String,Object> resultMap = sysRoleService.findAllRoles(seleteDto);
        return Result.build(resultMap,ResultCodeEnum.SUCCESS);
    }
}
