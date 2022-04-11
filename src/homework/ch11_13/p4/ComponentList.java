package homework.ch11_13.p4;

import java.util.ArrayList;

public class ComponentList extends ArrayList<Component> implements Iterator {
    private int position;

    public ComponentList() {
        super();
        position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < size();
    }

    @Override
    public Component next() {
        if (!hasNext()) {
            return null;
        }
        Component component = get(position);
        position++;
        return component;
    }

}
