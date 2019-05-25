package com.example.dp.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dp.Model.Agent;
import com.example.dp.Model.Picture;
import com.example.dp.R;
import com.example.dp.View.HouseFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    private List<Picture> list;
    private Context context;

    public PictureAdapter(List<Picture> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.picture_item, viewGroup, false);
        v.setOnClickListener(HouseFragment.onSet);
        return new PictureAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Picture picture = list.get(i);
        Picasso.get().load(picture.getUrl()).into(viewHolder.imageView);;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private CardView cv;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cv=itemView.findViewById(R.id.piccv);
            imageView=itemView.findViewById(R.id.picView);
        }
    }
}
