package github.chenupt.talk.fragment;

import android.view.View;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import github.chenupt.common.listhelper.SimpleModelAdapter;
import github.chenupt.talk.R;
import github.chenupt.talk.base.BaseFragment;
import github.chenupt.talk.dataservice.MainDataService;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by chenupt@gmail.com on 2014/9/6.
 * Description TODO
 */
@EFragment(R.layout.fragment_main)
public class MainFragment extends BaseFragment{

    @ViewById(R.id.ptr_layout)
    PullToRefreshLayout pullToRefreshLayout;

    @ViewById(R.id.list_view)
    ListView listView;

    SimpleModelAdapter adapter;
    @Bean
    MainDataService mainDataService;

    @AfterViews
    void afterViews(){
        adapter = new SimpleModelAdapter(getActivity(), mainDataService.getFactory());
        listView.setAdapter(adapter);

        // Now setup the PullToRefreshLayout
        ActionBarPullToRefresh.from(getActivity())
                .allChildrenArePullable()
                .listener(onRefreshListener)
                .setup(pullToRefreshLayout);

        action();
    }

    private void action(){
        test();
    }

    private void test(){
        adapter.addList(mainDataService.wrapMainList());
        adapter.notifyDataSetChanged();
    }

    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefreshStarted(View view) {

        }

    };
}
