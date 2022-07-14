package com.example.puckodynamics;

import android.app.Application;

import com.example.puckodynamics.data.model.Group;
import com.example.puckodynamics.data.model.Script;

import java.util.ArrayList;
import java.util.List;

public class AppDelegate extends Application {
    List<Group> groups = new ArrayList<>();
    {
        groups.add(new Group("Разное"));
        groups.add(new Group("Зима"));
        groups.add(new Group("Лето"));
        groups.add(new Group("Выходные"));
        groups.add(new Group("Будни"));
        groups.add(new Group("Ванная"));
    }
    List<Script> scripts = new ArrayList<>();
    {
        scripts.add(new Script("Уходим"));
        scripts.add(new Script("Приходим"));
        scripts.add(new Script("Жарко"));
        scripts.add(new Script("Романтика"));
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    public List<Group> getGroups() {
        return groups;
    }
    public List<Script> getScripts() {
        return scripts;
    }
}
