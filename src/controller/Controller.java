package controller;

import itemsAndBuffer.Buffer;
import model.Consumer;
import model.Producer;
import model.ProducerList;
import model.SaveToTextFile;
import view.GUI;

public class Controller {

	private Producer producer;
	private Consumer consumer;
	private GUI gui;
	private Buffer buffer;
	private ProducerList producerList;
	private SaveToTextFile saveToTextFile;

	public Controller(Producer producer, Consumer consumer, GUI gui, Buffer buffer, ProducerList producerList, SaveToTextFile saveToTextFile) {

		this.producer = producer;
		this.consumer = consumer;
		this.gui = gui;
		this.buffer = buffer;
		this.producerList = producerList;
		this.saveToTextFile = saveToTextFile;
		
		// Starts timer which calculates average production rate per 10 seconds.
		buffer.startTime();
		
		producer.setController(this);
		consumer.setController(this);
		producerList.setController(this);
		saveToTextFile.setController(this);
		gui.setController(this);
		buffer.setController(this);
	}
	
	
	public void setProducerNumber(int index) {
		buffer.setProducerNumber(index);
	}
	
	public int getProducerNumber() {
		return buffer.getProducerNumber();
	}
	
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	
	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	
	// Creates consumers and threads for each consumer, called in ProducerList.
	public void consumers() {
		consumer.createConsumer();
	}

	// Gets thread from each new producer instance from Producer class, called in ProducerList.
	public Thread getThread() {
		return producer.CreateAndGetThread();
	}
	
	// Starts thread from produces instances, called in GUI.
	public void startThread() {
		producerList.startThread();
	}
	
	// Interrupts and removes prodocuer threads, called in GUI.
	public void removeThread() {
		producerList.removeThread();
	}
	
	// Gets number designation of worker (index) and time worker took to produce something to GUI to 
	// be printed.
	public void getProductionInfo() {
		int index = producer.getIndex();
		int timeToProduce = producer.getTimeToProduce();
		gui.printTime(timeToProduce, index);
	}
	
	// Gets size of producer threads list and print them in GUI, called in Producer.
	public void totalProducers() {
		int totalProducers = producerList.getProducerThreadsList().size();
		gui.printTotalProducers(totalProducers);
	}
	
	// Gets current total number of available products and uses them in progress bar in GUI, called in Producer and Consumer.
	public void getTotalProducts() {
		gui.printProgressBar(buffer.getProductAmount());
	}
	
	// Get logArea text and save them into text file, called in GUI.
	public void saveLog(String log) {
		saveToTextFile.save(log);
	}
	
	// Stops all producers from producing, called in ProducerList.
	public void stopAll(){
		producer.stop();
	}
	
	// Gets average production rate and prints them in GUI, called in Buffer.
	public void sendAverage(double average) {
		gui.printAverage(average);
	}


}