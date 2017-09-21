package com.example.honda.android_edittext_demo;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText userIDEditText;
    private EditText passwordEditText;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    public void setup() {

        final Mask mMask = new Mask();
        userIDEditText = (EditText) findViewById(R.id.userIDEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        userIDEditText.setText(mMask.maskText(mSharedPreferences.getString(Constants.SP_LOGIN_USER_ID,null)));
        passwordEditText.setText(mSharedPreferences.getString(Constants.SP_LOGIN_USER_PASSWORD,null));
        passwordEditText.requestFocus();

        userIDEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                String userID;

                if (hasFocus) {
                    userID = mSharedPreferences.getString(Constants.SP_LOGIN_USER_ID,null);
                }else {
                    mSharedPreferences.edit().putString(Constants.SP_LOGIN_USER_ID,userIDEditText.getText().toString()).apply();
                    userID = mMask.maskText(userIDEditText.getText().toString());
                }

                userIDEditText.setText(userID);
            }
        });

        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    login();
                }
            }
        });
    }

    public void login() {
        System.out.println(mSharedPreferences.getString(Constants.SP_LOGIN_USER_ID,null));
        System.out.println(passwordEditText.getText().toString());
    }
}
