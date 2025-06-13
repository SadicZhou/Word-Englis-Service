package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysRoleMenuService;
import com.atguigu.spzx.model.dto.system.AssginMenuDto;
import com.atguigu.spzx.model.dto.system.SeleteDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@Tag(name = "分配菜单")
@RestController
@RequestMapping(value = "/admin/system/sysRoleMenu")
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Operation(summary = "查询角色菜单")
    @PostMapping("/findSysRoleMenuByRoleId")
    public Result<Map<String, Object>> findSysRoleMenuByRoleId(@RequestBody SeleteDto seleteDto){
        Map<String,Object> menuMap = sysRoleMenuService.findSysRoleMenuByRoleId(seleteDto);
        return  Result.build(menuMap, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "分配菜单")
    @PostMapping("/assignMenu")
    public Result assignMenu(@RequestBody AssginMenuDto assginMenuDto){
        sysRoleMenuService.assignMenu(assginMenuDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}
