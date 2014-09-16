package github.chenupt.talk.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.apache.http.Header;

import github.chenupt.common.listhelper.SimpleModelAdapter;
import github.chenupt.talk.R;
import github.chenupt.talk.base.BaseFragment;
import github.chenupt.talk.dataservice.MainDataService;
import github.chenupt.talk.entity.TComment;
import github.chenupt.talk.entity.TCommentPage;
import github.chenupt.talk.net.TalkHttpResponseHandler;
import github.chenupt.talk.net.service.NetService;
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
    ListView listView;
    @ViewById(R.id.edit_text)
    EditText editText;
    @ViewById(R.id.send_btn)
    Button sendBtn;


    SimpleModelAdapter adapter;
    @Bean
    MainDataService mainDataService;
    @Bean
    NetService netService;

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
        long cursor = 0;
        int pageSize = 20;
        netService.getMainList(cursor, pageSize, new TalkHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseBody, String action, int status, String body, String msg) {
                TCommentPage tCommentPage = new Gson().fromJson(body, TCommentPage.class);
                Log.d("eee", "status：" + body);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseBody, Throwable error) {
                super.onFailure(statusCode, headers, responseBody, error);
                Log.d("eee", "error" + error);
            }
        });
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

    private void postComment(TComment tComment){

        netService.postComment(tComment, new TalkHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseBody, String action, int status, String body, String msg) {

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
