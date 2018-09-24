package com.android.ericlaroche.products.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Tag {
    private String mTagName = "";
    private Integer productCount = null;

    public Tag(String tagName, Integer count) {
        mTagName = tagName;
        productCount = count;
    }

    public String getmTagName() {
        return mTagName;
    }

    public void setmTagName(String mTagName) {
        this.mTagName = mTagName;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
