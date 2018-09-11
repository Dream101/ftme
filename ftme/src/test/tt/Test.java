package test.tt;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {
	public static void main(String[] args) {
		final LockTest lock = new LockTest();
		// 输出张三
		new Thread() {
			public void run() {
				lock.test2("张三张三张三张三张三");
			}
		}.start();

		// 输出李四
		new Thread() {
			public void run() {
				lock.test2("李四李四李四李四李四");
				System.out.println("\n---------------------------------------------------------------");
			}
		}.start();

		// ---------------------------------------------------------------
		// 模拟写入数据的
		for (int i = 0; i < 3; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 5; j++) {
						// lock.set(new Random().nextInt(30));
						lock.set2(new Random().nextInt(30));

					}
				}
			}.start();

		}
		// 模拟读取数据的
		for (int i = 0; i < 3; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 5; j++) {
						// lock.get();
						lock.get2();
					}
				}
			}.start();
		}

	}
}
