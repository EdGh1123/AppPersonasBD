package com.ed.AppPersonaBD.BDAC;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BD extends SQLiteOpenHelper {

    public BD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override  public void onCreate(SQLiteDatabase DB) { DB.execSQL(DefDB.CREATE_TABLA_PERSONA); }
    @Override  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { onCreate(db); }
}
