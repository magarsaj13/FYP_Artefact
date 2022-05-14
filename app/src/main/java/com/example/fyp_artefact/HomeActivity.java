package com.example.fyp_artefact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ImageButton img_boy, img_girl;
    RecyclerView recyclerView;
    TextView boy, girl;
    Button btn_adds;
    ArrayList<String> itemName, itemID, itemPrice, itemCategory, itemQuantity;
    CustomAdapter customAdapter;
    DBManage myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        img_boy = findViewById(R.id.ib_men);
        img_girl = findViewById(R.id.ib_women);
        boy = findViewById(R.id.tv_men);
        girl = findViewById(R.id.tv_women);
        btn_adds = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.recycle_Main);

        img_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotomens();
            }
        });

        img_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotowomen();
            }
        });

        boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotomens();
            }
        });

        girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotowomen();
            }
        });

        btn_adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Add_Items.class);
                startActivity(intent);
            }
        });

        myDatabase = new DBManage(HomeActivity.this);
        itemName = new ArrayList<>();
        itemID = new ArrayList<>();
        itemPrice = new ArrayList<>();
        itemCategory = new ArrayList<>();
        itemQuantity = new ArrayList<>();
        storeDataInArrays();

        customAdapter = new CustomAdapter(HomeActivity.this, this, itemName, itemID, itemPrice, itemCategory, itemQuantity);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
    }

    public void gotomens(){
        Intent intent = new Intent(getApplicationContext(), mens.class);
        startActivity(intent);
    }

    public void gotowomen(){
        Intent intent = new Intent(getApplicationContext(), Women.class);
        startActivity(intent);
    }

    public void storeDataInArrays(){
        try (Cursor cursor = myDatabase.readAllData()) {
            if (cursor.getCount() == 0) {
                Toast.makeText(HomeActivity.this, "No Data", Toast.LENGTH_SHORT).show();
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