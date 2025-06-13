package com.atguigu.spzx.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "查询dto")
public class SeleteDto {
    @Schema(description = "数据id")
   private long id;
}
