package gradetracker.gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import gradetracker.data.AppData;
import gradetracker.data.Student;

public class AppState {

	private static final String filename = "gt_data.ser"; // choose a good filename

	public static void saveToFile() {
		// Load the data to a file
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(AppData.students);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void loadFromFile() {
		// Save the data from a file
		try {
			FileInputStream is = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(is);
			AppData.students = (ArrayList<Student>) ois.readObject();
			ois.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			// Ok there is an error with the file (or it doesn't exist yet)
			// Make a new empty AppData and save it to file for next time
			AppData.students = new ArrayList<Student>();
			saveToFile();
		}
	}

}
