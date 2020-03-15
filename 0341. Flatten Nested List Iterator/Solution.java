/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    
    /**
     * stack: holds iterator of type NestedInteger.
     * cursor: holds value that would be subsequently returned on the future next() call.
     */
    Stack<Iterator<NestedInteger>> stack;
    Integer cursor;
 
    /**
     * eg: [1, 2, [3, [4, 5], 6], 7]
     * Given a non null list, constructor pushes iterator of 'nestedList' on the stack.
     * 
     * @param nestedList list that holds NestedInteger (see above API).
     */
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        if(nestedList != null) { // null check
            stack.push(nestedList.iterator());
        }
    }
 
    /**
     * evict cursor
     * @return cursor's value
     */
    @Override
    public Integer next() {
        int value = cursor;
        cursor = null;
        return value;
    }
 
    /**
     * 
     * @return true if there are elements to iterate on, false otherwise.
     */
    @Override
    public boolean hasNext() {
        
        boolean hasNext = false;
​
        while(true) {
            // stack is out of iterators, also cursor is null
            if(stack.isEmpty() && cursor == null) {
                hasNext = false;
                break;
            } 
            
            // let's see what we have got
            Iterator<NestedInteger> iterator = stack.peek();
​
            if(!iterator.hasNext()) { // iterator out of elements
                stack.pop();
            } else { // this iterator has something to offer
                NestedInteger nestedInteger = iterator.next();
                if(nestedInteger.isInteger()) { // is it integer?
                    // save to cursor, hasNext true
                    cursor = nestedInteger.getInteger();
                    hasNext = true;
                    break;
                } else { // is it a list? push to stack, in hope to find an Integer 
                    stack.push(nestedInteger.getList().iterator());
                }
            } 
        }
​
        return hasNext;
    }
