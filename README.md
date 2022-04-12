# MyIterator
## Background
本项目是老师布置的关于的一个复合迭代器的实现。
## Structure
![image1](https://github.com/pangtongtong663/picture/blob/main/picture3.png)
## Compositions
### 组件抽象类
为构建原子组件和复合组件做铺垫。
```java
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
```
### 原子组件
原子组件不包含任何子组件。

原子组件计算价格只需要返回自身价格即可。
```java
@Override
public double calcPrice() {
   return super.getPrice();
}
```
原子组件迭代器返回NullIterator
```java
@Override
public Iterator iterator() {
   return new NullIterator();
}
```
### 复合组件
复合组件包含原子组件和复合组件。

复合组件计算价格，需要递归地计算每个子组件的价格（包括复合组件）
```java
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
```
复合组件返回迭代器CompositeIterator
```java
@Override
public Iterator iterator() {
   return new CompositeIterator(childs);
}
```
### NullIterator
NullIterator的实现非常简单，hasNext()返回false，next()返回null即可，在此为CompositeIterator的实现做铺垫。
```java
@Override
public boolean hasNext() {
   return false;
}

@Override
public Component next() {
   return null;
}
```
### CompositeIterator
由于子组件可能也包含复合组件，在此需要内置一个迭代器，用来指向复合组件的迭代器。
```java
private Iterator cntIte;
```
同时，为了实现hasNext()方法，内置一个cnt变量对组件进行计数，这里的cnt是组件个数，原子组件和复合组件一视同仁，均为一个组件。
```java
protected ComponentList components;
private Iterator cntIte;
```
对于hasNext()方法，如果cnt小于组件个数，那必然还有下一个组件。如果cnt指向最后一个组件，这时候就需要判断是否为原子组件或者是复合组件。如果是原子组件，直接返回true，如果为复合组件，在此就需要判断是否为复合组件的最后一个。
```java
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
```
对于next()方法,首先要判断内置迭代器是否为空。如果迭代器不为空，说明迭代器正在指向复合组件的一个子复合组件。若内置迭代器的hasNext()为true，直接返回next()即可。否则，将内置迭代器清空，将cnt加1.
对内置迭代器的逻辑判断完以后，设置Component变量指向下一个组件，用内置迭代器指向该组件的迭代器。若该组件为原子组件，则将cnt加1直接返回，否则返回复合组件，内置迭代器指向复合组件的迭代器。
```java
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
```
### 其他
迭代器的主要功能和实现逻辑已经介绍完毕，具体的其他细节，可以查看项目源码。
