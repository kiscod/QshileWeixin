package com.kiscod.util;


import com.kiscod.po.*;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: QiuShiLe
 * Date: 2015/4/29
 * Time: 15:36
 * Project: QshileWeixin
 * Detail:
 */
public class MessageUtil {

    public static final String MESSAGE_TEXT         = "text";
    public static final String MESSAGE_IMAGE        = "image";
    public static final String MESSAGE_MUSIC        = "music";
    public static final String MESSAGE_VOICE        = "voice";
    public static final String MESSAGE_VIDEO        = "video";
    public static final String MESSAGE_LINK         = "link";
    public static final String MESSAGE_LOCATION     = "location";
    public static final String MESSAGE_EVENT        = "event";
    public static final String MESSAGE_SUBSCRIBE    = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE  = "unsubscribe";
    public static final String MESSAGE_CLICK        = "CLICK";
    public static final String MESSAGE_VIEW         = "VIEW";
    public static final String MESSAGE_NEWS         = "news";


    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();

        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);

        Element root = doc.getRootElement();
        List<Element> list = root.elements();

        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        ins.close();

        return map;
    }

    public static String textMessageToXml(TextMessage textMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    public static String initText(String toUserName, String fromUserName, String content){
        TextMessage text = new TextMessage();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MESSAGE_TEXT);
        text.setCreateTime(new Date().getTime());
        text.setContent(content);
        return textMessageToXml(text);
    }

    public static String menuText(){
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您的关注，请按照以下说明进行操作\n\n");
        sb.append("1、公众号介绍\n2、邱世乐介绍\n\n");
        sb.append("回复“？”显示此帮助菜单");
        return sb.toString();
    }

    public static String firstMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("这个公众号是邱世乐用来练习微信开发的公众号，通过百度的BAE来维护。");
        return sb.toString();
    }
    public static String secondMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("邱世乐是个男生， 180cm, 76kg。");
        return sb.toString();
    }

    /**
     * 图文消息转为xml
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", newsMessage.getClass());
        xStream.alias("item", new News().getClass());
        return xStream.toXML(newsMessage);
    }


     public static String imageMessageToXml(ImageMessage imageMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", imageMessage.getClass());
        xStream.alias("item", new News().getClass());
        return xStream.toXML(imageMessage);
    }

    public static String musicMessageToXml(MusicMessage musicMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", musicMessage.getClass());
        xStream.alias("item", new News().getClass());
        return xStream.toXML(musicMessage);
    }



    /**
     * 图文消息的组装
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initNewsMessage(String toUserName, String fromUserName) {
        String message = null;
        List<News> newsList = new ArrayList<>();
        NewsMessage newsMessage = new NewsMessage();

        News news = new News();
        news.setTitle("邱世乐介绍");
        news.setDescription("邱世乐是个男生， 180cm, 76kg。");
        news.setPicUrl("http://qingfeng.tunnel.mobi/image/shile.jpg");
        news.setUrl("www.kiscod.com");

        newsList.add(news);

        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        message = newsMessageToXml(newsMessage);

        return message;
    }


    /**
     * 组装图片消息
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initImageMessage (String toUserName, String fromUserName) {
        String message = null;

        Image image = new Image();
        image.setMediaId("8HDgfa7J3yw0ldRGD3q9aE1-Zt-O5oBBAa6_2Ul9HwySnmRfTYA2TmEDd6bEuXtI");
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setMsgType(MESSAGE_IMAGE);
        imageMessage.setCreateTime(new Date().getTime());
        imageMessage.setImage(image);

        message = imageMessageToXml(imageMessage);
        return message;
    }

    /**
     * 组装音乐消息
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initMusicMessage (String toUserName, String fromUserName) {
        String message = null;

        Music music = new Music();
        music.setThumbMediaId("CtFUR19tXGEKDt-QYXLZWhciwAUykd82VrTKs3ZnirhQIX_h2WpGWMbekotI03le");
        music.setTitle("see you again");
        music.setDescription("速7片尾曲");
        music.setMusicUrl("http://qingfeng.tunnel.mobi/resource/see you again.mp3");
        music.setHQMusicUrl("http://qingfeng.tunnel.mobi/resource/Wiz Khalifa,Charlie Puth - See You Again.mp3");


        MusicMessage musicMessage = new MusicMessage();
        musicMessage.setFromUserName(toUserName);
        musicMessage.setToUserName(fromUserName);
        musicMessage.setMsgType(MESSAGE_MUSIC);
        musicMessage.setCreateTime(new Date().getTime());
        musicMessage.setMusic(music);

        message = musicMessageToXml(musicMessage);
        return message;
    }
}
