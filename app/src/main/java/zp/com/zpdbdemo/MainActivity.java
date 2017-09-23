package zp.com.zpdbdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import zp.com.zpdbdemo.db.ZpDbActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setDb(View view){
        Intent intent = new Intent(MainActivity.this, ZpDbActivity.class);
        startActivity(intent);
    }

    public void setJihe(View view){
        Toast.makeText(this, "setJihe 有待处理", Toast.LENGTH_SHORT).show();
    }


}
