package com.bootcamp.mygridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SingerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridview);


        adapter = new SingerAdapter();

        adapter.addItem(new SingerItem("소녀시대", "010-1234-1234", R.drawable.icon1));
        adapter.addItem(new SingerItem("걸스데이", "010-1234-1212", R.drawable.icon2));
        adapter.addItem(new SingerItem("소녀시대", "010-1234-1234", R.drawable.icon3));
        adapter.addItem(new SingerItem("걸스데이", "010-1234-1212", R.drawable.icon4));
        adapter.addItem(new SingerItem("소녀시대", "010-1234-1234", R.drawable.icon5));
        adapter.addItem(new SingerItem("걸스데이", "010-1234-1212", R.drawable.icon6));

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"선택 : "+ item.getName(),Toast.LENGTH_LONG).show();
            }
        });


    }

    class SingerAdapter extends BaseAdapter {
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item){
            items.add(item);
        }
        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingerItemView view = new SingerItemView(getApplicationContext());

            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImage(item.getResId());
            return view;
        }
    }
}
