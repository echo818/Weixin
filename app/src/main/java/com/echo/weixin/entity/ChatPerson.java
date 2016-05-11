package com.echo.weixin.entity;

/**
 * Created by Echo-z on 2016/5/3/0003.
 */
public class ChatPerson {
    private Long time;
    private int id;
    private int image;
    private String content;

    public ChatPerson(Long time, int id, int image, String content) {
        this.time = time;
        this.id = id;
        this.image = image;
        this.content = content;
    }

    public Long getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }
}
