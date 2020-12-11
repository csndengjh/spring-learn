package com.springboot.demo.designmode.singleton;

/**
 * @author dengjianhan
 * @className Singleton    (双检查锁) 解决性能问题   懒加载式
 * @description  用双检查锁的写法/ 以及reentrantLock 提升并发
 * @date 2020/9/9 9:27
 */
public class Singleton {

    /**
     * 应用场景:
     * 1、要求生产唯一序列号。
     * 2、WEB 中的计数器，不用每次刷新都在数据库里加一次，用单例先缓存起来。
     * 3、创建的一个对象需要消耗的资源过多，比如 I/O 与数据库的连接等
     */

    private static  volatile Singleton singleton = null;

    private Singleton (){}

//    private static Lock lock = new ReentrantLock();

    public static Singleton getInstance(){
//        lock.lock();
//        try {
//            if (singleton == null) {
//                    singleton = new Singleton();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
        // 第一个if判断要不要阻塞
        if (singleton == null) {
            synchronized (Singleton.class){
                // 第二个if判断要不要创建对象 (解决性能问题)     加volatile 解决cpu指令重排序问题
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
