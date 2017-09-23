package zp.com.zpdbdemo.db.sqlite.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public final class ZpSqliteHelper extends SQLiteOpenHelper {

    public ZpSqliteHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "";

        sql = "CREATE TABLE " +
                ZpSqliteConstance.ZP_TABLE_NAME +
                "("+
                "_id TEXT ," +
                "age TEXT ," +
                "name TEXT ," +
                "address TEXT" +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
