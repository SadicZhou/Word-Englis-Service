package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.AssginMenuDto;
import com.atguigu.spzx.model.dto.system.SeleteDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysRoleMenuMapper {
    //查询当前角色所有菜单
    List<Long> findSysRoleMenuByRoleId(SeleteDto seleteDto);
    //分配菜单
    void assignMenu(AssginMenuDto assginMenuDto);
    //删除绑定关系
    void deleteById(Long roleId);
    //设置半开
    void updateIsHlaf(Long menuId);
}
