package com.example.pidorasgames.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHendler extends SQLiteOpenHelper {

    public DataBaseHendler(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
    }

    // Мтеод для создания BD
    @Override
    public void onCreate(SQLiteDatabase db) {
        
    }

    // Метод для обновления BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constant.DATABASE_NAME); // Удаление BD
        onCreate(db); // Создание BD
    }
}
