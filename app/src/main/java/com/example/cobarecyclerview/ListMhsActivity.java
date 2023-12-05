package com.example.cobarecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListMhsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmhs);

//        ListView lvNama = (ListView) findViewById(R.id.lvNama);

        RecyclerView viewById = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView recyclerView =  findViewById(R.id.recyclerView);

        ArrayList<Mhs> mhsList = getIntent().getExtras().getParcelableArrayList("mhsList");

        MhsAdapter mhsAdapter = new MhsAdapter(mhsList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListMhsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mhsAdapter);


//       if (mhsList.isEmpty()){
//            mhsList.add("data masih kosong");
//        }

//        ArrayAdapter<String> ad_nama = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, daftar_nama);
//
//        lvNama.setAdapter(ad_nama);

    }
}