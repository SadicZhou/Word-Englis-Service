package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.WebsiteMapper;
import com.atguigu.spzx.manager.service.WebsiteService;
import com.atguigu.spzx.model.entity.website.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteServiceImpl implements WebsiteService {
    @Autowired
    private WebsiteMapper websiteMapper ;
    @Override
    public void saveBanner(Banner banner) {
        websiteMapper.saveBanner(banner);
    }

    @Override
    public List<Banner> findAllBanner() {
        return websiteMapper.findAllBanner();
    }
}
