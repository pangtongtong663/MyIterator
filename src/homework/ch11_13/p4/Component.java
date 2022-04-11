package homework.ch11_13.p4;

abstract public class Component {
    protected int id;
    protected String name;
    protected double price;

    public Component() {

    }

    public Component(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public abstract void add(Component component);

    public abstract double calcPrice();

    public abstract Iterator iterator();

    public abstract void remove(Component component);

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Component other = (Component) obj;

        return getId() == other.getId();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id : ").append(getId())
                .append(", name : ").append(getName())
                .append(", price : ").append(getPrice()).append("\n");
        return sb.toString();
    }


}
