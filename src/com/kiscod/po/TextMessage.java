package com.kiscod.po;

/**
 * Created with IntelliJ IDEA.
 * User: QiuShiLe
 * Date: 2015/4/29
 * Time: 15:56
 * Project: QshileWeixin
 * Detail:
 */
public class TextMessage extends BaseMessage{

    private String Content;
    private String MsgId;



    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        this.MsgId = msgId;
    }
}
