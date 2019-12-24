package com.example.wordmerger;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    List<Integer>  usedWords = new ArrayList<Integer>();
    //    String[] test1={"Пенопласт","Ласточки"};
    Button start;
    ArrayList<String> result = new ArrayList<>();
    TextView outputWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.newWordButton);
        outputWord = findViewById(R.id.outPut);
//            result.add("Пенопласт");
//            result.add("Ласточки");

        dictionaryPreparation();
        Log.i("Testing", "Start");



    }


    void dictionaryPreparation(){
//            try {
//                Log.i("Testing", "File reader prepare");
//                inputStream = this.getResources().openRawResource(R.raw.list);
//                fileReader = new FileReader(inputStream);
//                Log.i("Testing", "File reader worked");
//
//            } catch (FileNotFoundException e) {
//                Log.i("Testing", "File reader NOT worked");
//                e.printStackTrace();
//            }


        InputStream is = getResources().openRawResource(R.raw.list);

//            try (Scanner s = new Scanner(openFileInput("raw/list.txt"))) {
//                while (s.hasNext()) {
//                    result.add(s.nextLine());
//                }
//                Log.i("Testing", "File reader worked");
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                Log.i("Testing", "File reader NOT worked");
//            }


//            result = Files.readAllLines(Paths.get(file));


        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            Log.i("Testing", "BufferedReader ready");

            while (br.ready()) {
                result.add(br.readLine());
//                    Log.i("Testing", result.get(result.size()-1));

            }
            Log.i("Testing", "Array list Ready");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String analizer(ArrayList<String> words){
        String newWord = "";
        int randomWord;

        while(true) {
            do {
                randomWord = new Random().nextInt(words.size());
//                Log.i("Testing", "" + randomWord);

            }
            while (usedWords.contains(randomWord));

            usedWords.add(randomWord);

            String firstHalf = "";
            String firstWord = words.get(randomWord).toLowerCase();
            words.set(randomWord,"");

            while (firstWord.length()>3) {
                firstHalf += firstWord.substring(0, 1);
                firstWord = firstWord.substring(1, firstWord.length());
                Log.i("Testing", "fistword "+firstWord);


                for (int i = 0; i < words.size(); i++) {
                    if (words.get(i).toLowerCase().startsWith(firstWord)) {
                        newWord = firstHalf + words.get(i);
                        Log.i("Testing", newWord);
                        return newWord;
                    }


                }
            }
        }


    }


    public void onClick(View view) {
        outputWord.setText(analizer(result));
//        Log.i("Testing", "Start");

    }


}

