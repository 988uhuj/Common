package github.chenupt.common.listhelper;

import android.content.Context;
import android.util.Log;

/**
 * Created by chenupt@gmail.com on 2014/8/7.
 * Description : A factory create multiple item views
 */
public class AAModelFactory extends ModelFactory{


    protected AAModelFactory(Builder builder) {
        super(builder);
    }

    /**
     * When use the AndroidAnnotations, the View constructor is build method.
     * @param context
     * @param modelType
     * @return
     */
    @Override
    public BaseItemModel createModel(Context context, String modelType) {
        Log.d(TAG, "createModelByAA: " + modelType);
        BaseItemModel baseItemModel = null;
        Class<?> owner = builder.viewMap.get(modelType);
        try {
            baseItemModel = (BaseItemModel) owner.getMethod("build", new Class[]{Context.class}).invoke(owner, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseItemModel;
    }
}
