package cn.dombro.datastructures.List;


import java.util.EmptyStackException;

/**
 * 基于数组实现的栈
 */
public class ArrayStack<AnyType> {

    //默认规模
    private static final int DEFAULT_CAPACITY = 10;
    //用数组实现栈
    private AnyType[] theArray;
    //记录栈的规模
    private int theSize;
    //记录栈顶元素位置
    private int top0fStack;
    //通过构造方法完成初始化
    public ArrayStack(){
        doClear();
    }

    /**
     * 入栈操作
     * 1.栈顶元素索引 ++，并指向插入元素
     * 2.栈规模 ++
     */
    public void push(AnyType item){
        //判断栈是否已满
        if(theSize == theArray.length){
            ensureCapacity(theSize*2+1);
        }
        top0fStack++;
        theArray[top0fStack] = item;
        theSize++;
    }

    public AnyType peek(){
        if (size() == 0){
            throw new EmptyStackException();
        }else {
            return theArray[theSize-1];
        }

    }

    public int size() {
        return theSize;
    }

    /**
     * 出栈操作
     * 1.栈规模 --
     * 2.返回当前栈顶元素，栈顶元素索引--
     */
    public AnyType pop(){
        theSize--;
        return theArray[top0fStack--];
    }



    private void doClear(){
        theSize = 0;
        top0fStack = -1;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    //扩充栈规模
    public void ensureCapacity(int newCapacity){

        if (theSize > newCapacity)
            return;
        //判断theArray有没有被初始化
        if (theArray != null){
            AnyType[] oldItems = theArray;
            theArray = (AnyType[]) new Object[newCapacity];
            for (int i = 0; i < theSize; i++){
                theArray[i] = oldItems[i];
            }
        }else{
            theArray = (AnyType[]) new Object[newCapacity];
        }

    }

    public boolean isEmpty(){
        return theSize == 0;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<Integer>();
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
    }
}
