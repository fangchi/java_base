package com.fc.jd8;

import static java.util.stream.Collectors.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        Random random = new Random();
        List<Student> stuList = new ArrayList<Student>() {
            {
                for (int i = 0; i < 100; i++) {
                    add(new Student("student" + i % 30, random.nextInt(50) + 50d));
                }
            }
        };
        List<String> studentList = stuList.stream().filter(s -> s.getScore() > 20)
            .sorted((s1, s2) -> Double.compare(s1.getScore(), s2.getScore()))
            .map(student -> "hi" + student.getName() + "," + student.getScore()).collect(Collectors.toList());
        System.out.println(studentList);

        Stream<String> stream =
            Stream.generate(() -> "user" + System.currentTimeMillis()).limit(20).filter(s -> !s.equals("s"));
        stream.forEach(System.out::println);

        Integer s = Stream.generate(() -> new Random().nextInt(100)).limit(10).max(Integer::compareTo).get();
        System.out.println(s);

        Stream.iterate(3, s1 -> s1 + 2).skip(2).limit(5).forEach(System.out::println);

        System.out.println(Stream.iterate(3, s1 -> s1 + 2).skip(2).limit(5)
            .collect(Collectors.summarizingInt((s12) -> s12)).getAverage());

        Map<String, List<Student>> map0 = stuList.stream().collect(groupingBy(s2 -> s2.getName(), Collectors.toList()));
        map0.forEach((x, y) -> System.out.println(x + "->" + y.size()));

        Map<String, Long> map1 = stuList.stream().collect(groupingBy(s2 -> s2.getName(), counting()));
        map1.forEach((x, y) -> System.out.println(x + "->" + y));

        Map<String, Double> map2 =
            stuList.stream().collect(groupingBy(s2 -> s2.getName(), summingDouble(s3 -> s3.getScore())));
        map2.forEach((x, y) -> System.out.println(x + "->" + y));

        Map<String, Optional<Student>> map3 = stuList.stream()
            .collect(groupingBy(s2 -> s2.getName(), maxBy((s3, s4) -> Double.compare(s3.getScore(), s4.getScore()))));
        map3.forEach((x, y) -> System.out.println(x + "->" + (y.isPresent() ? y.get() : y.orElse(null))));
        System.out.println("parallelStream....");
        stuList.parallelStream().filter(s1 -> s1.getScore() > 80).peek(s1 -> s1.setScore(s1.getScore() + 100))
            .peek(System.out::println).collect(toList());// .forEach(System.out::println);
    }
}
