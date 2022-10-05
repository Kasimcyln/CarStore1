package com.kasimkartal866.carstore.common;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.kasimkartal866.carstore.db.Dao;
import com.kasimkartal866.carstore.db.MyDatabase;
public class App extends Application {
    private static MyDatabase myDatabase;
    public static Dao dao;
    private static SharedPreferences pref;
    private static SharedPreferences.Editor prefEditor;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        myDatabase = myDatabase.getMyDatabase(getApplicationContext());
        dao = myDatabase.dao();
    }
    public static Context getContext() {
        return context;
    }
}