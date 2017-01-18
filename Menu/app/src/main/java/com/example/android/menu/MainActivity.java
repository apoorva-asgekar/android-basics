
package com.example.android.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void printToLogs(View view) {
        // Find first menu item TextView and print the text to the logs
        TextView menu1 = (TextView) findViewById(R.id.menu_item_1);
        Log.i("MainActivity.java", menu1.getText().toString());

        // Find second menu item TextView and print the text to the logs
        TextView menu2 = (TextView) findViewById(R.id.menu_item_2);
        Log.i("MainActivity.java", menu2.getText().toString());

        // Find third menu item TextView and print the text to the logs
        TextView menu3 = (TextView) findViewById(R.id.menu_item_3);
        Log.i("MainActivity.java", menu3.getText().toString());

    }
}