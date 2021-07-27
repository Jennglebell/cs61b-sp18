import java.util.Objects;

public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int left;
    private int right;
    private int capacity = 8;

    public ArrayDeque() {
        items = (T[]) new Objects[capacity];
        left = 3;
        right = 4;
    }

    @Override
    /* Adds the item of type T to the front of the Deque. */
    public void addFirst(T item) {
        if (isFull()) {
            resize((int) (capacity * 1.5));
        }
        items[left] = item;
        left = (left - 1 + capacity) % capacity;
    }

    @Override
    /* Adds an item of Type T to the back of the deque. */
    public void addLast(T item) {
        if (isFull()) {
            resize((int) (capacity * 1.5));
        }
        items[right] = item;
        right = (right + 1 + capacity) % capacity;
    }

    @Override
    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return (size() == 0);
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return (right - left + capacity) % capacity - 1;
    }

    @Override
    /* Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        if (left < right) {
            for (int i = left + 1; i < right; i += 1) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = left + 1; i < items.length; i += 1) {
                System.out.print(items[i] + " ");
            }
            for (int j = 0; j < right; j += 1) {
                System.out.print(items[j] + " ");
            }
        }
    }

    @Override
    /* Removes and returns the item at the front of the deque. If no such item exists,
    returns null. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = items[(left + 1) % capacity];
        left = (left + 1) % capacity;
        if (islowUsageRate()) {
            resize((int) (capacity * 0.5));
        }
        return first;
    }

    @Override
    /* Removes and returns the item at the back of the deque. If no such item exists,
    returns null. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = items[(right - 1 + capacity) % capacity];
        right = (right - 1 + capacity) % capacity;
        if (islowUsageRate()) {
            resize((int) (capacity * 0.5));
        }
        return last;
    }

    @Override
    /* Gets the item at the given index, where 0 is the front, 1 is the next item,
    and so forth. If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index >= size() || index < 0 || isEmpty()) {
            return null;
        }
        if (right > left) {
            return items[left + index + 1];
        } else {
            if (index + left + 1 < size()) {
                return items[index + left + 1];
            } else {
                return items[(index + left + 1) % capacity];
            }
        }
    }

    private boolean isFull() {
        return (capacity - 2 ==  size());
    }

    private boolean islowUsageRate() {
        return (capacity >= 16 && size() / (double) capacity < 0.25);
    }

    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];

        if (left < right) {
            for (int i = left, j = 0; i < right && j < size(); i++, j++) {
                newItems[j] = items[i];
            }
        } else if (left > right) {
            int j = 0;
            for (int i = left; j < size() - left; i++, j++) {
                newItems[j] = items[i];
            }
            for (int i = 0; j < size(); i++, j++) {
                newItems[j] = items[i];
            }
        }
        left = 0;
        right = size() + 1;
        items = newItems;
        capacity = newSize;
    }
}



