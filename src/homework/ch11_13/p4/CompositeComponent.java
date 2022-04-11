package homework.ch11_13.p4;

public class CompositeComponent extends Component {

    protected ComponentList childs;

    public CompositeComponent() {
        super();
    }

    public CompositeComponent(int id, String name, double price) {
        super(id, name, price);
        childs = new ComponentList();
    }

    @Override
    public void add(Component component) {
        if (!childs.contains(component)) {
            childs.add(component);
        }
    }
    @Override
    public double getPrice() {
        return this.calcPrice();
    }

    @Override
    public double calcPrice() {
        double totalPrices = 0.0;
        for (Component component : childs) {
            totalPrices += component.calcPrice();
        }
        return totalPrices;
    }

    @Override
    public Iterator iterator() {
        return new CompositeIterator(childs);
    }

    @Override
    public void remove(Component component) {
        childs.remove(component);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("sub-components of ").append(getName()).append(":\n");
        for (Component component : childs) {
            sb.append(component);
        }
        return sb.toString();
    }

}
