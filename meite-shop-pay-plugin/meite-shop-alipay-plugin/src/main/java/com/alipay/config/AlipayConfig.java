package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
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
	public static String app_id = "2016101100657497";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCn3fNDw7BuZc4il5ELwAJ+W0ZPY/UYG7iwMKwfudvih6jKe4iLbnxOhNobxoNiHDRUqCVf+TAVh44wh8/l6hiZ/4ZXM3zdceD8qOGa8NPF1Mc4XFcHbrjZ5+Zd++c8+Yr1yIdwftUGV9PYzoCJ7dHvfdwzUhGFN7uHlYFjGvLYfkzRFgAmFuUulxMrFSilmpJli7juLq68JzckxaGTm5Wo74JIYKXpbT/v6TL6KO+pz2dtjq4aCjmEJrxpy9xkehwNuryaD5FFYCsuTp4ENV8JlvySJTAgxXXIFHKcV8I0xDQ/23hxtp1lzUCrLTA4DCxP/kUJl7XcZ69U5W8xX3IfAgMBAAECggEAZb8u0rUK/G8yeV8a6F9SY5oZTMeOaDVtRUXLHlO+Blkposkroy+tor39oXY6bj4gLevY84oLbhFCywGWromsrbLmj3ZCycpbj2khjZNJGRD2S6AUlAm1e0aHKBImP5wVcRJBnZsSrYyQeEn6OR/v7lSAIo8s1W1Jek3RJevQpA02XyS13OkTRDZeSeIFqKQ9s3sTk90RtyUmG4p7gMKc59N47IszCQz5aGqHFqvem1iOUvwmEnFgIeZOaruhB7p5hrHX5cpMrx1A8cMN55ZZ9lO1UF6oOz4kcLUez0LrCXlDGLP1WNj4p8GjVn0XpzHMGKhkM+7jf62H5EOLF20VeQKBgQDPZ6IycZcr6epnroHAjSXAX/FCYY7WbzyaGol9Pl2BY11V20+Hqs9MoDyPGthMWDyvecLm64Vs9sGtg5DGoZ8AfpsytTJmNjB4WHY8Trmnyoy0mVSlgtZlZCATHPT+wY/YE9+y9zoi4XkiVh8KsCYPoOPtv0hUWT3VmO1jV+OVNQKBgQDPMsuAg4Unx2ZS0d5UEVrg6SiAmMzyeICapqlfJSU+ZcDGuSa/wHhCYLvVU75yOoATjYRGR2RiohD5HBuL6IIzzXxJMt/5mb8KJaYq0oPBUj9NrMf0DuLuSvpgSAjdjFl734fOcbaKnGQUyxrujMSjA04mdrM5igOQ1Xyiw3y4gwKBgBI0DlBPF+2KwkAHWOwFd2LDCPHCjj7T1IXR/m83og1RI0f9z6UgoMEg7y/AM2u/8tOT5D9QPI2ruqP4ACTu2n24nvZ8YMgHyYlGelogrrrDVEYljwBX02liuE31JKuXYYCdfey/NiMJlxAkbuUJQM3uWRLszvRYQM0Wdv9ZH2hhAoGAIhdKcm+xVfUx+ch8Gzrrx/u05Uu+1NpM1qU7UuNKuHSHfmryXDRv1QQTg6sOt762WgE71VBjzPK7djFQuvlbiuhxXZ3YSjoou6OsLnmKoiFFNyOJlrdoUSrW/UZ85u7zvjTuJGKDVVUcBmvQl5VjuSqPDjcTkBEMOyVVJ3agq78CgYEAu4TKxVxpBzUg8v/5ECJS3LZgDZaspi8PrCR7NR0482Pr46k9URdpglFpm/cppqZuPPxRH+tHnVWUgxAZoy4e5LcIszAOHfWar/MULaDIwZCwKZ4gl3mTyXUD1BR7dFBcbniKmCQsBL2O3GB2l/YnEGNv0/el0Wt49b5/k+4GyPM=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp93zQ8OwbmXOIpeRC8ACfltGT2P1GBu4sDCsH7nb4oeoynuIi258ToTaG8aDYhw0VKglX/kwFYeOMIfP5eoYmf+GVzN83XHg/KjhmvDTxdTHOFxXB2642efmXfvnPPmK9ciHcH7VBlfT2M6Aie3R733cM1IRhTe7h5WBYxry2H5M0RYAJhblLpcTKxUopZqSZYu47i6uvCc3JMWhk5uVqO+CSGCl6W0/7+ky+ijvqc9nbY6uGgo5hCa8acvcZHocDbq8mg+RRWArLk6eBDVfCZb8kiUwIMV1yBRynFfCNMQ0P9t4cbadZc1Aqy0wOAwsT/5FCZe13GevVOVvMV9yHwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/ACPSample_B2C/frontRcvResponse";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://xxmit.natapp1.cc/unionPayAsynCallback";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:\\";


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

