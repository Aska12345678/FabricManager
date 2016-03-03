package com.steinsvik.fabricmanagaer.data;

import java.util.Date;

/**
 * Created by Gigabyte on 3/2/2016.
 */
public class Information {
    private Date date;
    private double price;
    private String detail;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
