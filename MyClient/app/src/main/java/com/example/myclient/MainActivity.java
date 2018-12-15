package com.example.myclient;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientThread clientThread = new ClientThread();
                clientThread.start();
            }
        });
    }

    class ClientThread extends Thread{
        public void run(){
            String host = "localhost";
            int port = 5001;

            try{
                Socket socket = new Socket(host,port);

                ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                outStream.writeObject("안녕!");
                outStream.flush();
                Log.d("ClientThread","서버로 보냄");

                ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                final Object input = inStream.readObject();
                Log.d("ClientThread", "받은메세지" + input);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("받은 데이터 : " + input);
                    }
                });

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
