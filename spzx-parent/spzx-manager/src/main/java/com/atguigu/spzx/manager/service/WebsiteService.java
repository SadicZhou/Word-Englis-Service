package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.website.Banner;

import java.util.List;

public interface WebsiteService {
     void saveBanner(Banner banner);
     List<Banner> findAllBanner();
}
