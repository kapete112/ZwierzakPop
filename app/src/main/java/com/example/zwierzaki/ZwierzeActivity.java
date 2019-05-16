package com.example.zwierzaki;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ZwierzeActivity extends AppCompatActivity {

    TextView tNrMetryki;
    TextView tNrMetrykiMatki;
    TextView tNrMetrykiOjca;
    TextView tImieZwierzecia;
    TextView tPlec;
    TextView tDatUr;
    ImageView tZdjecie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zwierze);

        if(getIntent().hasExtra("selected_zwierze")){
            Zwierze zwierze = getIntent().getParcelableExtra("selected_zwierze");
            tNrMetryki = (TextView)findViewById(R.id.textNrMetryki);
            tNrMetryki.setText(zwierze.getNrMetryki());

            //Pobrać prosto z bazy danych kolejne dane na podstawie numeru metryki

            tNrMetrykiMatki = (TextView)findViewById(R.id.textNrMetrykiMatki);
            tNrMetrykiMatki.setText(zwierze.getNrMetrykiMatki());

            tNrMetrykiOjca = (TextView)findViewById(R.id.textNrMetrykiOjca);
            tNrMetrykiOjca.setText(zwierze.getNrMetrykiOjca());

            tImieZwierzecia = (TextView)findViewById(R.id.textImie);
            tImieZwierzecia.setText(zwierze.getImieZwierzecia());

            tPlec = (TextView)findViewById(R.id.textPlec);
            tPlec.setText(zwierze.getPlec());

            tDatUr = (TextView)findViewById(R.id.textData);
            tDatUr.setText(zwierze.getDatUr());
        }
    }
}
