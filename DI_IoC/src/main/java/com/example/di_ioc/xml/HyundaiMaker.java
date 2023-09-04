package com.example.di_ioc.xml;

public class HyundaiMaker implements CarMaker {

    @Override
    public Car sell(Money money) {
        System.out.println("현대 자동차(입금) : " + money.getAmount());
        Car car = new Car("아이오닉 5");
        return car;
    }

}
