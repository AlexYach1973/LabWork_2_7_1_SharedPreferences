package com.example.android.labwork_2_7_1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    // Имя файла
    private static final String MY_PREF = "myPref";
    // Ключ для строки
    private static final String TEXT_SAVE_LOAD = "textValue";
    SharedPreferences sPref;

    private EditText textSaveLoad;
    private Button btnSavePref, btnLoadPref;

    private Button btnSaveInternalStorage, btnLoadInternalStorage;
    private static final String FILE_NAME = "fileName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Internal Storage
         */
        btnSaveInternalStorage = findViewById(R.id.button_save_internal);
        btnLoadInternalStorage = findViewById(R.id.button_load_internal);

        btnSaveInternalStorage.setOnClickListener(v -> {
            String textSave = textSaveLoad.getText().toString();
            if (textSave.isEmpty()) {
                Toast.makeText(MainActivity.this, "Ввдедите текст",
                        Toast.LENGTH_SHORT).show();
            } else {
                try {
                    saveInternalStorage(textSave);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textSaveLoad.setText("");// очищаем TextView
            }
        });

        btnLoadInternalStorage.setOnClickListener(v -> {
            try {
                textSaveLoad.setText(loadInternalStorage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        /**
         *  End Internal Storage
         */


        /**
         * SharedPreferences
         */
        textSaveLoad = findViewById(R.id.text_save_load);
        btnSavePref = findViewById(R.id.button_save);
        btnLoadPref = findViewById(R.id.button_load);
        // Инициализируем
        sPref = getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);

        // SAVE
        btnSavePref.setOnClickListener(v -> {
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
        btnLoadPref.setOnClickListener(v -> textSaveLoad.setText(loadPreferences()));
        /**
         * END SharedPreferences
         */

    }

    /**
     * SharedPreferences
     */
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
    /**
    * END SharedPreferences
    */


    /**
     * Internal Storage
     */
    private void saveInternalStorage(String str) throws IOException {
        // Открываем поток
        FileOutputStream out = openFileOutput(FILE_NAME, MODE_PRIVATE);
        out.write(str.getBytes());
        out.close();
        Toast.makeText(MainActivity.this, "Информация сохранена в файл: " +
                        FILE_NAME,
                Toast.LENGTH_SHORT).show();
    }

    public String loadInternalStorage() throws IOException {
        // Открываем поток
        FileInputStream in =  openFileInput(FILE_NAME);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder strL = new StringBuilder();
        String str;

        while ((str = br.readLine()) != null) {
            strL.append(str);
        }
        return strL.toString();
    }
    /**
     *  End Internal Storage
     */

}