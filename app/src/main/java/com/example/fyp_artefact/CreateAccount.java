package com.example.fyp_artefact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    EditText sNam, sID, sPass, sCpass;
    Button btn_creat;
    DBManage db;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        sNam = findViewById(R.id.ev_sName);
        sID = findViewById(R.id.ev_sID);
        sPass = findViewById(R.id.ev_pass);
        sCpass = findViewById(R.id.ev_cPass);
        btn_creat = findViewById(R.id.btn_create);
        db = new DBManage(this);
        title = findViewById(R.id.tv_title);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoLogin();
            }
        });

        btn_creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = sNam.getText().toString().trim();
                String ID = sID.getText().toString().trim();
                String password = sPass.getText().toString().trim();
                String cPassword = sCpass.getText().toString().trim();

                if (name.equals("") || ID.equals("") || password.equals("") || cPassword.equals("")){
                    Toast.makeText(CreateAccount.this, "Please fill all fields!!", Toast.LENGTH_SHORT).show();
                }else{
                    if (password.equals(cPassword)){
                        if(password.length() < 8){
                            Toast.makeText(CreateAccount.this, "Password should be 8 characters or longer!", Toast.LENGTH_SHORT).show();
                        }else{
                            boolean checkUser = db.check_username(ID);
                            if (checkUser){
                                Toast.makeText(CreateAccount.this, "User Already Exists\nGo To Login Page", Toast.LENGTH_SHORT).show();
                            }else{
                                Boolean aBoolean = db.add_users(ID, password, name);
                                if (aBoolean){
                                    Toast.makeText(CreateAccount.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    gotoLogin();
                                }else{
                                    Toast.makeText(CreateAccount.this, "Registration Failed!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }else{
                        Toast.makeText(CreateAccount.this, "Password Not Matched!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void gotoLogin(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}