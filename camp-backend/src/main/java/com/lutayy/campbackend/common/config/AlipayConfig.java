package com.lutayy.campbackend.common.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101800716513";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCegij/yhI2Ggwc2wv/v5nUjEITuIusXo2zoYrST+Q0YZmQT73emmBaMho13I/pBdzWVaLBzRTx/ihCNr4G5y06mWx3K4peARBWuvIVFbuNvTidErPrj5Bo4/WhSMihgHR9NJ68TrPMtZRrhRj4FQCsR5OGlLQje5M3o4pHdVZqoaYy4G6MmkhGhnJaZOzEfrEM+7o1P0xvwvLEc1lHCHZnvefcLEaR7v+8HEdn/8ddGOxcihFeha/L12i4UxeWxHYKybhpIpG7uID/l+S1Arzz4rK5qHCNAj0TZ1f+CnkIaYTQ2sfFI2yr0o/aeWwo7FWY/IcOQIL0KDDcC5tIXxjXAgMBAAECggEAHwVmNLk9Wy051aPg80pyNrDEdcuWOkO/A7rHymUsSjwXq7CPfjNETCKmEoh5+abWE3RrB/a09K9C/cJs6jjwFxnTqnbw+3jF09N+cTXxoMm5fLQeVimgn4aNmeT+ruzO33oe3segVJ3s0CYaPuMNYKoZaytLHe6fXzI4a8fBA9kTk6mX9I891WV7A1Gm0/9bOWqb7kJNl6iN8ckUD2XE/hO5lyPiNabZUUQKyDSB+4R1ebGYdtUFfEXrP9fPveNEcKnChA5xxpQlhMM2+bcw/XxUEk1XaOvoNfJko7njQbZPDxmUhZzm7kq1i/HbjHmA02xZbz4J5SVAGUzNzbfq0QKBgQDkAvEvsb5aUsGws3eSRkk+jsLTghVZ/DC/5Wu+lNIIfwWQzGF6lkDy2c19yD5PMrk1Rzdnjz/oD4ZOp2KpShr240Nj6ZUDjqlF5Neu6zOHcxvYG6QLquW/0eB8HeWfo9/rOCu0ECywxGwQwBIxXvAvfVGya69n/VWXAcOaqzKf1QKBgQCx9yWAwguqKDxYaOt5b+oEGhGshUrX3cCh4QFXEGJ99aO1aaj/m4i08Ls012CAqvGjj1VqIWVjwLhDBxkTnBSh7NcFyCVzQA/8xd5ap6KvcRoscD3sSG/lr8+KHobUnEXh0lBlmMCsRZOHO8mPXtyMCuC+csElHq3FWnFgVD9X+wKBgDV0Cx7nSHzcUdQe/rUoDRvBVCNJp7/Ew5j5K90t/l9HiAJ+J4wZlXhZXzdRrmnatlirLW31w0GgAhiGXTbazJ98f9v4cpq24zAjifzT3uyuK6TqNfcWRW5GwZ3XVHGsdV4rePNpp1B5e+whWyJ/40b+1hxB6okydtJD85ta7y9RAoGAGXUwUTXw8ztkPoVrwehngoS0KIi8xxp6tu14Aptea4RbEdV2jnISYUfaj9mLfXx2qp4TauYyGc30zenTBYIbVXqK29HgwZbVcqRfhXpbhFtakibQRN+r2iCHQMvTH9DnEkHCrHXogfxF+V9tGuxIAmR/9950kLnNw+CHPW9vzEcCgYBNANGtjBrnaAWoVYnJRTctYDSJcpO65ACQymXi1gp1mpbdwZ5vNbZheWR0PkfAGWgpSUgme7yGdBluUM+4jtq+ho5NA63n1fiDWbuAhUn4IzvCgLDWdN5zP85LKLtDlC/rKLfOVC82nz9sAftVrHVNt6IY9l2+s1OKqF62wmr+VQ==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmgs728+zsZtLCiYDLNCsGPN40IhCyqKBWZN4Ai1tbTG+/KEBP5FYHqaQbPKXaeoeN8G6fKdX3vaO/Z1gdqwl9CBrSv7HcEw/qhRJN0T4B/OJOBpAZQ19iTGBRoZgkzadfcXUojSPoC2rCy4fg+8xsxFQKXgzm036gJYzG8SxUD2ZMM9ujbkIjZj4uCOG0DFtsvK+s9hGa1C+DiguRxbdgYD59i1tSshH4tNJOt02kc0JxaFxt4pY8cf3B/+IT5dCQxohWK7QfKZdufG7pwdZtvM9b16Z6Go1azG0K79kdqB1nA5EH6Ne9EeMFYncTAqib46C+mABISWD5F7Ht8ud1QIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

