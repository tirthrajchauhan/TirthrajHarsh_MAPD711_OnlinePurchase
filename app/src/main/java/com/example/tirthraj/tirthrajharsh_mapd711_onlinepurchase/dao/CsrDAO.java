package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model.Csr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CsrDAO extends DbHelper {


    public CsrDAO(Context context) {
        super(context);
    }

    public void insert(Csr csr) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("userName", csr.getUserName());
        data.put("password", csr.getPassword());
        data.put("firstName", csr.getFirstName());
        data.put("lastName", csr.getLastName());
        db.insert("Csr", null, data);
        db.close();
    }


    public Csr login(String userName, String password) {
        List<Csr> csrs = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Csr c " +
                "WHERE c.userName = ? AND c.password = ? ";

        Cursor c = db.rawQuery(sql, new String[]{userName, password});

        while (c.moveToNext()) {
            Csr csr = new Csr();
            csr.setEmployeeId(c.getLong(c.getColumnIndex("employeeId")));
            csr.setUserName(c.getString(c.getColumnIndex("userName")));
            csr.setFirstName(c.getString(c.getColumnIndex("firstName")));
            csr.setLastName(c.getString(c.getColumnIndex("lastName")));
            csrs.add(csr);
        }
        c.close();
        if (!csrs.isEmpty()) return csrs.get(0);
        return null;
    }

    public Collection<Csr> findAll() {
        Collection<Csr> csrs = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Csr c ";

        Cursor c = db.rawQuery(sql, null);

        Csr csr = null;
        while (c.moveToNext()) {
            csr = new Csr();
            csr.setEmployeeId(c.getLong(c.getColumnIndex("employeeId")));
            csr.setUserName(c.getString(c.getColumnIndex("userName")));
            csr.setFirstName(c.getString(c.getColumnIndex("firstName")));
            csr.setLastName(c.getString(c.getColumnIndex("lastName")));
            csrs.add(csr);
        }
        c.close();
        return csrs;
    }
}
