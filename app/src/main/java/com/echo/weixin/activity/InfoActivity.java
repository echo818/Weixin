package com.echo.weixin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.echo.weixin.R;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView name, mark, more, send, chat;
    private LinearLayout album;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        name = (TextView) findViewById(R.id.info_name);
        title = getIntent().getStringExtra("name");
        name.setText(title);

        mark = (TextView) findViewById(R.id.info_mark);
        more = (TextView) findViewById(R.id.info_more);
        send = (TextView) findViewById(R.id.info_send);
        chat = (TextView) findViewById(R.id.info_chat);
        album = (LinearLayout) findViewById(R.id.info_album);

        mark.setOnClickListener(this);
        more.setOnClickListener(this);
        send.setOnClickListener(this);
        chat.setOnClickListener(this);
        album.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_mark:
                Toast.makeText(this, "设置备注和标签", Toast.LENGTH_SHORT).show();
                break;
            case R.id.info_album:
                Toast.makeText(this, "个人相册", Toast.LENGTH_SHORT).show();
                break;
            case R.id.info_more:
                Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
                break;
            case R.id.info_send:
                Intent intent = new Intent(this, ChatActivity.class);
                intent.putExtra("title", title);
                startActivity(intent);
//                Toast.makeText(this, "发消息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.info_chat:
                Toast.makeText(this, "视频聊天", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
