package gradetracker.data;

import java.util.ArrayList;

public class BoardingStudent extends Student {
	private String houseParent, roomNumber;

	public BoardingStudent(String studentName, String gradeLevel, ArrayList<Subject> subjects, String houseParent, String roomNumber) {
		super(studentName, gradeLevel, subjects);
		this.houseParent = houseParent;
		this.roomNumber = roomNumber;
	}

	public String getHouseParent() {
		return houseParent;
	}

	public void setHouseParent(String houseParent) {
		this.houseParent = houseParent;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
}
