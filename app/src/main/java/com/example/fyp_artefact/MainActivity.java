package com.example.fyp_artefact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText s_ID, s_pass;
    TextView click;
    Button btn_login;
    DBManage database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s_ID = findViewById(R.id.et_id);
        s_pass = findViewById(R.id.et_pass);
        click = findViewById(R.id.click_here);
        btn_login = findViewById(R.id.btn_create);
        database = new DBManage(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String storeID = s_ID.getText().toString().trim();
                String storePassword = s_pass.getText().toString().trim();

                if (storeID.equals("") || storePassword.equals("")) {
                    Toast.makeText(MainActivity.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean result = database.check_SID(storeID, storePassword);
                    if (result) {
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
                startActivity(intent);
            }
        });
    }
}