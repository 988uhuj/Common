package github.chenupt.talk.customitemview;

import android.content.Context;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.joda.time.DateTime;

import github.chenupt.common.listhelper.BaseItemModel;
import github.chenupt.talk.R;
import github.chenupt.talk.entity.TComment;

/**
 * Created by chenupt@gmail.com on 2014/9/6.
 * Description TODO
 */
@EViewGroup(R.layout.view_item_main)
public class MainItemView extends BaseItemModel<TComment> {

    @ViewById(R.id.content)
    TextView contentTextView;
    @ViewById(R.id.time)
    TextView timeTextView;
    @ViewById(R.id.title)
    TextView titleTextView;


    public MainItemView(Context context) {
        super(context);
    }

    @Override
    public void bindView() {
        contentTextView.setText(model.getContent().getContent());
        titleTextView.setText(R.string.comment_title);
        timeTextView.setText(new DateTime(model.getContent().getCreateTime()).toString("yyyy-MM-dd HH:mm"));
    }
}
