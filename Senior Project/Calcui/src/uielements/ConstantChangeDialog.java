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
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import parser.ConstantMemory;

public class ConstantChangeDialog extends JDialog {
	private final JPanel contentPanel;
	private JTextField nameField, valueField;
	JLabel namelabel, valuelabel;
	JPanel entername, entervalue;
	String oldname, newname;
	float oldvalue, newvalue;

	String varName;

	/** Creates the reusable dialog. */
	public ConstantChangeDialog(Frame aFrame) {
		super(aFrame, true);

		setSize(450, 500);
		setTitle("Add named constant");
		String message1 = "Constant Name";
		String message2 = "Value";

		namelabel = new JLabel("Name");
		namelabel.setHorizontalAlignment(SwingConstants.CENTER);
		namelabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		valuelabel = new JLabel("value");
		valuelabel.setHorizontalAlignment(SwingConstants.CENTER);
		valuelabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		nameField = new JTextField();
		nameField.setColumns(10);

		valueField = new JTextField();
		valueField.setColumns(10);

		entername = new JPanel();
		entername.setLayout(new GridLayout(2, 1, 5, 5));
		entername.add(namelabel);
		entername.add(nameField);
		entername.setPreferredSize(new Dimension(220, 75));

		entervalue = new JPanel();
		entervalue.setLayout(new GridLayout(2, 1, 5, 5));
		entervalue.add(valuelabel);
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

		DefaultTableModel dtm = new DefaultTableModel();

		JTable table = new JTable(dtm);
		table.setPreferredScrollableViewportSize(new Dimension(400, 100));

		for (Entry<String, Float> entry : ConstantMemory.mem.entrySet()) {
			dtm.addRow(new Object[] { entry.getKey(), entry.getValue() });
		}
		dtm.setColumnIdentifiers(new String[] { "name", "value" });

		/*
		 * for (String names: ConstantMemory.mem.keySet()){ dtm.addRow(new
		 * Object[]{names, ConstantMemory.mem.get(names)} );
		 * 
		 * }
		 */

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);

		JPanel tablelabel = new JPanel();
		tablelabel.setLayout(new GridLayout(0, 2, 5, 0));

		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(tablelabel, BorderLayout.NORTH);
		contentPanel.add(scrollPane, BorderLayout.CENTER);

		getContentPane().setLayout(new BorderLayout(5, 5));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getRootPane().setDefaultButton(okButton);
	}
}
