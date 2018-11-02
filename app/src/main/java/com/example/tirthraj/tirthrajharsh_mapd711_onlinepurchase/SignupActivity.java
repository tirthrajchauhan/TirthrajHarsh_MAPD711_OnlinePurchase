package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao.CustomerDAO;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Customer;

public class SignupActivity extends AppCompatActivity {


    private EditText txtUsername;
    private EditText txtPostalCode;
    private EditText txtCity;
    private EditText txtAddress;
    private EditText txtPassword;
    private EditText txtLastName;
    private EditText txtFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtUsername = findViewById(R.id.editTextUsername);
        txtPostalCode = findViewById(R.id.editTextPostalcode);
        txtCity = findViewById(R.id.editTextCity);
        txtAddress = findViewById(R.id.editTextAddress);
        txtPassword = findViewById(R.id.editTextPassword);
        txtLastName = findViewById(R.id.editTextLastname);
        txtFirstName = findViewById(R.id.editTextFirstname);

    }
    public void signUp(View view) {
        boolean formValid = true;
        CustomerDAO customerDAO = new CustomerDAO(this);
        Customer customer = new Customer();
        // Validate all the required fields in the form
        if (TextUtils.isEmpty(txtUsername.getText().toString().trim())) {
            txtUsername.setError("User name is required!");
            formValid = false;
        }
        if (TextUtils.isEmpty(txtAddress.getText().toString().trim())) {
            txtAddress.setError("Address is required!");
            formValid = false;
        }
        if (TextUtils.isEmpty(txtPostalCode.getText().toString().trim())) {
            txtPostalCode.setError("Postal code is required!");
            formValid = false;
        }
        if (TextUtils.isEmpty(txtCity.getText().toString().trim())) {
            txtCity.setError("City is required!");
            formValid = false;
        }
        if (TextUtils.isEmpty(txtAddress.getText().toString().trim())) {
            txtAddress.setError("Address number is required!");
            formValid = false;
        }
        if (TextUtils.isEmpty(txtPassword.getText().toString().trim())) {
            txtPassword.setError("Password is required!");
            formValid = false;
        }
        if (TextUtils.isEmpty(txtLastName.getText().toString().trim())) {
            txtLastName.setError("Last name is required!");
            formValid = false;
        }
        if (TextUtils.isEmpty(txtFirstName.getText().toString().trim())) {
            txtFirstName.setError("First name is required!");
            formValid = false;
        }

        if (formValid) {
            customer.setUserName(txtUsername.getText().toString());
            customer.setPostalCode(txtPostalCode.getText().toString());
            customer.setCity(txtCity.getText().toString());
            customer.setAddress(txtAddress.getText().toString());
            customer.setPassword(txtPassword.getText().toString());
            customer.setLastName(txtLastName.getText().toString());
            customer.setFirstName(txtFirstName.getText().toString());
            customerDAO.insert(customer);

            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);
        }
}
}
