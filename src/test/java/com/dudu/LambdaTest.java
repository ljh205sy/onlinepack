package com.dudu;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: liujinhui
 * @Date: 2019/11/12 17:08
 */
public class LambdaTest {


    interface MathOperation {
        int operation(int a, int b);
    }

    @Test
    public void lamTest() {
        List<String> names = new ArrayList<>();
        names.add("google");
        names.add("taobao");
        names.add("sina ");

        List<String> areaList = new ArrayList<>();
        areaList.add("song");
        areaList.add("apple");
        areaList.add("cookie");

        System.out.println("JAVA8 原始顺序 >>> :          " + names);
        sortTest7(names);
        System.out.println("Java7 Collections排序后 >>> : " + names);

        System.out.println("JAVA8 原始顺序 >>> :          " + areaList);
        sortTest8(areaList);
        System.out.println("JAVA8 Collections排序后 >>> : " + areaList);

        MathOperation add = (int a, int b) -> a + b;
        MathOperation sub = (a, b) -> a - b;
        MathOperation mul = (int a, int b) -> {
            return a * b;
        };
        MathOperation div = (int a, int b) -> a / b;

        // 方法引用
        int a = add.operation(1, 2);
        System.out.println(a);

        int b = sub.operation(1, 2);
        System.out.println(b);

        int c = mul.operation(1, 2);
        System.out.println(c);

        int d = div.operation(1, 2);
        System.out.println(d);


    }

    // 使用java7排序
    private static void sortTest7(List<String> names) {
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    // 使用java8语法排序
    private static void sortTest8(List<String> names) {
        Collections.sort(names, ((o1, o2) -> o1.compareTo(o2)));
    }

    /**
     * Java 8 Lambda 表达式
     *
     * Lambda表达式，也可以称之为闭包，它是推动Java8发布的最重要的特性
     *
     * Lambda表达式允许函数作为一个方法的参数
     *
     * Lambda表达式的语法
     *
     * (parameters) -> {statements;}
     *
     *
     * Lambda表达式简单例子
     *
     *
     * () -> 5 不需要参数, 返回值为 5
     *
     * x -> 2 * x 接收一个参数（数字类型）返回其2倍的数
     *
     * (x,y) -> x - y 接收两个参数(数字) 并返回它们的差值
     *
     * (int x, int y) -> x + y 接收两个int值，并返回他们的和
     *
     * (String s) -> System.out.print(s) 接收一个String 对象，并在控制台打印
     *
     */

}
