package com.atguigu.spzx.manager.controller;
import com.atguigu.spzx.manager.service.WebsiteService;
import com.atguigu.spzx.model.entity.website.Banner;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "网站管理接口")
@RestController
@RequestMapping("/admin/website")
public class WebsiteController {
    @Autowired
    private WebsiteService websiteService;

    @Operation(summary = "保存banner")
    @PostMapping("saveBanner")
    public Result  saveBanner(Banner banner){
        websiteService.saveBanner(banner);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "查询banner")
    @PostMapping("findAllBanner")
    public Result findAllBanner(){
        List<Banner> list = websiteService.findAllBanner();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
