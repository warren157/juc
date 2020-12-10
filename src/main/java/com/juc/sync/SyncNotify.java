package com.juc.sync;

public class SyncNotify {

	public static void main(String[] args) {
		SouceAdd source = new SouceAdd();
		new Thread(() -> {
			for (int i = 0; i < 4; i++) {
				try {
					source.increment();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"A").start();
		new Thread(() -> {
			for (int i = 0; i < 4; i++) {
				try {
					source.increment();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"D").start();
		new Thread(() -> {
			for (int i = 0; i < 4; i++) {
				try {
					source.decrement();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"B").start();
		new Thread(() -> {
			for (int i = 0; i < 4; i++) {
				try {
					source.decrement();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"C").start();
	}
}

class SouceAdd {
	
	int number = 1;
	
	public synchronized void increment () throws InterruptedException {
		while(number ==1 ) {
			this.wait();
		}
		 number++;
		System.out.println(Thread.currentThread().getName() +" ===> "+number);
		this.notifyAll();
	}
	
	public synchronized void decrement () throws InterruptedException {
		while(number ==0 ) {
			this.wait();
		}
		number = number -1 ;
		System.out.println(Thread.currentThread().getName() +" ===> "+number);
		this.notifyAll();
	}
}