package com.atguigu.spzx.model.entity.website;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Banner extends BaseEntity {

    @Schema(description = "banner图片")
    private String imgUrl;

    @Schema(description = "跳转链接")
    private String link;

    @Schema(description = "banner名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;
}
