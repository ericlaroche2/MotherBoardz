package com.android.ericlaroche.products.Adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.ericlaroche.products.Data.Tag;
import com.android.ericlaroche.products.R;

import java.util.ArrayList;

public class TagAdapter extends BaseAdapter {
    private Context mContext = null;
    private String TAG = "TagAdapter";
    private ArrayList<Tag> mTags = null;

    public TagAdapter(Context context, ArrayList<Tag> tags) {
        this.mContext = context;
        this.mTags = tags;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTags.size();
    }

    @Override
    public Tag getItem(int i) {
        return mTags.get(i);
    }



    @Override
    public long getItemId(int i) {
        return mTags.indexOf(getItem(i));
    }

    private class ViewHolder {
        TextView tagName;
        TextView tagProductCount;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TagAdapter.ViewHolder holder = null;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = inflater.inflate(R.layout.tag_list_item, null);
        }

        holder = new TagAdapter.ViewHolder();
        holder.tagName = view.findViewById(R.id.tagName);
        holder.tagProductCount = view.findViewById(R.id.tagProductCount);

        Tag row_tag = mTags.get(i);
        holder.tagName.setText(row_tag.getmTagName());
        holder.tagProductCount.setText("Products with this Tag: " +row_tag.getProductCount().toString());

        return view;
    }
}
