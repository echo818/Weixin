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
public class GoodAdapter extends BaseAdapter {
    private Context context;
    private List<Map> list;

    public GoodAdapter(Context context, List<Map> list) {
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
        Map map = list.get(position);
        if (map.containsKey("icon")) {
            ViewHolder holder;
            if (convertView == null || convertView.getTag() == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.good_item, null);
                holder.icon = (ImageView) convertView.findViewById(R.id.good_item_icon);
                holder.title = (TextView) convertView.findViewById(R.id.good_item_title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.icon.setImageResource((int) map.get("icon"));
            holder.title.setText((String) map.get("title"));
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.good_item_index, null);
            TextView title = (TextView) convertView.findViewById(R.id.good_item_index_title);
            title.setText((String) map.get("title"));
        }
        return convertView;
    }

    private class ViewHolder {
        private ImageView icon;
        private TextView title;
    }
}
