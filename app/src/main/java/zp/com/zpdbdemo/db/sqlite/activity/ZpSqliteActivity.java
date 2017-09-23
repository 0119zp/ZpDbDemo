package zp.com.zpdbdemo.db.sqlite.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zp.com.zpdbdemo.R;
import zp.com.zpdbdemo.db.greendao.activity.ZpAdapter;
import zp.com.zpdbdemo.db.greendao.entity.ZpStudent;
import zp.com.zpdbdemo.db.sqlite.entity.ZpSqlStudent;
import zp.com.zpdbdemo.db.sqlite.util.ZpSqliteManager;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class ZpSqliteActivity extends Activity {

    private ListView listView;
    private ZpAdapter adapter;
    private ArrayList<ZpSqlStudent> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_db);

        initView();

        initData();
    }

    private void initData() {
        if (list != null && list.size() > 0){
            list.clear();
        }

        for (int i = 0 ; i < 5 ; i++){
            ZpSqlStudent zpStudent = new ZpSqlStudent();
            zpStudent.setId(i+"");
            zpStudent.setName("张--" + i);
            zpStudent.setAge("2" + i);
            zpStudent.setAddress("河南=" + i);

            list.add(zpStudent);
        }
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.zp_listview);
        adapter = new ZpAdapter(ZpSqliteActivity.this);
        listView.setAdapter(adapter);
    }

    // 插入
    public void putin(View view){
        Toast.makeText(this, "不做处理", Toast.LENGTH_SHORT).show();
    }

    // 批量插入
    public void putinMore(View view){
        ZpSqliteManager.getInstance(ZpSqliteActivity.this).insert(list);
        Toast.makeText(this, "批量插入成功", Toast.LENGTH_SHORT).show();
    }
    // 删除
    public void delete(View view){
        Toast.makeText(this, "不做处理", Toast.LENGTH_SHORT).show();
    }

    // 删除
    public void deleteAll(View view){
        ZpSqliteManager.getInstance(ZpSqliteActivity.this).delete();
        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
    }
    // 更新
    public void update(View view){
        ZpSqlStudent zpSqlStudent = new ZpSqlStudent();
        zpSqlStudent.setId("1");
        zpSqlStudent.setAge("27");
        zpSqlStudent.setName("小柯");
        zpSqlStudent.setAddress("彦庄");

        ZpSqliteManager.getInstance(ZpSqliteActivity.this).updateByMId(zpSqlStudent);
        Toast.makeText(this, "更新单个成功", Toast.LENGTH_SHORT).show();
    }
    // 查询一条
    public void selectOne(View view){
        List<ZpSqlStudent> students = ZpSqliteManager.getInstance(ZpSqliteActivity.this).searchByString("2");
        if (students == null){
            Toast.makeText(this, "没有查询到数据", Toast.LENGTH_SHORT).show();
        }else {
            adapter.setSqlData(students);
        }
    }
    // 查询全部
    public void selectAll(View view){
        List<ZpSqlStudent> zpStudents = ZpSqliteManager.getInstance(ZpSqliteActivity.this).search();
        if (zpStudents == null){
            Toast.makeText(this, "没有查询到数据", Toast.LENGTH_SHORT).show();
        }else {
            adapter.setSqlData(zpStudents);
        }
    }

    // 原顺序查询
    public void selectNative(View view){
        Toast.makeText(this, "不做处理", Toast.LENGTH_SHORT).show();
    }

    // 节点查询
    public void select(View view){
        Toast.makeText(this, "不做处理", Toast.LENGTH_SHORT).show();
    }

}
