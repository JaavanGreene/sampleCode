package parser;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public interface Operand {

	public abstract float value();

	public abstract void evaluate();

	public abstract void add(ExpressionStatement exst);

	public abstract void setIndex(int i);

	public abstract String getName();

	public abstract void display(Table t);

}
