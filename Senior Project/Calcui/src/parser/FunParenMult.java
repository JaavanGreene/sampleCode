package parser;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class FunParenMult extends ParenFunction {
	private Operand op1, op2;
	private int index;

	public FunParenMult() {
	}

	public FunParenMult(Operand op12, Operand op22) {
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
	public float value() {
		return op1.value() * op2.value();
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public void display(Table t) {
		op1.display(t);
		System.out.println("displayed the first part of the parenmult");
		// System.out.println("(");
		// m.eqVisualize("(",
		// Color.PINK, "(");
		op2.display(t);
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
		op1.add(exst);
		exst.add(new ParenthesisOpen());
		op2.add(exst);
		exst.add(new ParenthesisClose());
	}

	@Override
	public String getName() {
		return "(*)";
	}

}
