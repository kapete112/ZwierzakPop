package com.example.zwierzaki;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class wizRodz extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    Button mOrder;
    TextView mItemSelected;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    String[] szczepItems;
    boolean[] checkedSzczep;
    ArrayList<Integer> szczepUserItem = new ArrayList<>();
    String[] badItems;
    boolean[] checkedBad;
    ArrayList<Integer> badUserItem = new ArrayList<>();
    String[] higienItems;
    boolean[] checkedHigien;
    ArrayList<Integer> higienUserItem = new ArrayList<>();
    FirebaseUser currentUser;
    private String currentUI;
    private EditText nrMetr;
    private Button btnKalendarz;
    private TextView textKalendarz;
    private int year;
    private int month;
    private int dayOfMonth;
    private Calendar calendar;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiz_rodz);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        currentUI = currentUser.getUid();
        nrMetr = findViewById(R.id.editTextNrMetr);
        mOrder = (Button) findViewById(R.id.btnOrder);
        mItemSelected = (TextView) findViewById(R.id.tvItemSelected);
        btnKalendarz = findViewById(R.id.buttonKalendarz2);
        textKalendarz = findViewById(R.id.textViewKalendarz2);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        textKalendarz.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        btnKalendarz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(wizRodz.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                textKalendarz.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();


            }
        });


        listItems = getResources().getStringArray(R.array.shopping_item);
        checkedItems = new boolean[listItems.length];

           /* mOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder mBuilder = new AlertDialog.Builder(wizRodz.this);
                    mBuilder.setTitle(R.string.dialog_title);
                    mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                            if (isChecked) {
                                mUserItems.add(position);
                            } else {
                                mUserItems.remove((Integer.valueOf(position)));
                            }
                        }
                    });
                    mBuilder.setCancelable(false);
                    mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            String item = "";
                            for (int i = 0; i < mUserItems.size(); i++) {
                                item = item + listItems[mUserItems.get(i)];
                                if (i != mUserItems.size() - 1) {
                                    item = item + ", ";
                                }
                            }
                            mItemSelected.setText(item);

                            szczep(0);

                        }
                    });


                    mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog mDialog = mBuilder.create();
                    mDialog.show();
                }

            });*/
    }
