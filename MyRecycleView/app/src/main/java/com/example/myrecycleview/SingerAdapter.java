package com.example.myrecycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;


public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHolder> {
    Context context;

    ArrayList<SingerItem> items = new ArrayList<SingerItem>();

    OnItemClickListener listener;

    public static interface OnItemClickListener{
        public void onItemClick(ViewHolder holder, View view, int position);
    }

    public SingerAdapter(Context context){
        this.context = context;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = (View) inflater.inflate(R.layout.singer_item, viewGroup,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        SingerItem item = items.get(position);
        viewHolder.setItem(item);

        viewHolder.setOnItemClickListener(listener);
    }

    public void addItem(SingerItem item){
        items.add(item);
    }

    public void addItems(ArrayList<SingerItem> items){
        this.items = items;
    }

    public SingerItem getSingerItem(int position){
        return items.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2;

        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener !=null){
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });

        }

        public void setItem(SingerItem item){
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }

        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }

    }
}
