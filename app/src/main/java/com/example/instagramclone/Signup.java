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
import android.widget.ImageView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

//import androidx.annotation.Nullable;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgMain;
    private EditText edtEmail, edtUserName, edtPassword;
    private Button btnSignUp2, btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Sign Me Up!");

        edtEmail = findViewById(R.id.edtEmail);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the enter key has been  pressed while completing the password field... this is when the user neglects to click on the Sign Up button
                // and instead, hits the <enter> key
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnSignUp2); // Now recall btnSignUp isA button AND a button is a subclass of View !!!
                    // this is really cool cause we have essentially done a 'programmatic' click by passing in this btnSignUp view by way ot ifs id
                }
                return false;
            }
        });
        btnSignUp2 = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp2.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            // we don't want the session token to break us in other activities but now that we have an activity to transition to,
            // we can comment this out
            // ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }



    }

    @Override
    public void onClick(View signUpView) {
        Log.i("MJ","uuuuuuuuuuuuuuuu");

        switch(signUpView.getId()) {
            case R.id.btnLogin:
                Log.i("MJ","iiimarkippppo");
                // From Signup To: LoginActivity
                Intent intent = new Intent(Signup.this, LoginActivity.class);
                startActivity(intent);

                break;
            case R.id.btnSignUp:

               if(edtEmail.getText().toString().equals("") ||
                       edtUserName.getText().toString().equals("") ||
                       edtPassword.getText().toString().equals("")) {
                   FancyToast.makeText(Signup.this, "Can't have empty field!!", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
               } else {
                   final ParseUser appUser = new ParseUser();
                   appUser.setUsername(edtUserName.getText().toString());
                   appUser.setPassword(edtPassword.getText().toString());
                   appUser.setEmail(edtEmail.getText().toString());

                   final ProgressDialog progressDialog = new ProgressDialog(this);
                   progressDialog.setMessage("Signing in " + edtUserName.getText().toString());
                   progressDialog.show();

                   appUser.signUpInBackground(new SignUpCallback() {
                       @Override
                       public void done(ParseException e) {
                           Log.i("MJ", e + "markio");
                           if (e == null) {
                               FancyToast.makeText(Signup.this, appUser.get("username")
                                       + " signbed up  Successfully!!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                               transitionToSocialMediaActivity();

                           } else {
                               FancyToast.makeText(Signup.this, e + " issue!", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                           }
                           progressDialog.dismiss();
                       }
                   });

               }
                    break;
        }


    }
    public void rootLayoutTapped(View rootView) {
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
        Intent intent = new Intent(Signup.this,SocialMediaActivity.class);
        startActivity(intent);

    }
}
