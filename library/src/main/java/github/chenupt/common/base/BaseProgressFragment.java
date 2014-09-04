package github.chenupt.common.base;

import android.os.Bundle;

import com.devspark.progressfragment.ProgressFragment;


public class BaseProgressFragment extends ProgressFragment {

	
	protected boolean isActive;

    @Override
    public void onResume() {
        isActive = true;
        BusHelper.create().commonRegister(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        isActive = false;
        BusHelper.create().commonUnregister(this);
        super.onPause();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BusHelper.create().commonRegister(this);
    }

    @Override
    public void onDestroy() {
        super.onDetach();
    }

    public void onEvent(Integer i){
        // TODO
    }

}
