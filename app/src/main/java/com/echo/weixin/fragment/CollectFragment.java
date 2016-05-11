package com.echo.weixin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.echo.weixin.R;

public class CollectFragment extends Fragment implements View.OnClickListener {
    private LinearLayout one, two, three, four, five, six, seven;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect, container, false);
        one = (LinearLayout) view.findViewById(R.id.collect_item_one);
        two = (LinearLayout) view.findViewById(R.id.collect_item_two);
        three = (LinearLayout) view.findViewById(R.id.collect_item_three);
        four = (LinearLayout) view.findViewById(R.id.collect_item_four);
        five = (LinearLayout) view.findViewById(R.id.collect_item_five);
        six = (LinearLayout) view.findViewById(R.id.collect_item_six);
        seven = (LinearLayout) view.findViewById(R.id.collect_item_seven);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collect_item_one:
                showInfo("朋友圈");
                break;
            case R.id.collect_item_two:
                showInfo("扫一扫");
                break;
            case R.id.collect_item_three:
                showInfo("摇一摇");
                break;
            case R.id.collect_item_four:
                showInfo("附近的人");
                break;
            case R.id.collect_item_five:
                showInfo("漂流瓶");
                break;
            case R.id.collect_item_six:
                showInfo("购物");
                break;
            case R.id.collect_item_seven:
                showInfo("游戏");
                break;
        }
    }

    private void showInfo(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }
}
