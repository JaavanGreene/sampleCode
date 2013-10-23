package parser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.calculator.methods;

public class Constant implements Operand {
	private float value;
	private int index;
	methods m = new methods();

	public Constant(float value) {
		this.value = value;
	}

	@Override
	public float value() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void display(Table t) {
		String tip = String.valueOf(value);
		m.eqVisualize(value + "", Color.RED, t);
		System.out.print(tip);

	}

	@Override
	public void evaluate() {
		System.out.println(value);
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public void add(ExpressionStatement exst) {
		System.out.println("adding " + value + " to the array");
		exst.add(this);

	}

	@Override
	public String getName() {
		return String.valueOf(value);
	}

}
