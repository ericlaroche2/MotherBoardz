package com.android.ericlaroche.products.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.android.ericlaroche.products.R;


public class ProductListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
    }

    /**TODO: Get json request
     * 1. parse all products
     * 2. loop through variants (array)and add inventory amount
     * 3. null check product title, produc tag, and imgurl
     *  - add in product array list
     * 4. set adapter
     * 5. set adapter on list view
     * */
}
