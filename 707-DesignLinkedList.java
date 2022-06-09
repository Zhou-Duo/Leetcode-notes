class ListNode {
    int val;
    ListNode next;

    ListNode() {
        val = 0;
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.next = next;
    }
}

class MyLinkedList {
    int size;
    ListNode head;

    public MyLinkedList() {
        size = 0;
        head = new ListNode();
    }

    public int get(int index) {
        if (index < 0 || index >= size)
            return -1;
        ListNode curr = head;
        // **注意 head 是 dummy head，此处为 index + 1
        for (int i = 0; i < index + 1; i++)
            curr = curr.next;
        return curr.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        // invalid index
        if (index < 0)
            index = 0;
        if (index > size)
            return;
        // add node
        ListNode newNode = new ListNode(val);
        ListNode curr = head;
        for (int i = 0; i < index; ++i)
            curr = curr.next;
        newNode.next = curr.next;
        curr.next = newNode;
        // add size
        ++size;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size)
            return;
        ListNode pred = head;
        for (int i = 0; i < index; ++i)
            pred = pred.next;
        pred.next = pred.next.next;
        --size;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */