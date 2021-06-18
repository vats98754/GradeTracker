package gradetracker.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gradetracker.data.AppData;
import gradetracker.data.Student;
import gradetracker.data.Subject;
import gradetracker.data.Test;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class TestAdder extends JFrame {

	private JPanel contentPane;
	private JTextField tFTestName;
	private JTextField tFReflection;
	private JTextField tFTestDate;
	private JTextField tFTestWeight;
	private JTextField tFTestOutOf;
	private JTextField tFTestScore;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestAdder frame = new TestAdder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestAdder() {
		setBackground(new Color(238, 238, 238));
		createTheGUIControls();
		setLocationRelativeTo(null);
	}

	public void close() {
		this.dispose();
	}

	public void createTheGUIControls() {
		setBounds(100, 100, 675, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAssessments = new JLabel("Assessments");
		lblAssessments.setFont(new Font("Lao Sangam MN", Font.PLAIN, 20));
		lblAssessments.setBounds(259, 24, 117, 36);
		contentPane.add(lblAssessments);

		JLabel lblTestName = new JLabel("Topic:");
		lblTestName.setBounds(25, 95, 61, 16);
		contentPane.add(lblTestName);

		tFTestName = new JTextField();
		tFTestName.setBounds(78, 90, 216, 26);
		contentPane.add(tFTestName);
		tFTestName.setColumns(10);

		JLabel lblReflection = new JLabel("Reflection:");
		lblReflection.setBounds(339, 95, 88, 16);
		contentPane.add(lblReflection);

		tFReflection = new JTextField();
		tFReflection.setBounds(339, 123, 313, 327);
		contentPane.add(tFReflection);
		tFReflection.setColumns(10);

		JLabel lblTestType = new JLabel("Type");

		lblTestType.setBounds(25, 132, 48, 16);
		contentPane.add(lblTestType);

		JComboBox cBTestType = new JComboBox();
		cBTestType.setModel(
				new DefaultComboBoxModel(new String[] { "Test", "Exam", "Assignment", "IA", "Homework", "Other" }));
		cBTestType.setBounds(78, 128, 127, 27);
		contentPane.add(cBTestType);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(25, 181, 48, 16);
		contentPane.add(lblDate);

		tFTestDate = new JTextField();
		tFTestDate.setBounds(78, 176, 130, 26);
		contentPane.add(tFTestDate);
		tFTestDate.setColumns(10);

		JLabel lblExampleDate = new JLabel("(e.g. 10/02/2004)");
		lblExampleDate.setForeground(Color.RED);
		lblExampleDate.setBounds(210, 181, 117, 16);
		contentPane.add(lblExampleDate);

		JLabel lblLevel = new JLabel("Level:");
		lblLevel.setBounds(25, 231, 48, 16);
		contentPane.add(lblLevel);

		JComboBox cBTestLevel = new JComboBox();
		cBTestLevel.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
		cBTestLevel.setBounds(78, 227, 61, 27);
		contentPane.add(cBTestLevel);

		JLabel lblScore = new JLabel("Score:");
		lblScore.setBounds(25, 283, 61, 16);
		contentPane.add(lblScore);

		JLabel lblOutOf = new JLabel("Out of:");
		lblOutOf.setBounds(25, 329, 61, 16);
		contentPane.add(lblOutOf);

		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setBounds(25, 372, 48, 16);
		contentPane.add(lblWeight);

		tFTestWeight = new JTextField();
		tFTestWeight.setBounds(78, 367, 130, 26);
		contentPane.add(tFTestWeight);
		tFTestWeight.setColumns(10);

		tFTestOutOf = new JTextField();
		tFTestOutOf.setBounds(78, 324, 130, 26);
		contentPane.add(tFTestOutOf);
		tFTestOutOf.setColumns(10);

		tFTestScore = new JTextField();
		tFTestScore.setBounds(78, 278, 130, 26);
		contentPane.add(tFTestScore);
		tFTestScore.setColumns(10);

		JLabel lblExampleWeight = new JLabel("(0 to 100)");
		lblExampleWeight.setBounds(220, 372, 61, 16);
		contentPane.add(lblExampleWeight);

		JLabel lblEditing = new JLabel("Editing");
		lblEditing.setForeground(Color.BLUE);
		lblEditing.setBounds(289, 62, 48, 16);
		contentPane.add(lblEditing);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubjectEditor se = new SubjectEditor();
				se.setVisible(true);
				close();
			}
		});
		btnCancel.setBounds(25, 421, 117, 29);
		contentPane.add(btnCancel);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Test test = new Test(tFTestName.getText(), (String) cBTestType.getSelectedItem(), tFTestDate.getText(),
						Integer.parseInt((String) cBTestLevel.getSelectedItem()), tFTestScore.getText(),
						tFTestOutOf.getText(), Integer.parseInt(tFTestWeight.getText()), tFReflection.getText());
				AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests.add(test);
				AppState.saveToFile();
				SubjectEditor se = new SubjectEditor();
				se.setVisible(true);
				close();
			}
		});
		btnSave.setBounds(177, 421, 117, 29);
		contentPane.add(btnSave);

		JLabel lblBGImage = new JLabel("");
		lblBGImage.setIcon(new ImageIcon(
				new ImageIcon("/Users/anvayvats/eclipse-workspace/GradeTracker/src/gradetracker/images/testadderbg.jpg")
						.getImage().getScaledInstance(697, 506, Image.SCALE_DEFAULT)));
		lblBGImage.setBounds(0, 0, 697, 506);
		contentPane.add(lblBGImage);
	}
}
