package com.qf.demo.test02.other.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * Created by Administrator on 16-1-16.
 */
public class ZhuShouHttpUtil {
    /**
     *进行GET请求
     *
     * @param httpUrl
     *            请求地址
     * @return 请求结果
     */
    public static Object doGet(String httpUrl){
        if (httpUrl == null){
            throw new NullPointerException("get 请求 url 不能为空！！！");
        }
        InputStream inputStream = null;

        InputStreamReader reader = null;

        BufferedReader bufferedReader = null;

        try {
            URL url = new URL(httpUrl);
            //打开一个连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式
            conn.setRequestMethod("GET");
            //设置连接超时时间
            conn.setConnectTimeout(5000);
            //设置读取超时时间
            conn.setReadTimeout(5000);
            //设置可读操作
            conn.setDoInput(true);
            //进行连接
            conn.connect();

            //获取返回码
            int responseCode = conn.getResponseCode();
            //如果返回码是200表示请求成功，方可作后面的读取操作
            if (responseCode == HttpURLConnection.HTTP_OK){
                inputStream = conn.getInputStream();

                reader = new InputStreamReader(inputStream);

                bufferedReader = new BufferedReader(reader);

                StringBuffer resultBuffer = new StringBuffer();

                String line = null;
                //循环读取每一行，判断是否为空，读完为止
                while ((line = bufferedReader.readLine()) != null){

                    //把每一行的结果连接起来
                    resultBuffer.append(line);
                }

                String result = resultBuffer.toString();

                Log.w("tag","请求成功 result = " + result);

                return result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream == null){
                    Log.e("tag","请求失败");
                    return null;
                }
                inputStream.close();
                reader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.e("tag","请求失败");
        return null;
    }

    /**
     *
     * @param httpUrl
     *            请求地址
     * @param params
     *            参数
     * @return  请求结果
     */
    public static Object doPost(String httpUrl, Map<String,String> params){
        if (httpUrl == null || params == null){
            throw new NullPointerException("post 请求 url 或 参数不能为空");
        }
        //参数处理 把参数map转换成字符串
        //platform=2&ver=v1.2.2

        //先把Map转化成set
        Set<Map.Entry<String,String>> entries = params.entrySet();
        //从set里面获取迭代器
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        StringBuffer paramsBuffer = new StringBuffer();
        while (iterator.hasNext()){
            //获取entry
            Map.Entry<String, String> entry = iterator.next();
            //entry的key进行连接
            String key = entry.getKey();
            paramsBuffer.append(key);
            paramsBuffer.append("=");

            //获取entry的value进行连接
            String value = entry.getValue();
            paramsBuffer.append(value);
            paramsBuffer.append("&");
        }

        String paramsString = paramsBuffer.toString();
        paramsString = paramsString.substring(0, paramsString.length() - 1);

        InputStream inputStream = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        OutputStream outputStream = null;

        try {
            //创建一个url
            URL url = new URL(httpUrl);
            //打开连接的属性
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置连接的属性
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);

            //开始连接
            conn.connect();
            //获取输出流
            outputStream = conn.getOutputStream();
            //向服务器写入参数
            outputStream.write(paramsString.getBytes());
            outputStream.flush();
            //获取请求码
            int responseCode = conn.getResponseCode();
            //如果返回码==200表示请求写入数据成功了
            if (responseCode == HttpURLConnection.HTTP_OK){
                //获取输入流
                inputStream = conn.getInputStream();
                reader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(reader);

                StringBuffer resultBuffer = new StringBuffer();
                //循环读取返回的内容
                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    //拼接内容
                    resultBuffer.append(line);
                }

                String result = resultBuffer.toString();
                Log.w("tag","请求成功 result = " + result);
                //返回请求的结果
                return result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //请求结束后关闭流
            try {
                //如果没有连网的情况下，这些刘都为null，要避免空指针异常
                if (inputStream == null){
                    Log.e("tag","请求失败");
                    return null;
                }
                inputStream.close();
                reader.close();
                bufferedReader.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.e("tag","请求失败");
        return null;
    }
}
