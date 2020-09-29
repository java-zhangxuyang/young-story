package com.young.base.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.young.base.utils.MapCache;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {

    protected MapCache cache = MapCache.single();

    public static String THEME = "themes/default";

    public String render(String viewName) {
        return THEME + "/" + viewName;
    }

    public String render404() {
        return "comm/error_404";
    }

    public BaseController title(HttpServletRequest request, String title) {
        request.setAttribute("title", title);
        return this;
    }

    public BaseController keywords(HttpServletRequest request, String keywords) {
        request.setAttribute("keywords", keywords);
        return this;
    }


    
    /**
     * 输出JSON数据
     * 
     * @param response
     * @param jsonStr
     */
    public void writeJson(HttpServletResponse response, String jsonStr) {
        response.setContentType("text/json;charset=utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.write(jsonStr);
            pw.flush();
        } catch (Exception e) {
            log.info("输出JSON数据异常", e);
        }finally{
            if(pw!=null){
                pw.close();
            }
        }
    }
    /**
     * 
     * 向页面响应json字符数组串流.
     * 
     * @param response
     * @param jsonStr
     * @throws IOException
     * @return void
     * @author 蒋勇
     * @date 2015-1-14 下午4:18:33
     */
    public void writeJsonStr(HttpServletResponse response, String jsonStr) throws IOException {

        OutputStream outStream = null;
        try {
            response.reset();
            response.setCharacterEncoding("UTF-8");
            outStream = response.getOutputStream();
            outStream.write(jsonStr.getBytes("UTF-8"));
            outStream.flush();
        } catch (IOException e) {
            log.info("输出JSON数据异常(writeJsonStr)", e);
        } finally {
            if(outStream!=null){
                outStream.close();
            }
        }
    }

    public void writeJsonStr(HttpServletResponse response, InputStream in) throws IOException {
        
        if(null == in ){
            return ;
        }
        OutputStream outStream = null;
        try {
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            outStream = response.getOutputStream();
            int len = 0;
            byte[] byt = new byte[1024];
            while ((len = in.read(byt)) != -1) {
                outStream.write(byt, 0, len);
            }
            outStream.flush();

        } catch (IOException e) {

            log.info("输出JSON数据异常(writeJsonStr)", e);
        } finally {
            if(outStream!=null){
                outStream.close();
                in.close();
            }
        }
    }
    
    
    /**
     * 输出JSON数据
     * 
     * @param response
     * @param jsonStr
     */
    public void writeJson(HttpServletResponse response, Object obj) {
        response.setContentType("text/json;charset=utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.write(JSON.toJSONString(obj));
            
            pw.flush();
        } catch (Exception e) {
            log.info("输出JSON数据异常", e);
        }finally{
            if(pw!=null){
                pw.close();
            }
        }
    }
    
    
    
    
   public void writeHtml(HttpServletResponse response, String html) {
        response.setContentType("text/html;;charset=utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.write(html);
            pw.flush();
        } catch (Exception e) {
            log.info("输出HTML数据异常", e);
        }finally{
            if(pw!=null){
                pw.close();
            }
        }
    }



}
