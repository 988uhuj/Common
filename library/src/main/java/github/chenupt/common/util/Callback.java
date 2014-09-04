package github.chenupt.common.util;

import android.util.Log;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-11-2
 */
public abstract class Callback<T> {


    public void onComplete(T result) {
    }


    public void onError(Exception ex) {
        Log.d("AsyncCall", ex.getMessage());
    }
}
