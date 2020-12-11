package com.springboot.demo.lambda;

/**
 *  lambda 表达式，对应重写的接口里面只能有一个抽象方法
 * @author dengjianhan
 * @date 2018/12/7 10:19
 */
public class LambdaExpressionTest1 {
    /**
     * 外部传入lambda表达式里的参数变量
     * 可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有 final 的语义）
     *
     *  Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量。
     */
    public static void main(String[] args) {
        int num = 2;
        Converter converter = (int i) -> {
            System.out.println(i+num);
        };
        converter.convert(22);
       // num =3;

        Converter converter2 = i -> System.out.println(i);
        Converter converter3 = (int i) -> {
            System.out.println(i);
        };
        converter3.convert(33);
    }

    interface Converter<T1,T2>{
        void convert(int i);
    }
}
