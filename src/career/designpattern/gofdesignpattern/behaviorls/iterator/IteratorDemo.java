package career.designpattern.gofdesignpattern.behaviorls.iterator;

import java.util.ArrayList;
import java.util.List;

interface Iterator{
    boolean hasNext();

    Object next();

    void reset();
}

class Element{
    int values;

    public Element(int values) {
        this.values = values;
    }

    public int getValues() {
        return values;
    }
}

class ListIterator implements Iterator {
    private List<Element> profiles;
    int index=0;

    public ListIterator(List<Element> profiles) {
        this.profiles = profiles;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index <= profiles.size()-1;
    }

    @Override
    public Object next() {
        Object value = profiles.get(index);
        index++;
        return value;
    }

    @Override
    public void reset() {
        index = 0;
    }
}

public class IteratorDemo {
    public static void main(String[] args) {
        List<Element> elementList = new ArrayList<>();
        elementList.add(new Element(1));
        elementList.add(new Element(2));
        elementList.add(new Element(3));
        elementList.add(new Element(4));
        ListIterator listIterator = new ListIterator(elementList);
        while(listIterator.hasNext()){
            Element element = (Element) listIterator.next();
            System.out.println(element.getValues());
        }
    }
}
