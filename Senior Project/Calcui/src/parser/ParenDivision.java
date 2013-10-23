package parser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.calculator.methods;

public class ParenDivision extends ParenBinaryExpression {
	private Operand op1, op2;
	private int index;
	methods m = new methods();

	public ParenDivision() {
	}

	public ParenDivision(Operand op12, Operand op22) {
		op1 = op12;
		op2 = op22;
	}

	@Override
	public float get() {
		if (op2.value() == 0)
			throw new RuntimeException("cant divide by zero");
		return op1.value() / op2.value();
	}

	@Override
	public float value() {
		if (op2.value() == 0)
			throw new RuntimeException("cant divide by zero");
		return op1.value() / op2.value();
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public void display(Table t) {
		System.out.println("/");
		String tip = "/";
		m.eqVisualize("/", Color.WHITE, t);
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
		exst.add(new ExpressionDivision());
		op2.add(exst);
		exst.add(new ParenthesisClose());
	}

	@Override
	public String getName() {
		return "(/)";
	}

}
