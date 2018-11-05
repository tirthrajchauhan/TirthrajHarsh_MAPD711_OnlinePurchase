package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao.ShoesDAO;
import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Shoes;

import java.util.ArrayList;
import java.util.List;

public class ShoesListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes_list);
        listView = findViewById(R.id.shoes_list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Shoes shoes = (Shoes) listView.getItemAtPosition(position);
                Intent intent = new Intent(ShoesListActivity.this, NewShoesActivity.class);
                intent.putExtra("shoe", shoes);
                startActivity(intent);
            }
        });
        registerForContextMenu(listView);
    }
    private void loadShoesList() {
        ShoesDAO shoesDAO = new ShoesDAO(this);
        List<Shoes> shoes = new ArrayList<>(shoesDAO.findAll());
        shoesDAO.close();
        ArrayAdapter<Shoes> adapter;
        adapter = new ArrayAdapter<Shoes>(this, android.R.layout.simple_list_item_1, shoes);
        listView.setAdapter(adapter);
    }


    public void addShoes(View view) {
        Intent intent = new Intent(ShoesListActivity.this, NewShoesActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadShoesList();
    }
}
