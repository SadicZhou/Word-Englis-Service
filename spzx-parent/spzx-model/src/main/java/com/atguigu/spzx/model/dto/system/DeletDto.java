package com.atguigu.spzx.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "删除请求参数")
public class DeletDto {
    @Schema(description = "数据ID")
    private Integer id;
}
