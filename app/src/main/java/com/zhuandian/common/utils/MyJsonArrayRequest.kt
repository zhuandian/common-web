package com.zhuandian.common.utils

import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONArray
import org.json.JSONException
import java.io.UnsupportedEncodingException

/**
 * desc :重写JsonArrayRequest，解决乱码问题
 * author：xiedong
 * date：2019/7/23
 */
class MyJsonArrayRequest(url: String?, listener: Response.Listener<JSONArray>?, errorListener: Response.ErrorListener?) : JsonArrayRequest(url, listener, errorListener) {
    override fun parseNetworkResponse(response: NetworkResponse?): Response<JSONArray> {
        try {
            response!!.headers["HTTP.CONTENT_TYPE"] = "utf-8"
            //                String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            val jsonString = String(response!!.data, charset("utf-8"))
            return Response.success(JSONArray(jsonString), HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: UnsupportedEncodingException) {
            return Response.error(ParseError(e))
        } catch (je: JSONException) {
            return Response.error(ParseError(je))
        }
    }
}