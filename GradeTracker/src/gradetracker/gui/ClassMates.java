package gradetracker.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gradetracker.data.AppData;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClassMates extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassMates frame = new ClassMates();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClassMates() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHeader = new JLabel("Your classmates:");
		lblHeader.setBounds(6, 6, 107, 21);
		contentPane.add(lblHeader);

		JLabel lblCM = new JLabel("");
		lblCM.setBounds(6, 35, 438, 196);
		contentPane.add(lblCM);

		String friendsList = "";
		for (int i = 0; i < AppData.students.size(); i++) {
			if (i != AppData.studentNumber) {
				if (AppData.students.get(AppData.studentNumber).getGradeLevel()
						.equals(AppData.students.get(i).getGradeLevel())) {
					for (int j = 0; j < AppData.students.get(i).subjects.size(); j++) {
						if (AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber)
								.getSubjectName().equals(AppData.students.get(i).subjects.get(j).getSubjectName())
								&& AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber)
										.getTeacherName()
										.equals(AppData.students.get(i).subjects.get(j).getTeacherName())) {
							friendsList += AppData.students.get(i).getStudentName() + ", ";
						}
					}
				}

			}
		}
		try {
			lblCM.setText(friendsList.substring(0, friendsList.length() - 2));
		} catch (Exception e) {

		}

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubjectEditor se = new SubjectEditor();
				se.setVisible(true);
				close();
			}
		});
		btnClose.setBounds(6, 243, 117, 29);
		contentPane.add(btnClose);
	}

	public void close() {
		this.dispose();
	}

}
