package com.steinsvik.fabricmanagaer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.steinsvik.fabricmanagaer.utilies.CONFIG;

public class MainActivity extends ActionBarActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnSignIn;
    private ProgressBar spinner;
    private LinearLayout viewContent;
    private CheckBox cbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        spinner = (ProgressBar) findViewById(R.id.loading);
        spinner.setVisibility(View.GONE);
        viewContent = (LinearLayout) findViewById(R.id.viewContent);
        cbRemember = (CheckBox) findViewById(R.id.rememberPassword);

        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences preferences = getBaseContext().getSharedPreferences(CONFIG.PREF_FILE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(CONFIG.PREF_REMEMBER_PW, isChecked);
                if (isChecked) {
                    editor.putString("username", txtEmail.getText().toString());
                    editor.putString("password", txtPassword.getText().toString());
                }
                editor.apply();
            }
        });
        loadingData();

        final SharedPreferences preferences = getBaseContext().getSharedPreferences(CONFIG.PREF_FILE_NAME, Context.MODE_PRIVATE);
        if (preferences.getBoolean(CONFIG.PREF_REMEMBER_PW, false)) {
            ParseUser.logInInBackground(preferences.getString("username", "default"), preferences.getString("password", "default"), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Intent intent = new Intent(MainActivity.this, MainForm.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } else
                        endLoading();
                }
            });
        } else endLoading();

        txtEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    isSignIn();
                    if (!isValidEmail(txtEmail.getText() + "")) {
                        txtEmail.setText("");
                        txtEmail.setHint("Invalid email");
                        txtEmail.setHintTextColor(Color.RED);
                    } else {
                        txtEmail.setHint("Enter email");
                        txtEmail.setHintTextColor(Color.parseColor("#bbbbbb"));
                    }
                    txtPassword.requestFocus();
                    return true;
                }
                return false;
            }
        });

        txtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    isSignIn();
                    if (!isValidPassword(txtPassword.getText() + "")) {
                        txtPassword.setText("");
                        txtPassword.setHint("Invalid password");
                        txtPassword.setHintTextColor(Color.RED);
                    } else {
                        txtPassword.setHint("Enter password");
                        txtPassword.setHintTextColor(Color.parseColor("#bbbbbb"));
                    }
                    InputMethodManager im = (InputMethodManager) getSystemService(MainActivity.this.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
//                }
//                return false;
            }
        });


        btnSignIn.setEnabled(false);
        btnSignIn.setBackgroundDrawable(getResources().getDrawable(R.drawable.un_enable_signin));

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingData();
                ParseUser.logInInBackground(txtEmail.getText().toString(), txtPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Intent intent = new Intent(MainActivity.this, MainForm.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
//                        endLoading();
                    }
                });
            }
        });
    }

    private void loadingData() {
        viewContent.setVisibility(View.GONE);
        spinner.setVisibility(View.VISIBLE);
    }

    private void endLoading() {
        viewContent.setVisibility(View.VISIBLE);
        spinner.setVisibility(View.GONE);
    }

    private boolean isSignIn() {
        String text1 = txtEmail.getText() + "";
        String text2 = txtPassword.getText() + "";
        if (isValidEmail(text1) && isValidPassword(text2)) {
            btnSignIn.setEnabled(true);
            btnSignIn.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_sign_in_login_bg));
            return true;
        } else {
            btnSignIn.setEnabled(false);
            btnSignIn.setBackgroundDrawable(getResources().getDrawable(R.drawable.un_enable_signin));
        }
        return false;
    }


    public boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target))
            return false;
        return true;
    }

    public boolean isValidPassword(CharSequence target) {
        return target.length() >= 3 ? true : false;
    }
}
