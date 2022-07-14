package com.example.puckodynamics.data.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class SettingsData implements Serializable {

    @Expose
    List<Group> groups;
}
