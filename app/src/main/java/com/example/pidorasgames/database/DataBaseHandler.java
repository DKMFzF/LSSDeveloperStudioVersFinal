package com.example.pidorasgames.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Класс для работы с BD

public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
    }

    // Мтеод для создания BD
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + Constant.DATABASE_NAME
                + " (" + Constant.KEY_ID + " INTEGER PRIMARY KEY, "
                + Constant.KEY_DISCIPLINE + " TEXT, "
                + Constant.KEY_QUESTION + " TEXT" + " )"; // SQL запрос на создание таблицы

        db.execSQL(CREATE_EMPLOYEE_TABLE); // Запрос в BD
    }

    // Метод для обновления BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constant.DATABASE_NAME); // Удаление BD
        onCreate(db); // Создание BD
    }
}
