package com.example.dp.Controller;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.example.dp.Model.Agent;
import com.example.dp.R;
import java.util.ArrayList;
import java.util.List;


public class AgentsAdapter extends RecyclerView.Adapter<AgentsAdapter.ViewHolder>
{
    private List<Agent> agents;
    private Context context;
    private Typeface tf;

    public AgentsAdapter(List<Agent> agents, Context context) {
        this.agents = agents;
        this.context = context;
    }
    public List<Agent> getAgents()
    {
        return agents;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.agent_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
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
        private TextView name;
        private TextView position;
        private TextView group_name;
        private TextView mobile_phone;
        private TextView phone;
        private TextView email;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            name.setTypeface(tf);
            position=(TextView)itemView.findViewById(R.id.position);
            position.setTypeface(tf);
            group_name=(TextView)itemView.findViewById(R.id.group_name);
            group_name.setTypeface(tf);
            mobile_phone=(TextView)itemView.findViewById(R.id.mobile_phone);
            mobile_phone.setTypeface(tf);
            phone=(TextView)itemView.findViewById(R.id.phone);
            phone.setTypeface(tf);
            email=(TextView)itemView.findViewById(R.id.email);
            email.setTypeface(tf);
        }

    }
}
