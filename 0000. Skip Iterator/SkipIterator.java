import java.util.Arrays;
import java.util.*;

public class SkipIterator implements Iterator<Integer> {
    private final Iterator<Integer> iterator;
    private final Map<Integer, Integer> skipCount;
    private Integer cursor;

    public SkipIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        this.skipCount = new HashMap<>();
        seekCursor();
    }

    public boolean hasNext() {
        return cursor != null;
    }

    public Integer next() {
        Integer element = cursor;
        seekCursor();
        return element;

    }

    public void skip(int num) {
        if (cursor == num) {
            seekCursor();
        } else {
            skipCount.put(num, skipCount.getOrDefault(num, 0) + 1);
        }
    }

    private void seekCursor() {
        // seek strictly!!!
        // caller knows what they are doing when calling seek
        cursor = null;
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (!skipCount.containsKey(element)) {
                cursor = element;
                break;
            } else {
                skipCount.put(element, skipCount.get(element) - 1);
                skipCount.remove(element, 0);
            }
        }
    }

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(2, 3, 5, 6, 5, 7, 5, -1, 5, 10);
        Iterator iterator = list.iterator();
        SkipIterator skipIterator = new SkipIterator(iterator);

        System.out.println(skipIterator.hasNext()); // true
        System.out.println(skipIterator.next()); // 2
        skipIterator.skip(5);
        System.out.println(skipIterator.next()); // 3
        System.out.println(skipIterator.next()); // 6 as 5 is skipped
        System.out.println(skipIterator.next()); // 5
        skipIterator.skip(5);
        skipIterator.skip(5);
        System.out.println(skipIterator.next()); // 7
        System.out.println(skipIterator.next()); // -1
        System.out.println(skipIterator.next()); // 10
        System.out.println(skipIterator.hasNext()); // false
    }
}
