package com.bootcamp.myevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    GestureDetector detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        View view = findViewById(R.id.view1);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               int action = event.getAction();
               float curX = event.getX();
               float curY = event.getY();

               if (action == MotionEvent.ACTION_DOWN){
                   prindln("손가락 눌렸음" + curX + ", " + curY );
               }else if (action == MotionEvent.ACTION_MOVE){
                   prindln("손가락 움직임" + curX + ", " + curY );
               }else if (action == MotionEvent.ACTION_UP){
                   prindln("손가락 떼졌음" + curX + ", " + curY );
               }

               return true;
            }
        });

        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                prindln("onDown 호출됨");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                prindln("onShowPress 호출됨");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                prindln("onSingleTapUp 호출됨");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                prindln("onScroll 호출됨");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                prindln("onLongPress 호출됨");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                prindln("onFling 호출됨");
                return true;
            }
        });

        View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this,"system back 버튼 눌림", Toast.LENGTH_LONG).show();

            return true;
        }
        return false;
    }

    public void prindln(String data){
        textView.append(data + '\n');
    }
}
