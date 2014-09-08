package github.chenupt.talk.net;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Map;

import github.chenupt.common.util.JsonUtil;
import github.chenupt.talk.config.TalkConfig;

/**
 * Created by chenupt@gmail.com on 2014/9/7.
 * Description TODO
 */
public class TalkHttpClient {

    public static final String TAG = "TalkHttpClient";

    private static AsyncHttpClient client;
    static{
        client = new AsyncHttpClient();
        client.setTimeout(TalkConfig.TIME_OUT);
        client.setUserAgent(TalkConfig.USER_AGENT);
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(TalkConfig.getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(TalkConfig.getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getService(String url, TalkHttpResponseHandler responseHandler) {
        Log.d(TAG, "get url:" + TalkConfig.getAbsoluteUrl(url));
        client.get(TalkConfig.getAbsoluteUrl(url), responseHandler);
    }

    public static void getService(String url) {
        Log.d(TAG, "get url:" + TalkConfig.getAbsoluteUrl(url));
        client.get(TalkConfig.getAbsoluteUrl(url), null);
    }

    public static void postService(String url, Map<String, Object> params, TalkHttpResponseHandler responseHandler) {
        Log.d(TAG, "post url:" + TalkConfig.getAbsoluteUrl(url));
        Log.d(TAG, "post params:" + JsonUtil.fromObjectToJson(params));
        RequestParams requestParams = new RequestParams();
        requestParams.add(TalkConfig.POST_PARAM, JsonUtil.fromObjectToJson(params));
        client.addHeader("Content-Type", "application/json; charset=utf-8");
        client.post(TalkConfig.getAbsoluteUrl(url), requestParams, responseHandler);
    }

    public static void postService(String url, Object object, TalkHttpResponseHandler responseHandler) {
        Log.d(TAG, "post url:" + TalkConfig.getAbsoluteUrl(url));
        Log.d(TAG, "post params:" + JsonUtil.fromObjectToJson(object));
        RequestParams requestParams = new RequestParams();
        requestParams.add(TalkConfig.POST_PARAM, JsonUtil.fromObjectToJson(object));
        client.addHeader("Content-Type", "application/json; charset=utf-8");
        client.post(TalkConfig.getAbsoluteUrl(url), requestParams, responseHandler);
    }

    public static void postService(String url, Map<String, Object> params) {
        Log.d(TAG, "post url:" + TalkConfig.getAbsoluteUrl(url));
        RequestParams requestParams = new RequestParams();
        requestParams.add(TalkConfig.POST_PARAM, JsonUtil.fromObjectHasDateToJson(params));
        client.post(TalkConfig.getAbsoluteUrl(url), requestParams, null);
    }

}
