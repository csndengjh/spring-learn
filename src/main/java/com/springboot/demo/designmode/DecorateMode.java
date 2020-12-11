package com.springboot.demo.designmode;

import org.junit.Test;

/**
 * @author dengjianhan
 * @className DecorateMode
 * @description 装饰器模式
 * @date 2020/9/24 11:42
 */

/**
 * 应用场景 :  Java IO包里的众多流处理类
 * new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
 *
 * 思想就是要去繁就简，类似俄罗斯套娃一样，一层一层包裹
 */
public class DecorateMode {

    public interface Showable{
       void show();
    }

    public class Girl implements Showable{

        @Override
        public void show() {
            System.out.print("女孩的素颜");
        }
    }

    public class Decorator implements Showable {

        private Showable showable;

        @Override
        public void show() {
            System.out.print("粉饰(");
            showable.show();
            System.out.print(")");
        }

        public Decorator(Showable showable) {
            this.showable = showable;
        }
    }

    @Test
    public void test(){
        new Decorator(new Girl()).show();
    }

    /**
     * 多层粉饰器
     */

    public abstract class DecoratorTwo implements Showable{

        protected Showable showable;

        public DecoratorTwo(Showable showable) {
            this.showable = showable;
        }

        @Override
        public void show() {
            showable.show();
        }
    }

    public class FoundationMakeup extends DecoratorTwo{

        public FoundationMakeup(Showable showable) {
            super(showable);
        }

        @Override
        public void show() {
            System.out.print("粉饰(");
            showable.show();
            System.out.print(")");
        }
    }

    public class Lipstick extends DecoratorTwo{
        public Lipstick(Showable showable) {
            super(showable);
        }

        @Override
        public void show() {
            System.out.print("口红(");
            showable.show();
            System.out.print(")");
        }
    }

    @Test
    public void test3(){
        new Lipstick(new FoundationMakeup(new Girl())).show();
    }

}
