package parser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.calculator.methods;

public class FunArcSin extends ParenFunction {
	private Operand op1, op2;
	private int index;
	methods m = new methods();

	public FunArcSin() {
	}

	public FunArcSin(Operand op12, Operand op22) {
		op1 = op12;
		op2 = op22;
	}

	public FunArcSin(Operand op) {
		op2 = op;
	}

	public void gather(Operand expr1, Operand expr2) {
		op1 = new Variable("arcsin");
		op2 = expr2;
	}

	@Override
	public float get() {
		return (float) Math.asin(op2.value());
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public float value() {
		return (float) Math.asin(op2.value());
	}

	@Override
	public void display(Table t) {
		String tip = "Arc Sine";
		m.eqVisualize("arcsin", Color.BLUE, t);
		// System.out.println("(");
		// m.eqVisualize("(",
		// Color.PINK, "(");
		System.out.print("arcsin");
		// op2.display(t);
		// m.eqVisualize(")",
		// Color.PINK, ")");
		// System.out.println(")");
	}

	@Override
	public void evaluate() {
		op1.evaluate();
		op2.evaluate();
		System.out.println();
	}

	@Override
	public void add(ExpressionStatement exst) {
		exst.add(new FunArcSin());
		exst.add(new ParenthesisOpen());
		op2.add(exst);
		exst.add(new ParenthesisClose());
	}

	@Override
	public String getName() {
		return "arcsin";
	}

}
