package parser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.calculator.methods;

public class FunCos extends ParenFunction {
	private Operand op1, op2;
	private int index;
	methods m = new methods();

	public FunCos() {
	}

	public FunCos(Operand op12, Operand op22) {
		op1 = op12;
		op2 = op22;
	}

	public FunCos(Operand op) {
		op2 = op;
	}

	public void gather(Operand expr1, Operand expr2) {
		op1 = new Variable("cos");
		op2 = expr2;
	}

	@Override
	public float get() {
		return (float) Math.cos(op2.value());
	}

	@Override
	public float value() {
		return (float) Math.cos(op2.value());
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public void display(Table t) {
		String tip = "Cosine";
		m.eqVisualize("cos", Color.BLUE, t);
		// System.out.println("(");
		// m.eqVisualize("(",
		// Color.PINK, "(");
		System.out.print("ccos");
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
		exst.add(new FunCos());
		exst.add(new ParenthesisOpen());
		op2.add(exst);
		exst.add(new ParenthesisClose());
	}

	@Override
	public String getName() {
		return "cos";
	}

}
