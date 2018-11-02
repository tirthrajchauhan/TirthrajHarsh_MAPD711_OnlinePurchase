package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Shoes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ShoesDAO extends DbHelper {


    public ShoesDAO(Context context) {
        super(context);
    }

    public Shoes insert(Shoes shoes) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("itemName", shoes.getItemName());
        data.put("category", shoes.getCategory());
        data.put("shoeSize", shoes.getShoeSize());
        data.put("price", shoes.getPrice());
        shoes.setItemId(db.insert("Shoes", null, data));
        if (shoes.getItemId() != -1) {
            return shoes;
        } else {
            return null;
        }

    }

    public void update(Shoes shoes) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("itemName", shoes.getItemName());
        data.put("category", shoes.getCategory());
        data.put("shoeSize", shoes.getShoeSize());
        data.put("price", shoes.getPrice());
        db.update("Shoes", data, "itemId = ?", new String[]{shoes.getItemId().toString()});
    }

    public void remove(Long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Shoes", "itemId = ?", new String[]{id.toString()});
    }

    public Collection<Shoes> findAll() {
        Collection<Shoes> shoes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Shoes c ";

        Cursor c = db.rawQuery(sql, null);

        Shoes shoe = null;
        while (c.moveToNext()) {
            shoe = new Shoes();
            shoe.setPrice(c.getDouble(c.getColumnIndex("price")));
            shoe.setItemName(c.getString(c.getColumnIndex("itemName")));
            shoe.setCategory(c.getString(c.getColumnIndex("category")));
            shoe.setItemId(c.getLong(c.getColumnIndex("itemId")));
            shoe.setShoeSize(c.getDouble(c.getColumnIndex("shoeSize")));
            shoes.add(shoe);
        }
        c.close();
        return shoes;
    }

    public Shoes findById(Long shoesId) {
        List<Shoes> shoesList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Shoes c " +
                "WHERE c.itemId = ?";

        Cursor c = db.rawQuery(sql, new String[]{shoesId.toString()});

        loadShoesByCursor(shoesList, c);
        c.close();
        if (!shoesList.isEmpty()) return shoesList.get(0);
        return null;
    }

    public List<Shoes> findByShoesCategory(String type) {
        List<Shoes> shoesList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Shoes c " +
                "WHERE c.category = ?";

        Cursor c = db.rawQuery(sql, new String[]{type});

        loadShoesByCursor(shoesList, c);
        c.close();
        if (!shoesList.isEmpty()) return shoesList;
        return null;

    }

    private void loadShoesByCursor(List<Shoes> shoesList, Cursor c) {
        while (c.moveToNext()) {
            Shoes shoes = new Shoes();
            shoes.setShoeSize(c.getDouble(c.getColumnIndex("shoeSize")));
            shoes.setItemId(c.getLong(c.getColumnIndex("itemId")));
            shoes.setCategory(c.getString(c.getColumnIndex("category")));
            shoes.setItemName(c.getString(c.getColumnIndex("itemName")));
            shoes.setPrice(c.getDouble(c.getColumnIndex("price")));
            shoesList.add(shoes);
        }
    }

    public List<Shoes> findByShoesCategoryAndSize(String type, String size) {
        List<Shoes> shoesList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Shoes c " +
                "WHERE c.category = ? and c.shoeSize = ? ";

        Cursor c = db.rawQuery(sql, new String[]{type, size});

        loadShoesByCursor(shoesList, c);
        c.close();
        if (!shoesList.isEmpty()) return shoesList;
        return null;

    }
}
