package com.example.dp.Controller;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.dp.API.APIUrl;
import com.example.dp.Model.Agent;
import com.example.dp.Model.Picture;
import com.example.dp.Model.PictureList;
import com.example.dp.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class AgentsAdapter extends RecyclerView.Adapter<AgentsAdapter.ViewHolder>
{
    private List<Agent> agents;
    private Context context;
    private Typeface tf;
    private Handler mHandler;

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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Agent agent = agents.get(i);
        mHandler = new Handler(Looper.getMainLooper());
        viewHolder.name.setText(agent.getName());
        viewHolder.position.setText(agent.getPosition());
        viewHolder.group_name.setText(agent.getGroupName());
        viewHolder.mobile_phone.setText(agent.getMobilePhone());
        viewHolder.phone.setText(agent.getPhone());
        String uri = APIUrl.BASE_URL + "picture/AgentPhoto?key=6d35e1f591aa413189aa34cd93dc26fb&agent_id="+agent.getId()+"&width=200&height=400&crop=0";
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(uri)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
            }

            @Override
            public void onResponse(okhttp3.Call call, final okhttp3.Response response) throws IOException {

                final String res = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject ob = new JSONObject(res);
                            Gson gson=new Gson();
                            PictureList pl=gson.fromJson(ob.toString(),PictureList.class);
                            Picasso.get().load(pl.getResults().get(0).getUrl()).into(viewHolder.imageView);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                });
            }
        });
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
        private ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.agent);
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
        }

    }
}
