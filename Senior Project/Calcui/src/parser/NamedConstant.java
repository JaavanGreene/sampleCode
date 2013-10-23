package parser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.calculator.methods;

public class NamedConstant implements Operand {
	private String varName;
	private int index;
	methods m = new methods();

	// build and return variable with its value
	public NamedConstant(String charAt) {
		varName = charAt;
	}

	@Override
	public float value() {
		return ConstantMemory.find(varName);
	}

	protected String getVariableName() {
		return varName;
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public void display(Table t) {
		String tip = "n: " + varName + ", " + value();
		m.eqVisualize(value() + "", Color.YELLOW, t);
		System.out.print(tip);

	}

	@Override
	public void evaluate() {
		System.out.println(value());
	}

	@Override
	public void add(ExpressionStatement exst) {
		exst.add(new NamedConstant(varName));

	}

	@Override
	public String getName() {
		return varName;
	}

}
