package com.example.mouldrequestsystem;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class step extends AppCompatActivity {

    ListView ListViewStepcode;
    Spinner spinPartcode;
    DBPartCode _DBPartCode;
    final Context context = this;
    checkConnection checkConnection;

    ClassStepCodeCustom classStepCodeCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        getSupportActionBar().hide();

        ListViewStepcode = (ListView) findViewById(R.id.listStepcode);
        spinPartcode = (Spinner) findViewById(R.id.spinPartcode);

        GetPartCode();

        spinPartcode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                //Toast.makeText(getApplicationContext(), item.toString() , Toast.LENGTH_SHORT).show();\

                SelectStepCode(item.toString().trim());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void SelectStepCode(String PartCode) {

        DBStepCode dbStepCode= new DBStepCode();

        dbStepCode.SelectStepCode(PartCode);

        classStepCodeCustom = new ClassStepCodeCustom(this, dbStepCode.classStepCodeDT);

        ListViewStepcode.setAdapter(classStepCodeCustom);

        classStepCodeCustom.notifyDataSetChanged();
    }

    public void GetPartCode() {

        _DBPartCode = new DBPartCode();

        _DBPartCode.GetALLPartcode();

        ArrayAdapter spinAdapater = new ArrayAdapter(this, android.R.layout.simple_list_item_1, _DBPartCode.array);

        spinPartcode.setAdapter(spinAdapater);

        spinAdapater.notifyDataSetChanged();

    }






}
