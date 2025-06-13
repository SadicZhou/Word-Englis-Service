package com.atguigu.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "spzx.auth")//前缀要和yml配置的一致
public class UserProperties {
    //注意接收属性的变量名称需要和yml中配置的名称一直
    private List<String> noAuthUrl;
}
