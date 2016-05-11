package com.echo.weixin.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.echo.weixin.R;
import com.echo.weixin.entity.ChatPerson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Echo-z on 2016/5/3/0003.
 */
public class ChatAdapter extends BaseAdapter {
    private Context context;
    private List<ChatPerson> list;
    private String faceStr[] = {"\\[傻笑\\]", "\\[砍刀\\]", "\\[呲牙\\]", "\\[流泪\\]", "\\[惊恐\\]", "\\[色色\\]", "\\[无奈\\]", "\\[冷汗\\]", "\\[撇嘴\\]", "\\[微笑\\]",
            "\\[得意\\]", "\\[坏笑\\]", "\\[难过\\]", "\\[笑脸\\]", "\\[大笑\\]", "\\[尴尬\\]", "\\[发呆\\]", "\\[白眼\\]", "\\[疑问\\]", "\\[傲慢\\]"};
    private int faceImg[] = {R.drawable.face_01, R.drawable.face_02, R.drawable.face_03, R.drawable.face_04, R.drawable.face_05,
            R.drawable.face_06, R.drawable.face_07, R.drawable.face_08, R.drawable.face_09, R.drawable.face_10,
            R.drawable.face_11, R.drawable.face_12, R.drawable.face_13, R.drawable.face_14, R.drawable.face_15,
            R.drawable.face_16, R.drawable.face_17, R.drawable.face_18, R.drawable.face_19, R.drawable.face_20};

    public ChatAdapter(Context context, List<ChatPerson> list) {
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
//        if (convertView == null) {
//            holder = new ViewHolder();
//            convertView = LayoutInflater.from(context).inflate(R.layout.chat_adapter_right, null);
//            holder.time = (TextView) convertView.findViewById(R.id.chat_adapter_time);
//            holder.icon = (ImageView) convertView.findViewById(R.id.chat_adapter_icon);
//            holder.content = (TextView) convertView.findViewById(R.id.chat_adapter_content);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
        ChatPerson person = list.get(position);
        long lastTime = position == 0 ? 0l : list.get(position - 1).getTime();
        if (person.getId() == 0) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.chat_adapter_right, null);
            holder.time = (TextView) convertView.findViewById(R.id.chat_adapter_time);
            holder.icon = (ImageView) convertView.findViewById(R.id.chat_adapter_icon);
            holder.content = (TextView) convertView.findViewById(R.id.chat_adapter_content);
        } else {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.chat_adapter_left, null);
            holder.time = (TextView) convertView.findViewById(R.id.chat_adapter_time);
            holder.icon = (ImageView) convertView.findViewById(R.id.chat_adapter_icon);
            holder.content = (TextView) convertView.findViewById(R.id.chat_adapter_content);
        }
        if (person.getTime() - lastTime > 3 * 60 * 1000) {
            holder.time.setText(getTime(person.getTime()));
        } else {
            holder.time.setVisibility(View.GONE);
        }
        holder.icon.setImageResource(person.getImage());
        holder.content.setText(replaceString(person.getContent()));
        return convertView;
    }

    private class ViewHolder {
        private TextView time;
        private ImageView icon;
        private TextView content;
    }

    private String getTime(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //昨天
        String yesterday = dateFormat.format(System.currentTimeMillis());
        //昨天时间戳
        long yesStamp = 0;
        try {
            yesStamp = dateFormat.parse(yesterday).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //前天
        String twoDay = dateFormat.format(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        //前天时间戳
        long twoStamp = 0;
        try {
            twoStamp = dateFormat.parse(twoDay).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format;
        if (time > yesStamp) {
            format = new SimpleDateFormat("HH:mm");
        } else if (time < yesStamp && time > twoStamp) {
            format = new SimpleDateFormat("昨天 HH:mm");
        } else {
            format = new SimpleDateFormat("M月d日 HH:mm");
        }
        String date = format.format(time);
        return date;
    }

    private CharSequence replaceString(CharSequence text) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        for (int i = 0; i < faceImg.length; i++) {
            Pattern pattern = Pattern.compile(faceStr[i]);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                Drawable drawable = context.getResources().getDrawable(faceImg[i]);
                drawable.setBounds(0, 0, 48, 48);
                builder.setSpan(new ImageSpan(drawable), matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return builder;
    }
}
