package com.example.fyp_artefact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class mens extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fabAdd;
    DBManage database;
    ArrayList<String> itemName, itemID, itemPrice, itemCategory, itemQuantity;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mens);

        recyclerView = findViewById(R.id.rv_men);
        fabAdd = findViewById(R.id.fab_add);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mens.this, Add_Items.class);
                startActivity(intent);
            }
        });

        database = new DBManage(mens.this);
        itemName = new ArrayList<>();
        itemID = new ArrayList<>();
        itemPrice = new ArrayList<>();
        itemCategory = new ArrayList<>();
        itemQuantity = new ArrayList<>();
        storeDataInArrays();

        customAdapter = new CustomAdapter(mens.this, this, itemName, itemID, itemPrice, itemCategory, itemQuantity);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mens.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    public void storeDataInArrays(){
        try (Cursor cursor = database.readMens()) {
            if (cursor.getCount() == 0) {
                Toast.makeText(mens.this, "No Data", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    itemID.add(cursor.getString(0));
                    itemName.add(cursor.getString(1));
                    itemPrice.add(cursor.getString(2));
                    itemCategory.add(cursor.getString(3));
                    itemQuantity.add(cursor.getString(4));
                }
            }
        }catch (NullPointerException nullPointerException){
            Toast.makeText(this, "Nothing To show", Toast.LENGTH_SHORT).show();
        }
    }
}