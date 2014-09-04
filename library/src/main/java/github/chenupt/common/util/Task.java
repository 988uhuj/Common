package github.chenupt.common.util;

import android.util.Log;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oznyang@163.com">oznyang</a>
 * @version V1.0, 13-11-4
 */
public abstract class Task<T> {

    public abstract T call() throws Exception;

    public void onComplete(T result) {
    }

    public void onError(Exception ex) {
        Log.d("TaskCall", ex.getMessage());
    }
}
