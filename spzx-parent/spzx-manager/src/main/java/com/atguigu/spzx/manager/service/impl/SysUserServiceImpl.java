package com.atguigu.spzx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import com.atguigu.spzx.manager.mapper.SysUserMapper;
import com.atguigu.spzx.manager.mapper.SysUserRoleMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.dto.system.DeletDto;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import com.atguigu.spzx.utils.AuthContextUtil;
import com.atguigu.spzx.utils.MenuHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    RedisTemplate<String,String> redisTemplate;//通过Autowired注解注入redis实例

    //登录方法
    @Override
    public LoginVo login(LoginDto loginDto) {
        //获取图片验证码在redis中的key
        String valiDateCodeKey = loginDto.getCodeKey();
        //通过key获取验证码的value
        String valiDateCodeValue = loginDto.getCaptcha();
        String valiDateCodeValueRedis = redisTemplate.opsForValue().get("user:login:validateCode"+valiDateCodeKey);
        //如果为空或者不一致，提示用户校验失败（不区分大小写）
        if(StrUtil.isEmpty(valiDateCodeValue)|| !StrUtil.equalsIgnoreCase(valiDateCodeValue,valiDateCodeValueRedis)){
            throw new GuiguException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        //如果一致删除redis中的验证码
        redisTemplate.delete(valiDateCodeKey);
        //1.获取用户名
        String userName = loginDto.getAccount();
        //2.从数据库查询对应用户信息
        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(userName);
        //3.如果查询不到用户信息，返回错误信息
        if (sysUser == null) {
            //throw  new RuntimeException("用户名不存在");
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }
        //4.如果根据用户名查询到用户信息存在
        //5.获取输入的密码，比较输入的密码与数据库的密码是否一致，如果密码一致登录成功，如果密码不一致登录失败
        String input_password = loginDto.getPassword();
        String password = sysUser.getPassword();
        //将输入的密码加密后与数据库中的密码比较，使用DigestUtils.md5Digest工具类加密
        String md5_password = DigestUtils.md5DigestAsHex(input_password.getBytes());
        if (!password.equals(md5_password)) {
            //throw new RuntimeException("密码错误");
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }
        //6.登录成功生成用户唯一标识token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        //7.把登录成的用户信息存放到redis里面
        redisTemplate.opsForValue()
                .set("user:login" + token, JSON.toJSONString(sysUser), 30, TimeUnit.MINUTES);
        //8.返回LoginVo对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }

    //获取用户信息
    @Override
    public SysUser getUserInfo(String token) {
        if(StrUtil.isEmpty(token)){
            throw new GuiguException(ResultCodeEnum.TOKEN_MISS);
        }
        String tokenRedis = redisTemplate.opsForValue().get("user:login" + token);
        SysUser user = JSON.parseObject(tokenRedis, SysUser.class);
        return user;
    }
    //登出
    @Override
    public Void logout(String token) {
        redisTemplate.delete("user:login" + token);
        return null;
    }

    //分页查询
    @Override
    public PageInfo<SysUser> findBypage(Integer current, Integer limit, SysUserDto sysUserDto) {
        //设置分页参数
        PageHelper.startPage(current,limit);
        //根据条件查询所有数据
        List<SysUser> userList= sysUserMapper.findByPage(sysUserDto);
        //封装pageInfo对象
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(userList);
        return pageInfo;
    }

    @Override
    public void saveSysUser(SysUser sysUser) {
        //判断用户名是否存在
        SysUser user = sysUserMapper.findByUserName(sysUser);
        if(user != null){
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //对密码进行加密
        String password = sysUser.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(md5Password);
        //将用户状态设置为0
        sysUser.setStatus(0);
        sysUserMapper.saveSysUser(sysUser);
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteById(DeletDto deletDto) {
        sysUserMapper.deleteById(deletDto);
    }

    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {
        //1.删除该用户的所有角色
        sysUserRoleMapper.deleteById(assginRoleDto.getUserId());
        //2.重新绑定角色
        assginRoleDto.getRoleIdList().forEach(roleid->{
            sysUserRoleMapper.doAssign(assginRoleDto.getUserId(),roleid);
        });
    }

    @Override
    public List<SysMenuVo> menus() {
        //1.获取当前用户id
       SysUser user = AuthContextUtil.get();
        Long userId = user.getId();
        //2.根据用户id连表查询出所有的菜单信息
        List<SysMenu> menusList = sysMenuMapper.selectByUserId(userId);
        //3.构建树形结构
        List<SysMenu> menuTree = MenuHelper.buildTree(menusList);
        return buildMenus(menuTree);
    }

    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    public static List<SysMenuVo> buildMenus(List<SysMenu> menusList) {
        List<SysMenuVo> menuVoList = new LinkedList<SysMenuVo>();
        for(SysMenu sysMenu : menusList){
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setComponent(sysMenu.getComponent());
            sysMenuVo.setPath(sysMenu.getPath());
            sysMenuVo.setName(sysMenu.getName());
            sysMenuVo.setParentId(sysMenu.getParentId());
            //如果有子菜单对子菜单递归进行转换
            if(!CollectionUtils.isEmpty(sysMenu.getChildren())){
                sysMenuVo.setChildren(buildMenus(sysMenu.getChildren()));
            }
            menuVoList.add(sysMenuVo);
        }
        return menuVoList;
    }

}
