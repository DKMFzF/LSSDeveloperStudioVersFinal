package com.example.pidorasgames.database;

public class Constant {
    // FireBase
    public final static String USER_NAME = "user_name";
    public final static String USER_SUB_NAME = "user_sub_name";
    public final static String USER_GMAIL = "user_gmail";
    public final static int USER_ID_NUMB = 0;

    // BD


    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "mydatabase.db";
    public static final String TABLE_NAME = "ques_table";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DISCIPLINE = "discipline";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_RIGHT_ANSWER = "answer";
    public static final String COLUMN_ANSWER_VAR1 = "var1";
    public static final String COLUMN_ANSWER_VAR2 = "var2";
    public static final String COLUMN_ANSWER_VAR3 = "var3";
    public static final String COLUMN_USED = "used";
    public static final String STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_DISCIPLINE + " TEXT," + COLUMN_QUESTION + " TEXT," +
            COLUMN_RIGHT_ANSWER + " TEXT," + COLUMN_ANSWER_VAR1 + " TEXT," +
            COLUMN_ANSWER_VAR2 + " TEXT," + COLUMN_ANSWER_VAR3 + " TEXT," +
            COLUMN_USED + " INTEGER)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}