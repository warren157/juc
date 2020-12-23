package com.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

	
	public static void main(String[] args) {
		Souce s = new Souce();
		new Thread(() -> {
			for(int i=0 ;i<5 ;i++) {
				s.set();
			}
		}, "A" )  .start();
		
		new Thread(() -> {
			for(int i=0 ;i<5 ;i++) {
				s.set();
			}
		}, "B" )  .start();
	}
}


class Souce{
	AtomicInteger ai = new AtomicInteger();
	
	public Integer get() {
		return ai.get();
	}
	
	public void set() {
		System.out.println(Thread.currentThread().getName()+" ,"+ai.incrementAndGet());
	}
}