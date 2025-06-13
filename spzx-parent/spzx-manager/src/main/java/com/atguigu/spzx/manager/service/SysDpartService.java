package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.system.SysDepart;

import java.util.List;

public interface SysDpartService {
    //  查询所有部门
    List<SysDepart> findNodes(Integer parentId);
    //  新增部门
    void save(SysDepart sysDepart);

    //修改部门
    void updateById(SysDepart sysDepart);

    //删除部门
    void deleteById(Long id);
}
