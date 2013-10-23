package parser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.calculator.methods;

public class ParenMultiplication extends ParenBinaryExpression {
	private Operand op1, op2;
	private int index;
	methods m = new methods();

	public ParenMultiplication() {
	}

	public ParenMultiplication(Operand op12, Operand op22) {
		op1 = op12;
		op2 = op22;
	}

	public void gather(Operand expr1, Operand expr2) {
		op1 = expr1;
		op2 = expr2;
	}

	@Override
	public float get() {
		return op1.value() * op2.value();
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public float value() {
		return op1.value() * op2.value();
	}

	@Override
	public void display(Table t) {
		System.out.println("*");
		String tip = "*";
		m.eqVisualize("*", Color.WHITE, t);
	}

	@Override
	public void evaluate() {
		op1.evaluate();
		op2.evaluate();
		System.out.println();

	}

	@Override
	public void add(ExpressionStatement exst) {
		exst.add(new ParenthesisOpen());
		op1.add(exst);
		exst.add(new ExpressionMultiplication());
		op2.add(exst);
		exst.add(new ParenthesisClose());

	}

	@Override
	public String getName() {
		return "(*)";
	}

}
