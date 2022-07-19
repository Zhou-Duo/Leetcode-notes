import java.util.Iterator;

// 1. 简单数组法
/* 
 * 优点：查找和删除的性能非常快，只用访问 1 次数组；
 * 缺点：使用了非常大的空间，当元素范围很大时，无法使用该方法；当存储的元素个数较少时，性价比极低；需要预知数据的取值范围。
 */
class MyHashSet1 {
    private boolean[] array;

    public MyHashSet() {
        array = new boolean[1000001];
    }

    public void add(int key) {
        array[key] = true;
    }

    public void remove(int key) {
        array[key] = false;
    }

    public boolean contains(int key) {
        return array[key];
    }
}

// 2. 链表
/* 
 * 时间复杂度：O(b/n)。其中 n 为哈希表中的元素数量，b 为链表的数量。
 * 假设哈希值是均匀分布的，则每个链表大概长度为 b/n。
 * 空间复杂度：O(n+b)。
 */
class MyHashSet2 {

    private static final int BASE = 769;
    private LinkedList[] data;

    public MyHashSet() {
        data = new LinkedList[BASE];
        // array of reference type: 需要对每个元素用 new 关键字 initialize
        for (int i = 0; i < data.length; i++) {
            data[i] = new LinkedList<Integer>();            
        }
    }

    private int getIndex(int key) {
        return key % BASE;
    }

    public void add(int key) {
        int index = getIndex(key);
        /* 
         * Java Iterator（迭代器）不是一个集合，它是一种用于访问集合的方法，可用于迭代 ArrayList 和 HashSet 等集合。
         * 迭代器 it 的三个基本操作是 next 、hasNext 和 remove。
         * 1. 调用 it.next() 会返回迭代器的下一个元素，并且更新迭代器的状态。
         * 2. 调用 it.hasNext() 用于检测集合中是否还有元素。
         * 3. 调用 it.remove() 将迭代器返回的元素删除。
         */
        Iterator<Integer> iterator = data[index].iterator();
        while (iterator.hasNext()) {
            if (key == iterator.next())
                return;
        }
        data[index].offer(key);
    }

    public void remove(int key) {
        int index = getIndex(key);
        Iterator<Integer> iterator = data[index].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                /* 
                 * 此处易错; 应为 remove(element) 而非 remove(key)
                 * LinkedList 中 remove():
                 * 1. remove()：获取并移除此列表的头（第一个元素）。
                 * 2. remove(int index)：移除此列表中指定位置处的元素。
                 * remove(Objec o)：从此列表中移除首次出现的指定元素（如果存在）。
                 * 此处 key 是 int,  iterator.next()是 Integer。
                 * */
                data[index].remove(element);
                return;
            }
        }
    }

    public boolean contains(int key) {
        int index = getIndex(key);
        Iterator<Integer> iterator = data[index].iterator();
        while (iterator.hasNext()) {
            if (key == iterator.next()) {
                return true;
            }
        }
        return false;
    }
}
/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */