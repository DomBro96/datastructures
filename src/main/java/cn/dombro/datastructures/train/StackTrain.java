package cn.dombro.datastructures.train;

import cn.dombro.datastructures.List.ArrayStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * 几个栈操作的练习
 */
public class StackTrain {

    /**
     * 栈的平衡符号应用
     * 1.若遇到 [,{,(,< 入栈
     * 2.若遇到 ].},),> 出栈
     */
    public static void bracketCheck(String input){
        ArrayStack<Character> stack = new ArrayStack();

        for (int i = 0;i < input.length();i++){
            char inChar =  input.charAt(i);
            switch (inChar){
                case '[':
                case '{':
                case '(':
                case '<':
                    stack.push(inChar);
                break;

                case ']':
                case '}':
                case ')':
                case '>':
                    if (! stack.isEmpty()){
                        char popChar = stack.pop();
                        if ((popChar == '[' && inChar != ']') || (popChar == '{' && inChar != '}') || (popChar == '(' && inChar != ')')|| (popChar == '<' && inChar != '>')){
                            throw new RuntimeException("请确保有与"+inChar+"对应字符");
                        }
                    }else {
                        throw new RuntimeException("你并没输入左侧符号");
                    }
                break;

                default:
                    break;
            }
        }
        if (!stack.isEmpty()){
            throw new RuntimeException("你并没输入右侧符号");
        }
    }

    /**
     * 后缀表达式求值
     * 1.将数字依次压入栈中
     * 2.当遇到操作符时将栈中两个数字出栈，将两数字与操作符的结果再次压入栈中
     * 3.后缀表达式的好处是不用知道任何优先规则
     * 4.按照规定位与位之间用 " " 相隔
     */
    public static int postFix(String input){
        ArrayStack<Integer> stack = new ArrayStack();
        String[] postFix =  input.split(" ");
        for (int i = 0;i < postFix.length; i++){
            String card = postFix[i];
            int topOfStack ;
            int nextOfStack;
            int result;
            if (card.matches("[0-9]+")){
                 int number = Integer.parseInt(card);
                 stack.push(number);
            }else{
                switch (card){
                    case "+":
                        topOfStack = stack.pop();
                        nextOfStack = stack.pop();
                        result = topOfStack + nextOfStack;
                        stack.push(result);
                        break;
                    case "-":
                        topOfStack = stack.pop();
                        nextOfStack = stack.pop();
                        result = nextOfStack - topOfStack  ;
                        stack.push(result);
                        break;
                    case "*":
                        topOfStack = stack.pop();
                        nextOfStack = stack.pop();
                        result = nextOfStack * topOfStack  ;
                        stack.push(result);
                        break;
                    case "/":
                        topOfStack = stack.pop();
                        nextOfStack = stack.pop();
                        result = nextOfStack / topOfStack  ;
                        stack.push(result);
                        break;
                }
            }
        }
        return stack.pop();
    }

    /**
     * 通过中缀表达式得到后缀表达式
     * 1.使用栈放入操作符
     * 2.操作符仅允许使用 + - * / ()
     * 3.优先级  () 大于 * / 大于 + -
     * 4.当 遇到优先级 更高的操作符时，当遇到数字便全部出栈
     * 5.当 栈中有 ( 时，除非遇到 ) 否则 ( 不出栈
     */
    public static String getPostFix(String input){
        ArrayStack<String> opStack = new ArrayStack<>();

        String[] preFixArray = input.split(" ");
        String suffix = "";
        for (int i = 0; i < preFixArray.length; i++){
            String current = preFixArray[i];
            String temp ;
            switch (current){
                //遇到 ( 则入栈
                case "(":
                    opStack.push(current);
                    break;
                case "+":
                case "-":
                    while (!opStack.isEmpty()){
                        temp = opStack.pop();
                        //如果是栈顶是 ( 则直接将当前字符串入栈,不进行操作
                        if (temp.equals("(")){
                            opStack.push("(");
                            break;
                        }else {
                            suffix += " "+temp;
                        }

                    }
                    opStack.push(current);
                    suffix += " ";
                    break;
                case "*":
                case "/":
                    while (!opStack.isEmpty()){
                        temp = opStack.pop();
                        if (temp.equals("(") || temp.equals("+") || temp.equals("-")){
                            opStack.push(temp);
                            break;
                        }else {
                             suffix += " " + temp;
                        }
                    }
                    opStack.push(current);
                    suffix += " ";
                    break;
                case ")":
                    while (!opStack.isEmpty()){
                        temp = opStack.pop();
                        if (temp.equals("(")){
                            break;
                        }else {
                            suffix += " "+temp ;

                        }
                    }
                    break;
                default :
                        suffix += current;
                        break;
             }
        }

        while (!opStack.isEmpty()){
            suffix += " "+ opStack.pop();
        }
        System.out.println();

        return suffix;
        }







}
