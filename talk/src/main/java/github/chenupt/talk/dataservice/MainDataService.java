package github.chenupt.talk.dataservice;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.common.listhelper.ItemEntityWrapper;
import github.chenupt.common.listhelper.ModelFactory;
import github.chenupt.common.listhelper.SimpleItemEntity;
import github.chenupt.talk.customitemview.MainItemView_;

/**
 * Created by chenupt@gmail.com on 2014/9/6.
 * Description TODO
 */
@EBean
public class MainDataService {

    public ModelFactory getFactory(){
        ModelFactory factory = new ModelFactory.Builder()
                .addModel(MainItemView_.class)
                .build();
        return factory;
    }
    // content 包装类
    public List<SimpleItemEntity> wrapMainList(){
        List<SimpleItemEntity> list = new ArrayList<SimpleItemEntity>();
        ItemEntityWrapper.wrap("").setModelView(MainItemView_.class).attach(list);
        return list;
    }

}
