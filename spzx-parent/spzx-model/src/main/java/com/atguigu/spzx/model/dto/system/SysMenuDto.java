package com.atguigu.spzx.model.dto.system;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.context.annotation.Description;

@Schema(description = "菜单dto")
@Data
public class SysMenuDto extends BaseEntity {
    private String title;
    private String component;
    private Integer sortValue;
    private Integer parentId;
    private Integer status;
    private String path;
}
