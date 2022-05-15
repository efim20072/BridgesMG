package common.listener;

import java.util.ArrayList;
import java.util.List;

public class EventObservable<O extends BaseObserver> {

    public final List<O> listeners = new ArrayList<>();

    public void subscribe(O listener) {
        listeners.add(listener);
    }

    public void clear() {
        listeners.clear();
    }
}
