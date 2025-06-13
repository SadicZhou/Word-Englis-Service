package com.atguigu.spzx.manager.controller;
import com.atguigu.spzx.manager.service.SysDpartService;
import com.atguigu.spzx.model.entity.system.SysDepart;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "部门管理")
@RestController
@RequestMapping("/admin/system/sysDepart")
public class SysDepartController {

    @Autowired
    private SysDpartService sysDepartService;

    @Operation(summary = "部门列表")
    @GetMapping("/findNodes")
    public Result findNodes(Integer parentId ) {
        List<SysDepart> list = sysDepartService.findNodes(parentId);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "保存部门")
    @PostMapping("/save")
    public Result save(@RequestBody SysDepart sysDepart) {
        sysDepartService.save(sysDepart);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改部门")
    @PostMapping("/updateById")
    public Result updateById(@RequestBody SysDepart sysDepart) {
        sysDepartService.updateById(sysDepart);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除部门")
    @GetMapping("/deleteById")
    public Result deleteById(Long id) {
        sysDepartService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
