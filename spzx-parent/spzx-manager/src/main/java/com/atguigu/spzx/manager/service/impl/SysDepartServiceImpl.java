package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysDepartMapper;
import com.atguigu.spzx.manager.service.SysDpartService;
import com.atguigu.spzx.model.entity.system.SysDepart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDepartServiceImpl implements SysDpartService {
    @Autowired private SysDepartMapper sysDepartMapper;
    //查询部门列表

    @Override
    public List<SysDepart> findNodes(Integer parentId) {
        List<SysDepart> list = sysDepartMapper.findNodes(parentId);
        for( SysDepart sysDepart :  list) {
          int count =  sysDepartMapper.countByParentId(sysDepart.getId());
          if(count > 0){
              sysDepart.setHasChildren(true);
          }
        }
        return list;
    }
    //新增部门
    @Override
    public void save(SysDepart sysDepart) {
        sysDepartMapper.save(sysDepart);
    }
    //修改部门
    @Override
    public void updateById(SysDepart sysDepart) {
        sysDepartMapper.updateById(sysDepart);
    }
    //删除部门
    @Override
    public void deleteById(Long id) {
        sysDepartMapper.deleteById(id);
    }
}
