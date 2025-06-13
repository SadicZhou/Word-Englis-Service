package com.atguigu.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.img.ImgUtil;
import com.atguigu.spzx.manager.service.ValidateCodeService;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public ValidateCodeVo generateValidateCode(){
        //1.通过工具生成图片验证码,hutool
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(150,45,4,2);//长、宽、位数、干扰线数量
        //2.把验证码存储到redis中,设置redis的key：uuid,value:验证码，过期时间
        String validateCode = lineCaptcha.getCode();//获取验证码值
        //获取验证码图片
        //修改验证码突破原色(先改变背景色，然后重新生成图片，将图片转为base64)
        lineCaptcha.setBackground(Color.decode("#f8f8f8"));
        Image lineCaptchaImage = lineCaptcha.createImage(validateCode);
        //String validateCodeB64 = lineCaptcha.getImageBase64();
        String validateCodeB64 = ImgUtil.toBase64(lineCaptchaImage,"png");
        String key = UUID.randomUUID().toString().replaceAll("-","");
        redisTemplate.opsForValue().set("user:login:validateCode"+key,validateCode,5, TimeUnit.MINUTES);
        //3.返回ValidateCodeVo对象
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(key);
        validateCodeVo.setCodeValue("data:image/png;base64,"+validateCodeB64);
        return  validateCodeVo;
    }
}
