package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.vo.system.ValidateCodeVo;

public interface ValidateCodeService {
    //图片验证码生成方法
    ValidateCodeVo generateValidateCode();
}
