package com.lutayy.campbackend.common.config;

import org.springframework.stereotype.Component;

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


@Component
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101800716513";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCCZpA6uaRTot+DlKpk2yMPFUMsEXao//WopbLppAXJT/4iFttJR0SyyOkCfQbRgp0j0GxYNfrDUOolUo5woSmELpoEnhVkQazxUNbuzi7ln4d4iAT7Vxo5fpHoo/hxP7SvTfeYPkDJPlIkQ5KHvaVQtHYZHAGEd2b1Duj+QF/FayH1lG1IxNCiOp6G9Q+5utMyd1Cyk+mf+y19oEhvkhNbKxki23SUGfLZ7RvzSB1yTewyxTR7tJvVdX8290TS/++nyV20kOSPlsVRVygVmuxVnqyxVodaNz+7NzPoLuPGMT9DlGxKwjCE4fyBtuC57MeoP+HkVr1ShypzC1zkHm4dAgMBAAECggEAQItxwo+XBgrtZZzVhpxtSw70M8DlIhunxfVWjI0yjAW7PbZgjnHnUDNgGmo2UMe87jhBGJyHZdwMObMWbCtpeFL0y2pQHlLfvoaMDKIj1mbTUFbLWHkxstmycRuQYsXssoT+E+5kdPgTAn4r7mJxjHP2rNEO8zmCzbO2ofrNLNLzYuXz7IPVXVwAPEpjrvurOInqFcLmgGHqnIM+24O2nOGt14yuntZLAj3l1yaCFYSiEBUR5j+FhSNX517iKt7TIJyzDY1FyvIioztyVP6eJN0uwxGnfzQYx7H1LZ7jxz9Fb0uAviyJTyAc+M+xC8uux+1srkPkyCCIIXWJGEhbQQKBgQDoJ1/FyRxfwKZ8ofpjdqCMeuD3pwW3vX1b2P1+uepEPTRVAJOBTpKIsW5KwfgJn9R1MfPAqMDSTrL+7F+tMZiHOCwLsHCckUXj4dR2UbzbVUaLNUpSsbDUlMAqqG7T+zH66aFg3Adn7MTiKZ3Z3nERi7NLEALiaSwSMDZ60a2VkQKBgQCPy4cXvzxXRV3jR3SJix6tn6ydBvBbq1MwFIKgTbV+CFyolQHIlMM+Po2zSFaQBaeUXt7ugaX3+NHx5y6AcEksu0gnyRPbXq63dOBRRvsrh5EKgn3v2fRHj2dJBH+hYvG4Kn8zmqEPgxIsDcSmRD2DFi3N/LiHlSH89q/VvRqZzQKBgQDTyuAP4+qz3GDGwUtaJGZR4uSMat7HguLzwSF61IfJm4C7r5viUtlSRXTWVOFBfoPoKxsyf4HyG4QnF2Qt8FozumZMf7oszEfTnzoie21tN/30kDhu+tbVCaPLBwCc+cTeIPhuEi3bfOEdZ1Qv6ESE+ksmxsOq5MIlEcESCWpwUQKBgA4L8bxuH/Tef9dt6YJuJqcLTA9i2CP8TnoJ5uDF9o7DPlnxhGUVsyTnc2wqCtqy0rX5tolR/OaGVevti2tcjKlj+h541Am/7A1by3XlGzAbZB3EtYyym9xJs+s8F0OqqLY24Ion9sfPI/1NswIS9HdTuVG/+D62YMWXS4HzoR+ZAoGBAMzODEvaL0Oe/cBOPFnIHYLQlT+VPRDloLKG3DZ59v7gKGbZL5uuxeYdkPYN24kvrwTx+96gwA5W+8/sng3VANRuX04hzFIdbJTYn/lZKoKUeJbLgYgiJXHRxTizSvA0MFHhwdkmHw6RyIDC4ysAsL/Vouxql8LqSVg5L0SLHVkD";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmgs728+zsZtLCiYDLNCsGPN40IhCyqKBWZN4Ai1tbTG+/KEBP5FYHqaQbPKXaeoeN8G6fKdX3vaO/Z1gdqwl9CBrSv7HcEw/qhRJN0T4B/OJOBpAZQ19iTGBRoZgkzadfcXUojSPoC2rCy4fg+8xsxFQKXgzm036gJYzG8SxUD2ZMM9ujbkIjZj4uCOG0DFtsvK+s9hGa1C+DiguRxbdgYD59i1tSshH4tNJOt02kc0JxaFxt4pY8cf3B/+IT5dCQxohWK7QfKZdufG7pwdZtvM9b16Z6Go1azG0K79kdqB1nA5EH6Ne9EeMFYncTAqib46C+mABISWD5F7Ht8ud1QIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://free.shenzhuo.vip:13934/aliPayNotify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 返回格式
    public static String format = "json";

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

