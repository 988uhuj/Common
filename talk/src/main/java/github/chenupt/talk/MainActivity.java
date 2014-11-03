package github.chenupt.talk;

import android.content.Intent;
import android.os.Bundle;

import org.androidannotations.annotations.EActivity;

import github.chenupt.talk.base.BaseActivity;

/**
 * Created by chenupt@gmail.com on 2014/9/6.
 * Description TODO
 */
@EActivity(R.layout.activity_main_2)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent();
        i.setClass(this, github.chenupt.common.imageshower.MainActivity.class);
        startActivity(i);
    }
}
