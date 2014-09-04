package github.chenupt.common.base;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.EventBusException;

/**
 * Created by chenupt@gmail.com on 2014/8/6.
 * Description TODO
 */
public class BusHelper {

    private final EventBus commonBus = new EventBus();
    private final EventBus syncBus = new EventBus();

    public void commonRegister(Object object){
        if(!commonBus.isRegistered(object)){
            try {
                commonBus.register(object);
            } catch (EventBusException e) {
                e.printStackTrace();
            }
        }
    }

    public void commonUnregister(Object object){
        if(commonBus.isRegistered(object)) {
            commonBus.unregister(object);
        }
    }

    public void syncRegister(Object object){
        if(!syncBus.isRegistered(object)){
            syncBus.register(object);
        }
    }

    public void syncUnregister(Object object){
        syncBus.unregister(object);
    }


    public EventBus getCommonBus() {
        return commonBus;
    }

    public EventBus getSyncBus() {
        return syncBus;
    }

    private static volatile BusHelper instance = null;
    private BusHelper(){
    }
    public static BusHelper create() {
        if (instance == null) {
            synchronized (BusHelper.class) {
                if (instance == null) {
                    instance = new BusHelper();
                }
            }
        }
        return instance;
    }


}
