package com.android.ericlaroche.products.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.android.ericlaroche.products.R;

public class ProductTagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_tag);
    }

    /** TODO:
     * 1. make json request and retrieve products
     * 2. create array list of products
     * 4. make hash map for product tag so unique
     * 5. sort array list of tag alphabetically
     * 6. set onItem click listener on listview of tag
     *
     */


    public void displayProductsWithTag() {
        //TODO: return an array list of products and start another activity to display these products
        //TODO: Must re-initialize this array list eeach time so it doesn't add products to it
    }
}
