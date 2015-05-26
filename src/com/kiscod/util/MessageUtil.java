package com.kiscod.util;


import com.kiscod.po.News;
import com.kiscod.po.NewsMessage;
import com.kiscod.po.TextMessage;
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

}
