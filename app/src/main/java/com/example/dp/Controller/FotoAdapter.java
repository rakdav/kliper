package com.example.dp.Controller;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp.Model.Agent;
import com.example.dp.R;

import java.util.List;

public class FotoAdapter extends RecyclerView.Adapter<FotoAdapter.ViewHolder> implements Filterable {
    private List<Agent> fotos;
    private Context context;
    private Typeface tf;


    public List<Agent> getAgents()
    {
        return agents;
    }

    @NonNull
    @Override
    public FotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.foto_adapter, viewGroup, false);
        return new FotoAdapter().ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AgentsAdapter.ViewHolder viewHolder, int i) {
        final Agent agent = agents.get(i);
        viewHolder.name.setText(agent.getName());
        viewHolder.position.setText(agent.getPosition());
        viewHolder.group_name.setText(agent.getGroupName());
        viewHolder.mobile_phone.setText(agent.getMobilePhone());
        viewHolder.phone.setText(agent.getPhone());
        viewHolder.email.setText(agent.getEmail());
    }

    @Override
    public int getItemCount() {

        return agents.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            imageView.setTypeface(tf);
        }

    }
}
