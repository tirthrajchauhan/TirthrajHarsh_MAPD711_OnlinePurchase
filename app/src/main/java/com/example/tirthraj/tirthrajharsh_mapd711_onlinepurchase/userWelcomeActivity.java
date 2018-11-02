package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class userWelcomeActivity extends AppCompatActivity {
    private SharedPreferences userPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome);

        userPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String fullName = userPref.getString("username", "");

        TextView tfUserName = findViewById(R.id.textViewUsername);
        tfUserName.setText(fullName);
    }

    public void clickOrders(View view) {
        Intent intent = new Intent(userWelcomeActivity.this, CustomerOrderActivity.class);
        startActivity(intent);
    }

    public void clickNewOrder(View view) {
        Intent intent = new Intent(userWelcomeActivity.this, PlaceOrderActivity.class);
        startActivity(intent);
}
}
