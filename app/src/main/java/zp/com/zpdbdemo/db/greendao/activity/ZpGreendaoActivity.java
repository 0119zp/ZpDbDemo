package zp.com.zpdbdemo.db.greendao.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import zp.com.zpdbdemo.R;
import zp.com.zpdbdemo.db.greendao.entity.ZpStudent;
import zp.com.zpdbdemo.db.greendao.util.CommonUtils;

/**
 * Created by Administrator on 2017/7/17 0017.
 */

public class ZpGreendaoActivity extends Activity {

    private CommonUtils dbUtils;
    private ListView listView;
    private ZpAdapter adapter;
    private ArrayList<ZpStudent> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_db);

        initView();
    }

    private void initView() {
        dbUtils = new CommonUtils(this);
        listView = (ListView) findViewById(R.id.zp_listview);
        adapter = new ZpAdapter(ZpGreendaoActivity.this);
        listView.setAdapter(adapter);
    }

    // 插入
    public void putin(View view){
        ZpStudent student = new ZpStudent();
        student.setId((long) 6);
        student.setName("王少青-" + "添加号");
        student.setAge(18);
        student.setAddress("北京");
        dbUtils.insertStudent(student);

        Toast.makeText(this, "添加号插入成功", Toast.LENGTH_SHORT).show();
    }

    // 批量插入
    public void putinMore(View view){

        List<ZpStudent> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ZpStudent studentlist = new ZpStudent();
            studentlist.setId((long) i);
            studentlist.setName("王-" + i +"号");
            studentlist.setAge(18 + i);
            studentlist.setAddress("河南" + i);
            list.add(studentlist);
        }
        dbUtils.inserMultStudent(list);
        Toast.makeText(this, "批量插入成功", Toast.LENGTH_SHORT).show();
    }
    // 删除
    public void delete(View view){
        ZpStudent studentdelete = new ZpStudent();
        studentdelete.setId((long) 3);
        dbUtils.deleteStudent(studentdelete);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "单个删除成功", Toast.LENGTH_SHORT).show();
    }

    // 删除
    public void deleteAll(View view){
        Toast.makeText(this, "全部删除成功", Toast.LENGTH_SHORT).show();
        dbUtils.deleteAll();
        adapter.notifyDataSetChanged();
    }
    // 更新
    public void update(View view){
        ZpStudent studentupdate = new ZpStudent();
        //根据ID来修改
        studentupdate.setId((long) 1);
        //把年龄改成100岁
        studentupdate.setAge(100);
        studentupdate.setName("张");
        studentupdate.setAddress("深圳");
        dbUtils.updateStudent(studentupdate);

        Toast.makeText(this, "更新单个成功", Toast.LENGTH_SHORT).show();
    }
    // 查询一条
    public void selectOne(View view){
        if (list != null && list.size() > 0){
            list.clear();
        }
        if (dbUtils.listOneStudent(6) != null){
            list.add(dbUtils.listOneStudent(6));
            adapter.setData(list);
        }
    }
    // 查询全部
    public void selectAll(View view){
        List<ZpStudent> lists = dbUtils.listAll();
        if (lists != null && lists.size() > 0){
            adapter.setData(lists);
        }else {
            Toast.makeText(this, "查询全部无结果", Toast.LENGTH_SHORT).show();
        }
    }

    // 原顺序查询
    public void selectNative(View view){
        List<ZpStudent> lists = dbUtils.queryNative();
        if (lists != null && lists.size() > 0){
            adapter.setData(lists);
        }else {
            Toast.makeText(this, "原顺序查询无结果", Toast.LENGTH_SHORT).show();
        }
    }

    // 节点查询
    public void select(View view){
        List<ZpStudent> lists = dbUtils.queryBuilder();
        if (lists != null && lists.size() > 0){
            adapter.setData(lists);
        }else {
            Toast.makeText(this, "节点查询无结果", Toast.LENGTH_SHORT).show();
        }
    }


}
