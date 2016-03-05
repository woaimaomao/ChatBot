package com.dyl.chatbot.tools;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 用于连接网络的类，发送消息
 * 调用的是图灵机器人网站提供的接口
 * 具体接口使用请参考网站
 * www.tuling123.com
 * Created by Jay on 2016/1/22.
 */
public class SendMessage implements Runnable {
 
    public static final int RESPONES_OK = 1;
    public static final int RESPONES_ERROR = 2;
    protected static final String TAG = "SendMessage";
 
    private final String API_ANDRESS = "http://www.tuling123.com/openapi/api";
    private final String API_KEY = "f652aa012be90de13b45c99723acdbae";
    private final String baseUrl="http://182.254.135.30:8888/talk/game/message";
    private String msg;
    private Handler handler;
    private RequestQueue requestQueue;
 
    public SendMessage(Context context,Handler handler, String msg) {
        this.handler = handler;
        this.msg = msg;
        requestQueue = Volley.newRequestQueue(context);
    }
 
    @Override
    public void run() {
        sendMessage();
        /*String requestString = "key=" + API_KEY + "&info=" + msg;
        String responseString = "";
        try {
            URL url = new URL(API_ANDRESS);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(1000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(requestString.getBytes("utf-8"));
            outputStream.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String temp;
            while ((temp = reader.readLine()) != null) {
                responseString += temp;
            }
            //将消息通过handler传递给主线程
            Bundle bundle = new Bundle();
            bundle.putString("msg", responseString);
            Message message = handler.obtainMessage(RESPONES_OK);
            message.setData(bundle);
            handler.sendMessage(message);
        } catch (MalformedURLException e) {
            handler.sendEmptyMessage(RESPONES_ERROR);
            e.printStackTrace();
        } catch (IOException e) {
            handler.sendEmptyMessage(RESPONES_ERROR);
            e.printStackTrace();
        }*/
    }
    
    private void sendMessage(){
        
        StringRequest request = new StringRequest(Request.Method.POST,baseUrl,new Listener<String>() {

            @Override
            public void onResponse(String arg0) {
              //将消息通过handler传递给主线程
                Bundle bundle = new Bundle();
                bundle.putString("msg", arg0);
                Message message = handler.obtainMessage(RESPONES_OK);
                message.setData(bundle);
                handler.sendMessage(message);
            }
            
            
        },new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                handler.sendEmptyMessage(RESPONES_ERROR);
            }
            
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> paras = new HashMap<>();
                paras.put("info", msg);
                paras.put("userId", "111");
                return paras;
            }
        };
        /*******************方式二************************/
        // 服务器不支持解析json传递参数，所以尚不能使用这种方式
        /*Map<String, Object> paras = new HashMap<>();
        paras.put("info", msg);
        paras.put("userId", 111);
        JSONObject jsonObject = new JSONObject(paras);
        Log.e(TAG, "jsonObject--"+jsonObject.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(baseUrl, jsonObject, new Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject arg0) {
                try {
                    if (arg0.getInt("code") == 100000) {
                        Log.e(TAG, "JsonObjectRequest--" + arg0);
                        Message message = handler.obtainMessage(RESPONES_OK);
                        message.obj = arg0;
                        handler.sendMessage(message);
                    }else{
                        Log.e(TAG, "status code--"+arg0.getInt("code"));
                        handler.sendEmptyMessage(RESPONES_ERROR);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                Log.e(TAG, "VolleyError--"+arg0);
                handler.sendEmptyMessage(RESPONES_ERROR);
            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                         
                return headers;
            }
        };*/
        requestQueue.add(request);
        
    }
}