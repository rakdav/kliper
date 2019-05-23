package com.example.dp.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.dp.R;

public class InfoFragment extends Fragment {
private Button bt;

    public InfoFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.info_glav, container, false);
        bt=v.findViewById(R.id.button);
        return v;

    }

}
