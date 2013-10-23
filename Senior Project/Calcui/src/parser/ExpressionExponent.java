package parser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.calculator.methods;

public class ExpressionExponent extends BinaryExpression {
	private Operand op1, op2;
	private int index;
	methods m = new methods();

	public ExpressionExponent() {
	}

	public ExpressionExponent(Operand op12, Operand op22) {
		op1 = op12;
		op2 = op22;
	}

	public void gather(Operand expr1, Operand expr2) {
		op1 = expr1;
		op2 = expr2;
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public float get() {
		return (float) Math.pow(op1.value(), op2.value());
	}

	@Override
	public float value() {
		return (float) Math.pow(op1.value(), op2.value());
	}

	@Override
	public void display(Table t) {
		String tip = "exponent";
		m.eqVisualize("^", Color.WHITE, t);
		System.out.print("^");
	}

	@Override
	public void evaluate() {
		op1.evaluate();
		op2.evaluate();
		System.out.println();

	}

	@Override
	public void add(ExpressionStatement exst) {
		op1.add(exst);
		exst.add(new ExpressionExponent());
		op2.add(exst);

	}

	@Override
	public String getName() {
		return "^";
	}
}