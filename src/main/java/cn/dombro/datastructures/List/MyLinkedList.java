package cn.dombro.datastructures.List;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 自定义的双端链表
 * @param <AnyType>
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    //记录项数
    private int theSize;

    //记录对链表做出改变的次数，remove(),add()方法的调用会更新 modCount
    private int modCount = 0;

    //起始节点
    private Node<AnyType> beginMaker;

    //末尾节点
    private Node<AnyType> endMaker;

    public MyLinkedList(){
        doClear();
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void clear(){
        doClear();
    }


    /**
     * @param p 在 p 结点前添加新的节点，使 p 成为新节点的后继
     * @param x 新节点的数据域
     */
    private void addBefore(Node<AnyType> p,AnyType x){

        Node<AnyType> newNode = new Node<AnyType>(x,p.prev,p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    public void add(int idx,AnyType x){
        addBefore(getNode(idx,0,size()),x);
    }

    /**
     *添加到最后
     */
    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }


    public AnyType get(int idx){
        return getNode(idx).data;
    }

    public AnyType set(int idx,AnyType newValue){

        Node<AnyType> p = getNode(idx);
        AnyType oldValue = p.data;
        p.data = newValue;
        return oldValue;
    }

    public AnyType remove(int idx){
        return remove(getNode(idx));
    }



    //删除一个节点，时间复杂度为常数级别
    private AnyType remove(Node<AnyType> p){
        p.prev.next = p.next;
        p.next.prev = p.prev;
        theSize--;
        modCount++;
        return p.data;
    }

    public Node<AnyType> getNode(int idx){
       return  getNode(idx,0,size()-1);
    }


    /**
     * 通过 idx 查找节点，时间复杂度为 O(N)
     * @param idx 要查找的 Node 索引
     * @param lower 上界
     * @param upper 下界
     * @return
     */
    private Node<AnyType> getNode(int idx,int lower,int upper){

        Node<AnyType> p;

        if ( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException();

        if ( idx < size() / 2 ){
            p = beginMaker.next;
            for ( int i = 0; i < idx; i++)
                p = p.next;
        }else {
            p = endMaker;
            for (int i = size(); i > idx; i--)
                p = p.prev;
        }
        return p;
    }


    private void doClear(){

        beginMaker = new Node<AnyType>(null,null,null);
        endMaker = new Node<AnyType>(null,beginMaker,null);
        beginMaker.next = endMaker;
        theSize = 0;
        modCount++;
    }




    private static class Node<AnyType>{

        public Node(AnyType data,Node<AnyType> prev,Node<AnyType> next){
             this.data = data;
             this.prev = prev;
             this.next = next;
        }
        //数据域
        public AnyType data;
        //前驱的引用
        public Node<AnyType> prev;
        //后继的引用
        public Node<AnyType> next;
    }



    public Iterator<AnyType> iterator() {
        return null;
    }

    private class LinkedListIterator implements Iterator{

        //记录当前节点
        private Node<AnyType> current = beginMaker.next;

        //记录更改次数
        private int expectedModCount = modCount;

        private boolean okToRemove = false;


        public boolean hasNext() {

            return current != endMaker;
        }

        public Object next() {
            if ( expectedModCount != modCount )
                throw new ConcurrentModificationException();
            if ( !hasNext())
                throw new NoSuchElementException();
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if ( expectedModCount != modCount )
                throw new ConcurrentModificationException();
            if ( !okToRemove )
                throw new IllegalStateException();
            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }
}
