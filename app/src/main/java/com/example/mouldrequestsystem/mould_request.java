package com.example.mouldrequestsystem;

import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.PowerManager;
import android.renderscript.RenderScript;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class mould_request extends AppCompatActivity {

    private NotificationManagerCompat notificationManagerCompat;

    ExpandableListAdapter listAdapter;

    ExpandableListView expListView;

    List<String> listDataHeader;

    HashMap<String, List<String>> listDataChild;

    ListView listViewMouldRequest;

    AlertDialog alert;

    AlertDialog.Builder altdial;

    final Context context = this;

    checkConnection checkConnection;

    ClassMouldRequestCustom classMouldRequestCustom;

    String fname = "", sessionId = "", Partcode = "", Stepcode = "", Location = "", Machine = "", Date = "", Request = "";

    Integer RecCounterOld, RecCounterNew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mould_request);

        fname = getIntent().getExtras().getString("fname");

        getSupportActionBar().hide();

        listViewMouldRequest = (ListView) findViewById(R.id.listViewMouldRequest);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        SelectMouldRequest();

        DBDataAccess dbDataAccess = new DBDataAccess();

        dbDataAccess.CountRequests();

        RecCounterOld = dbDataAccess.recCounter;

        //RecCounterNew = dbDataAccess.recCounter;

        handler.postDelayed(runnable, 1000);


        listViewMouldRequest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {

                //final Object tmp=(Object) adapterView.getItemAtPosition(i);

                altdial = new AlertDialog.Builder(mould_request.this);

                altdial.setMessage("Accept Request?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Toast.makeText(getApplicationContext(), view.getTag().toString() , Toast.LENGTH_SHORT).show();
                                DBDataAccess dbDataAccess = new DBDataAccess();

                                dbDataAccess.updatemouldrequesttoOngoing(fname, view.getTag().toString());

                                dbDataAccess.SelectMouldRequestbyID(view.getTag().toString());

                                Partcode = dbDataAccess.PartCode;
                                Stepcode = dbDataAccess.StepCode;
                                Location = dbDataAccess.Location;
                                Machine = dbDataAccess.MachineNo;
                                Date = dbDataAccess.DateRequest;
                                Request = dbDataAccess.Requestby;


                                sessionId = view.getTag().toString();

                                Intent io = new Intent(mould_request.this, accept_request.class);
                                io.putExtra("sessionId", sessionId);
                                io.putExtra("fname", fname);
                                io.putExtra("Partcode", Partcode);
                                io.putExtra("Stepcode", Stepcode);
                                io.putExtra("Location", Location);
                                io.putExtra("Machine", Machine);
                                io.putExtra("Date", Date);
                                io.putExtra("Request", Request);
                                startActivity(io);
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
        });
    }


    @Override
    public void onBackPressed() {

        Intent i = new Intent(mould_request.this, MainActivity.class);
        i.putExtra("key", fname);
        startActivity(i);
    }


    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            SelectMouldRequest();

            DBDataAccess dbDataAccess = new DBDataAccess();

            dbDataAccess.CountRequests();

            if (dbDataAccess.recCounter != RecCounterOld && dbDataAccess.recCounter > 0) {
                RecCounterOld = dbDataAccess.recCounter;
                CreateNotifications();
            }

            handler.postDelayed(this, 1000);
        }
    };


    public void CreateNotifications() {
        String Title = "Mould Request System";

        String Message = "New Request Received";

        Notification notification = new NotificationCompat.Builder(this, App.Channel_1)
                .setSmallIcon(R.drawable.logo2)
                .setContentTitle(Title).setContentText(Message)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND | Notification.FLAG_SHOW_LIGHTS)
                .build();

        notificationManagerCompat.notify(1, notification);

        PowerManager powerManager = (PowerManager) context.getSystemService(context.POWER_SERVICE);
        PowerManager.WakeLock  wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK |
                PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.ON_AFTER_RELEASE, "appname::WakeLock");

        //acquire will turn on the display
        wakeLock.acquire();

        //release will release the lock from CPU, in case of that, screen will go back to sleep mode in defined time bt device settings
        wakeLock.release();
    }

    public void SelectMouldRequest() {

        DBDataAccess dbDataAccess = new DBDataAccess();

        dbDataAccess.SelectMouldRequest();

        classMouldRequestCustom = new ClassMouldRequestCustom(this, dbDataAccess.classMouldRequestDT);

        listViewMouldRequest.setAdapter(classMouldRequestCustom);

        classMouldRequestCustom.notifyDataSetChanged();
    }

}