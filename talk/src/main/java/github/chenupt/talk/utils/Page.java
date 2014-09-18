package github.chenupt.talk.utils;

import org.androidannotations.annotations.EBean;

/**
 * Created by chenupt@gmail.com on 2014/9/18.
 * Description TODO
 */
@EBean
public class Page {
    private long id;
    private long prevCursor;
    private int currentPage;
    private long nextCursor;
    private int pageSize = 20;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrevCursor() {
        return prevCursor;
    }

    public void setPrevCursor(long prevCursor) {
        this.prevCursor = prevCursor;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(long nextCursor) {
        this.nextCursor = nextCursor;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    //====================

    public long getCursor(boolean isPrev){
        return isPrev ? prevCursor : nextCursor;
    }

}
