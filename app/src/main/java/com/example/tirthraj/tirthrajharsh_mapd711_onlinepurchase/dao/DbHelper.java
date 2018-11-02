package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, "OnlinePurchase", null, 10);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCsr = "CREATE TABLE Csr (" +
                "           employeeId INTEGER PRIMARY KEY, " +
                "           userName TEXT NOT NULL, " +
                "           password TEXT NOT NULL, " +
                "           firstName TEXT NOT NULL, " +
                "           lastName TEXT NOT NULL); ";
        db.execSQL(sqlCsr);
        String sqlCustomers = "CREATE TABLE Customers (" +
                "                 customerId INTEGER PRIMARY KEY, " +
                "                 userName TEXT NOT NULL, " +
                "                 password TEXT NOT NULL, " +
                "                 firstName TEXT NOT NULL, " +
                "                 lastName TEXT NOT NULL, " +
                "                 address TEXT NOT NULL, " +
                "                 city TEXT NOT NULL, " +
                "                 postalCode TEXT NOT NULL);";
        db.execSQL(sqlCustomers);
        String sqlOrders = "CREATE TABLE Orders (" +
                "                 orderId INTEGER PRIMARY KEY, " +
                "                 customerId INTEGER NOT NULL, " +
                "                 itemId INTEGER NOT NULL, " +
                "                 orderDate TEXT NOT NULL, " +
                "                 quantity INTEGER NOT NULL, " +
                "                status TEXT NOT NULL); ";
        db.execSQL(sqlOrders);
        String sqlShoes = "CREATE TABLE Shoes (" +
                "                 itemId INTEGER PRIMARY KEY, " +
                "                 itemName TEXT NOT NULL, " +
                "                 category TEXT NOT NULL, " +
                "                 shoeSize DOUBLE NOT NULL, " +
                "                 price DOUBLE NOT NULL);";
        db.execSQL(sqlShoes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlCsr = "DROP TABLE IF EXISTS Csr; ";
        db.execSQL(sqlCsr);
        String sqlCustomer = "DROP TABLE IF EXISTS Customers; ";
        db.execSQL(sqlCustomer);
        String sqlOrder = "DROP TABLE IF EXISTS Orders; ";
        db.execSQL(sqlOrder);
        String sqlShoes = "DROP TABLE IF EXISTS Shoes; ";
        db.execSQL(sqlShoes);
        onCreate(db);
    }
}
