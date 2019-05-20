package com.example.zwierzaki;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class ZdjeciaActivity extends AppCompatActivity {

    Button tDodajZdjecie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zdjecia);
        String idZwierzaka;
        if(getIntent().hasExtra("selected_zwierze")){
            idZwierzaka = getIntent().getStringExtra("selected_zwierze"); //idZwierzaka = getIntent().getStringExtra()
            Toast.makeText(ZdjeciaActivity.this, idZwierzaka + "...", Toast.LENGTH_SHORT).show();
           // tDodajZdjecie = (Button)findViewById(R.id.dodajZdjecie);
           // tDodajZdjecie.setText(getIntent().getParcelableExtra("selected_zwierze").toString());
        }
    }
}
