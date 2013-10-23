package parser;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ParenUnaryExpression extends ParenArithmeticExpression {

	private Operand op;
	private int index;

	public ParenUnaryExpression(Operand op) {
		this.op = op;
	}

	@Override
	public float value() {
		return op.value();
	}

	@Override
	public void display(Table t) {
		op.display(t);
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public float get() {
		return op.value();
	}

	@Override
	public void evaluate() {
		op.evaluate();
	}

	@Override
	public void add(ExpressionStatement exst) {
		op.add(exst);
	}

	@Override
	public String getName() {
		return "parenunary";
	}

}
