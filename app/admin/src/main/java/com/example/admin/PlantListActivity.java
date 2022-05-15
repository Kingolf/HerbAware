package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class PlantListActivity extends AppCompatActivity {

    ImageButton back;
    RecyclerView recyclerView;
    DatabaseReference database;
    PlantAdapter plantAdapter;
    ArrayList<Plants> list;

    androidx.appcompat.widget.SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list);

        recyclerView = findViewById(R.id.plant_recyclerview);
        back = findViewById(R.id.btn_back);
        searchView = findViewById(R.id.search_view);
        searchView.clearFocus();
        database = FirebaseDatabase.getInstance().getReference("Plants");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        plantAdapter = new PlantAdapter(this,list);
        recyclerView.setAdapter(plantAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });



        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Plants plants = dataSnapshot.getValue(Plants.class);
                    list.add(plants);

                }
                plantAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void filter(String s) {
        ArrayList<Plants> filteredList = new ArrayList<>();
        for(Plants plants : list){
            if(plants.getPlantName().toLowerCase().contains(s.toLowerCase(Locale.ROOT)) || plants.getBotName().toLowerCase().contains(s.toLowerCase(Locale.ROOT))){
                filteredList.add(plants);
            }
            plantAdapter.filteredList(filteredList);
        }
    }

}