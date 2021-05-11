package com.example.mouldrequestsystem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


public class partcode extends AppCompatActivity {

    ListView Listviewpartcode;

    final Context context = this;
    checkConnection checkConnection;

    ClassPartCodeCustom classPartCodeCustom;

    Spinner spinnerPartcode;

    Button btnAddPartCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partcode);
        getSupportActionBar().hide();

        Listviewpartcode = (ListView) findViewById(R.id.listPartcode);
        SelectAllPartCode();

    }



    public void SelectAllPartCode() {

        DBPartCode dbPartCode = new DBPartCode();

        dbPartCode.SelectALLPartcode();

        classPartCodeCustom = new ClassPartCodeCustom(this, dbPartCode.classPartCodeDT);

        Listviewpartcode.setAdapter(classPartCodeCustom);

        classPartCodeCustom.notifyDataSetChanged();
    }


    public void AddPartcode(){
        DBPartCode dbPartCode = new DBPartCode();

        String partcode = String.valueOf(spinnerPartcode);

        dbPartCode.InsertPartCode(partcode);

        if (dbPartCode.isSuccess == true) {

            Toast.makeText(partcode.this, "Successfully Added", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(partcode.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }


}
