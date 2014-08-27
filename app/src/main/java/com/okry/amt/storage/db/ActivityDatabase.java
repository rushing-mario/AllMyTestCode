package com.okry.amt.storage.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.okry.amt.allbase.BaseActivity;

/**
 * Created by marui on 14-4-1.
 */
public class ActivityDatabase extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AMTDbHelper dbHelper = new AMTDbHelper(this);
        dbHelper.getDatabaseName();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.close();
    }



}
