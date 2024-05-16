package com.example.pidorasgames.basemain;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pidorasgames.R;
import com.example.pidorasgames.database.QuestModel;
import com.example.pidorasgames.database.DatabaseManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс, отвечающий за игровой процесс, вопросы-ответы
 */
public class PlayingTest extends AppCompatActivity {
    TextView questText;
    Button ans_but_1, ans_but_2, ans_but_3, ans_but_4;
    public List<QuestModel> data;
    public List<String> answers;
    public List<String> categories;
    private DatabaseManager dataManager;
    public int number_question = -1;
    public int right_ans;
    private int amount_of_question;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_test);
        // Инициализируем объекты на экране
        init();

        // получение списка выбранных категорий
        categories = getIntent().getStringArrayListExtra("categories");

        // Создаем объект для управления бд
        dataManager = new DatabaseManager(PlayingTest.this);

        // Открываем бд
        dataManager.open();

        // Количество вопросов за игру !!!
        amount_of_question = 3;

        // количество верных ответов
        right_ans = 0;

        // Подгрузка вопросов в бд !!!

//        dataManager.insertData("Русский", "деловой?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Матан", "интеграл?", "52", "14", "15", "3", 0);
//        dataManager.insertData("dsa", "да?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Русский", "русский?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Матан", "корень?", "52", "14", "15", "3", 0);
//        dataManager.insertData("dsa", "нет?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Русский", "язык?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Матан", "модуль?", "52", "14", "15", "3", 0);
//        dataManager.insertData("dsa", "понял?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Русский", "нет английский?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Матан", "квадрат?", "52", "14", "15", "3", 0);
//        dataManager.insertData("dsa", "пупупу?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Русский", "деловой?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Матан", "интеграл?", "52", "14", "15", "3", 0);
//        dataManager.insertData("dsa", "да?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Русский", "русский?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Матан", "корень?", "52", "14", "15", "3", 0);
//        dataManager.insertData("dsa", "нет?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Русский", "язык?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Матан", "модуль?", "52", "14", "15", "3", 0);
//        dataManager.insertData("dsa", "понял?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Русский", "нет английский?", "52", "14", "15", "3", 0);
//        dataManager.insertData("Матан", "квадрат?", "52", "14", "15", "3", 0);
//        dataManager.insertData("dsa", "пупупу?", "52", "14", "15", "3", 0);


        // Получаем все данные с бд
        data = dataManager.getData(categories);
        Log.d("MyTag", "Длина бд: " + data.size());

        // запускаем тест
        if (data.size() >= amount_of_question){
            setUpQuestion();
        }else{
            Toast.makeText(getApplicationContext(),
                    "Бд слишком мала",
                    Toast.LENGTH_LONG).show();
            // Возвращаемся в Меню, если бд мала
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
            finish();
        }


    }
    private void init(){
        questText = findViewById(R.id.questText);
        ans_but_1 = findViewById(R.id.ans_but_1);
        ans_but_2 = findViewById(R.id.ans_but_2);
        ans_but_3 = findViewById(R.id.ans_but_3);
        ans_but_4 = findViewById(R.id.ans_but_4);

    }
    @Override
    protected void onResume(){
        super.onResume();
        dataManager.open();
    }
    public void onClickAns(View view){

        // Проверка на правильность ответа, если есть возможность,
        // то сделать показатель правильного ответа (зеленый/красный цвет) при выборе ответа

        if (((Button) view).getText().toString().equals(data.get(number_question).ans)){
            right_ans++;
            Log.d("Right", "Right" );

        }
        // загрузка след вопросов
        setUpQuestion();


    }
    // метод установки вопросов
    public void setUpQuestion(){
        if (number_question+1 == amount_of_question){
            // если вопросы закончились, то возвращение в меню
            returnToMenu();
        } else {
            // распределение вопросов и ответов
            number_question++;
            answers = Arrays.asList(data.get(number_question).var1, data.get(number_question).var2,
                    data.get(number_question).var3, data.get(number_question).ans);
            shuffleArray(answers);
            questText.setText(data.get(number_question).quest);
            ans_but_1.setText(answers.get(0));
            ans_but_2.setText(answers.get(1));
            ans_but_3.setText(answers.get(2));
            ans_but_4.setText(answers.get(3));
        }

    }

    public void shuffleArray(List<String> array) {
        Random rnd = ThreadLocalRandom.current();

        for (int i = array.size() - 1; i >= 0; i--) {
            int j = rnd.nextInt(i + 1);

            String temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
        }

    }
    public void returnToMenu(){
        Intent intent = new Intent(this, Menu.class);
        int rating = (int) Math.floor(right_ans * Math.pow(1.5, (double) data.size()-1));
        intent.putExtra("rating", rating);
        startActivity(intent);
        finish();

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        // Закрываем бд
        dataManager.close();
    }

}
