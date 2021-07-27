public class LinkedListDeque<T> implements Deque<T> {

    private Node sentinel;
    private int size;

    private class Node {
        private T item;
        private Node prev;
        private Node next;
        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node((T) new Object(), null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    /* Adds an item  of type T to the front of the deque. */
    public void addFirst(T item) {
        Node front = sentinel.next;
        front.prev = new Node(item, sentinel, front);
        sentinel.next = front.prev;

        size += 1;
    }

    @Override
    /* Adds an item of Type T to the back of the deque. */
    public void addLast(T item) {
        Node back = sentinel.prev;
        back.next = new Node(item, back, sentinel);
        sentinel.prev = back.next;

        size += 1;
    }

    @Override
    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return (size == 0);
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    @Override
    /* Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node p = sentinel;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    @Override
    /* Removes and returns the item at the front of the deque. If no such item exists,
    returns null. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return first;
    }

    @Override
    /* Removes and returns the item at the back of the deque. If no such item exists,
    returns null. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return last;
    }

    @Override
    /* Gets the item at the given index, where 0 is the front, 1 is the next item,
    and so forth. If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node p = sentinel.next;
        while (index != 0) {
            p = p.next;
            index -= 1;
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
