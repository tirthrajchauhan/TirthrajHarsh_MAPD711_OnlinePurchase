package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

  }
}
