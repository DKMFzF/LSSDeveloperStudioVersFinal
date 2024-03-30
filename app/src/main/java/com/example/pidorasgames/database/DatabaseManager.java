package com.example.pidorasgames.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    public DatabaseManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Метод для открытия бд
    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    // Метод для закрытия бд
    public void close() {
        dbHelper.close();
    }

    // Метод для пуша данных в бд
    public void insertData(String dis, String quest, String ans, String var1, String var2, String var3, int used) {
        ContentValues values = new ContentValues();
        values.put(Constant.COLUMN_DISCIPLINE, dis);
        values.put(Constant.COLUMN_QUESTION, quest);
        values.put(Constant.COLUMN_RIGHT_ANSWER, ans);
        values.put(Constant.COLUMN_ANSWER_VAR1, var1);
        values.put(Constant.COLUMN_ANSWER_VAR2, var2);
        values.put(Constant.COLUMN_ANSWER_VAR3, var3);
        values.put(Constant.COLUMN_USED, used);

        database.insert(Constant.TABLE_NAME, null, values);
    }

    /**
     * Метод для получения данных из бд
     * return список из объектов QuestModel
      */
    public List<QuestModel> getAllData() {
        List<QuestModel> dataList = new ArrayList<>();


        Cursor cursor = database.query(
                Constant.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                QuestModel data = new QuestModel(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6), cursor.getInt(7));
                dataList.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dataList;
    }

    // Метод для удаление строки из бд
    public void deletOneRow(String row_id){
        long result = database.delete(Constant.TABLE_NAME, "id=?", new String[]{row_id});
    }
    
}