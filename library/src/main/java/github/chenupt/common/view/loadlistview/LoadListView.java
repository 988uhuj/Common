package github.chenupt.common.view.loadlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;


public class LoadListView extends ListView{
	private String TAG = "LoadListView";
	private boolean isLoading;
	private boolean enableAutoLoad;
	
	private OnLoadListener listener;

    private LoadFooterView footView;

    private ListView listview;

	private int scrollPos;
	private int scrollTop;
	
	public LoadListView(Context context) {
		super(context);
		init(context);
		
	}
	public LoadListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context c){
		enableAutoLoad = true;
		listview = this;
		listview.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				Log.d(TAG, view.getLastVisiblePosition() + "position");
				// Loading data when last position is showing;
				if (view.getLastVisiblePosition() == view.getCount() - 1 && enableAutoLoad) {
					startLoad();
				}
				
				if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
					scrollPos = listview.getFirstVisiblePosition();
				}
				
				Log.d(TAG, "onScrollStateChanged");
				View v = listview.getChildAt(0);
				scrollTop = (v == null) ? 0 : v.getTop();
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
			}
		});
		
		footView = new DefaultLoadView(getContext());
		listview.addFooterView(footView);

        footView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(enableAutoLoad){
					startLoad();
				}
			}
		});
	}
	
	private void stop() {
		isLoading = false;
        footView.onEndLoad();
	}
	
	
	public interface OnLoadListener{
		public void onLoad();
	}
	
	
	public void setOnLoadListener(OnLoadListener listener){
		this.listener = listener;
	}
	
	
	public boolean IsLoading(){
		return isLoading;
	}
	
	public void startLoad(){
		if(isLoading){
			Log.d(TAG, "Loading");
			return;
		}
		isLoading = true;
		footView.onStartLoad();
        listener.onLoad();
	}
	
	public void setLoadComplete(){
		stop();
	}


	public ListView getListView(){
		return listview;
	}
	
	// 设置记录的位置
	public void setSelectionFromTop(){
		listview.setSelectionFromTop(scrollPos, scrollTop);
	}
	
	public void setEnableAutoLoad(boolean isEnableAutoLoad){
		this.enableAutoLoad = isEnableAutoLoad;
	}
	
	

}
