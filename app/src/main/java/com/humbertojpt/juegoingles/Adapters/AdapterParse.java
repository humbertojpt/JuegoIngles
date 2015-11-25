package com.humbertojpt.juegoingles.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.humbertojpt.juegoingles.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Client on 11/14/2015.
 */
public class AdapterParse extends RecyclerView.Adapter<AdapterParse.MyViewHolder> {

    private LayoutInflater inflater;
    private RecyclerClickListner mRecyclerClickListner;
    private List<Information> data = Collections.emptyList();


    public AdapterParse(Context context, List<Information> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information information = data.get(position);
        holder.tv.setText(information.title);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setRecycleClickListner(RecyclerClickListner recyclerClickListner){
        mRecyclerClickListner = recyclerClickListner;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.listText);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerClickListner != null){
                mRecyclerClickListner.itemClick(v, getPosition());
            }

        }
    }

    public interface RecyclerClickListner{
        public void itemClick(View view, int position);
    }
}
