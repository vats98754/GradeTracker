package gradetracker.gui;

import java.awt.BorderLayout;
import gradetracker.data.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.util.Date;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JComboBox;
//import javax.security.auth.Subject;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class SubjectAdder extends JFrame {

	private JPanel contentPane;
	private JTextField tFSubjectName;
	private JTextField tFTeacherName;
	private static JTable table;
	private JTextField txtObservationsFromAn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubjectAdder frame = new SubjectAdder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public SubjectAdder() {
		setBackground(new Color(238, 238, 238));
		createTheGUIControls();
		setLocationRelativeTo(null);
	}

	public void createTheGUIControls() {
		setBounds(100, 100, 697, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSubjectAssessments = new JLabel("Subjects and Assessments");
		lblSubjectAssessments.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblSubjectAssessments.setBounds(6, 6, 308, 41);
		contentPane.add(lblSubjectAssessments);

		JLabel lblAdding = new JLabel("Adding");
		lblAdding.setForeground(Color.BLUE);
		lblAdding.setBounds(304, 21, 61, 16);
		contentPane.add(lblAdding);

		JLabel lblSubjectName = new JLabel("Subject:");
		lblSubjectName.setBounds(6, 61, 61, 16);
		contentPane.add(lblSubjectName);

		JLabel lblTeacher = new JLabel("Teacher:");
		lblTeacher.setBounds(6, 100, 61, 16);
		contentPane.add(lblTeacher);

		tFSubjectName = new JTextField();
		tFSubjectName.setBounds(79, 59, 194, 26);
		contentPane.add(tFSubjectName);
		tFSubjectName.setColumns(10);

		tFTeacherName = new JTextField();
		tFTeacherName.setBounds(79, 95, 194, 26);
		contentPane.add(tFTeacherName);
		tFTeacherName.setColumns(10);

		JCheckBox chckbxIsHL = new JCheckBox("HL");
		chckbxIsHL.setBounds(351, 73, 55, 23);
		contentPane.add(chckbxIsHL);

		JLabel lblNewLabel = new JLabel("Graph will go here");
		lblNewLabel.setBounds(6, 139, 291, 192);
		contentPane.add(lblNewLabel);

		DefaultTableModel tblModel = new DefaultTableModel();

		Vector columnTitles = new Vector();
		columnTitles.add("Date");
		columnTitles.add("Topic");
		columnTitles.add("Type");
		columnTitles.add("Level");
		tblModel.setColumnIdentifiers(columnTitles);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(326, 139, 280, 192);
		contentPane.add(scrollPane);

		table = new JTable(tblModel);
		scrollPane.setViewportView(table);

		JLabel lblObservations = new JLabel("Observations:");
		lblObservations.setBounds(6, 348, 99, 16);
		contentPane.add(lblObservations);

		JLabel lblCurrentLevel = new JLabel("Current Level:");
		lblCurrentLevel.setBounds(443, 343, 99, 16);
		contentPane.add(lblCurrentLevel);

		JComboBox cBGradeLevel = new JComboBox();
		cBGradeLevel.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
		cBGradeLevel.setBounds(533, 337, 70, 27);
		contentPane.add(cBGradeLevel);

		JButton btnNewTest = new JButton("New");
		btnNewTest.setEnabled(false);
		btnNewTest.setBounds(618, 170, 73, 29);
		contentPane.add(btnNewTest);

		JButton btnEditTest = new JButton("Edit");
		btnEditTest.setEnabled(false);
		btnEditTest.setBounds(618, 211, 73, 29);
		contentPane.add(btnEditTest);

		JButton btnDeleteTest = new JButton("Delete");
		btnDeleteTest.setEnabled(false);
		btnDeleteTest.setBounds(618, 252, 73, 29);
		contentPane.add(btnDeleteTest);

		txtObservationsFromAn = new JTextField();
		txtObservationsFromAn.setText(
				"Observations from an algorithm will go here (this isn't saved, just generated each time the user opens a subject based on current assessments.");
		txtObservationsFromAn.setBounds(6, 371, 685, 83);
		contentPane.add(txtObservationsFromAn);
		txtObservationsFromAn.setColumns(10);

		JButton btnSaveSubject = new JButton("Save");
		btnSaveSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Test> tests = new ArrayList<Test>();
				Subject subject = new Subject(tFSubjectName.getText(), tFTeacherName.getText(),
						(String) cBGradeLevel.getSelectedItem(), chckbxIsHL.isSelected(),
						txtObservationsFromAn.getText(), tests);
				AppData.students.get(AppData.studentNumber).subjects.add(subject);
				AppState.saveToFile();
				MainWindow mw = new MainWindow();
				mw.setVisible(true);
				close();
			}
		});
		btnSaveSubject.setBounds(6, 455, 117, 29);
		contentPane.add(btnSaveSubject);

		JButton btnCancelSubject = new JButton("Cancel");
		btnCancelSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow mw = new MainWindow();
				mw.setVisible(true);
				close();
			}
		});
		btnCancelSubject.setBounds(135, 455, 117, 29);
		contentPane.add(btnCancelSubject);

		JLabel lblBGImage = new JLabel("");
		lblBGImage.setIcon(new ImageIcon(new ImageIcon(
				"/Users/anvayvats/eclipse-workspace/GradeTracker/src/gradetracker/images/subjectadderbg.jpeg")
						.getImage().getScaledInstance(697, 506, Image.SCALE_DEFAULT)));
		lblBGImage.setBounds(0, 0, 697, 506);
		contentPane.add(lblBGImage);
	}

	public void close() {
		this.dispose();
	}
}
