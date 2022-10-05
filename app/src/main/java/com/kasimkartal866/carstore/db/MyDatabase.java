package com.kasimkartal866.carstore.db;


import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class MyDatabase extends RoomDatabase {
    private static final String dbName = "car";
    private static MyDatabase myDatabase;

    public static synchronized MyDatabase getMyDatabase(Context context) {
        if(myDatabase == null) {
            myDatabase = Room.databaseBuilder(context,MyDatabase.class,dbName).fallbackToDestructiveMigration().build();
        }
        return myDatabase;
    }
    public abstract Dao dao ();
}
