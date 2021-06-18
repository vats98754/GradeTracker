package gradetracker.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class errorInLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			errorInLogin dialog = new errorInLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public errorInLogin() {
		setBounds(100, 100, 683, 501);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblErrorMessage = new JLabel("<html><body style=\"text-align: justify;  text-justify: inter-word;\">Error in login! <br> <br> Please try again or Register if new.</body></html>");
			lblErrorMessage.setFont(new Font("Lucida Grande", Font.PLAIN, 64));
			lblErrorMessage.setBounds(35, 22, 624, 395);
			contentPanel.add(lblErrorMessage);
			
			JLabel lblBGImage = new JLabel("");
			lblBGImage.setIcon(new ImageIcon(
					new ImageIcon("/Users/anvayvats/eclipse-workspace/GradeTracker/src/gradetracker/images/errorbg.png")
							.getImage().getScaledInstance(670, 483, Image.SCALE_DEFAULT)));
			lblBGImage.setBounds(0, 0, 670, 483);
			contentPanel.add(lblBGImage);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	private void close() {
		// TODO Auto-generated method stub
		this.dispose();
	}

}
