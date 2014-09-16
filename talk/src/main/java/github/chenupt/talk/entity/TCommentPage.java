package github.chenupt.talk.entity;

import java.util.List;

/**
 * Created by chenupt@gmail.com on 14-3-22.
 * Description TODO
 */
public class TCommentPage {

    private Long cursor;
    private List<TComment> tCommentList;

    public Long getCursor() {
        return cursor;
    }

    public void setCursor(Long cursor) {
        this.cursor = cursor;
    }

    public List<TComment> gettCommentList() {
        return tCommentList;
    }

    public void settCommentList(List<TComment> tCommentList) {
        this.tCommentList = tCommentList;
    }
}
