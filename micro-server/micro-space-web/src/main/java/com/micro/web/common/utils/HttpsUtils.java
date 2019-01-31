package com.micro.web.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpsUtils {

    private static final String HTTPS_URL = "https://safe.lhspace.info/space/swagger-ui.html";
    public static void main(String[] args) {
        System.out.println( sendHttpsPost(HTTPS_URL,null));;
    }

    //设置链接超时和请求超时等参数，否则会长期停止或者崩溃
    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).setConnectionRequestTimeout(60000).build();

    public static String sendHttpsPost(String url, Map<String, String> params) {
        String responseContent = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            if(null!=params){
                for (String key : params.keySet()) {
                    nvps.add(new BasicNameValuePair(key, params.get(key)));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseContent = EntityUtils.toString(httpEntity, HTTP.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseContent;
    }

    //创建SSL安全连接
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }
}

