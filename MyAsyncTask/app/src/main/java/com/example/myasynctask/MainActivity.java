package com.example.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar myProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myProgress = (ProgressBar) findViewById(R.id.progressBar);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressTask task = new ProgressTask();
                task.execute("시작");
            }
        });
    }

    class ProgressTask extends AsyncTask<String, Integer, Integer>{
        int value = 0;

        @Override
        protected Integer doInBackground(String... strings) {
            while(true){
                if(value > 100){
                    break;
                }
                value += 1;

                publishProgress(value);

                try{
                    Thread.sleep(200);
                }catch (Exception e){}
            }
            return value;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            Toast.makeText(getApplicationContext(), "완료됨.", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            myProgress.setProgress(values[0].intValue());
        }
    }
}
