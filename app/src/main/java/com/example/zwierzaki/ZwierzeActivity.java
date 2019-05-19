package com.example.zwierzaki;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

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

            /*tNrMetrykiMatki = (TextView)findViewById(R.id.textNrMetrykiMatki);
            tNrMetrykiMatki.setText(zwierze.getNrMetrykiMatki());

            tNrMetrykiOjca = (TextView)findViewById(R.id.textNrMetrykiOjca);
            tNrMetrykiOjca.setText(zwierze.getNrMetrykiOjca());*/

            tImieZwierzecia = (TextView)findViewById(R.id.textImie);
            tImieZwierzecia.setText(zwierze.getImieZwierzecia());

           /* tPlec = (TextView)findViewById(R.id.textPlec);
            tPlec.setText(zwierze.getPlec());

            tDatUr = (TextView)findViewById(R.id.textData);
            tDatUr.setText(zwierze.getDatUr());*/

             FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Zwierzeta").whereEqualTo("nrMetryki", zwierze.getNrMetryki()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (DocumentSnapshot document : queryDocumentSnapshots) {
                        //String wiersz = document.getString("imieZwierzecia") + "   " + document.getString("nrMetryki");
                       // subjects.add(wiersz);

                        tNrMetrykiMatki = (TextView)findViewById(R.id.textNrMetrykiMatki);
                        tNrMetrykiMatki.setText(document.getString("nrMetrykiMatki"));

                        tNrMetrykiOjca = (TextView)findViewById(R.id.textNrMetrykiOjca);
                        tNrMetrykiOjca.setText(document.getString("nrMetrykiOjca"));

                        tPlec = (TextView)findViewById(R.id.textPlec);
                        tPlec.setText(document.getString("plec"));

                        tDatUr = (TextView)findViewById(R.id.textData);
                        tDatUr.setText(document.getString("datUr"));

                        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                        StorageReference islandRef = storageRef.child(document.getString("uid")+ "/" + "Zdjecie" + document.getString("nrMetryki"));

                        final long ONE_MEGABYTE = 1024 * 1024;
                        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                               // saveBitmap(couponName, bitmap);

                                tZdjecie = (ImageView) findViewById(R.id.imageViewZwierze);
                                tZdjecie.setImageBitmap(bitmap);
                                // Data for "images/island.jpg" is returns, use this as needed
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle any errors
                            }
                        });

                      /*  private StorageReference mStorageRef;
                        mStorageRef = FirebaseStorage.getInstance().getReference();

                        Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
                        StorageReference riversRef = storageRef.child("images/rivers.jpg");

                        File localFile = File.createTempFile("images", "jpg");
                        riversRef.getFile(localFile)
                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        // Successfully downloaded data to local file
                                        // ...
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle failed download
                                // ...
                            }
                        });*/
                    }
                   // adapter.notifyDataSetChanged();
                }
            });
        }
    }
}
