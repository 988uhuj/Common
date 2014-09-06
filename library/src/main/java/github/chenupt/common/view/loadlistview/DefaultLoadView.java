package github.chenupt.common.view.loadlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import github.chenupt.common.R;

/**
 * Created by chenupt@gmail.com on 2014/9/7.
 * Description TODO
 */
public class DefaultLoadView extends LoadFooterView {

    private View container;
    private ProgressBar progressBar;
    private TextView textView;

    public DefaultLoadView(Context context) {
        this(context, null);
    }

    public DefaultLoadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefaultLoadView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        View.inflate(getContext(), R.layout.common_view_item_footer_default, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        container = findViewById(R.id.footer_container);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        textView = (TextView) findViewById(R.id.footer_text_view);
    }

    @Override
    public void onStartLoad() {
        setVisibility(View.VISIBLE);
        textView.setText(R.string.common_default_loading);
    }

    @Override
    public void onEndLoad() {
        setVisibility(View.INVISIBLE);
        textView.setText(R.string.common_default_loading);
    }

    @Override
    public void onNoMore() {
        setVisibility(View.VISIBLE);
        textView.setText(R.string.common_default_no_more);
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        container.setOnClickListener(onClickListener);
    }
}
