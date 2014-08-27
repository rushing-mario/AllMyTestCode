package com.okry.amt.storage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.okry.amt.log.L;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Created by marui on 14-4-1.
 */
public class AMTDbHelper extends SQLiteOpenHelper{

    private static final String LOG_TAG = "AMTDB_";
    private static final String LOG_UPDATE = LOG_TAG + "Upgrade database from [%d] to [%d]";

    public final static String DB_NAME = "amt";
    public final static int DB_VERSION = 1;

    public AMTDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();
        columns.put("column1", "TEXT");
        columns.put("column3", "TEXT");
        columns.put("column2", "INTEGER");
        columns.put("column4", "INTEGER");
        String createSQL = makeCreateTableSQL("amt_test_table", columns);
        L.d(createSQL);
        sqLiteDatabase.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        L.i(LOG_UPDATE, oldVersion , newVersion);
    }

    /**
     *
     * 创建Table语句的方便构造，使用LinkedHashMap保证插入顺序
     * @param tableName
     * @param keyNameAndType
     * @return
     */
    private String makeCreateTableSQL(String tableName, LinkedHashMap<String, String> keyNameAndType) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + tableName + " (");
        Iterator<String> it = keyNameAndType.keySet().iterator();
        while(it.hasNext()) {
            String keyName = it.next();
            String type = keyNameAndType.get(keyName);
            sb.append(keyName).append(" ").append(type).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(");");
        return sb.toString();
    }
}
