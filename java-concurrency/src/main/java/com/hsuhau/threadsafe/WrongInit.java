package com.hsuhau.threadsafe06;

import java.util.HashMap;
import java.util.Map;

/**
 * 发布和初始化导致线程安全问题
 * 第二种是对象发布和初始化时导致的线程安全问题，我们创建对象并进行发布和初始化供其他类或对象使用是常见的操作，但如果我们操作的时间或地点不对，就可能导致线程安全问题。如代码所示。
 */
public class WrongInit {

    private Map<Integer, String> students;

    public WrongInit() {

        new Thread(new Runnable() {

            @Override

            public void run() {

                students = new HashMap<>();

                students.put(1, "王小美");

                students.put(2, "钱二宝");

                students.put(3, "周三");

                students.put(4, "赵四");

            }

        }).start();

    }

    public Map<Integer, String> getStudents() {

        return students;

    }

    public static void main(String[] args) throws InterruptedException {

        WrongInit multiThreadsError6 = new WrongInit();

        System.out.println(multiThreadsError6.getStudents().get(1));

    }

}
