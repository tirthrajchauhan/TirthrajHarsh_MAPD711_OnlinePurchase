package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao.ShoesDAO;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Shoes;

public class NewShoesActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    private Button addShoesButton;
    private EditText shoesName;
    private EditText shoesSize;
    private EditText shoesPrice;

    Shoes shoes = new Shoes();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shoes);

        categorySpinner = (Spinner) findViewById(R.id.spinnerShoetype);

        shoesName = findViewById(R.id.editTextShoeName);
        shoesSize = findViewById(R.id.editTextSize);
        shoesPrice = findViewById(R.id.editTextPrice);
        categorySpinner = findViewById(R.id.spinnerShoetype);

        Intent intent = getIntent();
        shoes = (Shoes) intent.getSerializableExtra("shoe");
        if (shoes != null) {
            shoesName.setText(shoes.getItemName());
            shoesPrice.setText(shoes.getPrice() + "");
            shoesPrice.setText(shoes.getShoeSize() + "");
            categorySpinner.setPrompt(shoes.getCategory());
        } else {
            shoes = new Shoes();
        }
    }

    public void save(View view) {
        boolean formValid = true;
        ShoesDAO shoesDAO = new ShoesDAO(this);
        // Validate all the required fields in the form
        if (TextUtils.isEmpty(shoesName.getText().toString().trim())) {
            shoesName.setError("Name is required!");
            formValid = false;
        }
        if (TextUtils.isEmpty(shoesSize.getText().toString().trim())) {
            shoesSize.setError("Shoe size is required!");
            formValid = false;
        }
        if (TextUtils.isEmpty(shoesPrice.getText().toString().trim())) {
            shoesPrice.setError("Price is required!");
            formValid = false;
        }


        if (formValid) {
            shoes.setShoeSize(Double.parseDouble(shoesSize.getText().toString()));
            shoes.setCategory(categorySpinner.getSelectedItem().toString());
            shoes.setItemName(shoesName.getText().toString());
            shoes.setPrice(Double.parseDouble(shoesPrice.getText().toString()));

            if (shoes.getItemId() != null) {
                shoesDAO.update(shoes);
            } else {
                shoesDAO.insert(shoes);
            }

            Intent intent = new Intent(NewShoesActivity.this, SignupActivity.class);
            startActivity(intent);
        }



    }

    public void addListenerOnSpinnerItemSelection() {
        categorySpinner = (Spinner) findViewById(R.id.spinnerShoetype);
        //categorySpinner.setOnItemSelectedListener(new Custom());
    }
}
