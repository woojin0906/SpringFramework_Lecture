package com.example.di_ioc.interfaces;

public class TestClient {

    public static void main(String[] args) {

        OrderManager orderManager = new OrderManager();
        orderManager.setMaker(new HyundaiMaker());
        orderManager.order(20000);

    }
}
