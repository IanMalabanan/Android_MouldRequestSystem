package com.example.mouldrequestsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class accept_request extends AppCompatActivity {

    private Button btnCompleted;
    String fname = "";
    String sessionId;
    public String Partcode= "",Stepcode= "",Location= "",Machine= "",Date= "",Request= "";
    EditText txtPartcode,txtStepcode,txtLocation,txtMachine,txtDate,txtRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_request);
        getSupportActionBar().hide();
        View overlay = findViewById(R.id.mylayout2);

        txtPartcode = (EditText) findViewById(R.id.txtPartcode);
        txtStepcode = (EditText) findViewById(R.id.txtStepcode);
        txtLocation = (EditText) findViewById(R.id.txtLocation);
        txtMachine = (EditText) findViewById(R.id.txtMachine);
        txtDate = (EditText) findViewById(R.id.txtDate);
        txtRequest = (EditText) findViewById(R.id.txtRequest);

        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN);


        Partcode  = getIntent().getExtras().getString("Partcode");
        Stepcode  = getIntent().getExtras().getString("Stepcode");
        Location  = getIntent().getExtras().getString("Location");
        Machine  = getIntent().getExtras().getString("Machine");
        Date  = getIntent().getExtras().getString("Date");
        Request  = getIntent().getExtras().getString("Request");

        fname = getIntent().getExtras().getString("fname");
        sessionId = getIntent().getExtras().getString("sessionId");


        txtPartcode.setText(Partcode);
        txtStepcode.setText(Stepcode);
        txtLocation.setText(Location);
        txtMachine.setText(Machine);
        txtDate.setText(Date);
        txtRequest.setText(Request);




//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//             sessionId = extras.getString("sessionId");
//             fname = extras.getString("fname");
//        }
//

        btnCompleted = (Button) findViewById(R.id.btnComplete);

        btnCompleted.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                 Toast.makeText(getApplicationContext(), "Request Completed" , Toast.LENGTH_SHORT).show();

                DBDataAccess dbDataAccess = new DBDataAccess();

                dbDataAccess.updatemouldrequesttoCompleted(sessionId);

                Intent i = new Intent(accept_request.this, mould_request.class);
                i.putExtra("fname",fname);
                startActivity(i);

            }
        });

    }

    @Override
    public void onBackPressed() {

    }






}


