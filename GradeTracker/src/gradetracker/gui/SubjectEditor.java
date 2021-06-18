package gradetracker.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.LegendPosition;

import gradetracker.data.AppData;
import gradetracker.data.Student;
import gradetracker.data.Subject;
import gradetracker.data.Test;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SubjectEditor extends JFrame {

	private JPanel contentPane;
	private JTextField tFSubjectName;
	private JTextField tFTeacherName;
	private static JTable table;
	private JTextField txtObservationsFromAn;
	private JTextField tFSearch;
	private ChartPanel chartPanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubjectEditor frame = new SubjectEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SubjectEditor() {
		AppState.loadFromFile();
		setBackground(new Color(238, 238, 238));
		createTheGUIControls();
		refresh();
		drawChart();
	}

	private void refresh() {
		// TODO Auto-generated method stub
		DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
		tblModel.setNumRows(0);
		try {
			for (int i = 0; i < AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests
					.size(); i++) {
				String date = AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests
						.get(i).getDate();
				String topic = AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests
						.get(i).getTopicName();
				String type = AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests
						.get(i).getType();
				int level = AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests.get(i)
						.getLevel();
				Object[] data = {date, topic, type, level};
				tblModel.addRow(data);
			}
		} catch (Exception e) {

		}
	}

	private void createTheGUIControls() {
		// TODO Auto-generated method stub
		setBounds(100, 100, 697, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DefaultTableModel tblModel = new DefaultTableModel();

		JButton btnXChart = new JButton("I want an area chart");
		btnXChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Create and set up the window.
				JFrame frame = new JFrame("Area Chart for Test Grade Levels");

				// Add content to the window.
				JPanel chartPanel = new XChartPanel(new AreaChart().getChart());
				frame.add(chartPanel);

				// Display the window.
				frame.pack();
				frame.setVisible(true);
				frame.setAlwaysOnTop(true);
			}
		});
		btnXChart.setBounds(443, 56, 232, 29);
		contentPane.add(btnXChart);

		tFSearch = new JTextField();
		tFSearch.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				String query = tFSearch.getText().toUpperCase();
				search(query);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				String query = tFSearch.getText().toUpperCase();
				search(query);
			}
		});
		tFSearch.setBounds(374, 113, 232, 26);
		contentPane.add(tFSearch);
		tFSearch.setColumns(10);

		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(326, 118, 61, 16);
		contentPane.add(lblSearch);

		JLabel lblSubjectAssessments = new JLabel("Subjects and Assessments");
		lblSubjectAssessments.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblSubjectAssessments.setBounds(6, 6, 308, 41);
		contentPane.add(lblSubjectAssessments);

		JLabel lblEditing = new JLabel("Editing");
		lblEditing.setForeground(Color.BLUE);
		lblEditing.setBounds(304, 21, 61, 16);
		contentPane.add(lblEditing);

		JLabel lblSubjectName = new JLabel("Subject:");
		lblSubjectName.setBounds(6, 61, 61, 16);
		contentPane.add(lblSubjectName);

		JLabel lblTeacher = new JLabel("Teacher:");
		lblTeacher.setBounds(6, 100, 61, 16);
		contentPane.add(lblTeacher);

		tFSubjectName = new JTextField();
		tFSubjectName.setText(
				AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).getSubjectName());
		tFSubjectName.setBounds(79, 59, 194, 26);
		contentPane.add(tFSubjectName);
		tFSubjectName.setColumns(10);

		tFTeacherName = new JTextField();
		tFTeacherName.setText(
				AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).getTeacherName());
		tFTeacherName.setBounds(79, 95, 194, 26);
		contentPane.add(tFTeacherName);
		tFTeacherName.setColumns(10);

		JCheckBox chckbxIsHL = new JCheckBox("HL");
		chckbxIsHL.setSelected(AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).isHL());
		chckbxIsHL.setBounds(351, 73, 55, 23);
		contentPane.add(chckbxIsHL);

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

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
		comboBox.setSelectedItem(
				AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).getGradeLevel());
		comboBox.setBounds(533, 337, 70, 27);
		contentPane.add(comboBox);

		JButton btnNewTest = new JButton("New");
		btnNewTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppData.testNumber = AppData.students.get(AppData.studentNumber).subjects
						.get(AppData.subjectNumber).tests.size();
				TestAdder addTestWindow = new TestAdder();
				addTestWindow.setVisible(true);
				addTestWindow.setAlwaysOnTop(true);
				close();
			}
		});
		btnNewTest.setBounds(618, 170, 73, 29);
		contentPane.add(btnNewTest);

		JButton btnEditTest = new JButton("Edit");
		btnEditTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppData.testNumber = table.getSelectedRow();
				editTest();
				close();
			}
		});
		btnEditTest.setBounds(618, 211, 73, 29);
		contentPane.add(btnEditTest);

		txtObservationsFromAn = new JTextField();
		txtObservationsFromAn.setText(
				"Observations from an algorithm will go here (this isn't saved, just generated each time the user opens a subject based on current assessments.");
		txtObservationsFromAn.setBounds(6, 371, 685, 83);
		contentPane.add(txtObservationsFromAn);
		txtObservationsFromAn.setColumns(10);

		JButton btnSaveSubject = new JButton("Save");
		btnSaveSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber)
						.setHL(chckbxIsHL.isSelected());
				AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber)
						.setGradeLevel((String) comboBox.getSelectedItem());
				AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber)
						.setSubjectName(tFSubjectName.getText());
				AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber)
						.setTeacherName(tFTeacherName.getText());
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

		JButton btnDeleteTest = new JButton("Delete");
		btnDeleteTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				tblModel.removeRow(row);
				AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests.remove(row);
			}
		});

		btnDeleteTest.setBounds(618, 252, 73, 29);
		contentPane.add(btnDeleteTest);

		JButton btnClassMates = new JButton("Who are my classmates?");
		btnClassMates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassMates cm = new ClassMates();
				cm.setVisible(true);
				close();
			}
		});
		btnClassMates.setBounds(443, 16, 232, 26);
		contentPane.add(btnClassMates);

		changeObservations();
	}

	public static void editTest() {
		TestEditor editTestWindow = new TestEditor();
		editTestWindow.setVisible(true);
		editTestWindow.setAlwaysOnTop(true);
	}

	public void changeObservations() {
		if (AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).getGradeLevel()
				.equals("7")) {
			txtObservationsFromAn.setText("You're at a 7. Nothing to do now but sleep.");
		} else if (AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).getGradeLevel()
				.equals("6")
				|| AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).getGradeLevel()
						.equals("5")
				|| AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).getGradeLevel()
						.equals("4")) {
			txtObservationsFromAn.setText("You can do it. You can reach a 7 if you work harder.");
		} else {
			txtObservationsFromAn.setText("There is no hope.");
		}
	}

	public void close() {
		this.dispose();
	}

	public void search(String query) {
		DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
		tblModel.setNumRows(0);
		for (int i = 0; i < AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests
				.size(); i++) {
			if (AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests.get(i).getDate()
					.toUpperCase().contains(query)
					|| AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests.get(i)
							.getTopicName().toUpperCase().contains(query)
					|| AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests.get(i)
							.getType().toUpperCase().contains(query)
					|| ("" + AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests
							.get(i).getLevel()).equals(query)) {
				String date = AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests
						.get(i).getDate();
				String topic = AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests
						.get(i).getTopicName();
				String type = AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests
						.get(i).getType();
				int level = AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests.get(i)
						.getLevel();
				Object[] data = { date, topic, type, level };
				tblModel.addRow(data);
			}
		}
	}

	public void drawChart() {
		if (chartPanel != null) {
			this.remove(chartPanel);
		}

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests
				.size(); i++) {
			dataset.addValue(
					AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests.get(i)
							.testPercentage(), "",
					AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests.get(i)
							.getTopicName());
		}

		JFreeChart chart = ChartFactory.createBarChart("Test results for "
				+ AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).getSubjectName(),
				"Tests", "Results", dataset, PlotOrientation.VERTICAL, false, false, false);

		chartPanel = new ChartPanel(chart);
		chartPanel.setSize(299, 198);
		chartPanel.setLocation(6, 138);
		getContentPane().add(chartPanel);

		JLabel lblBGImage = new JLabel("");
		lblBGImage.setIcon(new ImageIcon(new ImageIcon(
				"/Users/anvayvats/eclipse-workspace/GradeTracker/src/gradetracker/images/subjectadderbg.jpeg")
						.getImage().getScaledInstance(697, 506, Image.SCALE_DEFAULT)));
		lblBGImage.setBounds(0, 0, 697, 506);
		contentPane.add(lblBGImage);

		this.repaint();
	}
}
