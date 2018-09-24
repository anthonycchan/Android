package com.example.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SampleScrollView extends AppCompatActivity {

    private ArrayList<String> strArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_scroll_view);

        // Set content to the scrollview
        strArr = new ArrayList<String>();
        for(int i =0; i < 2; i++)  {
            strArr.add("Row: " + i);
        }

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout1);

        for(int i =0; i < strArr.size(); i++ ) {
            TextView tv2=  new TextView(this);
            tv2.setText("initial " + i);
            linearLayout.addView(tv2);
        }
    }

    public void addScrollViewElement(View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout1);
        TextView tv3=  new TextView(getApplicationContext());
        tv3.setText("miw");
        linearLayout.addView(tv3);
    }
}
