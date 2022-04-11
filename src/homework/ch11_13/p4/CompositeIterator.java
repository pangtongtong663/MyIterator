package homework.ch11_13.p4;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeIterator implements Iterator {

    protected ComponentList components;
    private int cnt = 0;
    private Iterator cntIte;

    public CompositeIterator(ComponentList  components) {
        this.components = components;
        this.cntIte = null;
    }

    @Override
    public boolean hasNext() {

        if (cnt < components.size() -1) {
            return true;
        }

        if (cnt == components.size() - 1) {
            if (cntIte == null) {
                return true;
            } else {
                return cntIte.hasNext();
            }
        }
        return false;

    }

    @Override
    public Component next() {
        if (cntIte != null) {
            if (cntIte.hasNext()) {
                return cntIte.next();
            } else {
                cnt++;
                cntIte = null;
            }
        }
        Component component = components.get(cnt);
        Iterator iterator = component.iterator();
        if (!iterator.hasNext()) {
            cnt++;
            return component;
        } else {
            this.cntIte = iterator;
            return component;
        }

    }
}
