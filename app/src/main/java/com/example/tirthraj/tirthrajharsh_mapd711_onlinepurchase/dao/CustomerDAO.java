package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomerDAO extends DbHelper {


    public CustomerDAO(Context context) {
        super(context);
    }

    public void insert(Customer customer) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("userName", customer.getUserName());
        data.put("password", customer.getPassword());
        data.put("firstName", customer.getFirstName());
        data.put("lastName", customer.getLastName());
        data.put("address", customer.getAddress());
        data.put("city", customer.getCity());
        data.put("postalCode", customer.getPostalCode());
        db.insert("Customers", null, data);
    }

    public Customer login(String userName, String password) {
        List<Customer> customers = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Customers c " +
                "WHERE c.userName = ? AND c.password = ? ";

        Cursor c = db.rawQuery(sql, new String[]{userName, password});

        while (c.moveToNext()) {
            Customer customer = new Customer();
            customer.setCustomerId(c.getLong(c.getColumnIndex("customerId")));
            customer.setUserName(c.getString(c.getColumnIndex("userName")));
            customer.setFirstName(c.getString(c.getColumnIndex("firstName")));
            customer.setLastName(c.getString(c.getColumnIndex("lastName")));
            customer.setAddress(c.getString(c.getColumnIndex("address")));
            customer.setCity(c.getString(c.getColumnIndex("city")));
            customer.setPostalCode(c.getString(c.getColumnIndex("postalCode")));
            customers.add(customer);
        }
        c.close();
        if (!customers.isEmpty()) return customers.get(0);
        return null;
    }

    public Collection<Customer> findAll() {
        Collection<Customer> customers = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Customers c ";

        Cursor c = db.rawQuery(sql, null);

        Customer customer = null;
        while (c.moveToNext()) {
            customer = new Customer();
            customer.setCustomerId(c.getLong(c.getColumnIndex("customerId")));
            customer.setUserName(c.getString(c.getColumnIndex("userName")));
            customer.setFirstName(c.getString(c.getColumnIndex("firstName")));
            customer.setLastName(c.getString(c.getColumnIndex("lastName")));
            customer.setAddress(c.getString(c.getColumnIndex("address")));
            customer.setCity(c.getString(c.getColumnIndex("city")));
            customer.setPostalCode(c.getString(c.getColumnIndex("postalCode")));
            customers.add(customer);
        }
        c.close();
        return customers;
    }

    public Customer findById(Long customerId) {
        List<Customer> customers = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Customers c " +
                "WHERE c.customerId = ?";

        Cursor c = db.rawQuery(sql, new String[]{customerId.toString()});

        while (c.moveToNext()) {
            Customer customer = new Customer();
            customer.setCustomerId(c.getLong(c.getColumnIndex("customerId")));
            customer.setUserName(c.getString(c.getColumnIndex("userName")));
            customer.setFirstName(c.getString(c.getColumnIndex("firstName")));
            customer.setLastName(c.getString(c.getColumnIndex("lastName")));
            customer.setAddress(c.getString(c.getColumnIndex("address")));
            customer.setCity(c.getString(c.getColumnIndex("city")));
            customer.setPostalCode(c.getString(c.getColumnIndex("postalCode")));
            customers.add(customer);
        }
        c.close();
        if (!customers.isEmpty()) return customers.get(0);
        return null;
    }
}

