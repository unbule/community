package com.nowcoder.community;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class CommunityApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CommunityApplication.class, args);

//		ExecutorService executorService = Executors.newCachedThreadPool();
//		Thread t1 = new MyThread();
//		Thread t2 = new MyThread();
//		Thread t3 = new MyThread();
//		Thread t4 = new MyThread();
//		Thread t5 = new MyThread();
//
//		executorService.execute(t1);
//		executorService.execute(t2);
//		executorService.execute(t3);
//		executorService.execute(t4);
//		executorService.execute(t5);
//
//		executorService.shutdown();
	}

}

class MyThread extends Thread{
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " running...");
	}

	static List<Message> process(List<Message> received) {
		Set<Message> s = new HashSet<>(received);

		return new ArrayList<Message>(s);
	}
}

class Message {
	public final int sequence;
	public final String text;
	public Message(int sequence, String text) {
		this.sequence = sequence;
		this.text = text;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Message){
			Message m = (Message) obj;
			return this.sequence == m.sequence;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.sequence);
	}
}

class Run implements Runnable {
	public void run() {
		for (int i = 0; i < 100; i++) {
			AtomicCounter.add(1);
		}
	}
}
class AtomicCounter {
	static AtomicInteger counter = new AtomicInteger();
	public static void add(int i) {
		while (true) {
			int oldValue = counter.get();
			int newValue = oldValue + i;
			if (counter.compareAndSet(oldValue, newValue))
				return;
		}
	}
	public static int getCount() {
		return counter.get();
	}
}
