package com.kiscod.util;


import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: QiuShiLe
 * Date: 2015/5/26
 * Time: 13:18
 * Project: QshileWeixin
 * Detail:
 */
public class WeixinUtil {

    private static final String APPID = "wxa5974d639ac372f9";
    private static final String APPSECRET = "122851ae2a7233044ac81332f46485af ";

    public static JSONObject doGetStr(String url) {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject doPostStr(String url, String ourStr) {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;

        httpPost.setEntity(new StringEntity(ourStr, "UTF-8"));

        try {
            HttpResponse response = httpClient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            jsonObject = JSONObject.fromObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;

    }

}
