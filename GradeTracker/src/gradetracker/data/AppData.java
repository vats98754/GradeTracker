package gradetracker.data;

import java.io.Serializable;
import java.util.ArrayList;

import gradetracker.gui.AppState;

public class AppData implements Serializable{
	public static int studentNumber;
	public static int subjectNumber;
	public static int testNumber;
	public static ArrayList<Student> students = new ArrayList<Student>();
}