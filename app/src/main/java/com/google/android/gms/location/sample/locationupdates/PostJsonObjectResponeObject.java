package com.google.android.gms.location.sample.locationupdates;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Sifis on 15/6/2015.
 */
public class PostJsonObjectResponeObject extends JsonRequest<JSONObject> {

    /**
     * Creates a new request.
     * @param url URL to fetch the JSON from
     * @param listener Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    /*public PostJsonArrayResponeArray(String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, null, listener, errorListener);
    }*/

    public PostJsonObjectResponeObject(int method, String url, JSONObject jObjectRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        super(method, url, (jObjectRequest == null) ? null : jObjectRequest.toString(),listener, errorListener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
}
