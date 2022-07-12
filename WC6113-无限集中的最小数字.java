import java.util.HashSet;
import java.util.Set;

class SmallestInfiniteSet {
    private Set<Integer> NotIn;
    private int smallest;

    public SmallestInfiniteSet() {
        NotIn = new HashSet<Integer>();
        smallest = 1;
    }

    public int popSmallest() {
        NotIn.add(smallest);
        int res = smallest;
        renewSmallest();
        return res;
    }

    public void addBack(int num) {
        if (num <= 0)
            return;
        if (num < smallest)
            smallest = num;
        NotIn.remove(num);
    }

    private void renewSmallest() {
        for (int i = smallest + 1;; i++) {
            if (!NotIn.contains(i)) {
                smallest = i;
                break;
            }
        }
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */

class SmallestInfiniteSet extends TreeSet<Integer> {
    private int curr;

    public int popSmallest() {
        return isEmpty() ? ++curr : pollFirst();
    }

    public void addBack(int num) {
        if (num <= curr)
            add(num);
    }
}