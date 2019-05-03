package com.example.zwierzaki;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class WyswietlZwierzaki extends AppCompatActivity {
    private static final String TAG = "AddAnimal";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    String currentUI = currentUser.getUid();
    TableLayout tl;
    TableRow tr;
    TextView tv;
    private CollectionReference listaZwierzat = db.collection("Zwierzeta");
    final String[] wartosciUsera = {"Imie", "Data urodzenia", "Płeć", "Numer metryki"};
    List<Zwierze> list = new ArrayList<Zwierze>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyswietl_zwierzaki);
        tl = findViewById(R.id.listOfAnimal);
        tr = new TableRow(getApplicationContext());
        ustawNaglowki();
        listaZwierzat.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    String[] daneLista = {"imieZwierzecia", "datUr", "plec", "nrMetryki"};
                    for (QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots) {
                        tr = new TableRow(getApplicationContext());
                        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                        for (String nazwa : daneLista) {
                            tv = new TextView(getApplicationContext());
                            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                            tv.getLayoutParams().width = 200;
                            //Toast.makeText(WyswietlZwierzaki.this, documentSnapshots.get(nazwa).toString(), Toast.LENGTH_SHORT).show();
                            tv.setText(documentSnapshots.get(nazwa).toString());
                            tr.addView(tv);
                        }
                        tl.addView(tr);
                    }
                }
            }
        });


        /*if (!list.isEmpty()) {
            for (Zwierze zwierzak : list) {
                tr = new TableRow(this);
                String[] daneLista = {zwierzak.getImieZwierzecia(), zwierzak.getDatUr().toString(), zwierzak.getPlec(), zwierzak.getNrMetryki()};
                for (String nazwaKol : daneLista) {
                    tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                    TextView tv = new TextView(this);
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                    tv.getLayoutParams().width = 250;
                    tv.setText(nazwaKol);
                    tr.addView(tv);
                }
                tl.addView(tr);
            }
            // Toast.makeText(WyswietlZwierzaki.this, "Wypisalem zwierzeta!", Toast.LENGTH_SHORT).show();
        }*/


    }

    /*private void pobierzDane() {
        listaZwierzat.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots) {
                        // tr = new TableRow(this);
                    }
                }
            }
        });

    }*/



    private void ustawNaglowki() {
        tr = new TableRow(this);
        for (String nazwaKol : wartosciUsera) {
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tv.getLayoutParams().width = 200;
            tv.setText(nazwaKol);
            tv.setGravity(Gravity.CENTER);
            tr.addView(tv);
        }
        tl.addView(tr);
    }


}