package gradetracker.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
	private String studentName;
	private String gradeLevel;
	public ArrayList<Subject> subjects = new ArrayList<Subject>();

	public Student(String studentName, String gradeLevel, ArrayList<Subject> subjects) {
		super();
		this.studentName = studentName;
		this.gradeLevel = gradeLevel;
		this.subjects = subjects;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public String getHouseParent() {
		return "";
	}

	public String getRoomNumber() {
		return "";
	}
}
