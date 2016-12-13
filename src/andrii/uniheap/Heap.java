package andrii.uniheap;

//import andrii.uniheap.HeapException;

import java.util.Comparator;

/**
 * Created by andrii on 07.12.16.
 */
@SuppressWarnings("unchecked")
public class Heap <E extends Comparable<E>>{

    private Object[] heap;
    private int lastIndex;
    private int capacity;
    private Comparator<E> comparator = new Comparator<E>() {
        @Override
        public int compare(E o1, E o2) {
            return o1.compareTo(o2);
        }
    };

    public Heap(Comparator<E> comparator){
        heap = new Object[10000];
        this.lastIndex = -1;
        this.capacity = heap.length;
        this.comparator = comparator;
    }

    public Heap(int capacity, Comparator<E> comparator){
        heap = new Object[capacity];
        this.lastIndex = -1;
        this.capacity = capacity;
        this.comparator = comparator;
    }

    public Heap(){
        heap = new Object[10000];
        this.lastIndex = -1;
        this.capacity = heap.length;
    }

    public Heap(int capacity){
        heap = new Object[capacity];
        this.lastIndex = -1;
        this.capacity = capacity;
    }

    private void bubbleUp(){
        int index = this.lastIndex;
        while(hasParent(index)){
            int parent = parentIndex(index);
            if (comparator.compare((E)heap[index], (E)heap[parent]) >= 0)
                //the minimum is on the top
                //break if the current element is greater or equal to the parent
                break;
            swap(index,parent);
            index = parent;
        }
    }

    private void bubbleDown(){

        int index = 0;
        while (true){
            if (!hasLeftChild(index)) break;
            int childIdx = leftChildIndex(index);

            if (hasRighChild(index)){
                //if there are two children -> take the smallest or
                //if they are equal take the left one
                childIdx = findMin(childIdx, rightChildIndex(index) );
            }
            if (comparator.compare((E)heap[index], (E)heap[childIdx]) <= 0)
                break;
            swap(index,childIdx);
            index = childIdx;
        }
    }

    /**
     * the method is used in the downHeapBubble() method
     * returns min of left and right child, if they are equal return the left
     */
    private int findMin(int leftChild, int rightChild) {
        if (comparator.compare((E)heap[leftChild], (E)heap[rightChild]) <= 0)
            return leftChild;
        else
            return rightChild;
    }

    /**
     * Returns (but does not remove) the minimum element in the heap.
     */
    public E peek(){

        if(lastIndex < 0)
            throw new HeapException("Heap is empty.");

        return (E)heap[0];
    }

    /**
     * Removes and returns the minimum element in the heap.
     */
    public E pop(){

        if(lastIndex < 0)
            throw new HeapException("Heap is empty.");

        E min = (E)heap[0];
        heap[0] = heap[lastIndex];
        lastIndex--;

        bubbleDown();

        return min;
    }

    public E insert(E elem){
        if(lastIndex+1 == capacity)
            throw new HeapException("Heap is full. No more room here!");

        heap[++lastIndex] = elem;
        bubbleUp();

        return (E)heap[0];
    }

    public E get(int index){
        return (E)heap[index];
    }

    private void swap(int i, int j){
        Object s = heap[i];
        heap[i] = heap[j];
        heap[j] = s;
    }

    public boolean isEmpty(){
        return lastIndex < 0;
    }

    public int size(){
        return lastIndex + 1;
    }

    private boolean hasParent(int index){
        return index > 0;
    }

    private boolean isParent(int index){
        return index == 0;
    }

    private boolean isLeftChild(int index){
        if(!hasParent(index)) throw new HeapException("No parent for element with index "+index);
        if(index > this.lastIndex) throw new HeapException("The heap does not have the element with index "+index);
        return index%2 == 1;
    }

    private boolean isRightChild(int index){
        if(!hasParent(index)) throw new HeapException("No parent for element with index "+index);
        if(index > this.lastIndex) throw new HeapException("The heap does not have the element with index "+index);
        return index%2 == 0;
    }

    private int parentIndex(int index){
        if(!hasParent(index)) throw new HeapException("No parent for element with index "+index);
        return (index - 1) / 2;
    }

    private int leftChildIndex(int index){
        int childIdx = index*2 + 1;
        if(childIdx > this.lastIndex)
            throw new HeapException("The element with index "+index+ " does not have a left child!");
        return childIdx;
    }

    private int rightChildIndex(int index){
        int childIdx = index*2 + 2;
        if(childIdx > this.lastIndex)
            throw new HeapException("The element with index "+index+ " does not have a right child!");
        return childIdx;
    }

    private boolean hasLeftChild(int index){
        return index >= 0 && index*2 + 1 <= this.lastIndex;
    }

    private boolean hasRighChild(int index){
        return index >= 0 && index*2 + 2 <= this.lastIndex;
    }
}
