package com.juc.thread.daemon;

public class DeamonDemo {

	public static class DaemonT extends Thread{
		@Override
		public void run() {
			while(true) {
				System.out.println("I am alive");
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new DaemonT();
		t.setDaemon(true);
		t.start();
		Thread.sleep(2000);
	}
}
