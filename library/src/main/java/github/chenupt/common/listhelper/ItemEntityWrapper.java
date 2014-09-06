package github.chenupt.common.listhelper;


/**
 * Created by chenupt@gmail.com on 2014/8/13.
 * Description : ItemEntityCreator creator to create SimpleItemEntity
 */
public class ItemEntityWrapper {

    public static <T> SimpleItemEntity<T> wrap(T content){
        return new SimpleItemEntity<T>(content);
    }

}
