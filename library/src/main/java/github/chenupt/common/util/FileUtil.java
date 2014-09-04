package github.chenupt.common.util;

import android.content.Context;

import org.apache.http.util.EncodingUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chenupt@gmail.com on 2014/7/11.
 * Description : TODO
 */
public class FileUtil {

    public static final String ENCODING = "UTF-8";

    public static String readAssets(Context context, String fileName){
        String result = null;
        try {
            InputStream in = context.getResources().getAssets().open(fileName);
            int length = in.available();
            byte[]  buffer = new byte[length];
            in.read(buffer);
            result = EncodingUtils.getString(buffer, ENCODING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String[] getFileNameArray(Context context, String path){
        String[] result = null;
        try {
            result = context.getResources().getAssets().list(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
