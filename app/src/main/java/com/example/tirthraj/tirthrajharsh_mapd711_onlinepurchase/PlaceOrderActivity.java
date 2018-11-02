package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao.CsrDAO;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao.CustomerDAO;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao.OrderDAO;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao.ShoesDAO;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Order;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Shoes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity {

    private SharedPreferences userPref;
    private CsrDAO csrDAO = new CsrDAO(this);
    private CustomerDAO customerDAO = new CustomerDAO(this);
    private OrderDAO orderDAO = new OrderDAO(this);
    private ShoesDAO shoesDAO = new ShoesDAO(this);

    private Spinner spinnerShoesType;
    private Spinner spinnerShoesSize;
    private ListView listViewShoes;
    private EditText quantity;

    private String shoeType;

    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);


        userPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String fullName = userPref.getString("username", "");

        spinnerShoesType = findViewById(R.id.shoeTypespinner);
        spinnerShoesSize = findViewById(R.id.shoeSizeSpinner);
        listViewShoes = findViewById(R.id.ListViewavailableShoes);
        quantity = findViewById(R.id.editTextQuantity);
        // Create a adapter to the status type spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.shoes_arrays, android.R.layout.simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerShoesType.setAdapter(adapter);

        Intent intent = getIntent();
        order = new Order();
        if (intent.getSerializableExtra("order") != null) {
            order = (Order) intent.getSerializableExtra("order");
        }
        if (order != null && order.getItemId() != null) {
            Shoes shoe = shoesDAO.findById(order.getItemId());
            spinnerShoesSize.setPrompt(shoe.getShoeSize().toString());
            spinnerShoesType.setPrompt(shoe.getCategory());
            quantity.setText(order.getQuantity()+"");
        } else {
            quantity.setText("1");
        }

        spinnerShoesType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shoeType = spinnerShoesType.getItemAtPosition(position).toString();
                List<Shoes> shoesSizeList = shoesDAO.findByShoesCategory(shoeType);
                shoesDAO.close();
                if (shoesSizeList != null && !shoesSizeList.isEmpty()) {
                    loadShoesSizes(shoesSizeList);
                    spinnerShoesSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            List<Shoes> shoesList = shoesDAO.findByShoesCategoryAndSize(shoeType,
                                    spinnerShoesSize.getItemAtPosition(position).toString());
                            loadShoesList(shoesList);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            loadShoesSizes(new ArrayList<Shoes>());
                            loadShoesList(new ArrayList<Shoes>());
                        }
                    });
                } else {
                    Toast.makeText(PlaceOrderActivity.this, "Any Shoe of this type was found", Toast.LENGTH_LONG).show();
                    loadShoesSizes(new ArrayList<Shoes>());
                    loadShoesList(new ArrayList<Shoes>());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadShoesSizes(List<Shoes> shoesSizeList) {
        List<Double> sizes = new ArrayList<>();
        for (Shoes shoe : shoesSizeList) {
            if (!sizes.contains(shoe.getShoeSize())) {
                sizes.add(shoe.getShoeSize());
            }
        }

        ArrayAdapter<Double> adapter;
        adapter = new ArrayAdapter<Double>(this, android.R.layout.simple_list_item_1, sizes);
        spinnerShoesSize.setAdapter(adapter);
    }

    private void loadShoesList(List<Shoes> shoesList) {
        ArrayAdapter<Shoes> adapter;
        adapter = new ArrayAdapter<Shoes>(this, android.R.layout.simple_list_item_1, shoesList);
        listViewShoes.setAdapter(adapter);

        listViewShoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (quantity.getText() != null && !quantity.getText().toString().isEmpty() &&
                        Integer.parseInt(quantity.getText().toString()) > 0) {
                    Shoes shoes = (Shoes) listViewShoes.getItemAtPosition(position);
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdf.format(new Date());
                    order.setOrderDate(date);
                    order.setItemId(shoes.getItemId());
                    order.setCustomerId(Long.parseLong(userPref.getString("userid", "")));
                    order.setQuantity(Integer.parseInt(quantity.getText().toString()));
                    order.setStatus("In-process");
                    if (order != null && order.getOrderId() != null) {
                        orderDAO.update(order);
                    } else {
                        orderDAO.insert(order);
                    }
                    Intent intent = new Intent(PlaceOrderActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(PlaceOrderActivity.this, "The quantity must be more equal or more than 1", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
