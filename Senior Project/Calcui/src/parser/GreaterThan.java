package parser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.calculator.methods;

public class GreaterThan extends BooleanExpression {
	private ExpressionStatement ex1, ex2;
	private int index;
	methods m = new methods();

	public GreaterThan(/* ArithmeticExpression op1, ArithmeticExpression op2 */) {
		// super(op1, op2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gather(ExpressionStatement expr1, ExpressionStatement expr2) {
		ex1 = expr1;
		ex2 = expr2;
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return ex1.get() > ex2.get();
	}

	@Override
	public float get() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public float value() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void display(Table t) {
		// TODO Auto-generated method stub
		String tip = ">";
		System.out.println(tip);
		m.eqVisualize(">", Color.WHITE, t);
	}

	@Override
	public void evaluate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(ExpressionStatement exst) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return ">";
	}

}
