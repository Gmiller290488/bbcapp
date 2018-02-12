package com.example.gmill.bbcapptest;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String SEARCH_TERM = "SEARCH_TERM";

    /**
     * Adapter for the list of news articles
     */
    AppAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText searchFor = (EditText) findViewById(R.id.search_text);
                String searchTerm = String.valueOf(searchFor.getText());
                if (searchTerm.length() == 0) {
                    Toast.makeText(getBaseContext(), "Please enter a search term.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(MainActivity.this, AppActivity.class);//new AppActivity();
                    i.putExtra(SEARCH_TERM, searchTerm);
                    startActivity(i);
                }
            }
        });
    }
}

//        mCursorAdapter = new ProductCursorAdapter(this, null);
//        productListView.setAdapter(mCursorAdapter);
//
//        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
//
//                Uri currentProductUri = ContentUris.withAppendedId(ProductContract.ProductEntry.CONTENT_URI, id);
//
//                intent.setData(currentProductUri);
//
//                startActivity(intent);
//            }
//        });




