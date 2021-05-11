package com.example.mouldrequestsystem;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserLogin extends AppCompatActivity {

    Button _btnSignin;

    AutoCompleteTextView _autoCompleteTextUsername;

    EditText _txtPassword;

    DBUsers _DBUsers;

    Utils _Utils;

    String fname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_login);

        getSupportActionBar().hide();


        _Utils = new Utils();

        _autoCompleteTextUsername =  findViewById(R.id.autoCompleteTextUsername);

        _txtPassword =  findViewById(R.id.txtPassword);

        _btnSignin =  findViewById(R.id.btnSignin);

        if (!_Utils.HasConnection(this)) {
            _Utils.NoInternetBuilDialog(this).show();

        } else {
            GetUsername();
            _btnSignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckLogin checkLogin = new CheckLogin();
                    checkLogin.execute("");
                }
            });

            _autoCompleteTextUsername.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
            });

        }

    }

    public class CheckLogin extends AsyncTask<String, String, String> {
        String z = "";

        Boolean isSuccess = false;

        String username = _autoCompleteTextUsername.getText().toString();

        String password = _txtPassword.getText().toString();

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != "") {
                Toast.makeText(UserLogin.this, s, Toast.LENGTH_SHORT).show();
            }

            if (isSuccess) {
                Intent i = new Intent(UserLogin.this, MainActivity.class);
                i.putExtra("fname", fname);
                startActivity(i);
            }
        }

        @Override
        protected String doInBackground(String... strings) {

            if (username.trim().equals(""))
                z = "Username Cannot Be Empty. Username Required";

            else if (password.trim().equals(""))
                z = "Password Cannot Be Empty. Password Required";

            else {
                _DBUsers = new DBUsers();

                _DBUsers.UserLogin(username, password);

                fname = _DBUsers.fname;

                z = _DBUsers.z;

                isSuccess = _DBUsers.isSuccess;
            }

            return z;
        }
    }

    public void GetUsername() {

        _DBUsers= new DBUsers();

        _DBUsers.GetUsernames();

        ArrayAdapter userNameAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, _DBUsers.array);

        _autoCompleteTextUsername.setAdapter(userNameAdapter);

        _autoCompleteTextUsername.setThreshold(1);
    }




}


