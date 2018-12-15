package com.example.myrecycleview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);//세로방향으로 설정된 리사이클러뷰

        final SingerAdapter adapter = new SingerAdapter(getApplicationContext());

        adapter.addItem(new SingerItem("소녀시대","010-0000-0000"));
        adapter.addItem(new SingerItem("걸즈대이","010-0000-0000"));
        adapter.addItem(new SingerItem("나인뮤즈스","010-0000-0000"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SingerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SingerAdapter.ViewHolder holder, View view, int position) {
                SingerItem item = adapter.getSingerItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.naver.com"));
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"아이템 선택됨" + item.getName(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
