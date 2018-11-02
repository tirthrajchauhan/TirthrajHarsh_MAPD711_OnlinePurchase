package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CSRwelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csrwelcome);
        TextView tfUserName = (TextView) findViewById(R.id.textViewCsrName);
        SharedPreferences userPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String fullName = userPref.getString("username", "");
        fullName.toUpperCase();
        tfUserName.setText(fullName);
    }

    public void clickShoes(View view) {
        Intent intent = new Intent(CSRwelcomeActivity.this, NewShoesActivity.class);
        startActivity(intent);
    }

    public void clickOrders(View view) {
        Intent intent = new Intent(CSRwelcomeActivity.this, OrdersActivity.class);
        startActivity(intent);
    }
}
