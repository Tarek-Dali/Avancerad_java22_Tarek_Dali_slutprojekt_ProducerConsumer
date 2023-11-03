package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.Controller;

public class GUI extends JFrame {
	

	
	private JTextArea logArea;
	private JLabel label;
	private JLabel label2;
	private JPanel panel;
	private JButton addWorkerButton;
	private JButton removeWorkerButton;
	private JButton saveLogButton;
	private JProgressBar progressBar;
	private JScrollPane scrollPane;
	
	private Controller controller = null;
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	

	public GUI() {
		

		label2 = new JLabel();
		label = new JLabel();
		panel = new JPanel();
		progressBar = new JProgressBar(0, 100);
		addWorkerButton = new JButton("+");
		removeWorkerButton = new JButton("-");
		saveLogButton = new JButton("Save Log");
		label2.setText("Producer consumer");
		logArea = new JTextArea(20, 38);
		logArea.setLineWrap(true);
		logArea.setWrapStyleWord(true);
		logArea.setEditable(false);
		logArea.setBackground(Color.BLACK);
		logArea.setForeground(Color.WHITE);
		scrollPane = new JScrollPane(logArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
	
		panel.add(label2);
		panel.add(scrollPane);
		panel.add(progressBar);
		panel.add(addWorkerButton);
		panel.add(removeWorkerButton);
		panel.add(saveLogButton);
		panel.add(label);
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 420);
		setVisible(true);
		
		
		addWorkerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.startThread();
				logArea.append("Worker added\n");
				
				
			}
		});
		
		removeWorkerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.removeThread();
				logArea.append("Worker fired\n");
				
			}
		});
		
		saveLogButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveLog(logArea.getText());
				
			}
		});
		
	}
	
	public void printAverage(double average) {
		logArea.append("Production Average: " + average + "% \n");
	}
	
	public void printTime(int seconds, int index) {
		logArea.append("Worker "+ index + " produced a product in " + seconds + " seconds\n");
	}
	
	public void printTotalProducers(int totalProducers) {
		logArea.append("Total producers " + totalProducers + "\n");
	}
	
	public void printProgressBar(int totalProducts) {
		logArea.setCaretPosition(logArea.getDocument().getLength());
		
		String strTotalProducts = Integer.toString(totalProducts);
		progressBar.setStringPainted(true);
		progressBar.setString(strTotalProducts + "%");
		progressBar.setValue(totalProducts);
		if(totalProducts <= 10 && totalProducts != 0) {
			logArea.append("Production is too low\n");
			progressBar.setForeground(Color.RED);
		} else if(totalProducts > 10 && totalProducts < 90) {
			progressBar.setForeground(Color.ORANGE);
		} else {
			logArea.append("Production is too high\n");
			progressBar.setForeground(Color.GREEN);
		}
		
	}
	
	
}
