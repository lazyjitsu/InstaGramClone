package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtLoginEmailName, edtLoginPassword;
    private Button btnLogin, btnSignUp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Loginn Activity");
        edtLoginEmailName = findViewById(R.id.edtLoginEmailName);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the enter key has been  pressed while completing the password field... this is when the user neglects to click on the Sign Up button
                // and instead, hits the <enter> key
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnSignUp); // Now recall btnSignUp isA button AND a button is a subclass of View !!!
                    // this is really cool cause we have essentially done a 'programmatic' click by passing in this btnSignUp view by way ot ifs id
                }
                return false;
            }
        });
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut(); // destroy token session if u detect the user is logged in!
        }


    }

    @Override
    public void onClick(View loginView) {

        switch(loginView.getId()) {
            case R.id.btnLogin:
                ParseUser.logInInBackground(edtLoginEmailName.getText().toString(), edtLoginPassword.getText().toString(),
                        new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            FancyToast.makeText(LoginActivity.this, user.get("username") + "Logged in successfully dude!", Toast.LENGTH_LONG).show();
                            transitionToSocialMediaActivity();
                        } else {
                            FancyToast.makeText(LoginActivity.this, e + " issue Marko!", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }

                    }
                });
                break;
            case R.id.btnSignUp:
                if(edtLoginEmailName.getText().toString().equals("") ||
                        edtLoginPassword.getText().toString().equals("")) {
                    FancyToast.makeText(LoginActivity.this, "Can't have empty field!!", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                } else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setUsername(edtLoginEmailName.getText().toString());
                    appUser.setPassword(edtLoginPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing in " + edtLoginEmailName.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            Log.i("MJ", e + "markio");
                            if (e == null) {
                                FancyToast.makeText(LoginActivity.this, appUser.get("username")
                                        + " signbed up  Successfully!!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            } else {
                                FancyToast.makeText(LoginActivity.this, e + " issue!", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
                break;

        }

    }
    public void rootConstraintTapped(View view) {
        // hide the keyboard when selecting on an empty area or non-field . try/catch keeps it from crashing in the event u click
        // on something WHEN the keyboard is not visible. Note i didn't need the try/catch block on my emulator but here cause of instructor
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void transitionToSocialMediaActivity() {
        Intent intent = new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);

    }
}
