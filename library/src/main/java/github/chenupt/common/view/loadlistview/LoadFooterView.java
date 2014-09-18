package github.chenupt.common.view.loadlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by chenupt@gmail.com on 2014/9/7.
 * Description TODO
 */
public abstract class LoadFooterView extends FrameLayout {
    public LoadFooterView(Context context) {
        super(context);
    }

    public LoadFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadFooterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onStartLoad(){

    }
    public void onEndLoad(boolean hasMore){

    }
    public void onNoMore(){

    }
    public void onError(){

    }
    public void setOnClickListener(OnClickListener onClickListner){

    }

}
