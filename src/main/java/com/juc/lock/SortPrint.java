package com.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SortPrint {

	public static void main(String[] args) {
		Demo print = new Demo();
		new Thread(() -> {for (int i = 0; i < 10; i++) {
			print.printA();
		}},"A").start();
		new Thread(() -> {for (int i = 0; i < 10; i++) {
			print.printB();
		}},"B").start();
		new Thread(() -> {for (int i = 0; i < 10; i++) {
			print.printC();
		}},"C").start();
	}
	
}

class Demo {
	Lock lock = new ReentrantLock();
	Condition a = lock.newCondition();
	Condition b = lock.newCondition();
	Condition c = lock.newCondition();
	int number = 1;
	public void printA() {
		lock.lock();
		try {
			while(number !=1) {
				a.await();
			}
			System.out.println(Thread.currentThread().getName()+" ===> A");
			number = 2;
			b.signal();
		} catch (Exception e) {
			
		}finally {
			lock.unlock();
		}
	}
	
	public void printB() {
		lock.lock();
		try {
			while(number !=2) {
				b.await();
			}
			System.out.println(Thread.currentThread().getName()+" ===> B");
			number = 3;
			c.signal();
		} catch (Exception e) {
			
		}finally {
			lock.unlock();
		}
	}
	
	
	 public void printC() {
			lock.lock();
			try {
				while(number !=3) {
					c.await();
				}
				System.out.println(Thread.currentThread().getName()+" ===> C ");
				System.out.println(" --------------------- ");
				number = 1;
				a.signal();
			} catch (Exception e) {
				
			}finally {
				lock.unlock();
			}
		}
}