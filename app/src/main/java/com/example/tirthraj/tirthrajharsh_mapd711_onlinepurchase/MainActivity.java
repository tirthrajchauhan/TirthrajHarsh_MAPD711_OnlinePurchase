package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao.CsrDAO;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao.CustomerDAO;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Csr;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Customer;

public class MainActivity extends AppCompatActivity {

    private CsrDAO csrDAO = new CsrDAO(this);
    private CustomerDAO customerDAO = new CustomerDAO(this);
        private RadioGroup rg;
        private RadioButton rb1,rb2;
        private int loginType;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = findViewById(R.id.radioGroup);
        loginType = rg.getCheckedRadioButtonId();
        }



  public void login(View v)  {

          final EditText etUsername= (EditText) findViewById(R.id.editTextUsername);
          final EditText etPwd = (EditText) findViewById(R.id.editTextPassword);
          String fullName = etUsername.getText().toString();
          String password = etPwd.getText().toString();
          SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
          SharedPreferences.Editor editor = sharedPref.edit();

          RadioButton rbSelected = findViewById(loginType);

      if (rbSelected.getText().toString().contains("Customer")) {
          Customer customer = customerDAO.login(fullName, password);
          if (customer != null && customer.getCustomerId() != null) {
              editor.putString("username", customer.getFirstName());
              editor.putString("userid", customer.getCustomerId().toString());
              editor.apply();
              //instantiate intent class
              Intent intent = new Intent(MainActivity.this, userWelcomeActivity.class);
              startActivity(intent);
          } else {
              Toast.makeText(MainActivity.this, "Invalid login or password", Toast.LENGTH_LONG).show();
              etUsername.setText("");
              etPwd.setText("");
              etUsername.requestFocus();
          }

      } else if (rbSelected.getText().toString().contains("CSR")) {
          Csr csr = csrDAO.login(fullName, password);
          if (csr != null && csr.getEmployeeId() != null) {
              editor.putString("username", csr.getFirstName());
              editor.putString("userid", csr.getEmployeeId().toString());
              editor.apply();
              //instantiate intent class
              Intent intent = new Intent(MainActivity.this, CSRwelcomeActivity.class);
              startActivity(intent);
          } else {
              Toast.makeText(MainActivity.this, "Invalid login or password", Toast.LENGTH_LONG).show();
              etUsername.setText("");
              etPwd.setText("");
              etUsername.requestFocus();
          }
      }

  }

    public void rbClick(View v) {
        loginType = rg.getCheckedRadioButtonId();
    }

    public void signUpCustomer(View view) {
        Intent intent = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    public void signUpCsr(View view) {
        Intent intent = new Intent(MainActivity.this, CSRSignupActivity.class);
        startActivity(intent);
    }
}
