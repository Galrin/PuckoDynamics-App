package com.example.puckodynamics;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.puckodynamics.data.model.Group;
import com.example.puckodynamics.data.model.Device;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppDelegate extends Application {
    List<Group> groups = new ArrayList<>();
//    {
//        groups.add(new Group("Разное"));
//        groups.add(new Group("Зима"));
//        groups.add(new Group("Лето"));
//        groups.add(new Group("Выходные"));
//        groups.add(new Group("Будни"));
//        groups.add(new Group("Ванная"));
 //   }
    List<Device> scripts = new ArrayList<>();
    {
        scripts.add(new Device("Колонка Алиса", R.drawable.alice));
        scripts.add(new Device("Пылесос", R.drawable.vacuum_robot));
    }

    @Override
    public void onCreate() {
        super.onCreate();

        File grousDir = getApplicationContext().getDir("groups", Context.MODE_PRIVATE); //Creating an internal dir;
        if (!grousDir.exists())
        {
            grousDir.mkdirs();
        }
    }


    public void revalidateGroups() {
        groups.clear();
        for(String fileName : getApplicationContext().fileList()) {
            Log.d("FILE: ", fileName);
            groups.add(new Group(fileName));
        }
    }
    public List<Group> getGroups() {
        revalidateGroups();

        return groups;

    }
    public List<Device> getScripts() {
        return scripts;
    }
}
