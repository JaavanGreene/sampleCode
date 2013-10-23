package parser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.calculator.methods;

public class ExpressionAddition extends BinaryExpression {
	private Operand op1, op2;
	private int index;
	methods m = new methods();

	public ExpressionAddition() {
	}

	public ExpressionAddition(Operand op12, Operand op22) {
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
		return op1.value() + op2.value();
	}

	@Override
	public float value() {
		return op1.value() + op2.value();
	}

	@Override
	public void display(Table t) {
		// TODO Auto-generated method stub
		String tip = "addition";
		m.eqVisualize("+", Color.WHITE, t);
		System.out.print("+");

	}

	@Override
	public void evaluate() {
		op1.evaluate();
		op2.evaluate();
		System.out.println();

	}

	@Override
	public void add(ExpressionStatement exst) {
		System.out.println("addition op1 go");
		op1.add(exst);
		System.out.println("addition op1 done");
		exst.add(new ExpressionAddition());
		System.out.println("addition op2 go");
		op2.add(exst);
		System.out.println("addition op2 done");
	}

	@Override
	public String getName() {
		return "+";
	}

}
