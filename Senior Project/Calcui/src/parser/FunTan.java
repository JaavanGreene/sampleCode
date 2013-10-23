package parser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.calculator.methods;

public class FunTan extends ParenFunction {
	private Operand op1, op2;
	private int index;
	methods m = new methods();

	public FunTan() {
	}

	public FunTan(Operand op12, Operand op22) {
		op1 = op12;
		op2 = op22;
	}

	public FunTan(Operand op) {
		op2 = op;
	}

	public void gather(Operand expr1, Operand expr2) {
		op1 = new Variable("tan");
		op2 = expr2;
	}

	@Override
	public float get() {
		return (float) Math.tan(op2.value());
	}

	@Override
	public float value() {
		return (float) Math.tan(op2.value());
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public void display(Table t) {
		String tip = "Tangent";
		m.eqVisualize("tan", Color.BLUE, t);
		// System.out.println("(");
		// m.eqVisualize("(",
		// Color.PINK, "(");
		System.out.print("tan");
		// op2.display(t);
		// m.eqVisualize(")",
		// Color.PINK, ")");
		// System.out.println(")");
	}

	@Override
	public void evaluate() {
		op2.evaluate();
		System.out.println();

	}

	@Override
	public void add(ExpressionStatement exst) {
		exst.add(new FunTan());
		exst.add(new ParenthesisOpen());
		op2.add(exst);
		exst.add(new ParenthesisClose());
	}

	@Override
	public String getName() {
		return "tan";
	}

}
