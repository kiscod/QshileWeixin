package com.kiscod.servlet;

import com.kiscod.po.TextMessage;
import com.kiscod.util.CheckUtil;
import com.kiscod.util.MessageUtil;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: QiuShiLe
 * Date: 2015/4/28
 * Time: 17:01
 * Project: QshileWeixin
 * Detail:
 */

public class WeixinServlet extends javax.servlet.http.HttpServlet {
    /**
     *   <servlet>
     *    <description>This is the description of my J2EE component</description>
     *    <display-name>This is the display name of my J2EE component</display-name>
     *    <servlet-name>WeixinServlet</servlet-name>
     *    <servlet-class>.WeixinServlet</servlet-class>
     *   </servlet>
     *
     *    <servlet-mapping>
     *      <servlet-name>WeixinServlet</servlet-name>
     *      <url-pattern>//WeixinServlet</url-pattern>
     *    </servlet-mapping>
     */
    /**
     * Constructor of the object.
     */
    public WeixinServlet() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
    }

    /**
     * The doGet method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to get.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String signature    = request.getParameter("signature");
        String timestamp    = request.getParameter("timestamp");
        String nonce        = request.getParameter("nonce");
        String echostr      = request.getParameter("echostr");
        System.out.println(signature);
        System.out.println("imooc");
        PrintWriter out = response.getWriter();
        if(CheckUtil.checkSignature(signature, timestamp, nonce)){
            out.print(echostr);
        }
    }

    /**
     * The doPost method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Map<String, String> map = MessageUtil.xmlToMap(request);
            String fromUserName = map.get("FromUserName");
            String toUserName = map.get("ToUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");

            String message = null;
            if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
                if("1".equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());
                } else if("2".equals(content)){
//                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.secondMenu());
                    message = MessageUtil.initNewsMessage(toUserName, fromUserName);
                } else if("?".equals(content) || "？".equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                } else {
                    TextMessage text = new TextMessage();
                    text.setFromUserName(toUserName);
                    text.setToUserName(fromUserName);
                    text.setMsgType(MessageUtil.MESSAGE_TEXT);
                    text.setCreateTime(new Date().getTime());
                    text.setContent("您发送的消息是：" + content);
                    message = MessageUtil.textMessageToXml(text);
                }

                System.out.println(message);
            } else if (MessageUtil.MESSAGE_EVENT.equals(msgType)){
                String eventType = map.get("Event");
                if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }
            }
            out.print(message);

        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    /**
     * Initialization of the servlet. <br>
     */
    public void init() throws ServletException {
        // Put your code here
    }
}
