package zp.com.zpdbdemo.db.sqlite.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class ZpSqliteUtil {

    private ZpSqliteHelper helper;
    private SQLiteDatabase db;
    private static Object lock = new Object();
    private static ZpSqliteUtil instance;

    public static ZpSqliteUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ZpSqliteUtil(context);
                }
            }
        }
        return instance;
    }

    private ZpSqliteUtil(Context context) {
        if (helper == null) {
            helper = new ZpSqliteHelper(context,
                    ZpSqliteConstance.ZP_DB_NAME,
                    ZpSqliteConstance.ZP_DB_VERSION);
        }
    }

    public SQLiteDatabase open() {
        if (db == null) {
            db = helper.getWritableDatabase();
        }
        return db;
    }

    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    public void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    public void destroy() {
        if (db != null) {
            db.close();
            db = null;
        }
    }



}
