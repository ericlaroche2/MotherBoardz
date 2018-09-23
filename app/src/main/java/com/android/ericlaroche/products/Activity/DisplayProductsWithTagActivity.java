package com.android.ericlaroche.products.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.android.ericlaroche.products.R;

public class DisplayProductsWithTagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products_with_tag);
    }

    /**TODO:
     * 1. grab product list view from the intent extra from product tag activity
     * 2. setAdapter
     * */
}
