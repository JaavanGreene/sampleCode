package parser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.calculator.methods;

public class FunLog extends ParenFunction {
	private Operand op1, op2;
	private int index;
	methods m = new methods();

	public FunLog() {
	}

	public FunLog(Operand op12, Operand op22) {
		op1 = op12;
		op2 = op22;
	}

	public FunLog(Operand op) {
		op2 = op;
	}

	public void gather(Operand expr1, Operand expr2) {
		op1 = new Variable("log");
		op2 = expr2;
	}

	@Override
	public float get() {
		return (float) Math.log(op2.value());
	}

	@Override
	public float value() {
		return (float) Math.log(op2.value());
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public void display(Table t) {
		String tip = "Log";
		m.eqVisualize("log", Color.BLUE, t);
		// System.out.println("(");
		// m.eqVisualize("(",
		// Color.PINK, "(");
		System.out.print("log");
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
		exst.add(new FunLog());
		exst.add(new ParenthesisOpen());
		op2.add(exst);
		exst.add(new ParenthesisClose());
	}

	@Override
	public String getName() {
		return "log";
	}

}
