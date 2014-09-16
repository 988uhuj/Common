package github.chenupt.talk.config;

/**
 * Created by chenupt@gmail.com on 2014/9/7.
 * Description TODO
 */
public class TalkConfig {

    public static final String BASE_URL = "http://192.168.88.148:8080";
    public final static String POST_PARAM = "param";
    public final static String CURSOR = "cursor";
    public final static String PAGE_SIZE = "pageSize";
    public final static String USER_AGENT = "android";
    public final static int TIME_OUT = 15000;    // http 超时15秒

    public static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
