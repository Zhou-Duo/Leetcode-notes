import java.util.Iterator;
import java.util.LinkedList;

class MyHashMap {

    class Node {
        int key;
        int value;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private static final int BASE = 769;
    private LinkedList[] data;

    public MyHashMap() {
        data = new LinkedList[BASE];
        for (int i = 0; i < data.length; i++) {
            data[i] = new LinkedList<Node>();
        }
    }

    public void put(int key, int value) {
        int index = getIndex(key);
        Iterator<Node> iterator = data[index].iterator();
        while (iterator.hasNext()) {
            Node element = iterator.next();
            if (element.key == key) {
                element.value = value;
                return;
            }
        }
        data[index].offer(new Node(key, value));
    }

    public int get(int key) {
        int index = getIndex(key);
        Iterator<Node> iterator = data[index].iterator();
        while (iterator.hasNext()) {
            Node element = iterator.next();
            if (element.key == key) {
                return element.value;
            }
        }
        return -1;
    }

    public void remove(int key) {
        int index = getIndex(key);
        Iterator<Node> iterator = data[index].iterator();
        while (iterator.hasNext()) {
            Node element = iterator.next();
            if (element.key == key) {
                data[index].remove(element);
                return;
            }
        }
    }

    private int getIndex(int key) {
        return key % BASE;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */