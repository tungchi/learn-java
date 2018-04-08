package chapter07;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author tengfei.fangtf
 * @version $Id: AtomicIntegerTest.java, v 0.1 2015-8-1 ÉÏÎç12:13:16 tengfei.fangtf Exp $
 */
public class AtomicIntegerTest {

    static AtomicInteger ai = new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
        System.out.println(ints[2]);
        ints[2] = 5;
        System.out.println(ints[2]);
        System.out.println(str);
//        str="adf";
//        System.out.println(str);
    }
    
    //数组用final修饰毫无意义
    public static final int[] ints = new int[5];
    public static final String str = "hahha";

}