package com.android.ericlaroche.products.Adapter;
import com.android.ericlaroche.products.R;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.ericlaroche.products.Data.Product;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class ProductAdapter extends BaseAdapter {
    private Context mContext = null;
    private String TAG = "ProductAdapter";
    private ArrayList<Product> mProducts = null;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.mContext = context;
        this.mProducts = products;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Object getItem(int i) {
        return mProducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mProducts.indexOf(getItem(i));
    }

    private class ViewHolder {
        TextView productTitle;
        TextView productTag;
        TextView inventoryAmount;
        ImageView productImage;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ProductAdapter.ViewHolder holder = null;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = inflater.inflate(R.layout.product_list_item, null);
        }

        holder = new ProductAdapter.ViewHolder();
        holder.productTitle = view.findViewById(R.id.productTitle);
        holder.productTag = view.findViewById(R.id.productTags);
        holder.inventoryAmount = view.findViewById(R.id.inventoryAmount);
        holder.productImage = view.findViewById(R.id.productImage);


        Product row_pdt = mProducts.get(i);
        holder.productTitle.setText(row_pdt.getmProductTitle());
        holder.productTag.setText(row_pdt.getmTag());
        holder.inventoryAmount.setText("Total Available Inventory: " +row_pdt.getmQuantity().toString());


        final int radius = 20;
        final int margin = 0;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        String imageUrl = row_pdt.getmProductImageUrl();
        Picasso.get().load(imageUrl).fit().transform(transformation).into(holder.productImage);

        return view;
    }
}
