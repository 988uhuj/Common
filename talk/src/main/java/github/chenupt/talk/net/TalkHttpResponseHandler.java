package github.chenupt.talk.net;

import android.util.Log;

import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import github.chenupt.common.util.JsonUtil;


/**
 * Created by chenupt@gmail.com on 2014/5/27.
 * Description : TODO
 */
public abstract class TalkHttpResponseHandler<T> extends TextHttpResponseHandler {

    public final static String TAG = "TalkHttpResponseHandler";

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseBody) {
        Log.d(TAG, "onSuccess : " + responseBody);
        TalkHttpEntity<T> talkHttpEntity = JsonUtil.fromJsonToObject(responseBody, TalkHttpEntity.class);
        onSuccess(statusCode, headers, responseBody,
                talkHttpEntity.getAction(),
                talkHttpEntity.getStatus(),
                talkHttpEntity.getBody(),
                talkHttpEntity.getMsg());
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseBody, Throwable error) {
        Log.d(TAG, "onFailure : " + error.getMessage());
    }



    public abstract void onSuccess(int statusCode, Header[] headers, String responseBody, String action, int status, T body, String msg);

}
