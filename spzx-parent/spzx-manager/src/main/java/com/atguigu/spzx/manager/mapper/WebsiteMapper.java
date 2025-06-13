package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.website.Banner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WebsiteMapper {
     void saveBanner(Banner banner);

     List<Banner> findAllBanner();
}
