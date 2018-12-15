package com.example.mysummery3;

import android.content.Intent;
import android.media.Rating;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    RatingBar ratingBar;
    TextView commentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        Button button = (Button) findViewById(R.id.button);

        commentView = (TextView) findViewById(R.id.commentView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentWriteActivity();
            }
        });
    }

    public void showCommentWriteActivity(){
        float rating = ratingBar.getRating();
        Intent intent = new Intent(getApplicationContext(),CommentWriteActivity.class);
        intent.putExtra("rating",rating);
        startActivityForResult(intent, 101);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101){
            if(data !=null){
                String contents = data.getStringExtra("contents");
                commentView.setText(contents);
            }
        }

    }
}
