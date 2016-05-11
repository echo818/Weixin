package com.echo.weixin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.echo.weixin.R;
import com.echo.weixin.activity.InfoActivity;
import com.echo.weixin.adapter.GoodAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView listView, listViewIndex;
    private GoodAdapter adaper;
    private List<Map> list = new ArrayList<>();
    private List<String> listName = new ArrayList<>();
    private List<Map<String, String>> listData = new ArrayList<>();
    private List<String> listIndex = new ArrayList<>();
    private String titles[] = {"新的朋友", "群聊", "标签", "公众号",
            "A", "Angelababy", "李易峰", "范冰冰",
            "B", "唐嫣", "李易峰",
            "C", "杨幂", "李易峰", "范冰冰", "赵本山",
            "D", "吴亦凡", "李易峰", "范冰冰", "林志颖", "赵本山", "李敏镐", "杨紫",
            "E", "刘诗诗", "张柏芝", "波多野结衣", "杨紫", "苍井优",
            "F", "宝宝好名字",
            "G", "刘德华", "张柏芝",
            "H", "周星驰", "李易峰", "张柏芝", "刘德华", "李敏镐", "吴镇宇", "章子怡",
            "J", "李小璐", "刘德华", "范冰冰", "郭德纲", "赵本山", "刘德华", "沈梦辰",
            "K", "柳岩", "李易峰", "范冰冰", "苍井优",
            "L", "周杰伦", "李易峰", "游戏名字", "林志颖", "李敏镐", "张柏芝",
            "M", "赵丽颖",
            "N", "给宝宝起个好名字", "李易峰",
            "P", "邓紫棋", "游戏名字", "范冰冰", "苍井优", "刘德华",
            "S", "范冰冰", "林志颖",
            "T", "电影明星", "张柏芝", "范冰冰", "高圆圆", "苍井优", "高圆圆", "吴镇宇",
            "W", "邓紫棋", "李易峰", "范冰冰", "波多野结衣", "刘德华", "波多野结衣", "沈梦辰",
            "X", "游戏名字", "刘德华", "张柏芝", "赵本山",
            "Y", "姚笛", "波多野结衣", "范冰冰", "苍井优", "高圆圆", "林志颖", "李敏镐",
            "Z", "邓紫棋", "李易峰", "波多野结衣", "赵本山"};
    private String indexs[] = {"~", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_good, container, false);
        listView = (ListView) view.findViewById(R.id.good_list_view);
        listView.setOnItemClickListener(this);

        //避免数据重复添加——方案一
//        list.removeAll(list);
//        listName.removeAll(listName);
//        listData.removeAll(listData);
//        listIndex.removeAll(listIndex);

        listViewIndex = (ListView) view.findViewById(R.id.good_list_view_index);

        listViewIndex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = ((TextView) view.findViewById(R.id.good_index_name)).getText().toString();
                listView.setSelection(listName.indexOf(name));
            }
        });

        listViewIndex.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(listIndex.get(position));
                return false;
            }
        });

        initView();
        return view;
    }

    private void initView() {
        //避免数据重复添加——方案二
        if (list.size() == 0) {
            for (int j = 0; j < indexs.length; j++) {
                Map<String, String> map = new HashMap();
                map.put("name", indexs[j]);
                listData.add(map);
                listIndex.add(indexs[j]);
            }

            for (int i = 0; i < titles.length; i++) {
                Map map = new HashMap();
                if (!listIndex.contains(titles[i])) {
                    map.put("icon", R.drawable.good_item_icon);
                }
                map.put("title", titles[i]);
                list.add(map);
                listName.add(titles[i]);
            }
        }
        adaper = new GoodAdapter(getActivity(), list);
        listView.setAdapter(adaper);

        SimpleAdapter simple = new SimpleAdapter(getActivity(), listData, R.layout.good_index, new String[]{"name"}, new int[]{R.id.good_index_name});
        listViewIndex.setAdapter(simple);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (view.getId() == R.id.good_item_view) {
            String name = ((TextView) view.findViewById(R.id.good_item_title)).getText().toString();
            if (position > 4) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
