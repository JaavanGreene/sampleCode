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
import screens.CalcScreen;

import com.me.calculator.EquationStorage;

public class VariableAddValueDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	JLabel label1;
	JPanel messagebox;

	String varName;

	/** Creates the reusable dialog. */
	public VariableAddValueDialog(Frame aFrame, final String varName) {
		super(aFrame, true);
		this.varName = varName;

		setBounds(100, 100, 450, 150);
		setTitle("Add Variable Value");
		String message = "Give variable " + varName + " a value.";

		label1 = new JLabel(message);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);

		messagebox = new JPanel();
		messagebox.setLayout(new BorderLayout(0, 0));
		messagebox.add(label1, BorderLayout.NORTH);

		textField = new JTextField();
		textField.setColumns(10);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new GridLayout(0, 1, 5, 5));
		contentPanel.add(messagebox);
		contentPanel.add(textField);

		JButton okButton = new JButton("OK");
		okButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				float tvalue = Float.parseFloat(textField.getText());
				VariableMemory.set(varName, tvalue);
				CalcScreen.eqstore.get().display();
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

		getContentPane().setLayout(new BorderLayout(5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(okButton);
	}
}
