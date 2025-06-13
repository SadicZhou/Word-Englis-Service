package com.atguigu.spzx.utils;

import com.atguigu.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {
    public static List<SysMenu> buildTree(List<SysMenu> menuList){
        List<SysMenu> treeList = new ArrayList<SysMenu>();
        //递归构建树形结构
        for(SysMenu menu : menuList){
            if(menu.getParentId().longValue() == 0){
                //如果没有parentId证明这是父节点(一级菜单)，开始给他添加子节点
                treeList.add(findChildren(menu,menuList));
            }

        }
        return treeList;
    }
    private static SysMenu findChildren(SysMenu menu,List<SysMenu> menuList){
        //递归查找子节点
          for(SysMenu child : menuList){
              if(child.getParentId().longValue() == menu.getId().longValue()){
                  //如果循环中菜单的parentId和父节点(一级菜单)的Id相同，父节点的children中添加该菜单
                  //对于循环中的菜单(二级、三级菜单等..)也一样，一直递归，直到没有下级子菜单为止
                  if (menu.getChildren() == null) {
//                    //防止空值报错
                      menu.setChildren(new ArrayList<>());
                  }
                  menu.getChildren().add(findChildren(child,menuList));
              }
          }
          return menu;
    }
}
