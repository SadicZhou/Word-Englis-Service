package com.atguigu.spzx.model.entity.system;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "部门实体类")
public class SysDepart extends BaseEntity {

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "上级部门id")
    private Integer parentId;

    @Schema(description = "排序")
    private Integer sortValue;

    @Schema(description = "状态(0:正常,1:删除)")
    private Integer isDeleted;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "状态(0:正常,1:禁用)")
    private Integer status ;

    @Schema(description = "是否有子部门")
    private Boolean hasChildren;
}
