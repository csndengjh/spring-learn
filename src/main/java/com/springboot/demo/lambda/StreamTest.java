package com.springboot.demo.lambda;

import lombok.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
class User{
    private String name;

    private Integer age;

    private Boolean bool;


    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


}


public class StreamTest {

    private  List<User> list = new ArrayList<>();

    @Before
    public void init(){
         list = Arrays.asList(
                // name，age
                new User("张三", 11, Boolean.TRUE),
                new User("王五", 20, Boolean.FALSE),
                new User("王五", 91, Boolean.FALSE),
                new User("张三", 8, Boolean.TRUE),
                new User("李四", 44, Boolean.FALSE),
                new User("李四", 44, Boolean.TRUE),
                new User("李四", 44, Boolean.FALSE)
        );

    }

    @Test
    public void testForEach(){
        list.stream().forEach(user -> System.out.println(user));
    }

    @Test
    public void testSort(){
        list.stream().sorted(Comparator.comparing(User::getAge)).forEach(user -> System.out.println("名字:"+ user.getName() + "年龄:"+user.getAge()));
    }

    @Test
    public void testFilter(){
        List<User> collect = list.stream().filter((User user) -> user.getAge() > 1000).collect(Collectors.toList());
        collect.forEach( user -> System.out.println(user));
    }

    @Test
    public void testLimit(){
        // 从第三个开始截断，只输出前三个
        System.out.println("-----截断前-----");
        list.stream().forEach(user -> System.out.println(user));
        System.out.println("-----截断后-----");
        list.stream().limit(3).forEach(user -> System.out.println(user));
    }

    /**
     * skip 跳过
     */
    @Test
    public void testSkip(){
        System.out.println("-----跳过前-----");
        list.stream().forEach(user -> System.out.println(user));
        System.out.println("-----跳过后-----");
        list.stream().skip(3).forEach(user -> System.out.println(user));
    }

    /**
     * distinct()：使用该方法去重，注意：必须重写对应泛型的hashCode()和equals()方法
     */
    @Test
    public void testDistinct(){
        System.out.println("-----去重前-----");
        list.stream().forEach(user -> System.out.println(user));
        System.out.println("-----去重后-----");
        list.stream().distinct().forEach(user -> System.out.println(user));
    }


    /**
     * 去重+按照年龄大于40以后从小到大+只取前二
     */
    @Test
    public void demo(){
//        list.stream().distinct().filter(user-> user.getAge()>40).sorted(Comparator.comparing(User ::getAge)).limit(2).forEach(user -> System.out.println(user));
        list.stream().distinct().filter(user-> user.getAge()>2000).map(User::getAge).collect(Collectors.toList());
    }

    /**
     * max，min，sum，avg，count
     */
    @Test
    public void testNum(){
        IntSummaryStatistics num = list.stream().mapToInt(user -> user.getAge()).summaryStatistics();
        System.out.println("总共人数：" + num.getCount());
        System.out.println("平均年龄：" + num.getAverage());
        System.out.println("最大年龄：" + num.getMax());
        System.out.println("最小年龄：" + num.getMin());
        System.out.println("年龄之和：" + num.getSum());
    }

    /**
     * map()：接收一个方法作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     *  映射
     */
    @Test
    public void testMap(){
        // 只输出所有人的年龄
        List<Integer> ages = list.stream().map(user -> user.getAge()).collect(Collectors.toList());
        ages.stream().forEach(age -> System.out.println(age));

        System.out.println("-----小写转大写-----");
        // 小写转大写
        List<String> words = Arrays.asList("aaa","vvv","ccc");
        words = words.stream().map(a -> a.toUpperCase()).collect(Collectors.toList());
        words.stream().forEach(s -> System.out.println(s));
    }

