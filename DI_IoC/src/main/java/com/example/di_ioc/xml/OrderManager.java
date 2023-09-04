package com.example.di_ioc.xml;

public class OrderManager {

    private CarMaker maker;

    public OrderManager() {}

    public OrderManager(CarMaker maker) {
        this.maker = maker;
    }

    public void order(int cost) {       // 주문 - 돈 받으면 차를 받아올 메소드
        Money money = new Money(cost);
        Car car = maker.sell(money);        // 현대에게 물건을 팔기

        System.out.println("판매상(인수) : " + car.getName());
    }

    public void setMaker(CarMaker maker) {
        this.maker = maker;
    }
}
