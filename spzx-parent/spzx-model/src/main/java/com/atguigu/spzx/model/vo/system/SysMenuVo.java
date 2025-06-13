package com.atguigu.spzx.model.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "系统菜单响应结果实体类")
public class SysMenuVo {

    @Schema(description = "系统菜单标题")
    private String title;

    @Schema(description = "组件名称")
    private String component;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "菜单地址")
    private String path;

    @Schema(description = "父级菜单id")
    private Long parentId;

    @Schema(description = "系统菜单子菜单列表")
    private List<SysMenuVo> children;

}