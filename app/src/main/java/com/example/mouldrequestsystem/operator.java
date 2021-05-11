package com.example.mouldrequestsystem;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class operator extends AppCompatActivity {

    ListView Listviewoperator;

    final Context context = this;
    checkConnection checkConnection;

    ClassOperatorCustom classOperatorCustom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);
        getSupportActionBar().hide();

        Listviewoperator = (ListView) findViewById(R.id.listOperator);
        SelectAllOperator();
    }


    public void SelectAllOperator() {

        DBOperator dbOperator = new DBOperator();

        dbOperator.SelectALLOperator();

        classOperatorCustom = new ClassOperatorCustom(this, dbOperator.classOperatorDT);

        Listviewoperator.setAdapter(classOperatorCustom);

        classOperatorCustom.notifyDataSetChanged();
    }

}


