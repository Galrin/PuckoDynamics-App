package com.example.puckodynamics.data.model;

public class Device {
    String name;


    int resId;


    public Device(String name) {
        this.name = name;
    }
    public Device(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
