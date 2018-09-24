package com.example.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SampleListView extends AppCompatActivity {

    private ArrayList<String> strArr;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list_view);

        // Set contents to the listview
        ListView lv = (ListView) findViewById(R.id.listView1);

        strArr = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, strArr);
        for(int i =0; i < 2; i++)  {
            strArr.add("Row: " + i);
        }

        lv.setAdapter(adapter);


        // Set listener on the textview
        Button runTestButton = (Button) findViewById(R.id.button2);
        runTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strArr.add("meow");
                adapter.notifyDataSetChanged();
        }
        });
    }
}
