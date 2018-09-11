package test.tt;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


class LockTest {
	private Lock lock = new ReentrantLock(); // 创建普通的锁
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();// 创建读写锁
	private int data;// 共享数据

	// 实现同步的方法一 使用同步关键字 synchronized
	public synchronized void test(String name) {
		// 下面的相关操作是一个原子性的操作
//		 lock.lock();// 得到锁
		try {
			for (int i = 0; i < name.length(); i++) {
				System.out.print(name.charAt(i));
			}
		} finally {
//			 lock.unlock();// 释放锁
		}
	}

	// 实现同步的方法二 使用lock锁机制
	public void test2(String name) {
		// 下面的相关操作是一个原子性的操作
		lock.lock();// 得到锁
		try {
			for (int i = 0; i < name.length(); i++) {
				System.out.print(name.charAt(i));
			}
		} finally {
			lock.unlock();// 释放锁
		}
	}

	// 使用set方法模拟写入数据
	// 使用 synchronized 实现了读读，写写，读写之间的互斥 ，但读读之间的互斥是没有什么必要的
	public synchronized void set(int data) {
		System.out.println(Thread.currentThread().getName() + "准备写入数据");
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.data = data;
		System.out.println(Thread.currentThread().getName() + "写入" + this.data);
	}

	// 使用get方法模拟读取数据
	// 使用 synchronized 实现了读读，写写，读写之间的互斥 ，但读读之间的互斥是没有什么必要的
	public synchronized void get() {
		System.out.println(Thread.currentThread().getName() + "准备读取数据");
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "读取" + this.data);
	}

	// 使用set方法模拟写入数据
	// 使用 读写锁实现了写写，读写之间的互斥 ，但读读之间的互斥是没有什么必要的
	public void set2(int data) {
		readWriteLock.writeLock().lock();// 获取写入锁
		try {
			System.out.println(Thread.currentThread().getName() + "准备写入数据");
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.data = data;
			System.out.println(Thread.currentThread().getName() + "写入"
					+ this.data);
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}

	// 使用get方法模拟读取数据
	// 使用 读写锁实现了写写，读写之间的互斥 ，但读读之间的互斥是没有什么必要的
	public void get2() {
		// 获取相应的读锁
		readWriteLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "准备读取数据");
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "读取"
					+ this.data);
		} finally {
			// 释放相应的写锁
			readWriteLock.readLock().unlock();
		}
	}
}


//public class LockTest {  
//    public static void main(String[] args) {  
//        final Outputter1 output = new Outputter1();  
//        new Thread() {  
//            public void run() {  
//                output.output("zhangsan");  
//            };
//        }.start();        
//        new Thread() {  
//            public void run() {  
//                output.output("lisi");  
//            };  
//        }.start();
//    }  
//}  
//class Outputter1 {  
//    private Lock lock = new ReentrantLock();// 锁对象  
//    public void output(String name) {  
//        // TODO 线程输出方法  
//        lock.lock();// 得到锁  
//        try {  
//            for(int i = 0; i < name.length(); i++) {  
//                System.out.println(name.charAt(i));  
//            }  
//        } finally {  
//            lock.unlock();// 释放锁  
//        }  
//    }  
//}  