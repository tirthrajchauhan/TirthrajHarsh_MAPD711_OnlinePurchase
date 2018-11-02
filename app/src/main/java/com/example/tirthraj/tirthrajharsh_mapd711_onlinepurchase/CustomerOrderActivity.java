package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao.OrderDAO;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Order;

import java.util.ArrayList;
import java.util.List;


public class CustomerOrderActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);
        SharedPreferences userPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        Long customerId = Long.parseLong(userPref.getString("userid", ""));
        OrderDAO ordersDAO = new OrderDAO(this);
        List<Order> orders = new ArrayList<>(ordersDAO.findByCustomerId(customerId));

        listView = findViewById(R.id.customer_orders_list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order order = (Order) listView.getItemAtPosition(position);
                if (!order.getStatus().equals("Delivered")) {
                    Intent intent = new Intent(CustomerOrderActivity.this, PlaceOrderActivity.class);
                    intent.putExtra("order", order);
                    startActivity(intent);
                }
            }
        });

        ArrayAdapter<Order> adapter;
        adapter = new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, orders);
        listView.setAdapter(adapter);
    }
}
