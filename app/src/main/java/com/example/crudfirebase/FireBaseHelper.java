package com.example.crudfirebase;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class FireBaseHelper {

    FirebaseDatabase db;
    DatabaseReference dbref;

    public static final String COLUMN_KEY = "key";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_NIM = "nim";
    public static final String COLUMN_NOHP = "nohp";
    public static final String TABLE_NAME = "mhstb";

    ArrayList<Mhs> mhsList;

    public FireBaseHelper() {
        db = FirebaseDatabase.getInstance();
        dbref = db.getReference(TABLE_NAME);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mhsList = new ArrayList<>();
                for(DataSnapshot dsp : snapshot.getChildren()){
                    String key = dsp.getKey();
                    String nama = dsp.child(COLUMN_NAMA).getValue(String.class);
                    String nim = dsp.child(COLUMN_NIM).getValue(String.class);
                    String nohp = dsp.child(COLUMN_NOHP).getValue(String.class);

                    Mhs mm = new Mhs(key, nama, nim, nohp);
                    mhsList.add(mm);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public Task<Void> simpan(Mhs mm){
        return dbref.push().setValue(mm);
    }

    public ArrayList<Mhs> list(){
        return mhsList;
    }

    public Task<Void> hapus(String key){
        return dbref.child(key).removeValue();
    }

    public Task<Void> ubah(Mhs mm) {
        Map<String, Object> dataMap = new ObjectMapper().convertValue(mm, Map.class);
        return dbref.child(mm.getKey()).updateChildren(dataMap);
    }

}
