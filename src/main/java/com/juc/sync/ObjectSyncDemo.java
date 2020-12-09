package com.juc.sync;

/**
 * @author EDZ
 * 8锁问题
 * 1对象锁,
 */
public class ObjectSyncDemo {
	public static void main(String[] args) {
		//Souce souce = new Souce();
		//new Thread(() -> {souce.printA();},"A").start();
		//new Thread(() -> {souce.printB();},"B").start();
		//先打印A 后打印B
		Souce1 souce = new Souce1();
		new Thread(() -> {souce.printA();},"A").start();
		new Thread(() -> {souce.printB();},"B").start();
	}
	
}

/**
 * @author EDZ
 * 被synchronized修饰的方法，锁的对象是方法的调用者。因为两个方法的调用者是同一个，所以两个方法用的是同一个锁，先调用方法的先执行。
 */
class Souce{
	
	public synchronized void printA() {
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			
		}
		System.out.println(Thread.currentThread().getName() +" ===> A");
	}
	
	public synchronized void printB() {
		System.out.println(Thread.currentThread().getName() +" ===> B");
	}
}

/**
 * @author EDZ
 *	没有被synchronized修饰，不是同步方法，不受锁的影响，所以不需要等待。其他线程共用了一把锁，所以还需要等待。
 */
class Souce1{
	public synchronized void printA() {
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			
		}
		System.out.println(Thread.currentThread().getName() +" ===> A");
	}
	
	public  void printB() {
		System.out.println(Thread.currentThread().getName() +" ===> B");
	}
}