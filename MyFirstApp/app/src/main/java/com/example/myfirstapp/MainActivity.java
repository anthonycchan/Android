package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void runListViewTest(View view) {
        Intent intent = new Intent(this, SampleListView.class);
        startActivity(intent);
    }

    public void runScollViewTest(View view) {
        Intent intent = new Intent(this, SampleScrollView.class);
        startActivity(intent);
    }

    public void runReadWriteFileView(View view) {
        Intent intent = new Intent(this, ReadWriteFileView.class);
        startActivity(intent);
    }
}
