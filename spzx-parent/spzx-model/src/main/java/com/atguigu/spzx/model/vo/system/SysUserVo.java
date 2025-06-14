package com.atguigu.spzx.model.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "系统用户信息响应结果实体类")
public class SysUserVo {

    @Schema(description = "唯一标识")
    private Long id;

    @Schema(description = "用户名-账号")
    private String userName;

    @Schema(description = "昵称")
    private String name;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "状态（1：正常 0：停用）")
    private Integer status;

    @Schema(description = "图像")
    private String avatar;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "角色编码集合")
    private List<String> roleCodeList;
}
