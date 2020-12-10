package com.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author EDZ
 * Lock
 */
public class LockDemo {

	public static void main(String[] args) {
		Souce souce = new Souce();
		new Thread(() -> {souce.printA();},"A").start();
		new Thread(() -> {souce.printB();},"B").start();
	}
}

class Souce {
	
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	
	public void printA() {
		try {
			lock.lock();
			Thread.sleep(3000);
			System.out.println(Thread.currentThread().getName()+" ===>A");
			condition.signalAll();
		} catch (Exception e) {
			
		}finally {
			lock.unlock();
		}
		
	}
	
	public void printB() {
		try {
			lock.lock();
			System.out.println(Thread.currentThread().getName()+" ===>B");
			condition.signalAll();
		} catch (Exception e) {
			
		}finally {
			lock.unlock();
		}
		
	}
}