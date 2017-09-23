package zp.com.zpdbdemo.db.sqlite.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import zp.com.zpdbdemo.db.sqlite.entity.ZpSqlStudent;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class ZpSqliteDao {

    private Context context;

    public ZpSqliteDao(Context context) {
        this.context = context;
    }

    /**
     * 插入数据
     * @param list
     */
    public void insert(List<ZpSqlStudent> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        SQLiteDatabase db = ZpSqliteUtil.getInstance(context).open();
        try {
            db.beginTransaction();
            for (ZpSqlStudent student : list) {
                db.insert(ZpSqliteConstance.ZP_TABLE_NAME, null, getContentValues(student));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
            ZpSqliteUtil.getInstance(context).close();
        }
    }

    /**
     * 删除整张表
     */
    public void delete() {
        SQLiteDatabase db = ZpSqliteUtil.getInstance(context).open();
        try {
            db.beginTransaction();
            db.delete(ZpSqliteConstance.ZP_TABLE_NAME, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
            ZpSqliteUtil.getInstance(context).close();
        }
    }

    /*****
     * 修改数据
     */
    public void updateByMId(ZpSqlStudent student) {
        if (null == student) {
            return;
        }
        SQLiteDatabase db = ZpSqliteUtil.getInstance(context).open();
        try {
            db.beginTransaction();
            db.update(ZpSqliteConstance.ZP_TABLE_NAME, getContentValues(student),
                    "_id = ?", new String[]{student.getId()});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
            ZpSqliteUtil.getInstance(context).close();
        }
    }

    /**
     * 查询所有数据
     */
    public List<ZpSqlStudent> search() {
        SQLiteDatabase db = ZpSqliteUtil.getInstance(context).open();
        List<ZpSqlStudent> list = new ArrayList<>();
        try {
            db.beginTransaction();
            Cursor cursor = db.rawQuery("select * from " + ZpSqliteConstance.ZP_TABLE_NAME, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    list.add(getModuleData(cursor));
                }
            }
            ZpSqliteUtil.getInstance(context).closeCursor(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
            ZpSqliteUtil.getInstance(context).close();
        }
        return list;
    }

    /**
     * 通过名称查询
     *
     * @return
     */
    public List<ZpSqlStudent> searchByString(String searchStr) {
        if (TextUtils.isEmpty(searchStr)) {
            return null;
        }
        SQLiteDatabase db = ZpSqliteUtil.getInstance(context).open();
        List<ZpSqlStudent> list = new ArrayList<>();
        try {
            db.beginTransaction();
            Cursor cursor = db.rawQuery(
                    "select * from " + ZpSqliteConstance.ZP_TABLE_NAME + " where name like ? ",
                    new String[]{"%" + searchStr + "%"});
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    list.add(getModuleData(cursor));
                }
            }
            ZpSqliteUtil.getInstance(context).closeCursor(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
            ZpSqliteUtil.getInstance(context).close();
        }
        return list;
    }

    /******
     * 插入数据
     * @return
     */
    private ContentValues getContentValues(ZpSqlStudent student) {
        ContentValues cValue = new ContentValues();
        String mId = "";//id
        if (!TextUtils.isEmpty(student.getId())) {
            mId = student.getId();
        }
        cValue.put("_id", mId);

        String name = "";//name
        if (!TextUtils.isEmpty(student.getName())) {
            name = student.getName();
        }
        cValue.put("name", name);

        String age = "";//age
        if (!TextUtils.isEmpty(student.getAge())) {
            age = student.getAge();
        }
        cValue.put("age", age);

        String address = "";//address
        if (!TextUtils.isEmpty(student.getAddress())) {
            address = student.getAddress();
        }
        cValue.put("address", address);

        return cValue;
    }

    /*****
     * 获取数据
     * @return
     */
    private ZpSqlStudent getModuleData(Cursor cursor) {
        ZpSqlStudent moduleData = new ZpSqlStudent();

        moduleData.setId(cursor.getString(0));//id
        moduleData.setAge(cursor.getString(1));//age
        moduleData.setName(cursor.getString(2)); //name
        moduleData.setAddress(cursor.getString(3)); //address
        return moduleData;
    }

}
