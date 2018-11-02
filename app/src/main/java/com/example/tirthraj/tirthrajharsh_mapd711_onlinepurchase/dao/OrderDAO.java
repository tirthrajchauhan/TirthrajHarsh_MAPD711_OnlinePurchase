package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Order;

import java.util.ArrayList;
import java.util.Collection;


public class OrderDAO extends DbHelper {


    public OrderDAO(Context context) {
        super(context);
    }

    public Collection<Order> findAll() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM Orders ";

        Cursor c = db.rawQuery(sql, null);

        Collection<Order> orders = getOrdersByCursor(c);
        c.close();
        return orders;
    }

    public Collection<Order> findByCustomerId(Long customerId) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM Orders o " +
                " WHERE o.customerId = ? ";

        Cursor c = db.rawQuery(sql, new String[]{customerId.toString()});
        Collection<Order> orders = getOrdersByCursor(c);
        c.close();
        return orders;
    }

    public void insert(Order order) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("customerId", order.getCustomerId());
        data.put("itemId", order.getItemId());
        data.put("orderDate", order.getOrderDate());
        data.put("quantity", order.getQuantity());
        data.put("status", order.getStatus());
        db.insert("Orders", null, data);
    }

    public void update(Order order) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("customerId", order.getCustomerId());
        data.put("itemId", order.getItemId());
        data.put("orderDate", order.getOrderDate());
        data.put("quantity", order.getQuantity());
        data.put("status", order.getStatus());
        db.update("Orders", data, "orderId = ?", new String[]{order.getOrderId().toString()});
    }

    private Collection<Order> getOrdersByCursor(Cursor c) {
        Collection<Order> orders = new ArrayList<>();
        while (c.moveToNext()) {
            Order order = new Order();
            order.setOrderId(c.getLong(c.getColumnIndex("orderId")));
            order.setCustomerId(c.getLong(c.getColumnIndex("customerId")));
            order.setItemId(c.getLong(c.getColumnIndex("itemId")));
            order.setOrderDate(c.getString(c.getColumnIndex("orderDate")));
            order.setQuantity(c.getInt(c.getColumnIndex("quantity")));
            order.setStatus(c.getString(c.getColumnIndex("status")));
            orders.add(order);
        }

        return orders;
    }
}
