package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

public class ProducerList {

	private List<Thread> producerThreadsList = new ArrayList<>();

	private Controller controller = null;

	public void setController(Controller controller) {
		controller.consumers();
		this.controller = controller;
	}

	public void startThread() {
		Thread producerThread = controller.getThread();
		producerThreadsList.add(producerThread);
		producerThread.start();
	}

	public void removeThread() {
		if (!producerThreadsList.isEmpty()) {
			Thread removedThread = producerThreadsList.remove(producerThreadsList.size() - 1);
			removedThread.interrupt();
			controller.stopAll();
		}

	}

	public List<Thread> getProducerThreadsList() {
		return producerThreadsList;
	}

	public void setProducerThreadsList(List<Thread> producerThreadsList) {
		this.producerThreadsList = producerThreadsList;
	}

}
