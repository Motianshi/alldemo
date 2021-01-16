package com.anqi.demo.demostream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(classes = DemoStreamApplicationTests.class)
public class DemoStreamApplicationTests {
    //计算机俱乐部
    private static List<Student> computerClub = Arrays.asList(
            new Student("2015134001", "小明", 15, "1501"),
            new Student("2015134003", "小王", 14, "1503"),
            new Student("2015134006", "小张", 15, "1501"),
            new Student("2015134008", "小梁", 17, "1505")
    );
    //篮球俱乐部
    private static List<Student> basketballClub = Arrays.asList(
            new Student("2015134012", "小c", 13, "1503"),
            new Student("2015134013", "小s", 14, "1503"),
            new Student("2015134015", "小d", 15, "1504"),
            new Student("2015134018", "小y", 16, "1505")
    );
    //乒乓球俱乐部
    private static List<Student> pingpongClub = Arrays.asList(
            new Student("2015134022", "小u", 16, "1502"),
            new Student("2015134021", "小i", 14, "1502"),
            new Student("2015134026", "小m", 17, "1504"),
            new Student("2015134027", "小n", 16, "1504")
    );

    private static List<List<Student>> allClubStu = new ArrayList<>();

    @BeforeAll
    public static void addAllClubStu() {
        allClubStu.add(computerClub);
        allClubStu.add(basketballClub);
        allClubStu.add(pingpongClub);
    }

    @Test
    public void testCreatStream() {
        //1.集合
        Stream<Student> stream = basketballClub.stream();
        //2.静态方法
        Stream<String> stream2 = Stream.of("a", "b", "c");
        //3.数组
        String[] arr = {"a","b","c"};
        Stream<String> stream3 = Arrays.stream(arr);
    }


    @Test
    public void testCollect() {
        //此处只是演示 此类需求直接用List构造器即可
        List<Student> collect = computerClub.stream().collect(Collectors.toList());
        Set<Student> collect1 = pingpongClub.stream().collect(Collectors.toSet());
        //注意key必须是唯一的 如果不是唯一的会报错而不是像普通map那样覆盖
        Map<String, String> collect2 = pingpongClub.stream()
                .collect(Collectors.toMap(Student::getIdNum, Student::getName));
        //分组 类似于数据库中的group by
        Map<String, List<Student>> collect3 = pingpongClub.stream()
                .collect(Collectors.groupingBy(Student::getClassNum));

        //字符串拼接 第一个参数是分隔符 第二个参数是前缀 第三个参数是后缀
        String collect4 = pingpongClub.stream().map(Student::getName).collect(Collectors.joining(",", "【", "】"));
        System.out.println(collect4);

        //三个俱乐部符合年龄要求的按照班级分组
        Map<String, List<Student>> collect5 = Stream.of(basketballClub, pingpongClub, computerClub)
                .flatMap(e -> e.stream().filter(s -> s.getAge() < 17))
                .collect(Collectors.groupingBy(Student::getClassNum));
        System.out.println(collect5);

        //按照是否年龄>16进行分组 key为true和false
        ConcurrentMap<Boolean, List<Student>> collect6 = Stream.of(basketballClub, pingpongClub, computerClub)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingByConcurrent(s -> s.getAge() > 16));
        System.out.println(collect6);
    }

    @Test
    public void testMaxMinAvgSum() {
        Double avg = pingpongClub.stream().collect(Collectors.averagingDouble(Student::getAge));

        Optional<Student> max = pingpongClub.stream().max(Comparator.comparing(Student::getIdNum));
        Optional<Student> min = pingpongClub.stream().min(Comparator.comparing(Student::getIdNum));
        if (max.isPresent()) {
            System.out.println(max.get());
        }
        if (min.isPresent()) {
            System.out.println(min.get());
        }
        Integer collect = pingpongClub.stream().mapToInt(Student::getAge).sum();
        System.out.println(collect);
        OptionalInt max2 = pingpongClub.stream().mapToInt(Student::getAge).max();
        if (max2.isPresent()) {
            int asInt = max2.getAsInt();
            System.out.println(asInt);
        }
    }

    @Test
    public void testFilter() {
        //筛选1501班的学生
        computerClub.stream().filter(e -> e.getClassNum().equals("1501")).forEach(System.out::println);
        //筛选年龄大于15的学生
        List<Student> collect = computerClub.stream().filter(e -> e.getAge() > 15).collect(Collectors.toList());
    }

    @Test
    public void testPeekAndMapAndFlatMap() {
        //篮球俱乐部所有成员名 + 赞助商商标^_^,并且获取所有队员详细内容
        List<Student> collect = basketballClub.stream()
                .peek(e -> e.setName(e.getName() + "^_^"))
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        
        //篮球俱乐部所有成员名 + 暂时住上商标^_^,并且获取所有队员名
        List<String> collect1 = basketballClub.stream()
                .map(e -> e.getName() + "^_^")
                .collect(Collectors.toList());
        collect1.forEach(System.out::println);

        //获取年龄大于15的所有俱乐部成员
        List<Student> collect2 = Stream.of(basketballClub, computerClub, pingpongClub)
                .flatMap(e -> e.stream().filter(s -> s.getAge() > 15))
                .collect(Collectors.toList());
        collect2.forEach(System.out::println);

        //用双层list获取所有年龄大于15的俱乐部成员
        List<Student> collect3 = allClubStu.stream()
                .flatMap(e -> e.stream().filter(s -> s.getAge() > 15))
                .collect(Collectors.toList());
        collect3.forEach(System.out::println);
    }

    @Test
    public void testDistinectAndSortedAndLimit() {
        List<String> list = Arrays.asList("b","b","c","a");
        list.forEach(System.out::print); //bbca
        List<String> collect = list.stream().distinct().sorted().collect(Collectors.toList());
        collect.forEach(System.out::print);//abc
        //获取list中排序后的top2 即截断取前两个
        List<String> collect1 = list.stream().distinct().sorted().limit(2).collect(Collectors.toList());
        collect1.forEach(System.out::print);//ab
    }

    @Test
    public void testMatch() {
        boolean b = basketballClub.stream().allMatch(e -> e.getAge() < 20);
        boolean b1 = basketballClub.stream().anyMatch(e -> e.getAge() < 20);
        boolean b2 = basketballClub.stream().noneMatch(e -> e.getAge() < 20);
    }

    @Test
    @RepeatedTest(5)
    public void testFind() {
        Optional<Student> first = basketballClub.stream().findFirst();
        if (first.isPresent()) {
            Student student = first.get();
            System.out.println(student);
        }

        Optional<Student> any = basketballClub.stream().findAny();
        if (any.isPresent()) {
            Student student2 = any.get();
            System.out.println(student2);
        }
        Optional<Student> any1 = basketballClub.stream().parallel().findAny();
        System.out.println(any1);
    }

    @Test
    public void testCountMaxMin() {
        long count = basketballClub.stream().count();
        Optional<Student> max = basketballClub.stream().max(Comparator.comparing(Student::getAge));
        if (max.isPresent()) {
            Student student = max.get();
        }
        Optional<Student> min = basketballClub.stream().min(Comparator.comparingInt(Student::getAge));
        if (min.isPresent()) {
            Student student = min.get();
        }

    }
}
