package com.example.di_ioc.xml;

public class Money {

    private int amount;     // 총액

    public Money(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
