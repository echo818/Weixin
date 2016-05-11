package com.echo.weixin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.echo.weixin.R;

public class CenterFragment extends Fragment implements View.OnClickListener {
    private LinearLayout info, one, two, three, four, five, six;
    private ImageView qrcode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_center, container, false);
        info = (LinearLayout) view.findViewById(R.id.center_info);
        one = (LinearLayout) view.findViewById(R.id.center_item_one);
        two = (LinearLayout) view.findViewById(R.id.center_item_two);
        three = (LinearLayout) view.findViewById(R.id.center_item_three);
        four = (LinearLayout) view.findViewById(R.id.center_item_four);
        five = (LinearLayout) view.findViewById(R.id.center_item_five);
        six = (LinearLayout) view.findViewById(R.id.center_item_six);
        qrcode = (ImageView) view.findViewById(R.id.center_qrcode);

        info.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        qrcode.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.center_info:
                showInfo("平凡的世界");
                break;
            case R.id.center_item_one:
                showInfo("相册");
                break;
            case R.id.center_item_two:
                showInfo("收藏");
                break;
            case R.id.center_item_three:
                showInfo("钱包");
                break;
            case R.id.center_item_four:
                showInfo("卡券");
                break;
            case R.id.center_item_five:
                showInfo("表情");
                break;
            case R.id.center_item_six:
                showInfo("设置");
                break;
            case R.id.center_qrcode:
                showInfo("二维码");
                break;
        }
    }

    private void showInfo(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }
}
