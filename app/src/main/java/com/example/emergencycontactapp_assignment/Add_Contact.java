package com.example.emergencycontactapp_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Contact extends AppCompatActivity {

    EditText etName,etPhoneNo;
    Button btnSave,btnView;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (Build.VERSION.SDK_INT>=23){
//            if (ActivityCompat.checkSelfPermission(Add_Contact.this,"android.permission.SEND_SMS") != PackageManager.PERMISSION_GRANTED){
//                ActivityCompat.requestPermissions(Add_Contact.this,new String[]{"android.permission.SEND_SMS"},1);
//            }
//        }

        db = openOrCreateDatabase("dbContact",MODE_PRIVATE,null);
        db.execSQL("create table if not exists ContactList (name varchar(20),mobileNo varchar(13))");


        etName = findViewById(R.id.etName);
        etPhoneNo = findViewById(R.id.etPhoneNo);
        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sName = etName.getText().toString();
                String sPhoneNo = etPhoneNo.getText().toString();

                if (sName.length() == 0){
                    Toast.makeText(Add_Contact.this,"Name field is empty",Toast.LENGTH_LONG).show();
                }
                if (sPhoneNo.length() == 0){
                    Toast.makeText(Add_Contact.this,"Phone No field is empty",Toast.LENGTH_LONG).show();
                }

                else {
                    try {
                        db.execSQL("insert into ContactList (name ,mobileNo) values ('"+sName+"','"+sPhoneNo+"')");
                        Toast.makeText(Add_Contact.this, "Contact Saved!", Toast.LENGTH_LONG).show();
                        etName.setText("");
                        etPhoneNo.setText("");
                    }
                    catch (Exception e) {
                        Toast.makeText(Add_Contact.this, "" + e, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Add_Contact.this,ViewContact.class);
                startActivity(i);

            }
        });
    }
}