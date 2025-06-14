package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.manager.service.ValidateCodeService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import com.atguigu.spzx.model.vo.system.SysUserVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import com.atguigu.spzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ValidateCodeService validateCodeService;
    //用户登录
    @Operation(summary = "登录")
    @PostMapping(value = "Login")
    public Result login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    //登录图形验证码
    @Operation(summary = "生成图形验证码")
    @PostMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode(){
        ValidateCodeVo validateCodeVo =  validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }

    //获取用户信息(通过token查询redis)
    @Operation(summary = "用户信息(redis)")
    @GetMapping(value = "/getUserInfoRedis")
    public Result getUserInfoRedis(@RequestHeader(name = "token") String token){
        SysUserVo sysUser = sysUserService.getUserInfo(token);
        return Result.build(sysUser, ResultCodeEnum.SUCCESS);
    }
    //获取用户信息(从theadLocal获取)
    @Operation(summary = "用户信息")
    @GetMapping(value = "/getUserInfo")
    public Result getUserInfo(){
        return Result.build(AuthContextUtil.get(), ResultCodeEnum.SUCCESS);
    }
    //登出
    @Operation(summary = "登出")
    @GetMapping("/logout")
    public Result logout(@RequestHeader(name = "token") String token){
        sysUserService.logout(token);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    //获取菜单
    @Operation(summary = "获取动态路由")
    @GetMapping("/menus")
    public Result menus(){
        List<SysMenuVo> menus = sysUserService.menus();
        return Result.build(menus,ResultCodeEnum.SUCCESS);
    }
}
