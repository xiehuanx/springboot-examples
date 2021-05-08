package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2018/9/7
 * Time: 16:51
 * Describe:
 */
public class Demo {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

        strings.stream().findFirst();

        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }
}
