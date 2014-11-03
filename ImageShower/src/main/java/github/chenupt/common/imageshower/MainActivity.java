package github.chenupt.common.imageshower;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class MainActivity extends ActionBarActivity {


    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(this)
                .load("http://s1.dwstatic.com/group1/M00/0D/7E/edf71df2010c1df1909f1afa8dd84155.gif")
//                .load("http://img0.imgtn.bdimg.com/it/u=3881076625,2941373367&fm=15&gp=0.jpg")
                .asGif()
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
