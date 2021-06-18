package gradetracker.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Subject implements Serializable {
	private String subjectName;
	private String teacherName;
	private String gradeLevel;
	private boolean isHL;
	private String observations;
	public ArrayList<Test> tests = new ArrayList<Test>();
	
	public Subject(String subjectName, String teacherName, String gradeLevel, boolean isHL, String observations,
			ArrayList<Test> tests) {
		super();
		this.subjectName = subjectName;
		this.teacherName = teacherName;
		this.gradeLevel = gradeLevel;
		this.isHL = isHL;
		this.observations = observations;
		this.tests = tests;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public boolean isHL() {
		return isHL;
	}

	public void setHL(boolean isHL) {
		this.isHL = isHL;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public ArrayList<Test> getTests() {
		return tests;
	}

	public void setTests(ArrayList<Test> tests) {
		this.tests = tests;
	}
	
	
}
