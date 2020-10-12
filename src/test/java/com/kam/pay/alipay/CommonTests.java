package com.kam.pay.alipay;

import com.kam.pay.alipay.config.BaseProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CommonTests {
    @Autowired
    BaseProperties baseProperties;

    @Test
    public void contextLoads() {
        System.out.println(baseProperties.getAppId());
    }

}
