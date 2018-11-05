package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao.CustomerDAO;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao.ShoesDAO;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Customer;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Order;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Shoes;

public class editOrderActivity extends AppCompatActivity {

    private Order order = new Order();

    private EditText customerName;
    private EditText itemName;
    private EditText orderDate;
    private EditText quantity;
    private Spinner spinnerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);

        customerName = findViewById(R.id.txtCustomerName);
        itemName = findViewById(R.id.txtItemName);
        orderDate = findViewById(R.id.txtOrderDate);
        quantity = findViewById(R.id.txtQuantity);
        spinnerStatus = findViewById(R.id.spinnerStatusEditOrder);

        // Create a adapter to the status type spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter);


        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra("order");
        Customer customer = new Customer();
        CustomerDAO customerDAO = new CustomerDAO(this);
        customer = customerDAO.findById(order.getCustomerId());
        customerDAO.close();
        customerName.setText(customer.getFirstName() + " " + customer.getLastName());

        Shoes shoe = new Shoes();
        ShoesDAO shoeDAO = new ShoesDAO(this);
        shoe = shoeDAO.findById(order.getItemId());
        shoeDAO.close();
        itemName.setText(shoe.getItemName());

        orderDate.setText(order.getOrderDate());
        quantity.setText(order.getQuantity()+"");
        spinnerStatus.setPrompt(order.getStatus());
    }
}
