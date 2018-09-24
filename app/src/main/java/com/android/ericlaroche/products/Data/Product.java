package com.android.ericlaroche.products.Data;

import java.io.Serializable;

public class Product implements Serializable{
    String mProductTitle = "";
    String mTag = "";
    String mProductImageUrl = "";
    Integer mQuantity = null;

    public Product(String title, String tags, String imgUrl, Integer quantity) {
        this.mProductTitle = title;
        this.mTag = tags;
        this.mProductImageUrl = imgUrl;
        this.mQuantity = quantity;
    }

    public Integer getmQuantity() {return mQuantity; }

    public String getmProductTitle() {
        return mProductTitle;
    }

    public void setmProductTitle(String mProductTitle) {
        this.mProductTitle = mProductTitle;
    }

    public String getmTag() {
        return mTag;
    }

    public void setmTag(String mTag) {
        this.mTag = mTag;
    }

    public String getmProductImageUrl() {
        return mProductImageUrl;
    }

    public void setmProductImageUrl(String mProductImageUrl) {
        this.mProductImageUrl = mProductImageUrl;
    }
}
