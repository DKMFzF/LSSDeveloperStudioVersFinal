package com.example.pidorasgames.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private DBHelper dbHelper;
    public String[] categories;
    private SQLiteDatabase database;

    private Cursor cursor;
    private String query;
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
    public List<QuestModel> getData(List<String> cats) {
        List<QuestModel> dataList = new ArrayList<>();

        // SelectionArgs
        categories = cats.toArray(new String[0]);

        int all_question = 3;
        int cur_amount_question;
        int amount_of_questions_one_cat = (int) Math.floor((double) all_question / cats.size());


        for (int i = 0; i < categories.length; i++){
            cur_amount_question = 0;
             query = "SELECT * FROM " + Constant.TABLE_NAME + " WHERE " +
                    Constant.COLUMN_DISCIPLINE + " = '" + categories[i] + "'";
            cursor = database.rawQuery(query, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {

                        QuestModel data = new QuestModel(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                                cursor.getString(3), cursor.getString(4), cursor.getString(5),
                                cursor.getString(6), cursor.getInt(7));

                        dataList.add(data);
                        cur_amount_question++;
                        if (cur_amount_question == amount_of_questions_one_cat && i != categories.length - 1) {
                            break;
                        } else if (i == categories.length - 1 &&
                                cur_amount_question == all_question - (categories.length - 1) * amount_of_questions_one_cat) {
                            break;
                        }
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        }
        return dataList;
    }

    // Метод для удаление строки из бд
    public void deletOneRow(String row_id){
        long result = database.delete(Constant.TABLE_NAME, "id=?", new String[]{row_id});
    }

    public void updateID(String deletedID){
        SQLiteStatement statement = database.compileStatement("UPDATE " + Constant.TABLE_NAME + " SET id = id - 1 WHERE id > ?");
        statement.bindLong(1, Integer.parseInt(deletedID));
        statement.execute();
    }


}