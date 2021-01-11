public class LinkedListDeque<T> {

    private Node sentinel;
    private int size;

    public class Node {
        public T item;
        public Node prior;
        public Node next;
        public Node(T i, Node p, Node n) {
            item = i;
            prior = p;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
    }

    /* Adds an item  of type T to the front of the deque. */
    public void addFisrt(T item) {
        Node front = sentinel.next;
        front.prior = new Node(item, sentinel, front);
        sentinel.next = front.prior;

        size += 1;
    }

    /* Adds an item of Type T to the back of the deque. */
    public void addLast(T item) {
        Node back = sentinel.prior;
        back.next = new Node(item, back, sentinel);
        sentinel.prior = back.next;

        size += 1;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return (size == 0);
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node p = sentinel;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    /* Removes and returns the item at the front of the deque. If no such item exists,
    returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        Node first = sentinel.next;
        first.next.prior = sentinel;
        sentinel.next = first.next;
        return first.item;
    }

    /* Removes and returns the item at the back of the deque. If no such item exists,
    returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        Node last = sentinel.prior;
        last.prior.next = sentinel;
        sentinel.prior = last.prior;
        return last.item;
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item,
    and so forth. If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node p = sentinel;
        while (index != 0) {
            p = p.next;
        }
        return p.item;
    }

    /* Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Node p) {
        if (index == 0) {
            return p.item;
        } else {
            return getRecursiveHelper(index - 1, p.next);
        }
    }

}
