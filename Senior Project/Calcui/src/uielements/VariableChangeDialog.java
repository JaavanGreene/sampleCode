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
import com.me.calculator.methods;

public class VariableChangeDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	JLabel label1, label2;
	JPanel messagebox;
	methods m = new methods();

	String varName;

	/** Creates the reusable dialog. */
	public VariableChangeDialog(Frame aFrame, final String varName) {
		super(aFrame, true);
		this.varName = varName;

		setBounds(100, 100, 450, 150);
		setTitle("Change Variable Value");
		String message = "change the value of variable " + varName;
		String message2 = "from " + VariableMemory.find(varName) + " to: ";

		label1 = new JLabel(message);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);

		label2 = new JLabel(message2);
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setAlignmentX(Component.RIGHT_ALIGNMENT);

		messagebox = new JPanel();
		messagebox.setLayout(new BorderLayout(0, 0));
		messagebox.add(label1, BorderLayout.NORTH);
		messagebox.add(label2, BorderLayout.CENTER);

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
				if (VariableMemory.search("answer")) {
					m.evaluateEquation(CalcScreen.eqstore.get());
				} else
					CalcScreen.eqstore.get().display();
				dispose();
			}
		});

		JButton clearButton = new JButton("Clear Value");
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				VariableMemory.remove(varName);
				VariableMemory.remove("answer");
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
		buttonPane.add(clearButton);
		buttonPane.add(cancelButton);

		getContentPane().setLayout(new BorderLayout(5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(okButton);
	}
}
