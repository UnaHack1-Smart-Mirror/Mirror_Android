package com.yaoandy.unihack;

/**
 * Created by Andy on 2017/2/18.
 */

public class Transaction {
    int hum;
    int temp;

    public int getHumility() {
        return hum;
    }

    public void setHumility(int humility) {
        this.hum = humility;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public Transaction(int temp, int hum) {
        this.hum = hum;
        this.temp = temp;
    }
}