import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author zhaofahu
 * @date 2019-01-12 10:22
 */

public class thread {

	public static void main(String[] args) {
		// 继承Thread类
		MyThread mt = new MyThread();//4.创建Thread类的子类对象
		mt.start();  // 一步完成，耦合性高

		// 实现Runnable接口
		MyRunnable myRunnable = new MyRunnable();
		Thread thread = new Thread(myRunnable);
		thread.start();

		// 0、   实现Callable接口
		//      1、重写Callable接口的call（）方法
		//		* 2、创建FutureTask的对象，然后线程放到FutureTask中
		//		* 3、线程启动FutureTask的对象
		MyCallable myCallable = new MyCallable();
		FutureTask<Integer> ft = new FutureTask<>(myCallable);
		// new Thread(ft).start();
		try {
			// FutureTask 可用于 闭锁 类似于CountDownLatch的作用，在所有的线程没有执行完成之前这里是不会执行的
			new Thread(ft).start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class MyThread extends Thread {				//1,继承Thread

	@Override
	public void run() {						//2.重写run方法
		System.out.println("方法1");
		for(int i = 0;i < 10;i++) {		//3,将要执行的代码放在run方法中
			System.out.println(i);
		}
	}
}
class MyRunnable implements Runnable{        //1,实现Runnable接口

	/**
	 * 重写run方法
	 */
	@Override
	public void run() {
		System.out.println("方法2");
		for(int i = 0;i < 10;i++) {		//3,将要执行的代码放在run方法中
			System.out.println(i);
		}
	}
}

/**
 * 实现Callable接口
 */
class MyCallable implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		System.out.println("方法3");
		for(int i = 0;i < 10;i++) {
			System.out.println(i);
			sum +=i;
		}
		return sum;
	}
}