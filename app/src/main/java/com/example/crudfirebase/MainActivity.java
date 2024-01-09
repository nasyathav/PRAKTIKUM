package com.example.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Mhs> mhsList ;
    Mhs mm ;
 // DbHelper db ;
    FireBaseHelper db ;
    boolean isEdit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edNama = (EditText) findViewById(R.id.edNama);
        EditText edNim = (EditText) findViewById(R.id.edNim);
        EditText edNoHp = (EditText) findViewById(R.id.edNoHp);
        Button btnSimpan = (Button) findViewById(R.id.btnSimpan);

        mhsList = new ArrayList<>();

        isEdit = false;

        Intent intent_main = getIntent();
        if(intent_main.hasExtra("mhsData")){
            mm = intent_main.getExtras().getParcelable("mhsData");
            edNama.setText(mm.getNama());
            edNim.setText(mm.getNim());
            edNoHp.setText(mm.getNoHp());

            isEdit = true;

            btnSimpan.setBackgroundColor(Color.MAGENTA);
            btnSimpan.setText("Edit");
        }

 //     db = new DbHelper(getApplicationContext());
        db = new FireBaseHelper();

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isian_nama = edNama.getText().toString();
                String isian_nim = edNim.getText().toString();
                String isian_nohp = edNoHp.getText().toString();


                if (isian_nama.isEmpty() || isian_nim.isEmpty() || isian_nohp.isEmpty()){
                    Toast.makeText(getApplicationContext(), "data masih kosong", Toast.LENGTH_SHORT).show();
                }else {
                   // boolean stts = false ;

                    if(!isEdit){
                        mm = new Mhs( isian_nama, isian_nim, isian_nohp);
                        db.simpan(mm).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(),"Data berhasil disimpan", Toast.LENGTH_LONG).show();
                                edNama.setText("");
                                edNim.setText("");
                                edNoHp.setText("");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Data gagal disimpan" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else{
                        mm = new Mhs(mm.getKey(), isian_nama, isian_nim, isian_nohp);
                        db.ubah(mm).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(),"Data berhasil diubah" , Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Data gagal diubah" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                        }
                    }

 //                   if(stts){

  //                       Toast.makeText(getApplicationContext(),"Data berhasil disimpan", Toast.LENGTH_LONG).show();
 //                   }else{
 //                       Toast.makeText(getApplicationContext(),"Data gagal disimpan", Toast.LENGTH_LONG).show();
  //                  }

                    //intent_list.putParcelableArrayListExtra("mhsList", mhsList);
                    //startActivity(intent_list);
                }
        });

        Button btnLihat = (Button) findViewById(R.id.btnLihat);
        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mhsList = db.list();

                if (mhsList.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Belum Ada Data", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent_list = new Intent( MainActivity.this, ListMhsActivity.class);
                    intent_list.putParcelableArrayListExtra("mhsList", mhsList);
                    startActivity(intent_list);
                }

            }
        });

    }
}