package com.atguigu.spzx.model.entity.system;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class SysRoleUser {
    private Long roleId;       // 角色id
    private Long userId;       // 用户id
}
