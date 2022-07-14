package com.example.puckodynamics.data.model;

import java.net.URL;
import java.util.List;

public class Script {
    String name;
    String path;
    //List<>

    public Script(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
