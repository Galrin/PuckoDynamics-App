package com.example.puckodynamics.data.model;

import java.util.List;

public class Group {

    String name;
    List<Device> scriptList;

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, List<Device> scriptList) {
        this.name = name;
        this.scriptList = scriptList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
