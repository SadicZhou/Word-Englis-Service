package com.atguigu.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.properties.MinIoProperties;
import com.atguigu.spzx.manager.service.FileUploadService;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.Date;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired()
    private MinIoProperties minIoProperties;
    @Override
    public String upload(MultipartFile file) {
        try {
            System.out.println("Endpoint URL: " + minIoProperties.getEndpointUrl());
            System.out.println("Access Key: " + minIoProperties.getAccessKey());
            System.out.println("Secret Key: " + minIoProperties.getSecretKey());
            System.out.println("Bucket Name: " + minIoProperties.getBucketName());

            // 创建minioClient对象
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(minIoProperties.getEndpointUrl())
                            .credentials(minIoProperties.getAccessKey(), minIoProperties.getSecretKey())
                            .build();

            // 寻找bucket如果不存在就创建一个
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(minIoProperties.getBucketName()).build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minIoProperties.getBucketName()).build());
            } else {
                System.out.println("Bucket"+minIoProperties.getBucketName()+"已经存在.");
            }
            //1.确保每个文件上传生成的文件名唯一，使用uuid生成
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //2.根据当前日期对文件进行分组 20241031
            //
            String date = DateUtil.format(new Date(),"yyyyMMdd");
            //组装上传的文件名最终 20241031/uuid+file.getOriginalFilename
            String fileName =date+"/"+uuid+file.getOriginalFilename();
           //获取文件输入流
            InputStream stream = file.getInputStream();
            //获取文件大小
          long size = file.getSize();
           //上传(stream形式)
            //bucket传入bucket名称
            // object传入文件名称
            //stream传入文件输入流
            //contentType规定文件类型
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(minIoProperties.getBucketName())
                            .object(fileName)
                            .stream(stream, size, -1)
                            //.contentType("video/mp4")
                            .build());
            //返回文件路径

            return minIoProperties.getEndpointUrl()+"/"+minIoProperties.getBucketName()+"/"+fileName;
        } catch (Exception e) {
           e.printStackTrace();
           throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
    }
}
