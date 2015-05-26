package com.kiscod.po;

/**
 * Created with IntelliJ IDEA.
 * User: QiuShiLe
 * Date: 2015/5/26
 * Time: 12:43
 * Project: QshileWeixin
 * Detail:
 */
public class News {

    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
