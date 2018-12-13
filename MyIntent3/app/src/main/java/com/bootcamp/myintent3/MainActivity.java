package com.bootcamp.myintent3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);

                ArrayList<String> names = new ArrayList<String>();

                names.add("백승주");
                names.add("백지현");

                intent.putExtra("names",names);

                SimpleData data = new SimpleData(100, "hello");
                intent.putExtra("data", data);

                startActivityForResult(intent,100);
            }
        });
    }
}
