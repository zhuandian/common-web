package com.zhuandian.common.utils

import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonObjectRequest

import org.json.JSONException
import org.json.JSONObject

import java.io.UnsupportedEncodingException

/**
 * desc :重写JsonObjectRequest，解决volley中午乱码问题
 * author：xiedong
 * date：2019/7/18
 */
class MyJsonRequest : JsonObjectRequest {

    override fun parseNetworkResponse(response: NetworkResponse): Response<JSONObject> {
        try {
            response.headers["HTTP.CONTENT_TYPE"] = "utf-8"
            //                String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            val jsonString = String(response.data, charset("utf-8"))
            return Response.success(JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: UnsupportedEncodingException) {
            return Response.error(ParseError(e))
        } catch (je: JSONException) {
            return Response.error(ParseError(je))
        }

    }

    /**
     * @param method
     * @param url
     * @param jsonRequest
     * @param listener
     * @param errorListener
     */
    constructor(method: Int, url: String, jsonRequest: JSONObject, listener: Response.Listener<JSONObject>,
                errorListener: Response.ErrorListener) : super(method, url, jsonRequest, listener, errorListener) {
    }

    /**
     * @param url
     * @param jsonRequest
     * @param listener
     * @param errorListener
     */
    constructor(url: String, jsonRequest: JSONObject?, listener: Response.Listener<JSONObject>,
                errorListener: Response.ErrorListener) : super(url, jsonRequest, listener, errorListener) {
    }

}
