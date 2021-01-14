import java.util.Objects;

public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Objects[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    /* Adds the item of type T to the front of the Deque. */
    public void addFirst(T item) {
        if (size >= items.length - 2) {
            resize(size * 2);
        }
        if (nextFirst == 0) {
            items[0] = item;
            nextFirst = items.length - 1;
        } else {
            items[nextFirst] = item;
            nextFirst -= 1;
        }
        size += 1;
    }

    /* Adds an item of Type T to the back of the deque. */
    public void addLast(T item) {
        if (size >= items.length - 2) {
            resize(size * 2);
        }
        if (nextLast == items.length - 1) {
            items[nextLast] = item;
            nextLast = 0;
        } else {
            items[nextLast] = item;
            nextLast += 1;
        }
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
        if (nextFirst < nextLast) {
            for (int i = nextFirst + 1; i < nextLast; i += 1) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = nextFirst + 1; i < items.length; i += 1) {
                System.out.print(items[i] + " ");
            }
            for (int j = 0; j < nextLast; j += 1) {
                System.out.print(items[j] + " ");
            }
        }
    }

    /* Removes and returns the item at the front of the deque. If no such item exists,
    returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            if (nextFirst == items.length - 1) {
                nextFirst = 0;
                size -= 1;
                return items[0];
            } else {
                nextFirst += 1;
                size -= 1;
                return items[nextFirst];
            }
        }
    }

    /* Removes and returns the item at the back of the deque. If no such item exists,
    returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            if (nextLast == 0) {
                nextLast = items.length - 1;
                size -= 1;
                return items[nextLast];
            } else {
                nextLast -= 1;
                size -= 1;
                return items[nextLast];
            }
        }
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item,
    and so forth. If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index >= size) return null;
        if (nextLast > nextFirst) {
            return items[nextFirst + index];
        } else {
            if (size - index - 1 < nextLast + 1) {
                return items[index - (size - nextLast - 1)];
            } else {
                return items[nextFirst + index];
            }
        }
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Objects[capacity];
        if (nextFirst < nextLast) {
            System.arraycopy(items, 0, newArray, nextFirst, nextLast - nextFirst + 1);
        } else {
            System.arraycopy(items, nextFirst, newArray, 0, items.length - nextFirst);
            System.arraycopy(items, 0, newArray, items.length - nextFirst, nextLast + 1);
            nextFirst = 0;
            nextLast = items.length - 1;
        }
        items = newArray;
    }

}



