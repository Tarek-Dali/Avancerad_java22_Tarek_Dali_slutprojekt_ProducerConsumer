package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import controller.Controller;

public class SaveToTextFile {
	
	private Controller controller = null;
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

	
	public void readData(String path) {
		try (BufferedReader br = new BufferedReader(new FileReader(path)) ){
			String line = br.readLine();
			
			while(line != null) {
				System.out.println(line);
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void writeData(String data, String path) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path)) ){
			bw.write(data);
			bw.newLine();
			bw.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void save(String log) {
		
		String path = "C:/Users/tarek/eclipse-workspace/datum2023-10-31SlutprojektV2/src/Files/log.txt";
		SaveToTextFile sv = new SaveToTextFile();
		sv.writeData(log, path);
		sv.readData(path);
	}
}
