package homework.ch11_13.p4;

import homework.ch11_13.p4.Component;
import homework.ch11_13.p4.Iterator;

public class NullIterator implements Iterator {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Component next() {
        return null;
    }
}
