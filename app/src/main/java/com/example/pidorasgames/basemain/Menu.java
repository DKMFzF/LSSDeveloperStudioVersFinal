package com.example.pidorasgames.basemain;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pidorasgames.R;
import com.example.pidorasgames.database.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс главное Меню, где должен быть дизайн, выбоор тем
 */
public class Menu extends AppCompatActivity {
    Button play_button;
    private FirebaseAuth mAuth;
    private String userId;
    private DatabaseReference currentUserRef;
    ArrayList<String> categories;
    int rating = 0;
    int curRate;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        init();

        // Изменение рейтинга пользователя, если он сыграл
        Intent intent = getIntent();
        if (intent != null){
            // Берем значение рейтинга, перейдя на это активити, если рейтинга нет, то изначальное значение 0
            rating = intent.getIntExtra("rating", 0);
            // Проверка, сыграли ли игрок
            if (rating != 0){

                Log.d("rate", "rateing " + rating);

                // получение данного пользователя
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser cUser = mAuth.getCurrentUser();
                // получение id пользователя
                userId = cUser.getUid();

                Log.d("idUser", "id = " + cUser.getTenantId());
                // получение узла пользователя в таблиице базы данных
                currentUserRef = FirebaseDatabase.getInstance().getReference("User/"+userId);

                // Обращение к данным пользователя из таблицы
                currentUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Обработка данных при успешном получении
                        User value = dataSnapshot.getValue(User.class);
                        if (value != null) {
                            // Получение имеющегося рейтинга пользователя
                            curRate = value.getReting();

                            // Изменение рейтинга пользователя
                            Map<String, Object> updates = new HashMap<>();
                            Log.d("CurRate", "rate = " + curRate);
                            updates.put("reting", curRate + rating);
                            currentUserRef.updateChildren(updates, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    if (error != null) {
                                        Log.d("TAG", "Данные не были успешно обновлены.");
                                    } else {
                                        Log.d("TAG", "Данные успешно обновлены.");
                                    }
                                }
                            });
                            Log.d("value", "val = " + curRate);

                        }
                        System.out.println("Полученные данные: " + curRate);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Обработка ошибки
                        System.err.println("Ошибка при получении данных из базы: " + databaseError.getMessage());
                    }
                });


            }
        }


    }

    @Override
    protected void onStart(){
        super.onStart();

    }

    void init(){
        play_button = findViewById(R.id.play_button);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }

    // Переход в игру, передавая список тем
    public void startPlayOnClick(View view){
        addCategories();

        Intent intent = new Intent(this, PlayingTest.class);
        intent.putStringArrayListExtra("categories", categories);
        startActivity(intent);
        finish();
    }


    // Метод для выбора категорий (должен быть связан с выбором тем пользователем!!!)
    public void addCategories(){
        categories = new ArrayList<>();
        categories.add("dsa");
        categories.add("Матан");
    }

}