    /**
     * flatMap()：对每个元素执行mapper指定的操作，并用所有mapper返回的Stream中的元素组成一个新的Stream作为最终返回结果，
     * 通俗易懂就是将原来的stream中的所有元素都展开组成一个新的stream
     */
    @Test
    public void testFlatMap(){
        //创建一个 装有两个泛型为integer的集合
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1,2,3),Arrays.asList(4,5,6));
        // 将两个合为一个
        Stream<Integer> integerStream  = stream.flatMap(
                (Function<List<Integer>,Stream<Integer>>) integers -> integers.stream());
        //为新的集合
        List<Integer> collect = integerStream.collect(Collectors.toList());
        collect.stream().forEach(integer -> System.out.println(integer));
    }

    /**
     * findFirst() ：使用该方法获取第一个元素
     */
    @Test
    public void testFindFirst(){
        User user = list.stream().findFirst().get();
        System.out.println(user);
    }

    @Test
    public void wrwertest(){
       List<User> list = Arrays.asList(
                // name，age
               new User("02", 91),
                new User("00", 11),
                new User("01", 20),
                new User("03", 8),
                new User("05", 44),
                new User("李四", 44),
                new User("李四", 44)
        );

        Optional<User> first = list.stream().sorted(Comparator.comparing(User::getName)).findFirst();
        System.out.println(first);
    }


    @Test
    public void groupByToMapTest(){
        // 转成以名字自行groupby ,如需获取分组里面的最大值，可以提前进行排序
        Map<String, User> map = list.stream().collect(Collectors.toMap(User::getName, Function.identity(),
                (existing, replacement) -> existing));
        System.out.println(map);

        // groupby 写法
        Map<String, List<User>> collect1 = list.stream().collect(Collectors.groupingBy(User::getName));

        Map<Boolean, List<User>> collect = list.stream().collect(Collectors.partitioningBy(User::getBool));
        System.out.println(collect);


        Map<String, List<Integer>> collect2 = list.stream()
                .collect(Collectors.groupingBy(User::getName,
                        Collectors.mapping(User::getAge, Collectors.toList())
                        )
                );
        System.out.println(collect2);


    }


    @Test
    public void parallelStreamsTest(){
        // 并行流的使用 详细参考java 1.7 的ForkJoinTask这个框架的工作原理
        // 并行流会存在共享变量修改的问题
        /**
         * 尽量使用 LongStream / IntStream / DoubleStream 等原始数据流代替 Stream 来处理数字，以避免频繁拆装箱带来的额外开销
         *
         * 要考虑流的操作流水线的总计算成本，假设 N 是要操作的任务总数，Q 是每次操作的时间。N * Q 就是操作的总时间，Q 值越大就意味着使用并行流带来收益的可能性越大
         *
         * 例如：前端传来几种类型的资源，需要存储到数据库。每种资源对应不同的表。我们可以视作类型数为 N，存储数据库的网络耗时 + 插入操作耗时为
         * Q。一般情况下网络耗时都是比较大的。因此该操作就比较适合并行处理。当然当类型数目大于核心数时，该操作的性能提升就会打一定的折扣了。更好的优化方法在日后的博客会为大家奉上
         *
         * 对于较少的数据量，不建议使用并行流
         *
         * 容易拆分成块的流数据，建议使用并行流
         */
        list.parallelStream().forEach(
                user -> {
                    System.out.println(user);
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

    }

    @Test
    public void StreamCreate(){
        // Stream.of 可变参数
        Stream<String> stream1 = Stream.of("A", "B", "C");
        System.out.println("stream1:  " + stream1.collect(Collectors.joining(";")));
        // Stream.of 数组
        String[] values = new String[]{"A","B","C"};
        Stream<String> stream2 = Stream.of(values);
        System.out.println("stream2:  " + stream2.collect(Collectors.joining()));
        // Arrays.stream
        Stream<String> stream3 = Arrays.stream(values);
        System.out.println("stream3:  " + stream3.collect(Collectors.joining()));
        // List
        List<String> list = Arrays.asList(values);
        Stream<String> stream4 = list.stream();
        System.out.println("stream4:  " + stream4.collect(Collectors.joining()));
        // set
        Set set = new HashSet(list);
        Stream<String> stream5 = set.stream();
        System.out.println("stream5:  " + stream5.collect(Collectors.joining()));

        // map
        Map<String, String> map = new HashMap<>();
        map.put("1", "A");
        map.put("2", "B");
        map.put("3", "C");
        Stream<String> stream6 = map.values().stream();
        System.out.println("stream6:  " + stream6.collect(Collectors.joining()));
        // Stream.iterate
        Stream<String> stream7 = Stream.iterate("A", e -> String.valueOf((char) e.charAt(0) + 1)).limit(3);
        System.out.println("stream7:  " + stream7.collect(Collectors.joining()));
        // Pattern
        String value = "A B C";
        Stream<String> stream8 = Pattern.compile("\\W").splitAsStream(value);
        System.out.println("stream8:  " + stream8.collect(Collectors.joining()));
        // Files.lines
        try {
            Stream<String> stream9 = Files.lines(Paths.get("d:/data.txt"));
            System.out.println("stream9:  " + stream9.collect(Collectors.joining()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Stream.generate
        Stream<String> stream10 = Stream.generate(() -> "A").limit(3);
        System.out.println("stream10:  " + stream10.collect(Collectors.joining()));

    }


    @Test
    public void reduceTest(){
        // reduce接收了一个初始值为0的累加器，依次取出值与累加器相加，最后累加器的值就是最终的结果
        Integer reduce = Stream.of(1, 2, 3, 4).reduce(0, (acc, x) -> acc + x);
        System.out.println(reduce);
    }


    @Test
    public void maxTest(){
        User user = list.stream().max(Comparator.comparing(User::getAge)).get();
        User user1 = list.stream().collect(Collectors.maxBy(Comparator.comparing(User::getAge))).get();
        System.out.println(user);
        System.out.println(user1);
    }

    @Test
    public void partitioningByTest(){
        // 将userList 分成年龄 =44 ,与 年龄非44两个集合
        Map<Boolean, List<User>> collect =
                list.stream().collect(Collectors.partitioningBy(user -> user.getAge() == 44));
        List<User> users = collect.get(Boolean.TRUE);
        System.out.println(users);
        List<User> users1 = collect.get(Boolean.FALSE);
        System.out.println(users1);

//        Integer[] ages = {1,2,3};
//        System.out.println("打印数组:"+ ages);

    }


}

