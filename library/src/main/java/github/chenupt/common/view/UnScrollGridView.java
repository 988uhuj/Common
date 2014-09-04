package github.chenupt.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by chenupt@gmail.com on 2014/4/3.
 * Description : Ban to scroll for that GridView can show all content items.
 */
public class UnScrollGridView extends GridView {
    public UnScrollGridView(Context context) {
        super(context);
    }

    public UnScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnScrollGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // set unScroll
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
