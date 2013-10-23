package parser;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class FunMultiply extends Function {

	private Operand op1;
	private Operand op2;
	private int index;

	public FunMultiply() {
	}

	public FunMultiply(Operand op1, Operand op2) {
		this.op1 = op1;
		this.op2 = op2;
	}

	public void gather(Operand expr1, Operand expr2) {
		op1 = expr1;
		op2 = expr2;
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
		System.out.println("op1printed");
		op2.display(t);

	}

	@Override
	public float get() {
		return op1.value() * op2.value();
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
		op2.add(exst);

	}

	@Override
	public String getName() {
		return "*";
	}

}
