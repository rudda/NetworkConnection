package com.github.rudda.networkconnection;

/**
 * Created by Beltrão on 04/04/2016.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.github.rudda.networkconnection.CustomRequests.JsonArrayCustomRequest;
import com.github.rudda.networkconnection.CustomRequests.JsonObjectCustomRequest;
import com.github.rudda.networkconnection.CustomRequests.StringCustomRequest;
import com.github.rudda.networkconnection.Interfaces.JsonArrayListener;
import com.github.rudda.networkconnection.Interfaces.JsonObjectListener;
import com.github.rudda.networkconnection.Interfaces.StringListener;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by viniciusthiengo on 7/26/15.
 */
public class NetworkConnection {

    private static NetworkConnection instance;
    private Context mContext;
    private RequestQueue mRequestQueue;


    public NetworkConnection(Context c) {
        mContext = c;
        mRequestQueue = getRequestQueue();
    }


    public static NetworkConnection getInstance(Context c) {
        if (instance == null) {
            instance = new NetworkConnection(c);
        }
        return (instance);
    }

    private static boolean verificaConexao(Context contexto) {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return (mRequestQueue);
    }

    public void cancel(String tag) {

        this.mRequestQueue.cancelAll(tag);

    }

    private <T> void addRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

    public void execute(final JsonArrayListener transaction, final CustomRequest customRequest) {

        final JsonArrayCustomRequest request = new JsonArrayCustomRequest(customRequest.getMethod(),
                customRequest.getBaseUrl() + customRequest.getRouter(),
                customRequest.getParams(),

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            String aux = response.toString();
                            transaction.sucess(response, customRequest.getResult());
                        } catch (Exception err) {

                            transaction.fail("erro", customRequest.getResult());

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //transaction.loading(false, customRequest.getResult());
                        Log.i("log", "onErrorResponse(): " + error.getMessage());
                        transaction.fail("erro", customRequest.getResult());

                    }
                });

        request.setTag(customRequest.getTag());

        request.setRetryPolicy(new DefaultRetryPolicy(3 * 1800000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        addRequestQueue(request);


    }

    public void execute(final JsonObjectListener transaction, final CustomRequest customRequest) {

        final JsonObjectCustomRequest request = new JsonObjectCustomRequest(customRequest.getMethod(),
                customRequest.getBaseUrl() + customRequest.getRouter(),
                customRequest.getParams(), customRequest.getHeaders(),

                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String aux = response.toString();
                            transaction.sucess(response, customRequest.getResult());
                        } catch (Exception err) {

                            transaction.fail("erro", customRequest.getResult());

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //transaction.loading(false, customRequest.getResult());
                        Log.i("log", "onErrorResponse(): " + error.getMessage());
                        transaction.fail("erro", customRequest.getResult());

                    }
                });

        request.setTag(customRequest.getTag());

        request.setRetryPolicy(new DefaultRetryPolicy(3 * 1800000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        addRequestQueue(request);


    }

    public void execute(final StringListener transaction, final CustomRequest customRequest) {

        final StringCustomRequest request = new StringCustomRequest(customRequest.getMethod(),
                customRequest.getBaseUrl() + customRequest.getRouter(),
                customRequest.getParams(),

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {


                            transaction.sucess(response, customRequest.getResult());
                        } catch (Exception err) {

                            transaction.fail("erro", customRequest.getResult());

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //transaction.loading(false, customRequest.getResult());
                        Log.i("log", "onErrorResponse(): " + error.getMessage());
                        transaction.fail("erro", customRequest.getResult());

                    }
                });

        request.setTag(customRequest.getTag());

        request.setRetryPolicy(new DefaultRetryPolicy(3 * 1800000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        addRequestQueue(request);


    }


}