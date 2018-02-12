package com.example.gmill.bbcapptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AppDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail);
        Intent i = getIntent();
        App currentApp = (App)i.getSerializableExtra("app");
        TextView title = (TextView) findViewById(R.id.app_title);
        title.setText(currentApp.getTitle());


    }

}
