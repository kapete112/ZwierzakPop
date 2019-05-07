package com.example.zwierzaki;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class wizRodz extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiz_rodz);

        mOrder = (Button) findViewById(R.id.btnOrder);
        mItemSelected = (TextView) findViewById(R.id.tvItemSelected);

        listItems = getResources().getStringArray(R.array.shopping_item);
        checkedItems = new boolean[listItems.length];

        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(wizRodz.this);
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
            for (int i = 0; i < mUserItems.size(); i++) {
                switch (listItems[mUserItems.get(i)]) {
                    case "Szczepienie":
                        Toast.makeText(wizRodz.this, "Szczepienie", Toast.LENGTH_SHORT).show();
                        szczepItems = getResources().getStringArray(R.array.szczep_menu);
                        checkedSzczep = new boolean[szczepItems.length];
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
                        szczep.setCancelable(false);
                        szczep.setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        /*szczep.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterfac, int i) {
                                dialogInterfac.dismiss();
                            }
                        });*/
                        AlertDialog szczepOkno = szczep.create();
                        szczepOkno.show();
                        break;
                    case "Wszczepienie chipa":
                        // od razu do bazy
                        Toast.makeText(wizRodz.this, "Wszczepienie chipa", Toast.LENGTH_SHORT).show();
                        break;
                    case "Badania":
                        Toast.makeText(wizRodz.this, "Badania", Toast.LENGTH_SHORT).show();
                        badItems = getResources().getStringArray(R.array.badania);
                        checkedBad = new boolean[badItems.length];
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
                        badanie.setCancelable(false);
                        badanie.setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

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

                        Toast.makeText(wizRodz.this, "Zabieg higieniczny", Toast.LENGTH_SHORT).show();
                        higienItems = getResources().getStringArray(R.array.zabHignien);
                        checkedHigien = new boolean[higienItems.length];
                        AlertDialog.Builder higien = new AlertDialog.Builder(wizRodz.this);
                        higien.setTitle("wybierz badania");
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
                        higien.setCancelable(false);
                        higien.setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

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
