package com.example.fyp_artefact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Items extends AppCompatActivity {

    EditText uName, uCategory, uPrice, uQuantity;
    String id, name, price, quantity, category;
    Button btn_updat, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_items);

        uName = findViewById(R.id.u_Name);
        uCategory = findViewById(R.id.u_category);
        uPrice = findViewById(R.id.u_Price);
        uQuantity = findViewById(R.id.u_Quantity);
        btn_updat = findViewById(R.id.btn_uItems);
        btn_delete = findViewById(R.id.btn_dItems);

        getAndSETIntentData();

        btn_updat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBManage db = new DBManage(Update_Items.this);
                name = uName.getText().toString().trim();
                category = uCategory.getText().toString().trim();
                price = uPrice.getText().toString();
                quantity = uQuantity.getText().toString();
                db.updateData(id, name, category, price, quantity);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete();
                }
        });
    }

    public void getAndSETIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("category") &&
                getIntent().hasExtra("price") && getIntent().hasExtra("quantity")){
            //get intent data
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            category = getIntent().getStringExtra("category");
            price = getIntent().getStringExtra("price");
            quantity = getIntent().getStringExtra("quantity");

            //set Intent data
            uName.setText(name);
            uCategory.setText(category);
            uPrice.setText(price);
            uQuantity.setText(quantity);
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    public void confirmDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                id = getIntent().getStringExtra("id");
                DBManage db = new DBManage(Update_Items.this);
                db.deleteOneItem(id);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        builder.create().show();
    }
}