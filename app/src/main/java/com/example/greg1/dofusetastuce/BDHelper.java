package com.example.greg1.dofusetastuce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by greg1 on 27/05/2018.
 */


public class BDHelper extends SQLiteOpenHelper {

    public BDHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String reqDonjon = "create table Donjon (id int, nom text, pos text, lvl int, idboss int, idzone int); ";
        String reqBoss = "create table Boss (id int, nom text, lvl int, pvmin int, pvmax int, pa int, pm int); ";
        String reqZone = "create table Zone (id int, nom text);";
        db.execSQL(reqDonjon);
        db.execSQL(reqBoss);
        db.execSQL(reqZone);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
}