package com.example.gmill.bbcapptest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class AppDetailActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail);
        Intent i = getIntent();
        App currentApp = (App) i.getSerializableExtra("app");

        TextView title = (TextView) findViewById(R.id.app_title);
        TextView name = (TextView) findViewById(R.id.app_developer);
        TextView rating = (TextView) findViewById(R.id.app_rating);
        TextView price = (TextView) findViewById(R.id.app_price);
        TextView description = (TextView) findViewById(R.id.app_description);
        ImageView icon = (ImageView) findViewById(R.id.app_large_icon);

        title.setText(currentApp.getTitle());
        name.setText(currentApp.getName());
        rating.setText("Rating: " + currentApp.getRating());
        price.setText("Price: " + currentApp.getPrice());
        description.setText(currentApp.getDescription());
        new App.DownloadImageTask(icon).execute(currentApp.getLargeImageUrl());
    }
}
