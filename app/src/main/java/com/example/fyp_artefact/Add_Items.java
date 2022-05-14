package com.example.fyp_artefact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Add_Items extends AppCompatActivity {

    ImageView img;
    EditText Iname, Iprice, Icategory, Iquantity;
    Button btn_adding;
    DBManage database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        Iname = findViewById(R.id.ev_menName);
        Iprice = findViewById(R.id.ev_menPrice);
        Icategory = findViewById(R.id.ev_category);
        Iquantity = findViewById(R.id.ev_menQuantity);
        img = findViewById(R.id.imageView);
        btn_adding = findViewById(R.id.btn_aItems);
        database = new DBManage(this);

        btn_adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Iname.getText().toString().trim();
                int price = 0;
                int quantity = 0;
                try {
                    price = Integer.parseInt(Iprice.getText().toString());
                    quantity = Integer.parseInt(Iquantity.getText().toString());
                }catch (NumberFormatException exception){
                    Toast.makeText(Add_Items.this, "Please Fill the Fields Correctly", Toast.LENGTH_SHORT).show();
                }
                String category = Icategory.getText().toString().trim();

                if (name.equals("") /*|| id.equals("")*/ || category.equals("") || quantity <= 0 || price < 0){
                    Toast.makeText(Add_Items.this, "Please fill all fields!!", Toast.LENGTH_SHORT).show();
                }else{
                    String cat = category.toUpperCase();
                    if (cat.equals("MEN") || cat.equals("WOMEN") || cat.equals("GENERAL")){
                        database.addItems(name, price, cat, quantity);
                    }else{
                        Toast.makeText(Add_Items.this, "Fill the category correctly.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}