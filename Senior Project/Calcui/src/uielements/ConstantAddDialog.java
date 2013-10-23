package uielements;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import parser.ConstantMemory;

public class ConstantAddDialog extends JDialog {
	private final JSplitPane contentPanel;
	private JTextField nameField, valueField;
	JLabel label1, label2;
	JPanel entername, entervalue;

	String varName;

	/** Creates the reusable dialog. */
	public ConstantAddDialog(Frame aFrame) {
		super(aFrame, true);

		setBounds(100, 100, 450, 150);
		setTitle("Add named constant");
		String message1 = "Constant Name";
		String message2 = "Value";

		label1 = new JLabel(message1);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);

		label2 = new JLabel(message2);
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);

		nameField = new JTextField();
		nameField.setColumns(10);

		valueField = new JTextField();
		valueField.setColumns(10);

		entername = new JPanel();
		entername.setLayout(new GridLayout(2, 1, 5, 5));
		entername.add(label1);
		entername.add(nameField);
		entername.setPreferredSize(new Dimension(220, 75));

		entervalue = new JPanel();
		entervalue.setLayout(new GridLayout(2, 1, 5, 5));
		entervalue.add(label2);
		entervalue.add(valueField);

		JButton okButton = new JButton("OK");
		okButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String tname = nameField.getText();
				float tvalue = Float.parseFloat(valueField.getText());
				ConstantMemory.set(tname, tvalue);
				try {
					ConstantMemory.saveMem();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		});

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.add(okButton);
		buttonPane.add(cancelButton);

		contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, entername,
				entervalue);

		getContentPane().setLayout(new BorderLayout(5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(okButton);
	}
}
