package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import com.atguigu.spzx.manager.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.model.dto.system.DeletDto;
import com.atguigu.spzx.model.dto.system.SysMenuDto;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.utils.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Override
    public List<SysMenu> findNodes() {
        //1.查出所有菜单
        List<SysMenu> menuList = sysMenuMapper.findNodes();
       //2.构造菜单层级
        List<SysMenu> menusTree = MenuHelper.buildTree(menuList);
        return menusTree;
    }

    @Override
    public void save(SysMenu sysMenu) {
       sysMenuMapper.save(sysMenu);
        //如果新增时有父菜单，设置父菜单为半开
        updateSysRoleMenuIsHalf(sysMenu);

    }
    public void updateSysRoleMenuIsHalf(SysMenu sysMenu){
        //查找父节点
        SysMenu parentMenu = sysMenuMapper.selectById(sysMenu.getParentId());
        //如果有父节点设置父节点半开
        if(parentMenu != null){
            sysRoleMenuMapper.updateIsHlaf(parentMenu.getId());
            //递归对父节点的父节点查询并设置半开
            updateSysRoleMenuIsHalf(parentMenu);
        }
    }
    @Override
    public void updateById(SysMenuDto sysMenuDto) {
        sysMenuMapper.updateById(sysMenuDto);
    }

    @Override
    public void deleteById(DeletDto deletDto) {
        //1.判断是否有子菜单，如果有禁止删除
        Integer count = sysMenuMapper.selectCountById(deletDto);
        if (count > 0) {
            throw new GuiguException(ResultCodeEnum.NODE_ERROR);
        }else{
            sysMenuMapper.deleteById(deletDto);
        }
    }

}
