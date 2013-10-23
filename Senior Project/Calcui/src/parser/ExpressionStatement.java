package parser;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ExpressionStatement {
	private List<Operand> statement = new ArrayList<Operand>();

	public ExpressionStatement() {
	}

	public void add(Operand op) {
		statement.add(op);
	}

	public void add(int i, Operand op) {
		statement.add(i, op);
	}

	public int size() {
		return statement.size();
	}

	public Operand get(int i) {
		return statement.get(i);
	}

	public void numerate() {
		for (Operand op : statement) {
			op.setIndex(statement.indexOf(op));
		}
	}

	public void evaluate() {
		// TODO Auto-generated method stub

	}

	public float get() {
		// TODO Auto-generated method stub
		return (Float) null;
	}

	public boolean doesContain(Variable v) {
		return statement.contains(v);
	}

	public void remove(int i) {
		statement.remove(i);
	}

	public void display(Table t) {
		for (Operand op : statement) {
			op.display(t);
			// m.eqVisualize("|",
			// Color.ORANGE, null);
		}
		System.out.println("\n");

	}
}