//
    protected void btnlistaOgolna(View view) {
        if (!nrMetr.getText().toString().equals("")) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder mBuilder = new AlertDialog.Builder(wizRodz.this);
                    mBuilder.setTitle(R.string.dialog_title);
                    mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                            if (isChecked) {
                                mUserItems.add(position);
                            } else {
                                mUserItems.remove((Integer.valueOf(position)));
                            }
                        }
                    });
                    mBuilder.setCancelable(false);
                    mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            String item = "";
                            for (int i = 0; i < mUserItems.size(); i++) {
                                item = item + listItems[mUserItems.get(i)];
                                if (i != mUserItems.size() - 1) {
                                    item = item + ", ";
                                }
                            }
                            mItemSelected.setText(item);

                            szczep(0);

                        }
                    });


                    mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog mDialog = mBuilder.create();
                    mDialog.show();
                }

            });
        } else {
            Toast.makeText(wizRodz.this, "nie podano numeru metryki", Toast.LENGTH_SHORT).show();
        }
    }

    protected void szczep(int id) {
        if (!mUserItems.isEmpty()) {
            LayoutInflater inflater = LayoutInflater.from(wizRodz.this);

            for (int i = 0; i < mUserItems.size(); i++) {
                switch (listItems[mUserItems.get(i)]) {
                    case "Szczepienie":
                        szczepItems = getResources().getStringArray(R.array.szczep_menu);
                        checkedSzczep = new boolean[szczepItems.length];
                        View subViewszczep = inflater.inflate(R.layout.dialog_layout, null);
                        final EditText subEditText = subViewszczep.findViewById(R.id.dialogEditText);
                        AlertDialog.Builder szczep = new AlertDialog.Builder(wizRodz.this);
                        szczep.setTitle("wybierz szczepionki");
                        szczep.setMultiChoiceItems(szczepItems, checkedSzczep, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                                if (isChecked) {
                                    szczepUserItem.add(position);
                                } else {
                                    szczepUserItem.remove((Integer.valueOf(position)));
                                }
                            }
                        });
                        szczep.setView(subViewszczep);
                        szczep.setCancelable(false);
                        szczep.setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Szczepienie szczepienie = new Szczepienie();
                                szczepienie.setNumer_metrykisz(nrMetr.getText().toString());
                                Date data = new Date();
                                szczepienie.setDate(data);
                                szczepienie.setUid(currentUI);
                                for (int i = 0; i < szczepUserItem.size(); i++) {
                                    if (checkedSzczep[szczepUserItem.get(i)]) {
                                        switch (szczepItems[szczepUserItem.get(i)]) {
                                            case "Wścieklizna":
                                                szczepienie.setWscieklizna(true);
                                                break;
                                            case "Parwowiroza":
                                                szczepienie.setParwowiroza(true);
                                                break;
                                            case "Nosówka":
                                                szczepienie.setNosowka(true);
                                                break;
                                            case "Leptospiroza":
                                                szczepienie.setLeptospiroza(true);
                                                break;
                                            case "Choroba Rubartha":
                                                szczepienie.setRubarth(true);
                                                break;
                                        }
                                        //Toast.makeText(wizRodz.this, "Zaznaczono" + szczepItems[i], Toast.LENGTH_SHORT).show();

                                    }
                                    if (szczepUserItem.get(i) == 5 && !subEditText.getText().toString().matches("")) {
                                        // Toast.makeText(wizRodz.this, szczepItems[5], Toast.LENGTH_SHORT).show();
                                        szczepienie.setInne(subEditText.getText().toString());
                                    }


                                }
                                dodaj(szczepienie);


                            }

                            private void dodaj(Szczepienie szczepienie) {
                                db.collection("Szczepienia").add(szczepienie).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(wizRodz.this, "Dodano pomyślnie!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });

                        AlertDialog szczepOkno = szczep.create();
                        szczepOkno.show();
                        break;
                    case "Wszczepienie chipa":
                        // od razu do bazy
                        Chip chip=new Chip();
                        chip.setNrMetrch(nrMetr.getText().toString());
                        Date data = new Date();
                        chip.setDatech(data);
                        chip.setUidch(currentUI);
                        db.collection("Chip").add(chip).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(wizRodz.this, "Dodano pomyślnie!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        // Toast.makeText(wizRodz.this, "Wszczepienie chipa", Toast.LENGTH_SHORT).show();
                        break;
                    case "Badania":
                        badItems = getResources().getStringArray(R.array.badania);
                        checkedBad = new boolean[badItems.length];

                        inflater = LayoutInflater.from(wizRodz.this);
                        View subViewBad = inflater.inflate(R.layout.dialog_layout, null);
                        final EditText subEditText1 = subViewBad.findViewById(R.id.dialogEditText);


                        AlertDialog.Builder badanie = new AlertDialog.Builder(wizRodz.this);
                        badanie.setTitle("wybierz badania");
                        badanie.setMultiChoiceItems(badItems, checkedBad, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                                if (isChecked) {
                                    badUserItem.add(position);
                                } else {
                                    badUserItem.remove((Integer.valueOf(position)));
                                }
                            }
                        });
                        badanie.setView(subViewBad);
                        badanie.setCancelable(false);
                        badanie.setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Badanie badaniee = new Badanie();
                                badaniee.setNumer_metryki(nrMetr.getText().toString());
                                Date data = new Date();
                                badaniee.setDatee(data);
                                badaniee.setUidd(currentUI);
                                for (int i = 0; i < badUserItem.size(); i++) {
                                    if (checkedBad[badUserItem.get(i)]) {
                                        switch (badItems[badUserItem.get(i)]) {
                                            case "Morfologia":
                                                badaniee.setMorfologia(true);
                                                break;
                                            case "Rozmaz krwi":
                                                badaniee.setKrew(true);
                                                break;
                                            case "Badanie moczu":
                                                badaniee.setMocz(true);
                                                break;
                                            case "Badania biochemiczne":
                                                badaniee.setBiochem(true);
                                                break;
                                            case "RTG":
                                                badaniee.setRTG(true);
                                                break;
                                            case "EKG":
                                                badaniee.setEKG(true);
                                                break;
                                            case "USG":
                                                badaniee.setUSG(true);
                                                break;
                                        }
                                    }
                                    if (badUserItem.get(i) == 7 && !subEditText1.getText().toString().matches("")) {
                                        badaniee.setInne(subEditText1.getText().toString());
                                    }
                                }
                                dodaj(badaniee);
                            }
                            private void dodaj(Badanie badnie) {
                                db.collection("Badania").add(badnie).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(wizRodz.this, "Dodano pomyślnie!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                        AlertDialog badOkno = badanie.create();
                        badOkno.show();
                        break;
                    case "Zabieg":
                        Toast.makeText(wizRodz.this, "Zabieg", Toast.LENGTH_SHORT).show();
                        break;
                    case "Zabieg higieniczny":
                        higienItems = getResources().getStringArray(R.array.zabHignien);
                        checkedHigien = new boolean[higienItems.length];
                        inflater = LayoutInflater.from(wizRodz.this);
                        View subViewZabHig = inflater.inflate(R.layout.dialog_layout, null);
                        final EditText subEditText2 = subViewZabHig.findViewById(R.id.dialogEditText);
                        AlertDialog.Builder higien = new AlertDialog.Builder(wizRodz.this);
                        higien.setTitle("wybierz zabiegi");
                        higien.setMultiChoiceItems(higienItems, checkedHigien, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                                if (isChecked) {
                                    higienUserItem.add(position);
                                } else {
                                    higienUserItem.remove((Integer.valueOf(position)));
                                }
                            }
                        });
                        higien.setView(subViewZabHig);
                        higien.setCancelable(false);
                        higien.setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ZabHigien zabHigien = new ZabHigien();
                                zabHigien.setNumerMetryki(nrMetr.getText().toString());
                                Date data = new Date();
                                zabHigien.setDatezh(data);
                                zabHigien.setUidzh(currentUI);
                                for (int i = 0; i < higienUserItem.size(); i++) {
                                    if (checkedHigien[higienUserItem.get(i)]) {
                                        switch (higienItems[higienUserItem.get(i)]) {
                                            case "Czyszczenie zębów":
                                                zabHigien.setC_zebow(true);
                                                break;
                                            case "Wyciąganie kleszczy":
                                                zabHigien.setKleszcz(true);
                                                break;
                                            case "Czyszczenie uszu":
                                                zabHigien.setC_uszu(true);
                                                break;
                                            case "Strzyżenie sierści":
                                                zabHigien.setStrzyzenie(true);
                                                break;
                                            case "Usuwanie kamienia nazębnego":
                                                zabHigien.setUsuw_kamienia(true);
                                                break;
                                        }
                                    }
                                    if (higienUserItem.get(i) == 5 && !subEditText2.getText().toString().matches("")) {
                                        zabHigien.setInne(subEditText2.getText().toString());
                                    }
                                }
                                dodaj(zabHigien);
                            }

                            private void dodaj(ZabHigien zabHigien) {
                                db.collection("ZabHigien").add(zabHigien).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(wizRodz.this, "Dodano pomyślnie!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                        /*szczep.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterfac, int i) {
                                dialogInterfac.dismiss();
                            }
                        });*/
                        AlertDialog higienOkno = higien.create();
                        higienOkno.show();
                        break;

                }
            }
        }
    }
}
