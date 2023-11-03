package itemsAndBuffer;

import java.util.LinkedList;
import java.util.Queue;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controller;

public class Buffer {

	Queue<Item> buffer = new LinkedList<Item>();
	private int productAmount = 0;
	private int producerNumber = 0;
	private double producedInTen = 0;
	private double consumedInTen = 0;
	private double average = 0;
	private Controller controller = null;
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	
	Timer timer = new Timer(10000, new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(consumedInTen != 0 && producedInTen != 0) {
                
                average = consumedInTen / producedInTen;
                average = 1 - average;
                
                controller.sendAverage(Math.round(average * 1000.0/10.0));
                
                consumedInTen = 0;
                producedInTen = 0;
            }
            
        }
    });
	
	public void startTime() {
		timer.start();
	}

	public Queue<Item> getBuffer() {
		return buffer;
	}

	public synchronized void add(Item item) {
        
        if (productAmount >= 100) {
            try {
                wait();
            } catch (InterruptedException e) {
                
            } 
            
        } else {
        	++producedInTen;
            ++productAmount;
            buffer.add(item);
            notify();
        }
    }

	public synchronized Item remove() {
		if (buffer.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		} else {
			notifyAll();
		}
		++consumedInTen;
		--productAmount;
		return buffer.remove();
	}

	public int getProducerNumber() {
		return producerNumber;
	}

	public void setProducerNumber(int producerNumber) {
		this.producerNumber = producerNumber;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

}
