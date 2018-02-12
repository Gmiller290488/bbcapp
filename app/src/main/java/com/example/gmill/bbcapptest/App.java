package com.example.gmill.bbcapptest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by gmill on 12/02/2018.
 */

class App implements Serializable {
    private final String mTitle,
            mDescription,
            mRating,
            mPrice,
            mSmallImageUrl,
            mLargeImageUrl,
            mName,
            mScrnShotsUrls;

    App(String title, String description, String rating, String price, String smallImageUrl, String largeImageUrl, String name, String scrnshotUrls) {
        mTitle = title;
        mDescription = description;
        mRating = rating;
        mPrice = price;
        mSmallImageUrl = smallImageUrl;
        mLargeImageUrl = largeImageUrl;
        mName = name;
        mScrnShotsUrls = scrnshotUrls;
    }

    String getTitle() {
        return mTitle;
    }

    String getDescription() {
        return mDescription;
    }

    String getRating() {
        return mRating;
    }

    String getPrice() {
        return mPrice;
    }

    String getSmallImageUrl() {
        return mSmallImageUrl;
    }

    String getLargeImageUrl() {
        return mLargeImageUrl;
    }

    String getName() { return mName; }

    String getScrnShotsUrls() { return mScrnShotsUrls; }

    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        final ImageView bmImage;

        DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

