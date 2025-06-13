package com.atguigu.spzx.manager.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class LoginAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    //请求前置拦截器
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求方式
        String method = request.getMethod();
        //如果请求方式是options预检请求，直接放行
        if("OPTIONS".equals(method)){
            return true;
        }
        //2.从请求头获取token
        String token = request.getHeader("token");
        //3.如果token为空，返回错误提示
        if(StrUtil.isEmpty(token)){
            responseNoLoginInfo(response);
            return false;
        }
        //4.如果token不为空，拿着token查询redis中的用户信息
        String userRedis = redisTemplate.opsForValue().get("user:login"+token);
        //5.如果查询为空，返回错误提示
        if(StrUtil.isEmpty(userRedis)){
            responseNoLoginInfo(response);
            return false;
        }
        //6.如果不为空，将用户信息存储到threadLocal
        SysUser user = JSON.parseObject(userRedis, SysUser.class);
        AuthContextUtil.set(user);
        //7.更新redis过期时间
        redisTemplate.expire("user:login"+token,30, TimeUnit.MINUTES);
        //8.放行
        return true;
    }
    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }

    //请求后置拦截器
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
       //threadLocal删除
        AuthContextUtil.remove();
    }
}
