package com.example.belajarsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Mhs> mhsList;
    Mhs mm;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edNama = (EditText) findViewById(R.id.edNama);
        EditText edNim = (EditText) findViewById(R.id.edNim);
        EditText edNoHp = (EditText) findViewById(R.id.edNoHp);
        Button btnSimpan = (Button) findViewById(R.id.btnSimpan);

        mhsList = new ArrayList<>();

        Intent intent_list = new Intent( MainActivity.this, ListMhsActivity.class);

        db = new DbHelper(getApplicationContext());

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isian_nama = edNama.getText().toString();
                String isian_nim = edNim.getText().toString();
                String isian_noHp = edNoHp.getText().toString();


                if (isian_nama.isEmpty() || isian_nim.isEmpty() || isian_noHp.isEmpty()){
                    Toast.makeText(getApplicationContext(), "data masih kosong", Toast.LENGTH_SHORT).show();
                }else {
                    mm = new Mhs(-1, isian_nama, isian_nim, isian_noHp);
                    boolean stts = db.simpan(mm);

                    if(stts){
                        edNama.setText("");
                        edNim.setText("");
                        edNoHp.setText("");
                        Toast.makeText(getApplicationContext(),"Data berhasil disimpan", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Data gagal disimpan", Toast.LENGTH_LONG).show();
                    }

                    //intent_list.putParcelableArrayListExtra("mhsList", mhsList);
                    //startActivity(intent_list);
                }
            }
        });

        Button btnLihat = (Button) findViewById(R.id.btnLihat);
        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mhsList = db.List();

                if (mhsList.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Belum Ada Data", Toast.LENGTH_SHORT).show();
                }else {
                    intent_list.putParcelableArrayListExtra("mhsList", mhsList);
                    startActivity(intent_list);
                }

            }
        });

    }
}