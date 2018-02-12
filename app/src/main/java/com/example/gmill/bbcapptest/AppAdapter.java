package com.example.gmill.bbcapptest;

/**
 * Created by gmill on 12/02/2018.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

class AppAdapter extends ArrayAdapter<App> {

        AppAdapter(Context context, List<App> apps) {
        super(context, 0, apps);
        }

@NonNull
@Override
public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
        listItemView = LayoutInflater.from(getContext()).inflate(
        R.layout.app_list_item, parent, false);
        }

        App currentApp = getItem(position);

        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        TextView descriptionView = (TextView) listItemView.findViewById(R.id.description);
        TextView ratingView = (TextView) listItemView.findViewById(R.id.rating);
        TextView priceView = (TextView) listItemView.findViewById(R.id.price);
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView);

        titleView.setText(currentApp.getTitle());
        descriptionView.setText(currentApp.getDescription());
        ratingView.setText(currentApp.getRating());
        priceView.setText(currentApp.getPrice());



        return listItemView;
        }



}
