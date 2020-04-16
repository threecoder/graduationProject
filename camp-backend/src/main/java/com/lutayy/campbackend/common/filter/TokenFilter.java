package com.lutayy.campbackend.common.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.util.JwtUtil;
import com.lutayy.campbackend.dao.AdminMapper;
import com.lutayy.campbackend.pojo.Admin;
import com.lutayy.campbackend.pojo.request.TokenRequest;
import com.sun.deploy.net.cookie.CookieUnavailableException;
import javafx.scene.AmbientLight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@WebFilter(filterName = "TokenFilter",urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Autowired
    AdminMapper adminMapper;

    @Value("${SECRET_KEY}")
    private String SECRET_KEY;

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String url = request.getRequestURI();
        String contentType = request.getContentType();
        if (url.contains("/register") || url.contains("/login") || url.contains("/index") || url.contains(".") || url.equals("/") || contentType.contains("multipart/form-data")) {
            chain.doFilter(request, response);
            return;
        }
        System.out.println("--- " + url + "未被过滤 ---");
        Cookie[] cookies = request.getCookies();
        String token = null;

        JSONObject resMessage = new JSONObject();
        if (cookies == null) {
            resMessage.put("code", "error");
            resMessage.put("msg", "未登录");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(resMessage.toJSONString());
            response.setContentType("application/json;charset=UTF-8");
            return;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("token")) {
                token = cookies[i].getValue();
                break;
            }
        }
        if (token == null || token.equals("")) {
            resMessage.put("code", "error");
            resMessage.put("msg", "请重新登陆！");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(resMessage.toJSONString());
            response.setContentType("application/json;charset=UTF-8");
            return;
        }
        /**
         * 重写请求参数，由token得到用户信息并加进请求中
         */
        TokenRequest tokenRequest = JwtUtil.unsign(token, TokenRequest.class, SECRET_KEY);
        if (tokenRequest != null) {
            if (url.contains("/student")) {
                if (tokenRequest.getRole() == null || !tokenRequest.getRole().equals("student")) {
                    resMessage.put("code", "error");
                    resMessage.put("msg", "未登录");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(resMessage.toJSONString());
                    response.setContentType("application/json;charset=UTF-8");
                    return;
                }
            }
            if (url.contains("/member")) {
                if (tokenRequest.getRole() == null || !tokenRequest.getRole().equals("member")) {
                    resMessage.put("code", "error");
                    resMessage.put("msg", "未登录");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(resMessage.toJSONString());
                    response.setContentType("application/json;charset=UTF-8");
                    return;
                }
            }
            if (url.contains("/admin")) {
                if (tokenRequest.getRole() == null || !tokenRequest.getRole().equals("admin")) {
                    resMessage.put("code", "error");
                    resMessage.put("msg", "未登录");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(resMessage.toJSONString());
                    response.setContentType("application/json;charset=UTF-8");
                    return;
                }
            }
            Integer id = tokenRequest.getId();
            if (url.contains("/admin")) {
                Admin admin = adminMapper.selectByPrimaryKey(id);
                if (admin == null || admin.getIsLocked()) {
                    resMessage.put("code", "fail");
                    resMessage.put("msg", "该账号已被锁定！");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(resMessage.toJSONString());
                    response.setContentType("application/json;charset=UTF-8");
                    return;
                }
            }
            Map<String, String[]> map = new HashMap<String, String[]>(request.getParameterMap());
            ParameterRequestWrapper requestWrapper;
            String method = request.getMethod().toLowerCase();
            if (method.equals("post")) {
                requestWrapper = new ParameterRequestWrapper(request, map);
                String body = new String(requestWrapper.getBody());
                JSONObject jsonObject = JSON.parseObject(body);
                if (jsonObject == null) {
                    jsonObject = new JSONObject();
                }
                jsonObject.put("id", id);
                requestWrapper.setBody(jsonObject.toJSONString().getBytes());
                request = requestWrapper;
            } else if (method.equals("get")) {
                map.put("id", new String[]{id.toString()});
                requestWrapper = new ParameterRequestWrapper(request, map);
                request = requestWrapper;
            }
            String token2 = JwtUtil.sign(tokenRequest, 30 * 60 * 1000, SECRET_KEY);
            Cookie cookie = new Cookie("token", token2);
            //cookie.setMaxAge(3600);
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            resMessage.put("code", "error");
            resMessage.put("msg", "请重新登陆！");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(resMessage.toJSONString());
            response.setContentType("application/json;charset=UTF-8");
            return;
        }
        chain.doFilter(request, resp);
    }

    @Override
    public void destroy() {
    }

}
