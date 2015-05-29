package com.kiscod.test;

import com.kiscod.po.AccessToken;
import com.kiscod.util.WeixinUtil;
import net.sf.json.JSONObject;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: QiuShiLe
 * Date: 2015/5/26
 * Time: 15:39
 * Project: QshileWeixin
 * Detail:
 */
public class WeixinTest {

    public static void main(String[] args) {
        AccessToken token = WeixinUtil.getAccessToken();
        System.out.println("票据" + token.getToken());
        System.out.println("有效时间" + token.getExpiresIn());

        String path = "D:/Pictures/160.jpg";
        try {
            String mediaId = WeixinUtil.upload(path, token.getToken(), "thumb");
            System.out.println("mediaId::" + mediaId);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
