package zp.com.zpdbdemo.db.greendao.util;

import android.content.Context;
import android.util.Log;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import zp.com.zpdbdemo.db.greendao.dao.StudentDao;
import zp.com.zpdbdemo.db.greendao.entity.ZpStudent;

/**
 * 完成对某一张表的具体操作
 * Created by LGL on 2016/7/2.
 */
public class CommonUtils {

    //TAG
    private static final String TAG = CommonUtils.class.getSimpleName();

    private DaoManager daoManager;

    //构造方法
    public CommonUtils(Context context) {
        daoManager = DaoManager.getInstance();
        if (daoManager != null){
            daoManager.initManager(context);
        }
    }

    /**
     * 对数据库中student表的插入操作
     *
     * @param student
     * @return
     */
    public boolean insertStudent(ZpStudent student) {
        boolean flag = false;
        flag = daoManager.getDaoSession().insert(student) != -1 ? true : false;
        return flag;
    }

    /**
     * 批量插入
     *
     * @param students
     * @return
     */
    public boolean inserMultStudent(final List<ZpStudent> students) {
        //标识
        boolean flag = false;
        try {
            //插入操作耗时
            daoManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (ZpStudent student : students) {
                        daoManager.getDaoSession().insertOrReplace(student);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改
     *
     * @param student
     * @return
     */
    public boolean updateStudent(ZpStudent student) {
        boolean flag = false;
        try {
            daoManager.getDaoSession().update(student);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除
     *
     * @param student
     * @return
     */
    public boolean deleteStudent(ZpStudent student) {
        boolean flag = false;
        try {
            //删除指定ID
            daoManager.getDaoSession().delete(student);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deleteAll(){
        daoManager.getDaoSession().deleteAll(ZpStudent.class);
        return true;
    }

    /**
     * 查询单条
     *
     * @param key
     * @return
     */
    public ZpStudent listOneStudent(long key) {
        return daoManager.getDaoSession().load(ZpStudent.class, key);
    }

    /**
     * 全部查询
     *
     * @return
     */
    public List<ZpStudent> listAll() {
        return daoManager.getDaoSession().loadAll(ZpStudent.class);
    }

    /**
     * 原生查询
     */
    public List<ZpStudent> queryNative() {
        List<ZpStudent> list = null;
        //查询条件
        String where = "where name like ?";
        //使用sql进行查询
        if (daoManager != null){
            list = daoManager.getDaoSession().queryRaw(ZpStudent.class, where, new String[]{"%王%"});
        }
        return list;
    }

    /**
     * QueryBuilder查询大于
     */
    public List<ZpStudent> queryBuilder() {
        List<ZpStudent> list = null;
        if (daoManager != null){
            //查询构建器
            QueryBuilder<ZpStudent> queryBuilder = daoManager.getDaoSession().queryBuilder(ZpStudent.class);
            //查询年龄大于19的北京
            list = queryBuilder
                    .where(StudentDao.Properties.Age.ge(19))
//                    .where(StudentDao.Properties.Name.like("王"))
                    .list();
        }
        return list;
    }
}
