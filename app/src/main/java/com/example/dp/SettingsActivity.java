package com.example.dp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class SettingsActivity extends AppCompatActivity {
private Button mapa;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
//        getFragmentManager()
//                .beginTransaction()
//                .add(R.id.prefs_content, new SettingsFragment())
//                .commit();


        mapa= (Button) findViewById(R.id.mapa);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),MapsActivity.class);

                intent.putExtra("Lat", "54.710965");
                intent.putExtra("Lng", "20.507849");
                intent.putExtra("title","Kliper");
                startActivity(intent);
            }
        });

    }





//    public static class SettingsFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.settings);
//            getPreferenceManager().setStorageDefault();
//
//        }
//    }
}
