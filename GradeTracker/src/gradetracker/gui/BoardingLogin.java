package gradetracker.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gradetracker.data.AppData;
import gradetracker.data.BoardingStudent;
import gradetracker.data.Student;
import gradetracker.data.Subject;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BoardingLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tFforename;
	private JTextField tFsurname;
	private JTextField tFgradeLevel;
	private JTextField tFhouseParent;
	private JTextField tFroomNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardingLogin frame = new BoardingLogin();
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
	public BoardingLogin() {
		createGUI();
		AppState.loadFromFile();
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.BLUE);
		btnLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Subject> subjects = new ArrayList<Subject>();
				BoardingStudent student = new BoardingStudent(tFforename.getText() + " " + tFsurname.getText(),
						tFgradeLevel.getText(), subjects, tFhouseParent.getText(), tFroomNumber.getText());
				if (!AppData.students.isEmpty()) {
					for (int i = 0; i < AppData.students.size(); i++) {
						if (AppData.students.get(i).getStudentName().equals(student.getStudentName())
								&& AppData.students.get(i).getGradeLevel().equals(student.getGradeLevel())
								&& AppData.students.get(i).getHouseParent().equals(student.getHouseParent())
								&& AppData.students.get(i).getRoomNumber().equals(student.getRoomNumber())) {
							AppData.studentNumber = i;
							AppState.saveToFile();
							MainWindow mw = new MainWindow();
							mw.setVisible(true);
							close();
							break;
						} else if (i == AppData.students.size() - 1) {
							errorInLogin dialog = new errorInLogin();
							dialog.setVisible(true);
						}
					}
				} else {
					errorInLogin dialog = new errorInLogin();
					dialog.setVisible(true);
				}
			}
		});
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(105, 412, 197, 52);
		contentPane.add(btnLogin);

		JButton btnRegister = new JButton("Register");
		btnRegister.setForeground(Color.CYAN);
		btnRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Subject> subjects = new ArrayList<Subject>();
				BoardingStudent student = new BoardingStudent(tFforename.getText() + " " + tFsurname.getText(),
						tFgradeLevel.getText(), subjects, tFhouseParent.getText(), tFroomNumber.getText());
				int flag = 0;
				if (!AppData.students.isEmpty()) {
					if (!(tFforename.getText().equals("") || tFsurname.getText().equals("")
							|| tFgradeLevel.getText().equals("")) || tFhouseParent.getText().equals("")
							|| tFroomNumber.getText().equals("")) {
						for (int i = 0; i < AppData.students.size(); i++) {
							if (AppData.students.get(i).getStudentName().equals(student.getStudentName())
									&& AppData.students.get(i).getGradeLevel().equals(student.getGradeLevel())) {
								flag = 1;
								break;
							}
						}
						if (flag == 0) {
							AppData.studentNumber = AppData.students.size();
							AppData.students.add(student);
							AppState.saveToFile();
							MainWindow m = new MainWindow();
							m.setVisible(true);
							close();
						} else if (flag == 1) {
							errorInLogin dialog = new errorInLogin();
							dialog.setVisible(true);
						}
					} else {
						errorInLogin dialog = new errorInLogin();
						dialog.setVisible(true);
					}
				} else {
					AppData.studentNumber = AppData.students.size();
					AppData.students.add(student);
					AppState.saveToFile();
					MainWindow m = new MainWindow();
					m.setVisible(true);
					close();
				}
			}
		});
		btnRegister.setBounds(326, 412, 218, 52);
		contentPane.add(btnRegister);

		JLabel lblForename = new JLabel("Forename:");
		lblForename.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblForename.setBounds(105, 62, 197, 58);
		contentPane.add(lblForename);

		tFhouseParent = new JTextField();
		tFhouseParent.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		tFhouseParent.setColumns(10);
		tFhouseParent.setBounds(336, 270, 208, 57);
		contentPane.add(tFhouseParent);

		tFroomNumber = new JTextField();
		tFroomNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		tFroomNumber.setColumns(10);
		tFroomNumber.setBounds(336, 339, 208, 57);
		contentPane.add(tFroomNumber);

		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblSurname.setBounds(105, 131, 197, 58);
		contentPane.add(lblSurname);

		JLabel lblGradeLevel = new JLabel("Grade Level:");
		lblGradeLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblGradeLevel.setBounds(105, 201, 197, 58);
		contentPane.add(lblGradeLevel);

		JLabel lblHouseParent = new JLabel("House Parent:");
		lblHouseParent.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblHouseParent.setBounds(105, 270, 197, 58);
		contentPane.add(lblHouseParent);

		JLabel lblRoomNumber = new JLabel("Room Number:");
		lblRoomNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblRoomNumber.setBounds(105, 342, 219, 58);
		contentPane.add(lblRoomNumber);
		
		JButton btnBack = new JButton("<--");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				close();
			}
		});
		btnBack.setBounds(0, 0, 76, 36);
		contentPane.add(btnBack);
		
				JLabel lblBGImage = new JLabel("");
				lblBGImage.setIcon(new ImageIcon(
						new ImageIcon("/Users/anvayvats/eclipse-workspace/GradeTracker/src/gradetracker/images/loginbg.png")
								.getImage().getScaledInstance(670, 483, Image.SCALE_DEFAULT)));
				lblBGImage.setBounds(0, 0, 670, 483);
				contentPane.add(lblBGImage);
	}

	private void close() {
		this.dispose();
	}

	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogin = new JLabel("Login or Register");
		lblLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		lblLogin.setBounds(162, 0, 342, 63);
		contentPane.add(lblLogin);

		tFforename = new JTextField();
		tFforename.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		tFforename.setBounds(336, 63, 208, 57);
		contentPane.add(tFforename);
		tFforename.setColumns(10);

		tFsurname = new JTextField();
		tFsurname.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		tFsurname.setBounds(336, 132, 208, 57);
		contentPane.add(tFsurname);
		tFsurname.setColumns(10);

		tFgradeLevel = new JTextField();
		tFgradeLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		tFgradeLevel.setBounds(336, 201, 208, 57);
		contentPane.add(tFgradeLevel);
		tFgradeLevel.setColumns(10);
	}
}
