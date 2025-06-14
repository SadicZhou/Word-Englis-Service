package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.model.dto.system.DeletDto;
import com.atguigu.spzx.model.dto.system.SysMenuDto;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @Operation(summary = "菜单列表")
    @PostMapping("/findNodes")
    public Result findNodes(){
        List<SysMenu> menuList = sysMenuService.findNodes();
        return Result.build(menuList, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "新增菜单")
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu){
        sysMenuService.save(sysMenu);
        return  Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改菜单")
    @PostMapping("/update")
    public Result update(@RequestBody SysMenuDto SysMenuDto){
        sysMenuService.updateById(SysMenuDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除菜单")
    @PostMapping("deleteById")
    public Result deleteById(@RequestBody DeletDto deletDto){
        sysMenuService.deleteById(deletDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
