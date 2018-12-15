package com.example.mymovieapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mymovieapi.data.MovieList;
import com.example.mymovieapi.data.ResponseInfo;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMovieList();
            }
        });

        if(AppHelper.requestQueue == null ){
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void println(String data){
        textView.append(data + "\n");
    }
    public void requestMovieList(){
        String url = "http://" +AppHelper.host + ":" + AppHelper.port + "/movie/readMovieList";
        url += "?" + "type=1";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("responsed from API" + response);

                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("error occur : " + error.getMessage());
                    }
                }
        );
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
        println("MovieList request sended.");
    }

    public void processResponse(String res){
        Gson gson = new Gson();
        ResponseInfo info = gson.fromJson(res, ResponseInfo.class);
        if(info.code == 200){
            MovieList movieList = gson.fromJson(res,MovieList.class);
            println("Movie numbers : " +movieList.result.size());
        }

    }
}
