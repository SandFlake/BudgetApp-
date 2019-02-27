package com.mau.dalvi.expensesapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;


@Entity
public class ExpensesTable {

    @NonNull
    @PrimaryKey

    @ColumnInfo (name = "ex_title")
    private String ex_title;

    @ColumnInfo (name = "ex_price")
    private double ex_price;

    @ColumnInfo (name = "ex_category")
    private String ex_category;

    @ColumnInfo (name = "ex_date")
    @TypeConverters({TimestampConverter.class})
    private String ex_date;

    public ExpensesTable(String ex_title, double ex_price, String ex_category, String ex_date){
        this.ex_title = ex_title;
        this.ex_price = ex_price;
        this.ex_category = ex_category;
        this.ex_date = ex_date;
    }


    public String getEx_title() {
        return ex_title;
    }

    public void setEx_title(String ex_title) {
        this.ex_title = ex_title;
    }

    public double getEx_price() {
        return ex_price;
    }

    public void setEx_price(double ex_price) {
        this.ex_price = ex_price;
    }

    public String getEx_category() {
        return ex_category;
    }

    public void setEx_category(String ex_category) {
        this.ex_category = ex_category;
    }

    public String getEx_date() {
        return ex_date;
    }

    public void setEx_date(String ex_date) {
        this.ex_date = ex_date;
    }
}
