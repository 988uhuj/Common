package github.chenupt.talk.net.service;

import org.androidannotations.annotations.EBean;

import java.util.HashMap;
import java.util.Map;

import github.chenupt.talk.config.TalkConfig;
import github.chenupt.talk.config.UrlManager;
import github.chenupt.talk.entity.TComment;
import github.chenupt.talk.net.TalkHttpClient;
import github.chenupt.talk.net.TalkHttpResponseHandler;

/**
 * Created by chenupt@gmail.com on 2014/9/8.
 * Description TODO
 */
@EBean
public class NetService {

    /**
     * 获取首页数据
     * @param cursor
     * @param handler
     */
    public void getMainList(long cursor, int pageSize, TalkHttpResponseHandler handler){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(TalkConfig.CURSOR, cursor);
        map.put(TalkConfig.PAGE_SIZE, pageSize);
        TalkHttpClient.postService(UrlManager.QUERY_COMMENT, map, handler);
    }


    public void postComment(TComment tComment, TalkHttpResponseHandler handler){
        TalkHttpClient.postService(UrlManager.POST_COMMENT, tComment, handler);
    }
}
