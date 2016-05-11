package com.echo.weixin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.echo.weixin.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Echo-z on 2016/4/29/0029.
 */
public class HomeAdapter extends BaseAdapter {
    private List<Map> list;
    private Context context;

    public HomeAdapter(Context context, List<Map> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.home_item, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.home_item_icon);
            holder.title = (TextView) convertView.findViewById(R.id.home_item_title);
            holder.desc = (TextView) convertView.findViewById(R.id.home_item_desc);
            holder.time = (TextView) convertView.findViewById(R.id.home_item_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Map map = list.get(position);
        holder.icon.setImageResource((int) map.get("icon"));
        holder.title.setText((String) map.get("title"));
        holder.desc.setText((String) map.get("desc"));
        holder.time.setText((String) map.get("time"));
        return convertView;
    }

    private class ViewHolder {
        private ImageView icon;
        private TextView title;
        private TextView time;
        private TextView desc;
    }
}
