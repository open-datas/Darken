package com.hone.common.thread;

/**
 * <p>生产者消费者实例</p>
 * @author hourz
 * @since 2019-08-07
 */
public class ProducerConsumer {
	// 定义缓冲区大小的常量
	static final int N = 100;
	// 初始化一个新的生产者线程
	static Producer p = new Producer();
	// 初始化一个新的消费者线程
	static Consumer c = new Consumer();
	// 初始化一个新的管程
	static Our_monitor mon = new Our_monitor();
 
	public static void main(String[] args) {
		p.start();// 生产者启动
		c.start();// 消费者启动
	}
 
	/**
	 * <p>生产者</p>
	 * @author hourz
	 * @since 2019-08-07
	 */
	static class Producer extends Thread {
		// 线程运行主代码
		public void run() {
			int item;
			// 生产者循环
			while (true) {
				item = produce_item();
				mon.insert(item);
			}
		}
 
		// 实际生产
		private int produce_item() {
			System.out.println("生产了1个");
			return 1;
		}
	}
 
	/**
	 * <p>消费者</p>
	 * @author hourz
	 * @since 2019-08-07
	 */
	static class Consumer extends Thread {
		// 线程运行主代码
		public void run() {
			int item;
			// 消费者循环
			while (true) {
				item = mon.remove();
				consume_item(item);
			}
		}
		// 实际消费
		private void consume_item(int item) {
			System.out.println("消费了1个");
		}
	}
 
	/**
	 * <p>管程</p>
	 * @author hourz
	 * @since 2019-08-07
	 */
	static class Our_monitor {
		private int buffer[] = new int[N];
		private int count = 0, lo = 0, hi = 0;// 计数器和索引
 
		public synchronized void insert(int val) {
			// 若缓冲区满，则进入睡眠
			if (count == N) go_to_sleep();
			// 向缓冲区中插入一个新的数据项
			buffer[hi] = val;
			// 设置下一个数据项的槽
			hi = (hi + 1) % N;
			// 缓冲区的数据项又多了一项
			count = count + 1;
			// 如果消费者在休眠，则将其唤醒
			if (count == 1) notify();
		}
 
		public synchronized int remove() {
			int val;
			// 如果缓冲区空，进入休眠
			if (count == 0) go_to_sleep();
			// 从缓冲区中取出一个数据项
			val = buffer[lo];
			// 设置待取数据项的槽
			lo = (lo + 1) % N;
			// 缓冲区的数据数目减少1
			count = count - 1;
			// 如果生产者正在休眠，则将其唤醒
			if (count == N - 1) notify();
			return val;
		}
 
		private void go_to_sleep() {
			try {
				wait();
			} catch (InterruptedException exc) {
				exc.printStackTrace();
			};
		}
	}
}