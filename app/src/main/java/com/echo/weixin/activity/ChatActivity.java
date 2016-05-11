package com.echo.weixin.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.echo.weixin.R;
import com.echo.weixin.adapter.ChatAdapter;
import com.echo.weixin.entity.ChatPerson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private ImageView voice_iv, smile_iv, more_iv;
    private EditText edit;
    private TextView send;
    private LinearLayout rootView;
    private ChatAdapter adapter;
    private List<ChatPerson> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String title = getIntent().getStringExtra("title");
        setTitle(title);

        listView = (ListView) findViewById(R.id.chat_list_view);
        voice_iv = (ImageView) findViewById(R.id.chat_voice_iv);
        smile_iv = (ImageView) findViewById(R.id.chat_smile_iv);
        more_iv = (ImageView) findViewById(R.id.chat_more_iv);
        edit = (EditText) findViewById(R.id.chat_cursor_edit);
        send = (TextView) findViewById(R.id.chat_send_tv);
        voice_iv.setOnClickListener(this);
        smile_iv.setOnClickListener(this);
        more_iv.setOnClickListener(this);
        initEditListener();

        initListData();

        initListAdapter();

        send.setOnClickListener(this);

        listenerSoftInput();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_send_tv:
                refreshChatContent();
                break;
            case R.id.chat_voice_iv:
                Toast.makeText(this, "语音", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chat_smile_iv:
                Toast.makeText(this, "表情", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chat_more_iv:
                Toast.makeText(this, "功能", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void refreshChatContent() {
        String content = edit.getText().toString();
        ChatPerson person = new ChatPerson(System.currentTimeMillis(), 0, R.drawable.home_item_icon, content);
        list.add(person);
        adapter.notifyDataSetChanged();
        edit.setText("");
        listView.setSelection(list.size());
    }

    private void initListAdapter() {
        adapter = new ChatAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setSelection(list.size());
    }

    private void initListData() {
        System.out.println(System.currentTimeMillis());
        ChatPerson person1 = new ChatPerson(1461261573906l, 1, R.drawable.good_item_icon, "你这个傻逼");
        ChatPerson person2 = new ChatPerson(1462161736418l, 1, R.drawable.good_item_icon, "这个世界很美好，你不知道吗？");
        ChatPerson person3 = new ChatPerson(1462262548651l, 2, R.drawable.good_item_icon, "返回一个受指定数组支持的固定大小的列表。所以不能做Add、Remove等操作。");
        ChatPerson person4 = new ChatPerson(1462262553906l, 0, R.drawable.home_item_icon, "呵呵");
        ChatPerson person5 = new ChatPerson(1462268237420l, 1, R.drawable.good_item_icon, "正确的顺序包行时类型就是指定组与List相");
        ChatPerson person6 = new ChatPerson(1462268594145l, 1, R.drawable.good_item_icon, "你这个傻逼");
        ChatPerson person7 = new ChatPerson(1462269594145l, 1, R.drawable.good_item_icon, "这个世界很美好，你不知道吗？");
        ChatPerson person8 = new ChatPerson(1462270594145l, 2, R.drawable.good_item_icon, "返回一个受指定数");
        ChatPerson person9 = new ChatPerson(1462271094145l, 0, R.drawable.home_item_icon, "你妹妹的，说什么说");
        ChatPerson person10 = new ChatPerson(1462271594145l, 1, R.drawable.good_item_icon, "正确的顺序包含此列表中所有元素的数组;返回数组的运行时类型就是指定数组的运行...java中数组与List相互转换的方法");
        ChatPerson person11 = new ChatPerson(1462272594145l, 1, R.drawable.good_item_icon, "你这个傻逼");
        ChatPerson person12 = new ChatPerson(1462273594145l, 1, R.drawable.good_item_icon, "这个世界很美好，你不知道吗？");
        ChatPerson person13 = new ChatPerson(1462275594145l, 0, R.drawable.home_item_icon, "返回一个受指定数组支持的固定大小的列表。所以不能做Add、Remove等操作。");
        ChatPerson person14 = new ChatPerson(1462278594145l, 0, R.drawable.home_item_icon, "想什么鬼东西");
        ChatPerson person15 = new ChatPerson(1462285494145l, 1, R.drawable.good_item_icon, "正确的顺序包行时类型就是指定组与List相");
        ChatPerson person16 = new ChatPerson(1462285594145l, 0, R.drawable.home_item_icon, "你这个傻逼");
        ChatPerson person17 = new ChatPerson(1462290594145l, 1, R.drawable.good_item_icon, "这个世界很美好，你不知道吗？");
        ChatPerson person18 = new ChatPerson(1462292594145l, 2, R.drawable.good_item_icon, "返回一个受指定数");
        ChatPerson person19 = new ChatPerson(1462292694145l, 0, R.drawable.home_item_icon, "你妹妹的，说什么说");
        ChatPerson person20 = new ChatPerson(1462292794145l, 0, R.drawable.home_item_icon, "[傻笑]傻笑  [砍刀]砍刀  [呲牙]呲牙  [流泪]流泪  [惊恐]惊恐  [色色]色色  [无奈]无奈  [冷汗]冷汗  [撇嘴]撇嘴  [微笑]微笑  " +
                "[得意]得意  [坏笑]坏笑  [难过]难过  [笑脸]笑脸  [大笑]大笑  [尴尬]尴尬  [发呆]发呆  [白眼]白眼  [疑问]疑问  [傲慢]傲慢");
        ChatPerson person21 = new ChatPerson(1462299594145l, 1, R.drawable.good_item_icon, "正确的顺序[笑脸][得意][傻笑][色色][笑脸][得意]包含此列表中所有元素的数组;返回数组的运行时类型就是指定数组的运行...java中数组与List相互转换的方法");
        ChatPerson person22 = new ChatPerson(1462299604145l, 0, R.drawable.home_item_icon, "[笑脸][笑脸][笑脸][笑脸]");
        list.addAll(Arrays.asList(person1, person2, person3, person4, person5, person6, person7, person8, person9, person10,
                person11, person12, person13, person14, person15, person16, person17, person18, person19, person20, person21, person22));
    }

    private void initEditListener() {
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    more_iv.setVisibility(View.GONE);
                    send.setVisibility(View.VISIBLE);
                } else {
                    send.setVisibility(View.GONE);
                    more_iv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void listenerSoftInput() {
        rootView = (LinearLayout) findViewById(R.id.chat_root_view);
        rootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (oldBottom - bottom > 100) {
                    listView.setSelection(list.size());
                }
            }
        });
    }
}
