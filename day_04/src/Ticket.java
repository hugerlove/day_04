public class Ticket {

	/**
	 * 车站有100张票，多个窗口在卖
	 * @param args
	 */

	public static void main(String[] args) {
		MyTicket mt = new MyTicket();
		new Thread(mt).start();
		new Thread(mt).start();
		new Thread(mt).start();
		new Thread(mt).start();
	}

}

class MyTicket implements Runnable {
	private int chepiao = 100;

	@Override
	public void run() {
		while(true) {
			synchronized(Ticket.class) {
				if(chepiao <= 0) {
					break;
				}
				try {
					Thread.sleep(1);
					//线程1睡,线程2进来睡,线程3睡,线程4睡
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "...这是第" + chepiao-- + "号票");
			}
		}
	}
}