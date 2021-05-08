package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests<T> {

    @Test
    public void contextLoads() {

        List<String> list=new ArrayList<>();
        //栈
        Stack<String> stringStack=new Stack<>();

        //队列
        Queue<String> stringQueue=new LinkedList<>();
        stringQueue.offer("a");
        stringQueue.offer("b");
        stringQueue.offer("c");
        stringQueue.offer("d");
        stringQueue.offer("e");
        for(String q : stringQueue){
            System.out.println(q);
        }
        System.out.println("===");
        //返回第一个元素，并在队列中删除
        System.out.println("poll="+stringQueue.poll());
        for(String q : stringQueue){
            System.out.println(q);
        }
        System.out.println("===");
        //返回第一个元素
        System.out.println("element="+stringQueue.element());
        for(String q : stringQueue){
            System.out.println(q);
        }
        System.out.println("===");
        //返回第一个元素
        System.out.println("peek="+stringQueue.peek());
        for(String q : stringQueue){
            System.out.println(q);
        }

    }

/*    public  void demo(Class<T>,int i){

    }*/

}
