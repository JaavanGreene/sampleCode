package uielements;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import parser.VariableMemory;

public class Dialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	JLabel lblNewLabel;

	String varName;

	public Dialog(Frame aFrame, final String varName) {
		super(aFrame, true);
		this.varName = varName;

		setBounds(100, 100, 450, 150);
		setTitle("Undefined Variable");
		String message = "In order to evaluate this problem,\n the variable "
				+ varName + " needs a value.";

		lblNewLabel = new JLabel(message);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		textField = new JTextField();
		textField.setColumns(10);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		contentPanel.add(lblNewLabel);
		contentPanel.add(textField);

		JButton okButton = new JButton("OK");
		okButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				float tvalue = Float.parseFloat(textField.getText());
				VariableMemory.set(varName, tvalue);
				dispose();
			}
		});

		JButton cancelButton = new JButton("Cancel");

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.add(okButton);
		buttonPane.add(cancelButton);

		getContentPane().setLayout(new BorderLayout(5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(okButton);
	}

}
