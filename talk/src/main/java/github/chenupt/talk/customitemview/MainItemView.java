package github.chenupt.talk.customitemview;

import android.content.Context;

import org.androidannotations.annotations.EViewGroup;

import github.chenupt.common.listhelper.BaseItemModel;
import github.chenupt.talk.R;

/**
 * Created by chenupt@gmail.com on 2014/9/6.
 * Description TODO
 */
@EViewGroup(R.layout.view_item_main)
public class MainItemView extends BaseItemModel<String> {

    public MainItemView(Context context) {
        super(context);
    }

    @Override
    public void bindView() {

    }
}
