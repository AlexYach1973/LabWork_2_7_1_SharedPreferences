package com.example.android.labwork_2_7_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Имя файла
    private static final String MY_PREF = "myPref";
    // Ключ для строки
    private static final String TEXT_SAVE_LOAD = "textValue";
    SharedPreferences sPref;

    EditText textSaveLoad;
    Button btnSave, btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSaveLoad = findViewById(R.id.text_save_load);
        btnSave = findViewById(R.id.button_save);
        btnLoad = findViewById(R.id.button_load);
        // Инициализируем
        sPref = getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);

        // SAVE
        btnSave.setOnClickListener(v -> {
            String textSave = textSaveLoad.getText().toString();
            if (textSave.isEmpty()) {
                Toast.makeText(MainActivity.this, "Ввдедите текст",
                        Toast.LENGTH_SHORT).show();
            } else {
                savePreferences(textSave);
                textSaveLoad.setText("");// очищаем TextView
            }
        });

        // LOAD
        btnLoad.setOnClickListener(v -> textSaveLoad.setText(loadPreferences()));

    }

    // SAVE
    protected void savePreferences(String str) {
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(TEXT_SAVE_LOAD, str);
        editor.apply();
        Toast.makeText(MainActivity.this, "Запись прошла успешно",
                Toast.LENGTH_SHORT).show();
    }

    // LOAD
    public String loadPreferences() {
        return sPref.getString(TEXT_SAVE_LOAD, "");
    }


}