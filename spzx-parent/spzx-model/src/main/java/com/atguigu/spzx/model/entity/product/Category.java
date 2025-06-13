package com.atguigu.spzx.model.entity.product;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "分类实体类")
public class Category extends BaseEntity {
	@Schema(description = "分类名称")
	private String name;
	@Schema(description = "分类图片")
	private String imgUrl;
	@Schema(description = "父菜单id")
	private Integer parentId;
	@Schema(description = "是否显示[0-不显示，1显示]")
	private Integer status;
	@Schema(description = "排序")
	private Integer orderNum;
	@Schema(description = "是否有子菜单")
	private boolean hasChildren;
	@Schema(description = "子菜单")
	private List<Category> children;
}