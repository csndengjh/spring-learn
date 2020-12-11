package com.springboot.demo.lambda;



/**
 *  Lambda 表达式 (实际是匿名内部类的另外一种写法)
 * @author: dengjianhan
 * @date: 2018/11/26 9:45
 */
public class LambdaExpressionTest {

    public static void main(String[] args) {

        // java8 以前的写法(匿名内部类)
        MathOperation addtion = new MathOperation() {
            @Override
            public int operation(int a,int b) {
                return a+b;
            }
        };
        // java8 的写法
        //类型声明
        MathOperation addtion1 = (int a,int b)->{
            return a+b;
        };
        //不用类型声明
        MathOperation substraction = (a,b)-> a-b;
        System.out.println( substraction.operation(1,3));

        //大括号的返回语句
        MathOperation division = (int a,int b) -> { return a/b; };

        //没有大括号的返回语句
        MathOperation multiplactition = (int a,int b) -> a*b;


        GreetingService greetingService1 = message -> System.out.println("hello"+message);

        GreetingService greetingService2 = (String message) -> {
            System.out.println("hello"+message);
        };
        greetingService1.sendMessage("Runoob");
        greetingService2.sendMessage("Google");

        LambdaExpressionTest test = new LambdaExpressionTest();
        System.out.println("22+33 = "+test.operation(22,33,addtion1));

    }
    interface MathOperation{
        int operation(int a, int b);
    }

    interface GreetingService{
        void sendMessage(String message);
    }

    private int operation(int a,int b,MathOperation mathOperation){
        return mathOperation.operation(a,b);
    }
}
