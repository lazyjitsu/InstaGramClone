package com.example.instagramclone;

import android.app.Application;
import android.os.Bundle;

import com.parse.Parse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("OKRs917HO2iQxl9MLPbVwxH8OsMP89oVbguEgVcA")
                // if defined
                .clientKey("SQcucBFBp50RzY3Ct3Yqkp26kXRd2gsVcicHPRBf")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }

    public static class SignUpLoginActivity extends AppCompatActivity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);
        }
    }
}
