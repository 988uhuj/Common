package github.chenupt.talk.dataservice;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.common.listhelper.ItemEntityWrapper;
import github.chenupt.common.listhelper.ModelFactory;
import github.chenupt.common.listhelper.SimpleItemEntity;
import github.chenupt.talk.customitemview.MainItemView_;
import github.chenupt.talk.entity.TCommentPage;

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
        for (int i = 0; i < 20; i++) {
            ItemEntityWrapper.wrap("").setModelView(MainItemView_.class).attach(list);
        }
        return list;
    }

    // content 包装类
    public List<SimpleItemEntity> wrapMainList(TCommentPage tCommentPage){
        List<SimpleItemEntity> list = new ArrayList<SimpleItemEntity>();
        for (int i = 0; i < tCommentPage.getCommentList().size(); i++) {
            ItemEntityWrapper.wrap(tCommentPage.getCommentList().get(i)).setModelView(MainItemView_.class).attach(list);
        }
        return list;
    }

}
