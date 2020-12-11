package com.springboot.demo.lambda;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
public interface Supplier<T>{
    T get();
}

class Car{

    public Car() {}

    public Car(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * Supplier是jdk1.8的接口，这里和lamda一起使用了
     * @param supplier
     * @return
     */
    public static Car create(final Supplier<Car> supplier){
        return supplier.get();
    }

    public static void collide(final Car car){
        System.out.println("collide" + car.toString());
    }

    public void  follow(final Car car){
        System.out.println("follow" + car.toString());
    }

    public void repair(){
        System.out.println("repair" + this.toString());
    }

    public static void main(String[] args) {
        //构造器引用
        final Car car = Car.create(Car ::new);
        final List<Car> cars = Arrays.asList(car);
        //静态方法引用 ：它的语法是Class::static_method
        cars.forEach(Car::collide);
        //特定类的任意对象的方法引用：它的语法是Class::method
        cars.forEach(Car::repair);
    }
}