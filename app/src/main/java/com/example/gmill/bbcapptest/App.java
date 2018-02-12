package com.example.gmill.bbcapptest;

import java.io.Serializable;

/**
 * Created by gmill on 12/02/2018.
 */

class App implements Serializable {
    private final String mTitle,
            mDescription,
            mRating,
            mPrice,
            mImageUrl;

    App(String title, String description, String rating, String price, String imageUrl) {
        mTitle = title;
        mDescription = description;
        mRating = rating;
        mPrice = price;
        mImageUrl = imageUrl;
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

    String getImageUrl() {
        return mImageUrl;
    }
}

