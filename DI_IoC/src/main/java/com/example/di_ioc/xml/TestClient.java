package com.example.di_ioc.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClient {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("car-config.xml");  // 설정에 관한 파일 삽입

        OrderManager orderManager = context.getBean("manager", OrderManager.class);  // 아래 new와 동일
        orderManager.order(30000);

//        OrderManager orderManager = new OrderManager();  // 위와 동일한 것
//        orderManager.setMaker(new HyundaiMaker());
//        orderManager.order(20000);

    }
}
