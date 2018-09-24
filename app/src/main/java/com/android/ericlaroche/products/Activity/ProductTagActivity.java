package com.android.ericlaroche.products.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.ericlaroche.products.Adapter.TagAdapter;
import com.android.ericlaroche.products.Data.Product;
import com.android.ericlaroche.products.Data.Tag;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ProductTagActivity extends AppCompatActivity {
    private String url = "https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";

    private ListView mTagList = null;
    private Activity mCurrentActivity = null;
    private ArrayList<Tag> tagArrayList = new ArrayList<>();
    private TagAdapter mAdapter = null;
    private Map<String,Integer> tagMap = new HashMap<>();
    private Intent intent = null;
    private ArrayList<Product> productArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_tag);

        mTagList = findViewById(R.id.productTagList);
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

                                if (productTags.length() > 0 ) {
                                    String[] tagList = productTags.split("\\s*,\\s*");

                                    for (int j = 0; j < tagList.length; j++) {
                                        if (tagMap.containsKey(tagList[j])) {
                                            int productCount = tagMap.get(tagList[j]);
                                            tagMap.put(tagList[j], productCount + 1);
                                        } else {
                                            tagMap.put(tagList[j], 1);
                                        }
                                    }
                                }

                            } catch (JSONException e) {
                            }
                        }

                        Iterator it = tagMap.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry mapping = (Map.Entry) it.next();
                            String tagName = mapping.getKey().toString();
                            Integer productCountWithTag = (Integer)mapping.getValue();
                            tagArrayList.add(new Tag(tagName, productCountWithTag));
                        }
                    }

                    //Sorting it alphabetically to be nice ;)
                    Collections.sort(tagArrayList, new Comparator<Tag>() {
                        public int compare(Tag t1, Tag t2) {
                            return t1.getmTagName().compareTo(t2.getmTagName());
                        }
                    });

                    mAdapter = new TagAdapter(getApplicationContext(), tagArrayList);
                    mTagList.setAdapter(mAdapter);

                    mTagList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Tag tagObject = (Tag) mAdapter.getItem(i);
                            displayProductsWithTag(tagObject);
                        }
                    });

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

    public void displayProductsWithTag(Tag tag) {
        intent = new Intent(getApplicationContext(), DisplayProductsWithTagActivity.class);
        ArrayList<Product> productListWithTag = new ArrayList<>();

        for(Product currProduct : productArrayList) {
            if (currProduct.getmTag().contains(tag.getmTagName())) {
                productListWithTag.add(currProduct);
            }
        }

        intent.putExtra("productListWithTag", productListWithTag);
        startActivity(intent);
    }
}


