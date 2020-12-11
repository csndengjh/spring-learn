package com.springboot.demo.inherit;

import lombok.Data;

class Father {

    private  Integer money = 300;

    protected int count = 501;

    public Father() {
    }

    public void show(){
        System.out.println("father count is "+count);
    }

    public static void main(String[] args) {
        //向上转型
        Father father = new Son();
        System.out.println(father.money);
        father.show();
      /*  向上转型时调用的属性还是父类的属性,如果子类重写父类的方法，调用的就是子类的方法*/


        //向下转型
        Father ff = new Father();
        if(father instanceof Son){
            Son son = (Son)father;
            System.out.println(son.count);
        }

        if(ff instanceof Son){
            Son son = (Son)ff;
            System.out.println("FF 转型成功");
        }



    }
}

@Data
public class Son extends Father{

    private  Integer money;

    public int count = 200;

    private String name;

    public Son() {
    }

    public Son(Integer money, int count, String name) {
        this.money = money;
        this.count = count;
        this.name = name;
    }

    @Override
    public void show() {
        System.out.println("son count is "+count);
    }

    public static void main(String[] args) {
        Son son = new Son();
        System.out.println(son.count);
        son.show();
  /*      Father father = new Son();
        father.show();*/
    }

    public static class GrandFather{
    }
}
