package uielements;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class DataPad {
	private JLabel add, change;
	private JLabel load, save;
	private JLabel namedconstant, equation;
	private JPanel constops, eqops, datapanel, outerpanel, labelpanel;

	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel DataPad() {
		constops = new JPanel();
		constops.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		eqops = new JPanel();
		eqops.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		datapanel = new JPanel();
		datapanel.setLayout(new GridLayout(0, 3, 5, 0));
		datapanel.add(constops);
		datapanel.add(eqops);

		namedconstant = new JLabel("Named Constants");
		namedconstant.setHorizontalAlignment(SwingConstants.CENTER);
		equation = new JLabel("Equations");
		equation.setHorizontalAlignment(SwingConstants.CENTER);

		labelpanel = new JPanel();
		labelpanel.setLayout(new GridLayout(0, 3, 5, 5));
		labelpanel.add(namedconstant);
		labelpanel.add(equation);

		add = new JLabel();
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ConstantAddDialog constdiag = new ConstantAddDialog(
						new JFrame());
				//constdiag.setLocationRelativeTo(Calculator.textInput);
				constdiag.setVisible(true);
			}
		});
		add.setHorizontalAlignment(SwingConstants.CENTER);
		add.setAlignmentX(Component.CENTER_ALIGNMENT);
		add.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		add.setText("ADD");
		constops.add(add, "cell 0 0");

		change = new JLabel();
		change.setHorizontalAlignment(SwingConstants.CENTER);
		change.setAlignmentX(Component.CENTER_ALIGNMENT);
		change.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		change.setText("CHANGE");
		change.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ConstantChangeDialog constdiag2 = new ConstantChangeDialog(
						new JFrame());
				//constdiag2.setLocationRelativeTo(Calculator.textInput);
				constdiag2.setVisible(true);
			}
		});
		constops.add(change, "cell 0 1");

		save = new JLabel();
		save.setHorizontalAlignment(SwingConstants.CENTER);
		save.setAlignmentX(Component.CENTER_ALIGNMENT);
		save.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		save.setText("SAVE");
		eqops.add(save, "cell 0 0");

		load = new JLabel();
		load.setHorizontalAlignment(SwingConstants.CENTER);
		load.setAlignmentX(Component.CENTER_ALIGNMENT);
		load.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		load.setText("LOAD");
		eqops.add(load, "cell 0 1");

		outerpanel = new JPanel();
		outerpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		outerpanel.setLayout(new BorderLayout(5, 5));
		outerpanel.add(labelpanel, BorderLayout.NORTH);
		outerpanel.add(datapanel);
		return outerpanel;

	}
}
