package com.echo.weixin.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.echo.weixin.R;
import com.echo.weixin.activity.ChatActivity;
import com.echo.weixin.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView listView;
    private HomeAdapter adapter;
    private List<Map> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = (ListView) view.findViewById(R.id.home_list_view);
        listView.setOnItemClickListener(this);
        initView();
        return view;
    }

    private void initView() {
        //避免数据重复添加
        if (list.size()==0){
            for (int i = 0; i < 20; i++) {
                Map map = new HashMap<>();
                map.put("icon", R.drawable.home_item_icon);
                map.put("title", "测试标题" + i);
                map.put("desc", "这是一个非常好看的详情信息这是一个非常好看的详情信息");
                map.put("time", "12:50");
                list.add(map);
            }
        }
        adapter = new HomeAdapter(getActivity(), list);
        listView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String title = ((TextView) view.findViewById(R.id.home_item_title)).getText().toString();
//        Toast.makeText(getActivity(), title + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra("title",title);
        startActivity(intent);
    }
}
