package com.kiscod.po;

/**
 * Created with IntelliJ IDEA.
 * User: QiuShiLe
 * Date: 2015/5/29
 * Time: 12:41
 * Project: QshileWeixin
 * Detail:
 */
public class Music {
    private String Title;
    private String Description;
    private String MusicUrl;
    private String HQMusicUrl;

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

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

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    private String ThumbMediaId;
}
