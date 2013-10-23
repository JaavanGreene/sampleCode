package parser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.calculator.methods;

public class EqualTo extends BooleanExpression {
	private ExpressionStatement ex1, ex2;
	private int index;
	methods m = new methods();

	public EqualTo() {
	}

	@Override
	public void gather(ExpressionStatement expr1, ExpressionStatement expr2) {
		// TODO Auto-generated method stub
		ex1 = expr1;
		ex2 = expr2;
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public boolean check() {
		return ex1.get() == ex2.get();
	}

	@Override
	public float get() {
		return 0;
	}

	@Override
	public float value() {
		return 0;
	}

	@Override
	public void display(Table t) {
		String tip = "=";
		System.out.println(tip);
		m.eqVisualize("=", Color.WHITE, t);

	}

	@Override
	public void evaluate() {
		ex1.evaluate();
		ex2.evaluate();
		System.out.println();

	}

	@Override
	public void add(ExpressionStatement exst) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return "=";
	}

}
