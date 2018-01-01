package cn.dombro.datastructures.List;


/**
 * 用链表实现的栈
 */
public class LinkedStack<AnyType> {


    //栈规模
    private int theSize;

    //栈顶结点
    private Node<AnyType> topOfStack;

    /**
     *入栈操作：
     * 1.将栈顶结点作为插入节点的前驱
     * 2.栈顶结点变为新插入节点
     * 3.栈规模加一
     */
    public void push(AnyType item){
        Node<AnyType> newNode = new Node<AnyType>(item,topOfStack);
        topOfStack.next = newNode;
        topOfStack = topOfStack.next;
        theSize++;
    }

    public AnyType peek(){
        return topOfStack.data;
    }

    /**
     * 出栈操作:
     * 1.返回当前栈顶结点的数据域
     * 2.栈顶结点的前驱作为栈顶结点
     * 3.栈规模减一
     *
     */
    public AnyType pop(){

        Node<AnyType> oldTop;
        if (theSize <= 0){
            throw new IllegalStateException();
        }
        oldTop = topOfStack;
        topOfStack = topOfStack.prev;
        theSize--;
        return oldTop.data;
    }

    public LinkedStack(){
        doClear();
    }

    /**
     * 对栈进行初始化
     */
    private void doClear(){
        topOfStack = new Node<AnyType>(null,null);
        theSize = 0;
    }



    private static class Node<AntType>{

        public AntType data ;
        public Node<AntType> next;
        public Node<AntType> prev;

        public Node(AntType data,Node<AntType> prev){
            this.data = data;
            this.prev = prev;
        }

    }

    public boolean isEmpty(){
        return theSize <= 0;
    }



    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }



}

