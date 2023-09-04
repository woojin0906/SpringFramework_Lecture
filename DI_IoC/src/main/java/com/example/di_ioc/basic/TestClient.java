package com.example.di_ioc.basic;

public class TestClient {

    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();
        orderManager.order(10000);

    }
}
