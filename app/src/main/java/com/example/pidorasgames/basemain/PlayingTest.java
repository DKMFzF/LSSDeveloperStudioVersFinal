package com.example.pidorasgames.basemain;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pidorasgames.R;
import com.example.pidorasgames.database.QuestModel;
import com.example.pidorasgames.database.DatabaseManager;

import java.util.List;

public class PlayingTest extends AppCompatActivity {
    TextView questText;
    Button nextBut;
    public List<QuestModel> data;
    private DatabaseManager dataManager;
    public int number_question = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_test);
        // Инициализируем объекты на экране
        init();

        // Создаем объект для управления бд
        dataManager = new DatabaseManager(PlayingTest.this);

        // Открываем бд
        dataManager.open();

        dataManager.insertData("Сиськи", "Какой размер груди топ?", "3", "2", "1", "-1", 0);

        // Получаем все данные с бд
        data = dataManager.getAllData();
        Log.d("MyTag", "Длина бд: " + data.size());

    }
    private void init(){
        questText = findViewById(R.id.questText);
        nextBut = findViewById(R.id.nextBut);
    }
    @Override
    protected void onResume(){
        super.onResume();
        dataManager.open();
    }
    public void onClickNext(View view){
        // Удаление вопроса после нажатия на кнопку (перед переходом на след вопрос)
        //dataManager.deletOneRow(data.get(number_question).id);

        // Получение вопроса из QuestModel data и вывод вопроса
        questText.setText(data.get(number_question).quest);
        number_question++;


    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        // Закрываем бд
        dataManager.close();
    }
}
