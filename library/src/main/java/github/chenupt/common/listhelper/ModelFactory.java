package github.chenupt.common.listhelper;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by chenupt@gmail.com on 2014/8/7.
 * Description : A factory create multiple item views
 */
public class ModelFactory {

    public static final String TAG = "ModelFactory";

    public Builder builder;

    protected ModelFactory(Builder builder) {
        this.builder = builder;
    }

    public BaseItemModel createModel(Context context, String modelType){
//        Log.d(TAG, "createModel: " + modelType);
//        BaseItemModel baseItemModel = null;
//        try {
//            baseItemModel = (BaseItemModel) builder.viewMap.get(modelType).getConstructor(Context.class).newInstance(context);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return baseItemModel;
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

    /**
     * 通过模板类型获取模板指针
     * @param modelType
     * @return
     */
    public int getViewType(String modelType){
        if( !builder.indexMap.containsKey(modelType)){
            throw new RuntimeException("The list does not contain the modelView:'" + modelType + "'. Please check the ModelFactory.");
        }
        Log.d(TAG, "getViewType:" + builder.indexMap.get(modelType));
        return builder.indexMap.get(modelType);
    }

    /**
     * 获取模板数量
     * @return
     */
    public int getViewTypeCount(){
        Log.d(TAG, "getViewTypeCount:" + builder.viewMap.size());
        return builder.viewMap.size();
    }

    /**
     * 当前模板是否可以固定头部
     * @param type
     * @return
     */
    public boolean isItemViewTypePinned(int type){
        return builder.pinnedMap.get(type);
    }



    //------------创建ModelFactory需添加Model---------

    public static class Builder{

        protected HashMap<String, Class<?>> viewMap;  // 模板类型 -> 模板展示View
        protected HashMap<String, Integer> indexMap;  // 模板类型 -> 模板指针
        protected HashMap<Integer, Boolean> pinnedMap;// 模板指针 -> View是否固定

        public Builder() {
            viewMap = new HashMap<String, Class<?>>();
            indexMap = new HashMap<String, Integer>();
            pinnedMap = new HashMap<Integer, Boolean>();
        }

        public ModelFactory build(){
            return new ModelFactory(this);
        }

        public Builder addModel(Class<?> viewClass){
            return addModel(viewClass, false);
        }

        public Builder addModel(Class<?> viewClass, boolean isPinned){
            return addToMap(getModelTypeName(viewClass), viewClass, isPinned);
        }

        public Builder addModel(String modelType, Class<?> viewClass){
            return addModel(modelType, viewClass, false);
        }

        public Builder addModel(String modelType, Class<?> viewClass, boolean isPinned){
            return addToMap(modelType, viewClass, isPinned);
        }

        private Builder addToMap(String modelType, Class<?> viewClass, boolean isPinned){
            if(!viewMap.containsKey(modelType)){
                viewMap.put(modelType, viewClass);
                int viewType = viewMap.size() - 1;
                indexMap.put(modelType, viewType);
                pinnedMap.put(viewType , isPinned);
            }
            return this;
        }


        private String getModelTypeName(Class<?> modelView){
            return modelView.getName();
        }

    }


}
