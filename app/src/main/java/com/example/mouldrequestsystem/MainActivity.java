package com.example.mouldrequestsystem;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView mould_request, part_code, step, operator, about;
    String fname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        View overlay = findViewById(R.id.mylayout);

        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        //defining cards
        mould_request = (CardView) findViewById(R.id.mould_request);
        part_code = (CardView) findViewById(R.id.part_code);
        step = (CardView) findViewById(R.id.step);
        operator = (CardView) findViewById(R.id.operator);
        about = (CardView) findViewById(R.id.about);


        //add click listener to the cards
        mould_request.setOnClickListener(this);
        part_code.setOnClickListener(this);
        step.setOnClickListener(this);
        operator.setOnClickListener(this);
        about.setOnClickListener(this);

        fname = getIntent().getExtras().getString("fname");
    }


    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case R.id.mould_request:
                i = new Intent(this, mould_request.class);
                i.putExtra("fname", fname);
                startActivity(i);
                break;
            case R.id.part_code:
                i = new Intent(this, partcode.class);
                startActivity(i);
                break;
            case R.id.step:
                i = new Intent(this, step.class);
                startActivity(i);
                break;
            case R.id.operator:
                i = new Intent(this, operator.class);
                startActivity(i);
                break;
            case R.id.about:
                i = new Intent(this, about.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    AlertDialog alert;

    AlertDialog.Builder altdial;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        altdial = new AlertDialog.Builder(MainActivity.this);

        altdial.setMessage("Do you want to signout?").setCancelable(false)
                .setPositiveButton("Signout", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent launchNextActivity;
                        launchNextActivity = new Intent(MainActivity.this, UserLogin.class);
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(launchNextActivity);
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        alert = altdial.create();

        alert.show();
    }

}
