package chapter03;

import java.util.ArrayList;
import java.util.List;

class ReorderExample {
    int     a    = 0;
    boolean flag = false;

    public void writer() {
        a = 1; //1
        flag = true; //2
    }

    public void reader() {
        if (flag) { //3
            int i = a * a; //4
            System.out.println(i);
            //s……
        }
    }
    
    public static void main(String[] args){
    	List<Thread> ts = new ArrayList<Thread>(1000);
    	for (int j = 0; j < ts.size(); j++) {
            ReorderExampleThread reorderExampleThread = new ReorderExampleThread();
            Thread t = new Thread(reorderExampleThread);
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();
        }
    }
}