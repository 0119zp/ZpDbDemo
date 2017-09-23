package zp.com.zpdbdemo.db.greendao.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import zp.com.zpdbdemo.R;
import zp.com.zpdbdemo.db.greendao.entity.ZpStudent;
import zp.com.zpdbdemo.db.sqlite.entity.ZpSqlStudent;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class ZpAdapter extends BaseAdapter{

    private List<ZpStudent> zpList = new ArrayList<>();
    private List<ZpSqlStudent> zpSqlList = new ArrayList<>();
    private LayoutInflater inflater;
    private boolean tag = false;

    public ZpAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<ZpStudent> list){
        if (zpList != null && zpList.size() > 0){
            zpList.clear();
        }
        zpList.addAll(list);

        notifyDataSetChanged();
    }

    public void setSqlData(List<ZpSqlStudent> list){
        tag = true;
        if (zpSqlList != null && zpSqlList.size() > 0){
            zpSqlList.clear();
        }
        zpSqlList.addAll(list);

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (tag){
            return zpSqlList == null ? 0 : zpSqlList.size();
        }else {
            return zpList == null ? 0 : zpList.size();
        }
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int posion, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.adapter_greendao, null);

            viewHolder.textView = (TextView) view.findViewById(R.id.text);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (tag){
            if (zpSqlList != null && zpSqlList.size() > 0){
                viewHolder.textView.setText("id=" + zpSqlList.get(posion).getId()
                        +";name = "+ zpSqlList.get(posion).getName()
                        +";age = " + zpSqlList.get(posion).getAge()
                        +";address = " + zpSqlList.get(posion).getAddress());
            }
        }else {
            viewHolder.textView.setText("id=" + zpList.get(posion).getId()
                    +";name = "+ zpList.get(posion).getName()
                    +";age = " + zpList.get(posion).getAge()
                    +";address = " + zpList.get(posion).getAddress());
        }

        return view;
    }

    class ViewHolder{
        TextView textView;
    }
}
