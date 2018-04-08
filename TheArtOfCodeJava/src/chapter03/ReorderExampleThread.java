package chapter03;

public class ReorderExampleThread implements Runnable{

	@Override
	public void run() {
		ReorderExample order = new ReorderExample();
    	order.writer();
    	Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				order.writer();
			}
		});
    	Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				order.reader();
			}
		});
    	t1.start();
    	t2.start();
	}

}
