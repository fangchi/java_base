package com.fc.jd8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class LambaTest {

    public void add(int a, int b, Inf inf) {
        int c = inf.add(a, b);
        System.out.println(c);
    }

    public void function(int a, Function<Integer, String> fun2) {
        String s = fun2.apply(a);
        System.out.println(s);
    }

    public void consumer(int a, Consumer<Integer> fun2) {
        fun2.accept(a);
    }

    public void supplier(int a, Supplier<Long> fun2) {
        Long s = fun2.get();
        System.out.println(s);
    }

    public void predict(Long a, Predicate<Long> fun2) {
        Boolean s = fun2.test(a);
        System.out.println(s);
    }

    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<Student>() {
            {
                add(new Student("stu1", 100.0));
                add(new Student("stu2", 97.0));
                add(new Student("stu3", 96.0));
                add(new Student("stu4", 95.0));
            }
        };
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.getScore(), o2.getScore());
            }
        });

        Collections.sort(studentList, (s1, s2) -> Double.compare(s1.getScore(), s2.getScore()));

        Collections.sort(studentList, Student::localComp);

        System.out.println(studentList);

        new Thread(() -> System.out.println("hello, i am thread!")).start();

        new LambaTest().add(4, 2, (s1, s2) -> s1 * s2);

        new LambaTest().function(4, s1 -> String.valueOf(s1 * s1));

        new LambaTest().consumer(4, s1 -> System.out.println("hello:" + s1));

        new LambaTest().supplier(4, () -> System.currentTimeMillis());

        new LambaTest().predict(4L, (s1) -> s1 % 2 == 0);

        List<String> labels = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        Stream<Student> s2 = labels.stream().map(s -> new Student("1" + s + "1", 1d));
        Student[] students = s2.toArray(Student[]::new);
        for (Student student : students) {
            System.out.println(student.getName());
        }
    }
}
