package com.github.rudda.networkconnection;

import android.util.Base64;

import java.util.HashMap;

/**
 * Created by Rudda Beltrao on 25/04/2017.
 */

public class CustomRequest {

    private HashMap<String, String> params;
    private HashMap<String, String> headers;
    private int method;
    private String baseUrl;
    private String router;
    private String tag;
    private int result;




    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static String generateAutorizationHeader(String user, String pass){

        String credentials = user + ":" + pass;
        String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        return base64EncodedCredentials;

    }


    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }
}
