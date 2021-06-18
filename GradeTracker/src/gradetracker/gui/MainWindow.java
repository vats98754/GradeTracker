package gradetracker.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gradetracker.data.AppData;
import gradetracker.data.Student;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JTextField tFQuery;
	private String query;
	String[] tracker = new String[AppData.students.get(AppData.studentNumber).subjects.size()];
	int[] searcher = new int[AppData.students.get(AppData.studentNumber).subjects.size()];
	public int sFlag = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				sort("Subject", "Ascending");
			}
		});
		AppState.loadFromFile();
		setBackground(new Color(238, 238, 238));
		createTheGUIControls();
		refresh();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void refresh() {
		DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
		tblModel.setNumRows(0);
		for (int i = 0; i < AppData.students.get(AppData.studentNumber).subjects.size(); i++) {
			String studentName = AppData.students.get(AppData.studentNumber).subjects.get(i).getSubjectName();
			String teacherName = AppData.students.get(AppData.studentNumber).subjects.get(i).getTeacherName();
			String gradeLevel = AppData.students.get(AppData.studentNumber).subjects.get(i).getGradeLevel();
			Object[] data = { studentName, teacherName, gradeLevel };
			tblModel.addRow(data);
		}
	}

	private void createTheGUIControls() {
		setBounds(0, 0, 657, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DefaultTableModel tblModel = new DefaultTableModel();

		Vector columnTitles = new Vector();
		columnTitles.add("Subject");
		columnTitles.add("Teacher");
		columnTitles.add("Level");
		tblModel.setColumnIdentifiers(columnTitles);

		tFQuery = new JTextField();
		tFQuery.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				query = tFQuery.getText().toUpperCase();
				search(query);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				query = tFQuery.getText().toUpperCase();
				search(query);
			}
		});

		JComboBox cBOrder = new JComboBox();
		cBOrder.setModel(new DefaultComboBoxModel(new String[] { "Ascending", "Descending" }));
		cBOrder.setBounds(473, 18, 130, 27);
		contentPane.add(cBOrder);

		JLabel lblSort = new JLabel("Sort:");
		lblSort.setBounds(320, 22, 37, 16);
		contentPane.add(lblSort);

		JComboBox cBField = new JComboBox();
		cBField.setModel(new DefaultComboBoxModel(new String[] { "Subject", "Teacher", "Level" }));
		cBField.setBounds(356, 18, 105, 27);
		contentPane.add(cBField);

		tFQuery.setBounds(139, 372, 427, 26);
		contentPane.add(tFQuery);
		tFQuery.setColumns(10);

		cBOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort((String) cBField.getSelectedItem(), (String) cBOrder.getSelectedItem());
			}
		});
		cBField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort((String) cBField.getSelectedItem(), (String) cBOrder.getSelectedItem());
			}
		});

		JLabel lblSearch = new JLabel("Search each field:");
		lblSearch.setBounds(24, 377, 121, 16);
		contentPane.add(lblSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 50, 542, 327);
		contentPane.add(scrollPane);

		table = new JTable(tblModel);
		scrollPane.setViewportView(table);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sFlag == 0) {
					setSubjectNumberSort();
				} else if (sFlag == 1) {
					setSubjectNumberSearch();
				}
				SubjectEditor se = new SubjectEditor();
				se.setVisible(true);
				se.setAlwaysOnTop(true);
				close();
			}
		});

		btnEdit.setBounds(578, 160, 73, 29);
		contentPane.add(btnEdit);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubjectAdder sa = new SubjectAdder();
				sa.setVisible(true);
				sa.setAlwaysOnTop(true);
				close();
			}
		});
		btnAdd.setBounds(578, 113, 73, 29);
		contentPane.add(btnAdd);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSubject();
				AppState.saveToFile();
			}
		});
		btnDelete.setBounds(578, 201, 73, 29);
		contentPane.add(btnDelete);

		JLabel lblWelcome = new JLabel("");
		lblWelcome.setBounds(24, 22, 284, 16);
		contentPane.add(lblWelcome);

		lblWelcome.setText("Welcome, " + AppData.students.get(AppData.studentNumber).getStudentName());

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				close();
			}
		});
		btnLogout.setBounds(267, 396, 117, 29);
		contentPane.add(btnLogout);

		JLabel lblBGImage = new JLabel("");
		lblBGImage.setIcon(new ImageIcon(new ImageIcon(
				"/Users/anvayvats/eclipse-workspace/GradeTracker/src/gradetracker/images/mainwindowbg.jpg").getImage()
						.getScaledInstance(670, 483, Image.SCALE_DEFAULT)));
		lblBGImage.setBounds(0, 0, 657, 453);
		contentPane.add(lblBGImage);
	}

	public void close() {
		this.dispose();
	}

	public void search(String query) {
		for (int a = 0; a < AppData.students.get(AppData.studentNumber).subjects.size(); a++) {
			searcher[a] = a;
		}
		DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
		tblModel.setNumRows(0);
		sFlag = 1;
		int k = 0;
		for (int i = 0; i < AppData.students.get(AppData.studentNumber).subjects.size(); i++) {
			if (AppData.students.get(AppData.studentNumber).subjects.get(i).getSubjectName().toUpperCase()
					.contains(query)
					|| AppData.students.get(AppData.studentNumber).subjects.get(i).getTeacherName().toUpperCase()
							.contains(query)
					|| AppData.students.get(AppData.studentNumber).subjects.get(i).getGradeLevel().toUpperCase()
							.contains(query)) {
				String subjectName = AppData.students.get(AppData.studentNumber).subjects.get(i).getSubjectName();
				String teacherName = AppData.students.get(AppData.studentNumber).subjects.get(i).getTeacherName();
				String gradeLevel = AppData.students.get(AppData.studentNumber).subjects.get(i).getGradeLevel();
				searcher[i] = k;
				k++;
				Object[] data = { subjectName, teacherName, gradeLevel };
				tblModel.addRow(data);
			}
		}

	}

	public void sort(String field, String order) {
		DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
		tblModel.setNumRows(0);
		String[] subjects = new String[AppData.students.get(AppData.studentNumber).subjects.size()];
		String[] teachers = new String[AppData.students.get(AppData.studentNumber).subjects.size()];
		String[] levels = new String[AppData.students.get(AppData.studentNumber).subjects.size()];
		String[][] all = new String[AppData.students.get(AppData.studentNumber).subjects.size()][4];

		for (int a = 0; a < AppData.students.get(AppData.studentNumber).subjects.size(); a++) {
			subjects[a] = AppData.students.get(AppData.studentNumber).subjects.get(a).getSubjectName();
			teachers[a] = AppData.students.get(AppData.studentNumber).subjects.get(a).getTeacherName();
			levels[a] = AppData.students.get(AppData.studentNumber).subjects.get(a).getGradeLevel();
			tracker[a] = "" + a;
		}

		if (field.equals("Subject")) {
			if (order.equals("Ascending")) {
				for (int i = 0; i < AppData.students.get(AppData.studentNumber).subjects.size(); i++) {
					int placeHolderIndex = 0;
					String critical = "z";
					for (int j = i; j < AppData.students.get(AppData.studentNumber).subjects.size(); j++) {
						if ((int) critical.toLowerCase().toCharArray()[0] >= (int) subjects[j].toLowerCase()
								.toCharArray()[0]) {
							placeHolderIndex = j;
							critical = subjects[j];
						}
					}
					String placeHolderSubject = subjects[placeHolderIndex];
					String placeHolderTeacher = teachers[placeHolderIndex];
					String placeHolderLevel = levels[placeHolderIndex];
					String placeHolderTracker = tracker[placeHolderIndex];

					subjects[placeHolderIndex] = subjects[i];
					teachers[placeHolderIndex] = teachers[i];
					levels[placeHolderIndex] = levels[i];
					tracker[placeHolderIndex] = tracker[i];

					subjects[i] = placeHolderSubject;
					teachers[i] = placeHolderTeacher;
					levels[i] = placeHolderLevel;
					tracker[i] = placeHolderTracker;

					all[i][0] = placeHolderSubject;
					all[i][1] = placeHolderTeacher;
					all[i][2] = placeHolderLevel;
					all[i][3] = placeHolderTracker;
				}
			} else if (order.equals("Descending")) {
				for (int i = 0; i < AppData.students.get(AppData.studentNumber).subjects.size(); i++) {
					int placeHolderIndex = 0;
					String critical = "a";
					for (int j = i; j < AppData.students.get(AppData.studentNumber).subjects.size(); j++) {
						if ((int) critical.toLowerCase().toCharArray()[0] <= (int) subjects[j].toLowerCase()
								.toCharArray()[0]) {
							placeHolderIndex = j;
							critical = subjects[j];
						}
					}
					String placeHolderSubject = subjects[placeHolderIndex];
					String placeHolderTeacher = teachers[placeHolderIndex];
					String placeHolderLevel = levels[placeHolderIndex];
					String placeHolderTracker = tracker[placeHolderIndex];

					subjects[placeHolderIndex] = subjects[i];
					teachers[placeHolderIndex] = teachers[i];
					levels[placeHolderIndex] = levels[i];
					tracker[placeHolderIndex] = tracker[i];

					subjects[i] = placeHolderSubject;
					teachers[i] = placeHolderTeacher;
					levels[i] = placeHolderLevel;
					tracker[i] = placeHolderTracker;

					all[i][0] = placeHolderSubject;
					all[i][1] = placeHolderTeacher;
					all[i][2] = placeHolderLevel;
					all[i][3] = placeHolderTracker;
				}
			}
		} else if (field.equals("Teacher")) {
			if (order.equals("Ascending")) {
				for (int i = 0; i < AppData.students.get(AppData.studentNumber).subjects.size(); i++) {
					int placeHolderIndex = 0;
					String critical = "z";
					for (int j = i; j < AppData.students.get(AppData.studentNumber).subjects.size(); j++) {
						if ((int) critical.toLowerCase().toCharArray()[0] >= (int) teachers[j].toLowerCase()
								.toCharArray()[0]) {
							placeHolderIndex = j;
							critical = teachers[j];
						}
					}
					String placeHolderSubject = subjects[placeHolderIndex];
					String placeHolderTeacher = teachers[placeHolderIndex];
					String placeHolderLevel = levels[placeHolderIndex];
					String placeHolderTracker = tracker[placeHolderIndex];

					subjects[placeHolderIndex] = subjects[i];
					teachers[placeHolderIndex] = teachers[i];
					levels[placeHolderIndex] = levels[i];
					tracker[placeHolderIndex] = tracker[i];

					subjects[i] = placeHolderSubject;
					teachers[i] = placeHolderTeacher;
					levels[i] = placeHolderLevel;
					tracker[i] = placeHolderTracker;

					all[i][0] = placeHolderSubject;
					all[i][1] = placeHolderTeacher;
					all[i][2] = placeHolderLevel;
					all[i][3] = placeHolderTracker;
				}
			} else if (order.equals("Descending")) {
				for (int i = 0; i < AppData.students.get(AppData.studentNumber).subjects.size(); i++) {
					int placeHolderIndex = 0;
					String critical = "a";
					for (int j = i; j < AppData.students.get(AppData.studentNumber).subjects.size(); j++) {
						if ((int) critical.toLowerCase().toCharArray()[0] <= (int) teachers[j].toLowerCase()
								.toCharArray()[0]) {
							placeHolderIndex = j;
							critical = teachers[j];
						}
					}
					String placeHolderSubject = subjects[placeHolderIndex];
					String placeHolderTeacher = teachers[placeHolderIndex];
					String placeHolderLevel = levels[placeHolderIndex];
					String placeHolderTracker = tracker[placeHolderIndex];

					subjects[placeHolderIndex] = subjects[i];
					teachers[placeHolderIndex] = teachers[i];
					levels[placeHolderIndex] = levels[i];
					tracker[placeHolderIndex] = tracker[i];

					subjects[i] = placeHolderSubject;
					teachers[i] = placeHolderTeacher;
					levels[i] = placeHolderLevel;
					tracker[i] = placeHolderTracker;

					all[i][0] = placeHolderSubject;
					all[i][1] = placeHolderTeacher;
					all[i][2] = placeHolderLevel;
					all[i][3] = placeHolderTracker;
				}
			}

		} else if (field.equals("Level")) {
			if (order.equals("Ascending")) {
				for (int i = 0; i < AppData.students.get(AppData.studentNumber).subjects.size(); i++) {
					String critical = "9";
					int placeHolderIndex = 0;
					for (int j = i; j < AppData.students.get(AppData.studentNumber).subjects.size(); j++) {
						if ((int) critical.toLowerCase().toCharArray()[0] >= (int) levels[j].toLowerCase()
								.toCharArray()[0]) {
							critical = levels[j];
							placeHolderIndex = j;
						}
					}
					String placeHolderSubject = subjects[placeHolderIndex];
					String placeHolderTeacher = teachers[placeHolderIndex];
					String placeHolderLevel = levels[placeHolderIndex];
					String placeHolderTracker = tracker[placeHolderIndex];

					subjects[placeHolderIndex] = subjects[i];
					teachers[placeHolderIndex] = teachers[i];
					levels[placeHolderIndex] = levels[i];
					tracker[placeHolderIndex] = tracker[i];

					subjects[i] = placeHolderSubject;
					teachers[i] = placeHolderTeacher;
					levels[i] = placeHolderLevel;
					tracker[i] = placeHolderTracker;

					all[i][0] = placeHolderSubject;
					all[i][1] = placeHolderTeacher;
					all[i][2] = placeHolderLevel;
					all[i][3] = placeHolderTracker;
				}
			} else if (order.equals("Descending")) {
				for (int i = 0; i < AppData.students.get(AppData.studentNumber).subjects.size(); i++) {
					int placeHolderIndex = i;
					String critical = "0";
					for (int j = i; j < AppData.students.get(AppData.studentNumber).subjects.size(); j++) {
						if ((int) critical.toLowerCase().toCharArray()[0] <= (int) levels[j].toLowerCase()
								.toCharArray()[0]) {
							placeHolderIndex = j;
							critical = levels[j];
						}
					}
					String placeHolderSubject = subjects[placeHolderIndex];
					String placeHolderTeacher = teachers[placeHolderIndex];
					String placeHolderLevel = levels[placeHolderIndex];
					String placeHolderTracker = tracker[placeHolderIndex];

					subjects[placeHolderIndex] = subjects[i];
					teachers[placeHolderIndex] = teachers[i];
					levels[placeHolderIndex] = levels[i];
					tracker[placeHolderIndex] = tracker[i];

					subjects[i] = placeHolderSubject;
					teachers[i] = placeHolderTeacher;
					levels[i] = placeHolderLevel;
					tracker[i] = placeHolderTracker;

					all[i][0] = placeHolderSubject;
					all[i][1] = placeHolderTeacher;
					all[i][2] = placeHolderLevel;
					all[i][3] = placeHolderTracker;
				}
			}
		}

		for (int i = 0; i < AppData.students.get(AppData.studentNumber).subjects.size(); i++) {
			Object[] data = {
					AppData.students.get(AppData.studentNumber).subjects.get(Integer.parseInt(all[i][3]))
							.getSubjectName(),
					AppData.students.get(AppData.studentNumber).subjects.get(Integer.parseInt(all[i][3]))
							.getTeacherName(),
					AppData.students.get(AppData.studentNumber).subjects.get(Integer.parseInt(all[i][3]))
							.getGradeLevel() };
			tblModel.addRow(data);
		}
	}

	public void setSubjectNumberSort() {
		AppData.subjectNumber = Integer.parseInt(tracker[table.getSelectedRow()]);
	}
	
	public void setSubjectNumberSearch() {
		for (int j = 0; j < AppData.students.get(AppData.studentNumber).subjects.size(); j++) {
			if (searcher[j] == table.getSelectedRow()) {
				AppData.subjectNumber = j;
			}
		}
	}

	public void deleteSubject() {
		DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
		int row = Integer.parseInt(tracker[table.getSelectedRow()]);
		tblModel.removeRow(table.getSelectedRow());
		AppData.students.get(AppData.studentNumber).subjects.remove(row);
	}
}
