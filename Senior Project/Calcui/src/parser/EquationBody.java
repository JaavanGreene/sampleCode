package parser;

import uielements.CalcWindow;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class EquationBody {

	// build assignment statement
	private ExpressionStatement expr1;
	private ExpressionStatement expr2;
	private BooleanExpression rel_op;

	public EquationBody(ExpressionStatement exst1, BooleanExpression rel_op,
			ExpressionStatement exst2) {
		expr1 = exst1;
		this.rel_op = rel_op;
		expr2 = exst2;
	}

	public void display() {
		// shows the parse results
		System.out.println("okay lets print this mess.");
		Table t = new Table();
		expr1.display(t);
		rel_op.display(t);
		expr2.display(t);
		t.layout();
		t.pack();
		CalcWindow.window().add(t);
		CalcWindow.window().row().fill().expandX();
		CalcWindow.window().layout();
		CalcWindow.window().pack();

	}

	public void evaluate() {
		expr1.evaluate();
		expr2.evaluate();
		rel_op.gather(expr1, expr2);
		boolean right = rel_op.check();
		if (right) {
			System.out.println("Correct");
		} else {
			System.out.println("error");
		}
		Table t = new Table();
		expr1.display(t);
		rel_op.display(t);
		expr2.display(t);
		t.layout();
		t.pack();
		CalcWindow.window().add(t);
		CalcWindow.window().row().fill().expandX();
		CalcWindow.window().layout();
		CalcWindow.window().pack();
	}

	public ExpressionStatement getexpr1() {
		return expr1;
	}

	public ExpressionStatement getexpr2() {
		return expr2;
	}

	public BooleanExpression getrelop() {
		return rel_op;
	}

	public void setExpr1(ExpressionStatement expr1) {
		this.expr1 = expr1;
	}

	public void setExpr2(ExpressionStatement expr2) {
		this.expr2 = expr2;
	}

	public void setRel_op(BooleanExpression rel_op) {
		this.rel_op = rel_op;
	}
}
