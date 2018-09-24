package com.android.ericlaroche.products.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.ericlaroche.products.Adapter.ProductAdapter;
import com.android.ericlaroche.products.Data.Product;
import com.android.ericlaroche.products.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ProductListActivity extends AppCompatActivity {
    private String url = "https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
    private ListView mProductList = null;
    private Activity mCurrentActivity = null;
    private ArrayList<Product> productArrayList = new ArrayList<>();
    private ProductAdapter mAdapter = null;
    private String TAG = "ProductListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        mProductList = findViewById(R.id.productList);
        loadData();
    }

    public void loadData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray productsList = response.getJSONArray("products");
                    if ( productsList.length() > 0 ) {
                        for (int i = 0; i < productsList.length(); i++) {
                            JSONObject currProduct = (JSONObject) productsList.get(i);


                            try {
                                String productTitle = currProduct.getString("title");
                                String productTags = currProduct.getString("tags");
                                String imgUrl = currProduct.getJSONObject("image").getString("src");
                                Integer inventoryAmount = 0;

                                JSONArray variants = currProduct.getJSONArray("variants");



                                for (int j=0; j<variants.length(); j++) {
                                    JSONObject variant = (JSONObject) variants.get(j);
                                    Integer amount = Integer.parseInt(variant.getString("inventory_quantity"));
                                    inventoryAmount += amount;
                                }


                                if (productTitle == null) {
                                    productTitle = "";
                                }
                                if (productTags == null) {
                                    productTags = "";
                                }
                                if (imgUrl == null) {
                                    imgUrl = "";
                                }

                                Product newProduct = new Product(productTitle, productTags, imgUrl, inventoryAmount);
                                productArrayList.add(newProduct);
                            } catch (JSONException e) {
                                //One of the attr is null?
                            }
                        }

                    }

                    mAdapter = new ProductAdapter(getApplicationContext(), productArrayList);
                    mProductList.setAdapter(mAdapter);

                } catch (JSONException e) {
                    //can't send req or receive response?
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }
}


