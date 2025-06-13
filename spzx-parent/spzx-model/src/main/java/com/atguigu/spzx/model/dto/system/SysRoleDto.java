package com.atguigu.spzx.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "请求参数实体类")
public class SysRoleDto {
    @Schema(description = "id")
    private Integer id;
    
    @Schema(description = "角色名称")
    private String roleName ;

    @Schema(description = "角色编码")
    private String roleCode ;

    @Schema(description = "备注")
    private String description;
}
