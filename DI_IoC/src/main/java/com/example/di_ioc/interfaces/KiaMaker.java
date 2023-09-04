package com.example.di_ioc.interfaces;

public class KiaMaker implements CarMaker {

    @Override  // 기본값으로 붙긴 함
    public Car sell(Money money) {
        System.out.println("기아 자동차(입금) : " + money.getAmount());
        Car car = new Car("K5");
        return car;
    }

}
