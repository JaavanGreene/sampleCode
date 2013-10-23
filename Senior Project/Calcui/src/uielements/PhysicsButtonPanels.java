package uielements;

import java.io.FileNotFoundException;

import parser.ParserException;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class PhysicsButtonPanels {

	Table numpad1, datapanel, opspanel, eqpanel;
	Window window;
	EquationPad e=new EquationPad();
	OpsPad o = new OpsPad();
	NumberPad n = new NumberPad();

	private Table tab1, tab2, majortable;

	public Table ButtonPanels() throws ParserException,
			FileNotFoundException {

		numpad1 = n.NumbPad();
		// datapanel = DataPad.DataPad();
		opspanel = o.OpsPad();
		eqpanel = e.EquationPad();

		window = new Window("Physics", CalcSkin.skin());
		window.row().fill().expandX();
		window.add(eqpanel);
		window.add(numpad1);
		window.add(opspanel);

		tab1 = new Table();
		tab1.row().fill().expandX();
		tab1.add(window);
		tab1.defaults().spaceBottom(10);

		return tab1;
	}
}
