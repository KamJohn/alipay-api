package com.kam.pay.alipay.config;

import com.kam.pay.alipay.util.PropertiesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:api.properties", encoding = "utf-8")
@ConfigurationProperties(prefix = "alipay")
public class BaseProperties {

    /**
     * 应用appid
     */
    @Value("${alipay.app.id}")
    private String appId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public static void main(String[] args) {
        System.out.println(PropertiesUtils.propertiesUtil.getStringProperty("alipay.app.id"));
    }
}
