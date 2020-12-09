package com.juc.sync;

/**
 * @author EDZ
 * 8锁问题
 */
public class ObjectSyncDemo {
	public static void main(String[] args) {
		//Souce souce = new Souce();
		//new Thread(() -> {souce.printA();},"A").start();
		//new Thread(() -> {souce.printB();},"B").start();
		//先打印A 后打印B
//		Souce1 souce = new Souce1();
//		new Thread(() -> {souce.printA();},"A").start();
//		new Thread(() -> {souce.printB();},"B").start();
		
		Souce2 souce = new Souce2();
		Souce2 souce1 = new Souce2();
//		new Thread(() -> {Souce2.printA();},"A").start();
//		new Thread(() -> {Souce2.printB();},"B").start();
//		new Thread(() -> {Souce2.printC();},"C").start();
//		new Thread(() -> {souce.printD();},"D").start();
		
		/**
		 * 被synchronized和static修饰的方法，锁的对象是类的class对象。因为两个同步方法都被static修饰了，即便用了两个不同的对象调用方法，两个方法用的还是同一个锁，后调用的方法需要等待先调用的方法。
		 */
		new Thread(() -> {souce.printA();},"A").start();
		new Thread(() -> {souce1.printB();},"B").start();
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

/**
 * @author EDZ
 *被synchronized和static修饰的方法，锁的对象是类的class对象。仅仅被synchronized修饰的方法，锁的对象是方法的调用者。因为两个方法锁的对象不是同一个，所以两个方法用的不是同一个锁，后调用的方法不需要等待先调用的方法。
 *D是对象锁和AB不同，AB是类锁，所以不是同一把锁，所有会先执行D，C无锁，不受影响
 */
class Souce2 {
	public static  synchronized void printA() {
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			
		}
		System.out.println(Thread.currentThread().getName() +" ===> A");
	}
	
	public static synchronized void printB() {
		System.out.println(Thread.currentThread().getName() +" ===> B");
	}
	
	public static void printC() {
		System.out.println(Thread.currentThread().getName() +" ===> C");
	}
	
	public synchronized void printD() {
		System.out.println(Thread.currentThread().getName() +" ===> D");
	}
}