package cn.dombro.datastructures.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 一个自定义的 ArrayList，不提供List接口，
 * MyArrayList是独立的用于研究使用数组实现的表的原理
 * MyArrayList将实现 Iterable 接口，并提供迭代器
 * @param <AnyType>
 */
public class MyArrayList<AnyType> implements Iterable<AnyType> {

    //默认数组容量
    private static final int DEFAULT_CAPACITY = 10;

    //记录项数
    private int theSize;

    //使用数组实现
    private AnyType[] theItems;

    //构造方法，通过调用 doClear 将 theItems 初始化为容量为10的数组
    public MyArrayList() {
        doClear();
    }

    //清空ArrayList
    public void clear(){
        doClear();
    }

    //初始化ArrayList
    private void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    //获取当前ArrayList的大小
    public int size() {
        return theSize;
    }

    //判断ArrayList是否为空
    public boolean isEmpty(){
        return size()== 0;
    }

    public void trimSize(){
        ensureCapacity(size());
    }

    //获取指定索引处的成员，但要判断该处索引是否合法，时间复杂度为常数级别
    public AnyType get(int idx) {
        if (idx < 0 || idx > size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    //为指定索引出处的成员设值，时间复杂度为常数级别
    public AnyType set(int idx,AnyType value){
        if (idx < 0 || idx > size())
            throw new ArrayIndexOutOfBoundsException();
        AnyType old = theItems[idx];
        theItems[idx] = value;
        return old;
    }

    /**
     *
     * @param idx 添加新元素的索引，时间复杂度的 O(N)
     * @param value 元素的值
     */
    public void add(int idx,AnyType value){
        if (theItems.length == size())
            //扩容
            ensureCapacity(size()*2+1);
        for (int i = theSize; i > idx; i--){
            theItems[i] = theItems[i-1];
        }
        theItems[idx] = value;
        theSize++;
    }

    /**
     * 在元素末尾添加元素，时间复杂度为常数级别
     */
    public boolean add(AnyType value){
        add(size(),value);
        return true;
    }

    /**
     *
     * @param idx 删除元素位置的索引，时间复杂度的 O(N)
     * @return
     */
    public AnyType remove(int idx){

        AnyType removeItem = theItems[idx];
        for (int i = idx; i < size()-1; i++)
            theItems[i] = theItems[i+1];

        theSize--;
        return removeItem;

    }

    //扩充容量
    public void ensureCapacity(int newCapacity){

        //若扩充的容量小于当前数组的容量，直接返回
        if (newCapacity < theSize)
            return;
        if (theItems != null) {
            AnyType[] old = theItems;
            //Java不允许有泛型数组的出现，所以用泛型数组类型进行强转
            theItems = (AnyType[]) new Object[newCapacity];
            //通过复制操作，将当前的ArrayList扩充容量
            for (int i = 0; i < size(); i++)
                theItems[i] = old[i];
        }else {
            theItems = (AnyType[]) new Object[newCapacity];
        }
    }


    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    /**
     * 内部类迭代器,只可以通过该迭代器对MyArrayList进行遍历
     */
    private class ArrayListIterator implements Iterator<AnyType>{

        //记录当前迭代位置(准确来说已迭代的数目)
        private int current = 0;

        //是否还有下一个元素可以遍历
        public boolean hasNext() {
            return current < size();
        }

        /**
         *可以看到current从0开始，每次调用 next 返回 theItems[current] ，current+1
         *
         */
        public AnyType next() {
            if (! hasNext())
                throw new NoSuchElementException();
            return theItems[current++];
        }

        /**
         * 迭代器中的 remove 会调用 外部类的 remove 方法，会删除掉正在迭代的项，
         * 所以如果位调用 next()方法　之前调用remove们将会报错
         */
        public void remove() {
            if (current < 0)
                throw new IllegalArgumentException();
            MyArrayList.this.remove(--current);
        }
    }

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<Integer>();
        list.add(1);

    }
}
