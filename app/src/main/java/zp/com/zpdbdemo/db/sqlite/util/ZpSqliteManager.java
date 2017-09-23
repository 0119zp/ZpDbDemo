package zp.com.zpdbdemo.db.sqlite.util;

import android.content.Context;
import android.text.TextUtils;
import java.util.List;
import zp.com.zpdbdemo.db.sqlite.entity.ZpSqlStudent;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class ZpSqliteManager {

    private ZpSqliteDao dao;

    private static ZpSqliteManager manager = null;

    public static ZpSqliteManager getInstance(Context context) {
        if (manager == null) {
            manager = new ZpSqliteManager(context);
        }
        return manager;
    }

    public ZpSqliteManager(Context context) {
        this.dao = new ZpSqliteDao(context);
    }

    /**
     * 添加数据
     */
    public void insert(List<ZpSqlStudent> list) {
        if(null==list||list.size()<=0){
            return;
        }
        dao.insert(list);
    }

    /**
     * 删除整张表
     */
    public void delete() {
        dao.delete();
    }

    /*****
     * 修改数据
     */
    public void updateByMId(ZpSqlStudent student) {
        if(null==student){
            return;
        }
        dao.updateByMId(student);
    }
    /**
     * 查询所有数据
     */
    public List<ZpSqlStudent> search() {
        return dao.search();
    }

    /**
     *通过名称查询
     * @return
     */
    public List<ZpSqlStudent> searchByString(String searchStr) {
        if(TextUtils.isEmpty(searchStr)){
            return null;
        }
        return dao.searchByString(searchStr);
    }


}
