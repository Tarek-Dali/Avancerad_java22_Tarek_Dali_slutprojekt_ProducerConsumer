package main;

import controller.Controller;
import itemsAndBuffer.Buffer;
import model.Consumer;
import model.Producer;
import model.ProducerList;
import model.SaveToTextFile;
import view.GUI;

public class Main {

	public static void main(String[] args) {

		Buffer buffer = new Buffer();
		Producer producer = new Producer(buffer);
		Consumer consumer = new Consumer(buffer);
		SaveToTextFile saveToTextFile = new SaveToTextFile();

		ProducerList producerList = new ProducerList();
		GUI gui = new GUI();

		Controller controller = new Controller(producer, consumer, gui, buffer, producerList, saveToTextFile);
	}

}
