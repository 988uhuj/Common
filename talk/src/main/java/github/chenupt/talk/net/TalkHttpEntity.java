package github.chenupt.talk.net;

import com.google.gson.JsonObject;

/**
 * Created by chenupt@gmail.com on 2014/5/27.
 * Description : TODO
 */
public class TalkHttpEntity {
    private int status;
    private String action;
    private JsonObject body;
    private String msg;
    private int total;
    private int page;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonObject getBody() {
        return body;
    }

    public void setBody(JsonObject body) {
        this.body = body;
    }
}
