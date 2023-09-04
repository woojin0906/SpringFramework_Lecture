package com.example.di_ioc.interfaces;

public class Car {

    private String name;        // 자동차 이름

    public Car(String name) {
        this.name = name;
    }  // 생성자

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
