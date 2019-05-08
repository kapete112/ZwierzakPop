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

import java.util.ArrayList;
import java.util.Calendar;
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
    private Button btnKalendarz;
    private TextView textKalendarz;
    private int year;
    private int month;
    private int dayOfMonth;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiz_rodz);

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

        mOrder.setOnClickListener(new View.OnClickListener() {
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

                                for(int i=0;i<szczepUserItem.size();i++)
                                {
                                    if(szczepUserItem.get(i)==5 && !subEditText.getText().toString().matches(""))
                                    {
                                        Toast.makeText(wizRodz.this, szczepItems[5], Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });

                        AlertDialog szczepOkno = szczep.create();
                        szczepOkno.show();
                        break;
                    case "Wszczepienie chipa":
                        // od razu do bazy
                        Toast.makeText(wizRodz.this, "Wszczepienie chipa", Toast.LENGTH_SHORT).show();
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
                                for(int i=0;i<badUserItem.size();i++)
                                {
                                    if(badUserItem.get(i)==7 && !subEditText1.getText().toString().matches(""))
                                    {
                                        Toast.makeText(wizRodz.this, badItems[5], Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                        /*szczep.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterfac, int i) {
                                dialogInterfac.dismiss();
                            }
                        });*/
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
                                for(int i=0;i<higienUserItem.size();i++)
                                {
                                    if(higienUserItem.get(i)==5 && !subEditText2.getText().toString().matches(""))
                                    {
                                        Toast.makeText(wizRodz.this, higienItems[5], Toast.LENGTH_SHORT).show();
                                    }
                                }
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
