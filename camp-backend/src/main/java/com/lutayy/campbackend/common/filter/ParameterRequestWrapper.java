package com.lutayy.campbackend.common.filter;

import jodd.io.StreamUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;


public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    //Post参数
    private  byte[] body; //用于保存读取body中数据

    //Get参数
    private Map<String, String[]> params;

    public ParameterRequestWrapper(HttpServletRequest request, Map<String, String[]> newParams) throws IOException {
        super(request);
        //读取请求的数据保存到本类当中
        body = StreamUtil.readBytes(request.getReader(), "UTF-8");
        params = newParams;
    }
    //覆盖（重写）父类的方法
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
    //覆盖（重写）父类的方法
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    /**
     * 获取body中的数据
     * @return
     */
    public byte[] getBody() {
        return body;
    }
    /**
     * 把处理后的参数放到body里面
     * @param body
     */
    public void setBody(byte[] body) {
        this.body = body;
    }

    @Override
    public String getParameter(String name) {
        String result = "";

        Object v = params.get(name);
        if (v == null) {
            result = null;
        } else if (v instanceof String[]) {
            String[] strArr = (String[]) v;
            if (strArr.length > 0) {
                result =  strArr[0];
            } else {
                result = null;
            }
        } else if (v instanceof String) {
            result = (String) v;
        } else {
            result =  v.toString();
        }

        return result;
    }

    @Override
    public Map getParameterMap() {
        return params;
    }

    @Override
    public Enumeration getParameterNames() {
        return new Vector(params.keySet()).elements();
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] result = null;

        Object v = params.get(name);
        if (v == null) {
            result =  null;
        } else if (v instanceof String[]) {
            result =  (String[]) v;
        } else if (v instanceof String) {
            result =  new String[] { (String) v };
        } else {
            result =  new String[] { v.toString() };
        }

        return result;
    }



}
