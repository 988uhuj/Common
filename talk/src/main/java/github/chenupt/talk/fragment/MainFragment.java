package github.chenupt.talk.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.apache.http.Header;

import java.util.List;

import github.chenupt.common.listhelper.SimpleItemEntity;
import github.chenupt.common.listhelper.SimpleModelAdapter;
import github.chenupt.common.view.loadlistview.LoadListView;
import github.chenupt.talk.R;
import github.chenupt.talk.base.BaseFragment;
import github.chenupt.talk.dataservice.MainDataService;
import github.chenupt.talk.entity.TComment;
import github.chenupt.talk.entity.TCommentPage;
import github.chenupt.talk.net.TalkHttpResponseHandler;
import github.chenupt.talk.net.service.NetService;
import github.chenupt.talk.utils.Constants;
import github.chenupt.talk.utils.Page;
import github.chenupt.talk.utils.ToastUtil;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by chenupt@gmail.com on 2014/9/6.
 * Description TODO
 */
@EFragment(R.layout.fragment_main)
public class MainFragment extends BaseFragment{

    public static final String TAG = "MainFragment";

    @ViewById(R.id.ptr_layout)
    PullToRefreshLayout pullToRefreshLayout;
    @ViewById(R.id.list_view)
    LoadListView listView;
    @ViewById(R.id.edit_text)
    EditText editText;
    @ViewById(R.id.send_btn)
    Button sendBtn;


    SimpleModelAdapter adapter;
    @Bean
    MainDataService mainDataService;
    @Bean
    NetService netService;
    @Bean
    Page page;

//    @Extra
    String uriKey = "test";

    @AfterViews
    void afterViews(){
        adapter = new SimpleModelAdapter(getActivity(), mainDataService.getFactory());
        listView.setAdapter(adapter);
        listView.setOnLoadListener(new LoadListView.OnLoadListener() {
            @Override
            public void onLoad() {
                netGetComment(false);
            }
        });


        // Now setup the PullToRefreshLayout
        ActionBarPullToRefresh.from(getActivity())
                .allChildrenArePullable()
                .listener(onRefreshListener)
                .setup(pullToRefreshLayout);

        action();
    }

    private void action(){
       netGetComment(true);
    }

    @UiThread(delay = Constants.DELAY_TIME)
    void delayNetGetComment(){
        netGetComment(true);
    }

    private void netGetComment(final boolean isRefresh){
//        netService.getMainList(page.getCursor(isRefresh), page.getPageSize(), new TalkHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseBody, String action, int status, Object body, String msg) {
//                TCommentPage tCommentPage = new Gson().fromJson(body.toString(), TCommentPage.class);
//                handleData(isRefresh, tCommentPage);
//                loadFinish();
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseBody, Throwable error) {
//                super.onFailure(statusCode, headers, responseBody, error);
//                Log.d("eee", "error" + error);
//                loadFinish();
//            }
//        });
    }

    private void handleData(boolean isRefresh, TCommentPage tCommentPage){
        List<SimpleItemEntity> list = mainDataService.wrapMainList(tCommentPage);
        if (isRefresh) {
            ToastUtil.show(getActivity(), "成功刷新 " + list.size() + " 条");
            adapter.clearList();
            listView.setMore(true);
        }
        adapter.addList(list);

        page.setNextCursor(tCommentPage.getCursor());
        if(tCommentPage.getCommentList().size() < page.getPageSize() || tCommentPage.getCursor() == 0L){
            listView.setMore(false);
        }
    }

    private void loadFinish(){
        adapter.notifyDataSetChanged();
        listView.setLoadComplete();
        pullToRefreshLayout.setRefreshComplete();
    }


    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefreshStarted(View view) {
            delayNetGetComment();
        }

    };

    private void postComment(TComment tComment){

        netService.postComment(tComment, new TalkHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseBody, String action, int status, Object body, String msg) {
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseBody, Throwable error) {
                super.onFailure(statusCode, headers, responseBody, error);
            }
        });
    }

    @Click(R.id.send_btn)
    void sendClick(){
        collectData();
    }

    private void collectData(){
        String content = editText.getText().toString();
        TComment tComment = new TComment();
        tComment.setContent(content);
        tComment.setUrlKey("test");
        postComment(tComment);
    }

}
