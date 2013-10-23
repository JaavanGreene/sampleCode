package parser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.calculator.methods;

public class ParenthesisClose extends Parenthesis {
	methods m = new methods();
	private int index;

	public ParenthesisClose() {
	}

	@Override
	public float value() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void display(Table t) {
		m.eqVisualize(")", Color.PINK, t);
		System.out.print(")");

	}

	@Override
	public void evaluate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public void add(ExpressionStatement exst) {
		exst.add(new ParenthesisClose());

	}

	@Override
	public String getName() {
		return ")";
	}

}
