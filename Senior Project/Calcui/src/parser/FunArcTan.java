package parser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.calculator.methods;

public class FunArcTan extends ParenFunction {
	private Operand op1, op2;
	private int index;
	methods m = new methods();

	public FunArcTan() {
	}

	public FunArcTan(Operand op12, Operand op22) {
		op1 = op12;
		op2 = op22;
	}

	public FunArcTan(Operand op) {
		op2 = op;
	}

	public void gather(Operand expr1, Operand expr2) {
		op1 = new Variable("arctan");
		op2 = expr2;
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public float get() {
		return (float) Math.atan(op2.value());
	}

	@Override
	public float value() {
		return (float) Math.atan(op2.value());
	}

	@Override
	public void display(Table t) {
		String tip = "Arc Tangent";
		m.eqVisualize("arctan", Color.BLUE, t);
		// System.out.println("(");
		// m.eqVisualize("(",
		// Color.PINK, "(");
		System.out.print("arctan");
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
		exst.add(new FunArcTan());
		exst.add(new ParenthesisOpen());
		op2.add(exst);
		exst.add(new ParenthesisClose());
	}

	@Override
	public String getName() {
		return "arctan";
	}

}
