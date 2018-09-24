package com.android.ericlaroche.products.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.ericlaroche.products.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button productBtn = null;
    private Button productTagBtn = null;
    private Context mContext = null;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        productBtn = (Button) findViewById(R.id.productBtn);
        productTagBtn = (Button) findViewById(R.id.productTagBtn);

        productBtn.setOnClickListener(this);
        productTagBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.productBtn:
                intent = new Intent(mContext, ProductListActivity.class);
                startActivity(intent);
                break;

            case R.id.productTagBtn:
                intent = new Intent(mContext, ProductTagActivity.class);
                startActivity(intent);
                break;
        }
    }
}
