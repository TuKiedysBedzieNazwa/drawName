package com.example.heartrate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

class lotteryMachine {
    static String[] names = {};

    static String getAll() {
        StringBuilder text = new StringBuilder();
        for (String name : names) {
            text.append(name);
            text.append("\n");
        }
        return text.toString();
    }

    static String push(String toPush) {

        if(toPush.equals("")) return "Hej ale napisz coś w środku!";
        if(toPush.length() > 25) return "Hej nie przesadzasz trochę ?";

        names = Arrays.copyOf(names, names.length + 1);
        names[names.length - 1] = toPush;
        return "";
    }

    static void clear() {
        names = new String[0];
    }

    static String random() {
        if(names.length == 0) return "hej tu co se wpiszesz, jest puste!";

        Random random = new Random();
        return names[random.nextInt(names.length)];
    }
}

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button delete = findViewById(R.id.delete);
        Button add = findViewById(R.id.add);
        Button getRandom = findViewById(R.id.getRandom);
        TextView wilcoms = findViewById(R.id.names);
        TextView output = findViewById(R.id.output);
        EditText input = findViewById(R.id.input);

        delete.setOnClickListener(v -> {
            lotteryMachine.clear();
            wilcoms.setText(lotteryMachine.getAll());
        });
        add.setOnClickListener(v -> {
            if(lotteryMachine.push(input.getText().toString()).length() == 0) {
                wilcoms.setText(lotteryMachine.getAll());
                input.setText("");
            }
            else output.setText(lotteryMachine.push(input.getText().toString()));
        });
        getRandom.setOnClickListener(v -> output.setText(lotteryMachine.random()));
    }
}