package com.android.ericlaroche.products.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.ericlaroche.products.Adapter.ProductAdapter;
import com.android.ericlaroche.products.Data.Product;
import com.android.ericlaroche.products.R;

import java.util.ArrayList;

public class DisplayProductsWithTagActivity extends AppCompatActivity {
    private ArrayList<Product> productListWithTag  = null;
    private ListView productWithTag = null;
    private ProductAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products_with_tag);
        productWithTag = (ListView) findViewById(R.id.productListWithTag);

        productListWithTag = (ArrayList<Product>) getIntent().getSerializableExtra("productListWithTag");

        mAdapter = new ProductAdapter(getApplicationContext(), productListWithTag);
        productWithTag.setAdapter(mAdapter);
    }
}
