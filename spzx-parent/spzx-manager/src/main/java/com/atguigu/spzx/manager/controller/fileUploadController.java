package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.FileUploadService;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "文件上传")
@RestController
@RequestMapping(value = "admin/system/file")
public class fileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    //<input type=file name="file" />
    //MultipartFile 后面的参数名和input的name一致否则的无法获取文件
    //@RequestParam ("file") MultipartFile file 或 MultipartFile file 都可以
    @PostMapping(value = "/upload")
    public Result upload(@RequestParam ("file") MultipartFile file) {
        //1.获取上传的文件
        //2.调用service方法上传返回minio路径
        String path = fileUploadService.upload(file);
        return Result.build(path, ResultCodeEnum.SUCCESS);
    }
}
