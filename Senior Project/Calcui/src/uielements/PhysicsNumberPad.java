package uielements;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class PhysicsNumberPad {
	private JLabel but0, but1, but2, but3, but4, but5, but6, but7, but8,
			but9, butdec, alphaBut, betaBut, gammaBut, deltaBut, thetaBut,
			muBut, lamdaBut, sigmaBut, omegaBut;
	private JPanel numbers;

	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel NumberPad() {

		sigmaBut = new JLabel();
		sigmaBut.setHorizontalAlignment(SwingConstants.CENTER);
		sigmaBut.setAlignmentX(Component.CENTER_ALIGNMENT);
		sigmaBut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		sigmaBut.setText("σ");
		sigmaBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "σ");
			}
		});

		but7 = new JLabel();
		but7.setHorizontalAlignment(SwingConstants.CENTER);
		but7.setAlignmentX(Component.CENTER_ALIGNMENT);
		but7.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		but7.setText("7");
		but7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "7");
			}
		});

		but8 = new JLabel();
		but8.setHorizontalAlignment(SwingConstants.CENTER);
		but8.setAlignmentX(Component.CENTER_ALIGNMENT);
		but8.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		but8.setText("8");
		but8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "8");
			}
		});

		but9 = new JLabel();
		but9.setHorizontalAlignment(SwingConstants.CENTER);
		but9.setAlignmentX(Component.CENTER_ALIGNMENT);
		but9.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		but9.setText("9");
		but9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "9");
			}
		});

		but4 = new JLabel();
		but4.setHorizontalAlignment(SwingConstants.CENTER);
		but4.setAlignmentX(Component.CENTER_ALIGNMENT);
		but4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		but4.setText("4");
		but4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "4");
			}
		});

		but5 = new JLabel();
		but5.setHorizontalAlignment(SwingConstants.CENTER);
		but5.setAlignmentX(Component.CENTER_ALIGNMENT);
		but5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		but5.setText("5");
		but5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "5");
			}
		});

		but6 = new JLabel();
		but6.setHorizontalAlignment(SwingConstants.CENTER);
		but6.setAlignmentX(Component.CENTER_ALIGNMENT);
		but6.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		but6.setText("6");
		but6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "6");
			}
		});

		but1 = new JLabel();
		but1.setHorizontalAlignment(SwingConstants.CENTER);
		but1.setAlignmentX(Component.CENTER_ALIGNMENT);
		but1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		but1.setText("1");
		but1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "1");
			}
		});

		but2 = new JLabel();
		but2.setHorizontalAlignment(SwingConstants.CENTER);
		but2.setAlignmentX(Component.CENTER_ALIGNMENT);
		but2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		but2.setText("2");
		but2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "2");
			}
		});

		but3 = new JLabel();
		but3.setHorizontalAlignment(SwingConstants.CENTER);
		but3.setAlignmentX(Component.CENTER_ALIGNMENT);
		but3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		but3.setText("3");
		but3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "3");
			}
		});

		but0 = new JLabel();
		but0.setHorizontalAlignment(SwingConstants.CENTER);
		but0.setAlignmentX(Component.CENTER_ALIGNMENT);
		but0.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		but0.setText("0");
		but0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "0");
			}
		});

		butdec = new JLabel();
		butdec.setHorizontalAlignment(SwingConstants.CENTER);
		butdec.setAlignmentX(Component.CENTER_ALIGNMENT);
		butdec.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		butdec.setText(".");
		butdec.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + ".");
			}
		});

		alphaBut = new JLabel();
		alphaBut.setHorizontalAlignment(SwingConstants.CENTER);
		alphaBut.setAlignmentX(Component.CENTER_ALIGNMENT);
		alphaBut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		alphaBut.setText("α");
		alphaBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "α");
			}
		});

		numbers = new JPanel();
		numbers.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		numbers.add(but7, "cell 0 0,grow");
		numbers.add(but8, "cell 1 0,grow");
		numbers.add(but9, "cell 2 0,grow");
		numbers.add(but4, "cell 0 1,grow");
		numbers.add(but5, "cell 1 1,grow");
		numbers.add(but6, "cell 2 1,grow");
		numbers.add(alphaBut, "cell 3 1 2 1,grow");

		muBut = new JLabel();
		muBut.setHorizontalAlignment(SwingConstants.CENTER);
		muBut.setAlignmentX(Component.CENTER_ALIGNMENT);
		muBut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		muBut.setText("μ");
		muBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "μ");
			}
		});
		numbers.add(muBut, "cell 5 1 2 1,grow");
		numbers.add(but1, "cell 0 2,grow");
		numbers.add(but2, "cell 1 2,grow");
		numbers.add(but3, "cell 2 2,grow");

		betaBut = new JLabel();
		betaBut.setHorizontalAlignment(SwingConstants.CENTER);
		betaBut.setAlignmentX(Component.CENTER_ALIGNMENT);
		betaBut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		betaBut.setText("β");
		betaBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "β");
			}
		});

		numbers.add(betaBut, "cell 3 2 2 1,grow");

		deltaBut = new JLabel();
		deltaBut.setHorizontalAlignment(SwingConstants.CENTER);
		deltaBut.setAlignmentX(Component.CENTER_ALIGNMENT);
		deltaBut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		deltaBut.setText("Δ");
		deltaBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "Δ");
			}
		});
		numbers.add(deltaBut, "cell 5 2 2 1,alignx center,grow");
		numbers.add(but0, "cell 0 3 2 1,grow");
		numbers.add(butdec, "cell 2 3,grow");

		gammaBut = new JLabel();
		gammaBut.setHorizontalAlignment(SwingConstants.CENTER);
		gammaBut.setAlignmentX(Component.CENTER_ALIGNMENT);
		gammaBut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		gammaBut.setText("γ");
		gammaBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "γ");
			}
		});
		numbers.add(gammaBut, "cell 3 3 2 1,grow");

		thetaBut = new JLabel();
		thetaBut.setHorizontalAlignment(SwingConstants.CENTER);
		thetaBut.setAlignmentX(Component.CENTER_ALIGNMENT);
		thetaBut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		thetaBut.setText("θ");
		thetaBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "θ");
			}
		});
		numbers.add(thetaBut, "cell 5 3 2 1,grow");
		numbers.add(sigmaBut, "cell 3 4 2 2,grow");

		lamdaBut = new JLabel();
		lamdaBut.setHorizontalAlignment(SwingConstants.CENTER);
		lamdaBut.setAlignmentX(Component.CENTER_ALIGNMENT);
		lamdaBut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		lamdaBut.setText("λ");
		lamdaBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "λ");
			}
		});
		numbers.add(lamdaBut, "cell 5 4 2 2,grow");

		omegaBut = new JLabel();
		omegaBut.setHorizontalAlignment(SwingConstants.CENTER);
		omegaBut.setAlignmentX(Component.CENTER_ALIGNMENT);
		omegaBut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		omegaBut.setText("ω");
		omegaBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "ω");
			}
		});
		numbers.add(omegaBut, "cell 3 6 4 1,grow");
		return numbers;

	}
}
