package com.example.mysummery3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class CommentWriteActivity extends AppCompatActivity {
    RatingBar ratingBar;
    EditText contentsInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_write);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        contentsInput = (EditText) findViewById(R.id.contentsInput);

        Intent intent =  getIntent();
        processIntnet(intent);

        Button button = (Button) findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMain();
            }
        });
    }

    private void processIntnet(Intent intent){
        if(intent != null){
            float rating = intent.getFloatExtra("rating",0.0f);
            ratingBar.setRating(rating);
        }
    }

    public void returnToMain(){
        String contents = contentsInput.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("contents",contents);

        setResult(RESULT_OK, intent);
        finish();
    }
}
