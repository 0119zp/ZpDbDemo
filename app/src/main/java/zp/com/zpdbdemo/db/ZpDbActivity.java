package zp.com.zpdbdemo.db;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import zp.com.zpdbdemo.R;
import zp.com.zpdbdemo.db.greendao.activity.ZpGreendaoActivity;
import zp.com.zpdbdemo.db.sqlite.activity.ZpSqliteActivity;

/**
 * Created by Administrator on 2017/6/30 0030.
 */

public class ZpDbActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
    }

    public void greendao(View view){
        Intent intent = new Intent(ZpDbActivity.this, ZpGreendaoActivity.class);
        startActivity(intent);
    }

    public void realm(View view){
        Toast.makeText(this, "realm有待处理", Toast.LENGTH_SHORT).show();
    }

    public void sqlite(View view){
        Intent intent = new Intent(ZpDbActivity.this, ZpSqliteActivity.class);
        startActivity(intent);
    }

}
