package model;

import controller.Controller;
import itemsAndBuffer.Buffer;

public class Consumer implements Runnable{
	Buffer buffer = null;
	boolean isRunning = true;
	private Controller controller = null;
	private int timeToConsume;
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Consumer(Buffer buffer) {
		this.buffer = buffer;
	}
	
	// Created a random amount of consumers between 3 and 15 and starts thread for each of them
	public void createConsumer() {
		int consumerAmount = 3 +(int) (Math.random()*13);
		
		for(int i = 0; i < consumerAmount; i++) {
			Consumer consumer = new Consumer(buffer);
			consumer.setController(controller);
			Thread consumerThread = new Thread(consumer);
			consumerThread.start();
		}
	}
	
	// Consumer consumes at interval between 1 and 9 seconds and removes product from buffer list
	@Override
	public void run() {
		timeToConsume= (1 + (int) (Math.random() * 9)) * 1000;
		while (isRunning) {
			try {
				Thread.sleep(timeToConsume);
				buffer.remove();
				
				try {
					controller.setConsumer(this);
					controller.getTotalProducts();
				} catch (Exception e) {
					System.out.println("No controller instance");
				}
			} catch (InterruptedException e) {

				break;
			}
		}

	}

}
