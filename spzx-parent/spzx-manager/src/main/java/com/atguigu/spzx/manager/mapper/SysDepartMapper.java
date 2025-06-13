package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.system.SysDepart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDepartMapper {
    //查询所有部门信息
    List<SysDepart> findNodes(Integer parentId);
    //查询子部门数量
    Integer  countByParentId(Long parentId);
    //新增部门
    void save(SysDepart sysDepart);
    //修改部门
    void updateById(SysDepart sysDepart);
    //删除部门
    void deleteById(Long id);
}
