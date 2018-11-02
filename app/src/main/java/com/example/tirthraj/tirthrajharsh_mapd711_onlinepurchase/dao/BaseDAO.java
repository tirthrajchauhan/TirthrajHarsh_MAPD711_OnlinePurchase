package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.dao;

import android.content.Context;

public class BaseDAO extends DbHelper {

    public Context context;

    public BaseDAO(Context context) {
        super(context);
        this.context = context;
    }


}
