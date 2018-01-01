package cn.dombro.datastructures.tree;

import java.nio.BufferUnderflowException;

/**
 * 二叉树搜索树
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>{

    /**
     * 二叉树节点类嵌套类
     */
    private static class BinaryNode<AnyType>{

        //数据项
        AnyType elements;
        //左孩子节点
        BinaryNode<AnyType> left;
        //右孩子节点
        BinaryNode<AnyType> right;

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt){
            elements = theElement;
            left = lt;
            right = rt;
        }

        BinaryNode(AnyType theElement){
            this(theElement,null,null);
        }
    }

    //根节点引用
    private BinaryNode<AnyType> root;

    //利用构造方法初始化
    public BinarySearchTree(){
        root = null;
    }

    //令二叉搜索树为空
    public void makeEmpty(){
        root = null;
    }
    //判断二叉树是否为空
    public boolean isEmpty(){
        return root == null;
    }

    //判断树中是否包含某项
    public boolean contains(AnyType x){
        return contains(x,root);
    }



    public AnyType findMin(){
        if (isEmpty())
            throw new BufferUnderflowException();
        return findMin(root).elements;
    }

    public AnyType findMax(){
        if (isEmpty())
            throw new BufferUnderflowException();
        return findMax(root).elements;
    }

    public void insert(AnyType x){
        root = insert(x,root);

    }

    public void remove(AnyType x){
         remove(x,root);
    }

    //打印树
    public void printTree(){
        printTree(root);
    }



    //从根节点判断该二叉搜索树是否有节点的数据项与传入节点相同
    private boolean contains(AnyType x, BinaryNode<AnyType> t){
        if (t == null || t.elements == null)
            return false;
        int compareResult = x.compareTo(t.elements);
        //如果传入数据比当前节点的数据小，则去当前节点的左孩子查找
        if (compareResult < 0){
            return contains(x,t.left);
        //如果传入数据比当前节点的数据大，则去当前节点的右孩子查找
        }else if (compareResult > 0){
            return contains(x,t.right);
        }else
            return true;
    }

    /**
     * 从根节点查找最小节点
     * 由于二叉搜索树根节点左侧均比根节点小,
     *
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t){
         if (t != null){
             while (t.left != null){
                 t = t.left;
             }
         }
         return t;
    }

    //与查找最小值类似
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t){
        if (t != null){
            while (t.right != null){
                t = t.right;
            }
        }
        return t;
    }

    /**
     * 二叉查找树的插入操作
     * 该方法通过比较数据项的大小总额能找到插入节点的合适位置
     */
    private BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> t){

        if (t == null){
            return new BinaryNode<>(x,null,null);
        }

        int compareResult = x.compareTo(t.elements);

        if (compareResult < 0){
            t.left = insert(x,t.left);
        }else if (compareResult > 0 )
            t.right = insert(x,t.right);
        else
            ;
        return t;
    }

    //删除节点
    private BinaryNode<AnyType> remove(AnyType x,BinaryNode<AnyType> t){

        if (t == null){
            return t;
        }

        int compareResult = x.compareTo(t.elements);

        if ( compareResult < 0){
            remove(x,t.left);
        }else if( compareResult > 0){
            t.right = remove(x,t.right);
        //要删除的节点有两个孩子
        }else if ( t.left != null && t.right != null){
            //使其右子树中最小的节点替代
            t.elements = findMin(t.right).elements;
            //删除该最小节点
            t.right = remove(t.elements,t.right);
        //要删除的节点只有一个孩子,则由该孩子替代
        }else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    //中序遍历
    private void printTree(BinaryNode<AnyType> t){
        if (t != null && t.elements != null){
            printTree(t.left);
            System.out.println(t.elements);
            printTree(t.right );
        }
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(6);
        tree.insert(2);
        tree.insert(8);
        tree.insert(1);
        tree.insert(4);
        tree.insert(3);
        System.out.println(tree.contains(4));

    }

}
