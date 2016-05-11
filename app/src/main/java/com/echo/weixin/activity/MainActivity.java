package com.echo.weixin.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.echo.weixin.R;
import com.echo.weixin.adapter.FragmentAdapter;
import com.echo.weixin.fragment.CenterFragment;
import com.echo.weixin.fragment.CollectFragment;
import com.echo.weixin.fragment.GoodFragment;
import com.echo.weixin.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {
    private ViewPager viewPager;
    private FragmentTabHost tabHost;
    private int icons[] = {R.drawable.home, R.drawable.good, R.drawable.collect, R.drawable.center};
    private String texts[] = {"首页", "点赞", "收藏", "中心"};
    private Class fragments[] = {HomeFragment.class, GoodFragment.class, CollectFragment.class, CenterFragment.class};
    private List<Fragment> list = new ArrayList<>();
    private PopupWindow mWindow;
    private LinearLayout attention, cart, command, mark, upstage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPage();
    }

    //初始化视图
    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.main_view_pager);
        viewPager.setOnPageChangeListener(this);

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.main_view_pager);
        tabHost.setOnTabChangedListener(this);
        //删除FragmentTabhost的分割线
        tabHost.getTabWidget().setDividerDrawable(R.color.white);

        for (int i = 0; i < texts.length; i++) {
            tabHost.addTab(tabHost.newTabSpec(texts[i]).setIndicator(getTabItemView(i)), fragments[i], null);
            tabHost.setTag(i);
        }
    }

    //初始化视图页面
    private void initPage() {
        list.add(new HomeFragment());
        list.add(new GoodFragment());
        list.add(new CollectFragment());
        list.add(new CenterFragment());
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), list));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_item_attention:
                Toast.makeText(this, "发起群聊", Toast.LENGTH_SHORT).show();
                mWindow.dismiss();
                break;
            case R.id.menu_item_cart:
                Toast.makeText(this, "添加朋友", Toast.LENGTH_SHORT).show();
                mWindow.dismiss();
                break;
            case R.id.menu_item_command:
                Toast.makeText(this, "扫一扫", Toast.LENGTH_SHORT).show();
                mWindow.dismiss();
                break;
            case R.id.menu_item_mark:
                Toast.makeText(this, "收付款", Toast.LENGTH_SHORT).show();
                mWindow.dismiss();
                break;
            case R.id.menu_item_upstage:
                Toast.makeText(this, "帮助与反馈", Toast.LENGTH_SHORT).show();
                mWindow.dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_search:
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_more:
                showBarWindow();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //下拉菜单
    private void showBarWindow() {
        View view = getLayoutInflater().inflate(R.layout.bar_window, null);
        //弹出窗口隐藏
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindow.dismiss();
            }
        });
        attention = (LinearLayout) view.findViewById(R.id.menu_item_attention);
        cart = (LinearLayout) view.findViewById(R.id.menu_item_cart);
        command = (LinearLayout) view.findViewById(R.id.menu_item_command);
        mark = (LinearLayout) view.findViewById(R.id.menu_item_mark);
        upstage = (LinearLayout) view.findViewById(R.id.menu_item_upstage);
        attention.setOnClickListener(this);
        cart.setOnClickListener(this);
        command.setOnClickListener(this);
        mark.setOnClickListener(this);
        upstage.setOnClickListener(this);
        mWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    //设置底部导航栏
    private View getTabItemView(int i) {
        View view = getLayoutInflater().inflate(R.layout.tab_item, null);
        ImageView item_iv = (ImageView) view.findViewById(R.id.tab_item_iv);
        item_iv.setImageResource(icons[i]);
        TextView item_tv = (TextView) view.findViewById(R.id.tab_item_tv);
        item_tv.setText(texts[i]);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        TabWidget widget = tabHost.getTabWidget();
        int old = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        tabHost.setCurrentTab(position);
        widget.setDescendantFocusability(old);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        int position = tabHost.getCurrentTab();
        viewPager.setCurrentItem(position);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWindow != null && mWindow.isShowing()) {
                mWindow.dismiss();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
