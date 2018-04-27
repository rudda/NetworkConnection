package com.github.rudda.networkconnection.CustomRequests;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Rudda Beltrao on 09/09/2017.
 */

public class StringCustomRequest extends Request<String> {

    private final Map<String, String> headers;
    private Response.Listener<String> listener;
    private Map<String, String> params;
    private String requestBody;

    public StringCustomRequest(int method, String url, Map<String, String> params, Map<String, String> headers,
                               Response.Listener<String> reponseListener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);

        this.listener = reponseListener;
        this.params = params;
        this.headers = headers;


    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        return headers;
    }

    protected Map<String, String> getParams()
            throws com.android.volley.AuthFailureError {
        return params;
    }

    public void setBody(String body){

        this.requestBody = body;

    }

    @Override
    public byte[] getBody() throws AuthFailureError {

        try {

            return this.requestBody == null ? null : requestBody.getBytes("utf-8");

        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
            return null;
        }


    }



    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new String(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(String response) {
        listener.onResponse(response);
    }
}
