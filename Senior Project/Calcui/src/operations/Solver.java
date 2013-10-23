package operations;

//s+(23/(5+3))=(g+55)*x
//x=(1+(2/(3+7)))
import parser.BooleanExpression;
import parser.Constant;
import parser.EquationBody;
import parser.ExpressionAddition;
import parser.ExpressionDivision;
import parser.ExpressionExponent;
import parser.ExpressionMultiplication;
import parser.ExpressionStatement;
import parser.ExpressionSubtraction;
import parser.FunArcCos;
import parser.FunArcSin;
import parser.FunArcTan;
import parser.FunCos;
import parser.FunLog;
import parser.FunSin;
import parser.FunTan;
import parser.NamedConstant;
import parser.Operand;
import parser.ParenFunction;
import parser.ParenthesisClose;
import parser.ParenthesisOpen;
import parser.Variable;
import parser.VariableMemory;
import screens.CalcScreen;
import uielements.CalcWindow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Solver {
	private ExpressionStatement expr1s, expr2s;
	private ExpressionStatement target;
	private Variable v;
	private BooleanExpression rel_op;
	
	public Solver(EquationBody eq, Variable v) {
		expr1s = eq.getexpr1();
		expr2s = eq.getexpr2();
		rel_op = eq.getrelop();
		this.v = v;
	}

	public ExpressionStatement getExpr1() {
		return expr1s;
	}

	public ExpressionStatement getExpr2() {
		return expr2s;
	}

	public BooleanExpression getRelOp() {
		return rel_op;
	}

	public void solve() {
		target = expr2s;
		// ------------------------------------------------------------------------------------------------------------
		System.out.println("Solver is checking variables");
		for (int i = 0; i < target.size(); i++) {
			if (target.get(i) instanceof Variable) {
				final Variable check = new Variable(target.get(i).getName());
				if (!VariableMemory.search(check.getName())) {
					System.out.println("gotta fix " + check.getName());
					CalcScreen.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
					CheckVariables c = new CheckVariables(check);
					c.checkvariables();
					CalcScreen.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
				}
			}
		}
		System.out.println("checking done");
		// --------------------------------------------------------------------------------------------------------------------------------

		// step 1: parentheses/exponents
		paren(0, target.size() - 1);

		function(0, target.size() - 1);

		exponent(0, target.size() - 1);

		// step 2: multiplication/division
		multdiv(0, target.size() - 1);

		// step 3: addition/subtraction
		addsub(0, target.size() - 1);

		v.setValue(target.get(0).value());
		display();
		/*CalcScreen.opstore.set(new EquationBody(expr1, rel_op, expr2));
		CalcScreen.opstore.get().display();*/
		
	}

	private void function(int start, int end) {
		System.out.println("start function step from index " + start
				+ "to endex " + end);
		for (int i = start; i <= end; i++) {
			if (i < target.size() - 1) {
				if (isNumerical(target.get(i))) {
					if (isNumerical(target.get(i + 1))) {
						ExpressionMultiplication tempexp = new ExpressionMultiplication(
								target.get(i), target.get(i + 1));
						float answer = tempexp.value();
						Constant solution = new Constant(answer);
						target.remove(i + 1);
						target.remove(i);
						target.add(i, solution);
						display();
						/*CalcScreen.opstore.set(new EquationBody(expr1, rel_op,expr2));
						CalcScreen.opstore.get().display();*/
						for (int k = start; k < target.size(); k++) {
							if (target.get(k) instanceof ParenthesisClose) {
								end = k;
								break;
							}
							if (k == target.size() - 1)
								end = k;
						}
						i = start;
					}
				}
			}
			if (target.get(i) instanceof ParenFunction) {
				ParenFunction tempexp = null;
				if (target.get(i) instanceof FunArcCos)
					tempexp = new FunArcCos(target.get(i + 1));
				if (target.get(i) instanceof FunArcSin)
					tempexp = new FunArcSin(target.get(i + 1));
				if (target.get(i) instanceof FunArcTan)
					tempexp = new FunArcTan(target.get(i + 1));
				if (target.get(i) instanceof FunCos)
					tempexp = new FunCos(target.get(i + 1));
				if (target.get(i) instanceof FunLog)
					tempexp = new FunLog(target.get(i + 1));
				if (target.get(i) instanceof FunSin)
					tempexp = new FunSin(target.get(i + 1));
				if (target.get(i) instanceof FunTan)
					tempexp = new FunTan(target.get(i + 1));
				float answer = tempexp.value();
				Constant solution = new Constant(answer);
				target.remove(i + 1);
				target.remove(i);
				target.add(i, solution);
				display();
				/*CalcScreen.opstore.set(new EquationBody(expr1, rel_op, expr2));
				CalcScreen.opstore.get().display();*/
				CalcScreen.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
				for (int k = start; k < target.size(); k++) {
					if (target.get(k) instanceof ParenthesisClose) {
						end = k;
						break;
					}
					if (k == target.size() - 1)
						end = k;
				}
				i = start;
			}
		}
	}

	private void display() {
		Table t = new Table();
		expr1s.display(t);
		rel_op.display(t);
		target.display(t);
		t.layout();
		t.pack();
		CalcWindow.window().add(t);
		CalcWindow.window().row().fill().expandX();
		CalcWindow.window().layout();
		CalcWindow.window().pack();
		CalcWindow.window().act(1/30);
	}

	private void exponent(int start, int end) {
		System.out.println("start exponent step from index " + start
				+ "to endex " + end);
		for (int i = start; i <= end; i++) {
			if (target.get(i) instanceof ExpressionExponent) {
				ExpressionExponent tempexp = new ExpressionExponent(
						target.get(i - 1), target.get(i + 1));
				float answer = tempexp.value();
				Constant solution = new Constant(answer);
				target.remove(i + 1);
				target.remove(i);
				target.remove(i - 1);
				target.add(i - 1, solution);
				display();
				/*CalcScreen.opstore.set(new EquationBody(expr1, rel_op, expr2));
				CalcScreen.opstore.get().display();*/
				CalcScreen.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
				for (int k = start; k < target.size(); k++) {
					if (target.get(k) instanceof ParenthesisClose) {
						end = k;
						break;
					}
					if (k == target.size() - 1)
						end = k;
				}
				i = start;
			}
		}
	}

	// x=((22+4)/2)-9
	private void paren(int start, int end) {
		System.out.println("start parenthesis step from index " + start
				+ "to endex " + end);
		int parencount = 0;
		int parenstart, parenend;
		for (int i = start; i <= end; i++) {
			// parencount=0;
			System.out.println("looking for open parenthesis at " + i);
			if (target.get(i) instanceof ParenthesisOpen) {
				System.out.println("found open paren at " + i);
				parencount++;
				parenstart = i;
				for (int j = i + 1; j <= end; j++) {
					System.out.println("parencount is " + parencount
							+ ". currently at " + j);
					if (target.get(j) instanceof ParenthesisOpen) {
						parencount++;
						System.out.println("found another open at " + j
								+ ".parencount is " + parencount);
					}
					if (target.get(j) instanceof ParenthesisClose) {
						parencount--;
						System.out.println("found close paren at " + j
								+ ".parencount is " + parencount);
						if (parencount == 0) {
							parenend = j;
							System.out.println("parencount is " + parencount
									+ ". found paren segment from "
									+ parenstart + " to " + parenend);
							paren(parenstart + 1, parenend - 1);
							for (int k = parenstart + 1; k <= parenend; k++) {
								if (target.get(k) instanceof ParenthesisClose) {
									parenend = k;
									System.out.println("parenend is now" + k);
								}
							}

							exponent(parenstart + 1, parenend - 1);
							for (int k = parenstart + 1; k <= parenend; k++) {
								if (target.get(k) instanceof ParenthesisClose) {
									parenend = k;
									System.out.println("parenend is now" + k);
								}
							}

							multdiv(parenstart + 1, parenend - 1);
							for (int k = parenstart + 1; k <= parenend; k++) {
								if (target.get(k) instanceof ParenthesisClose) {
									parenend = k;
									System.out.println("parenend is now" + k);
								}
							}

							addsub(parenstart + 1, parenend - 1);
							for (int k = parenstart + 1; k <= parenend; k++) {
								if (target.get(k) instanceof ParenthesisClose) {
									parenend = k;
									System.out.println("parenend is now" + k);
								}
							}

							System.out.println("removing close at" + parenend);
							target.remove(parenend);
							System.out.println("removing open at" + parenstart);
							target.remove(parenstart);
							display();
							/*CalcScreen.opstore.set(new EquationBody(expr1, rel_op, expr2));
							CalcScreen.opstore.get().display();*/
							CalcScreen.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
							// break;
						}
						for (int l = start; l < target.size(); l++) {
							if (target.get(l) instanceof ParenthesisClose) {
								if (parencount == 0) {
									end = l;
									parencount--;
									break;
								}
							}
							if (l == target.size() - 1) {
								end = l;
								// break;
							}
						}
						if (parencount < 0)
							break;
					}
				}
				parencount = 0;
				i = start - 1;
				System.out.println("i is now " + start);
			}

			// x=(34.0+54.0)/(2.0*3.0)
			// y=((34.0/2)+54.0)/(2.0*(3/3))
		}
		System.out.println("end parenthesis step");
	}

	private void multdiv(int start, int end) {
		System.out.println("start multdiv step from index " + start
				+ "to endex " + end);
		for (int i = start; i <= end; i++) {
			if (target.get(i) instanceof ExpressionMultiplication) {
				ExpressionMultiplication tempexp = new ExpressionMultiplication(
						target.get(i - 1), target.get(i + 1));
				float answer = tempexp.value();
				Constant solution = new Constant(answer);
				target.remove(i + 1);
				target.remove(i);
				target.remove(i - 1);
				target.add(i - 1, solution);
				display();
				/*CalcScreen.opstore.set(new EquationBody(expr1, rel_op, expr2));
				CalcScreen.opstore.get().display();*/
				CalcScreen.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
				for (int k = start; k < target.size(); k++) {
					if (target.get(k) instanceof ParenthesisClose) {
						end = k;
						break;
					}
					if (k == target.size() - 1)
						end = k;
				}
				i = start;
			} else if (target.get(i) instanceof ExpressionDivision) {
				ExpressionDivision tempexp = new ExpressionDivision(
						target.get(i - 1), target.get(i + 1));
				float answer = tempexp.value();
				Constant solution = new Constant(answer);
				target.remove(i + 1);
				target.remove(i);
				target.remove(i - 1);
				target.add(i - 1, solution);
				display();
				/*CalcScreen.opstore.set(new EquationBody(expr1, rel_op, expr2));
				CalcScreen.opstore.get().display();*/
				CalcScreen.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
				for (int k = start; k < target.size(); k++) {
					if (target.get(k) instanceof ParenthesisClose) {
						end = k;
						break;
					}
					if (k == target.size() - 1)
						end = k;
				}
				i = start;
			}
		}
		System.out.println("end multdiv step");
	}

	private void addsub(int start, int end) {
		System.out.println("start addsub step from index " + start
				+ "to endex " + end);
		for (int i = start; i <= end; i++) {
			if (target.get(i) instanceof ExpressionAddition) {
				ExpressionAddition tempexp = new ExpressionAddition(
						target.get(i - 1), target.get(i + 1));
				float answer = tempexp.value();
				Constant solution = new Constant(answer);
				target.remove(i + 1);
				target.remove(i);
				target.remove(i - 1);
				target.add(i - 1, solution);
				display();
				/*CalcScreen.opstore.set(new EquationBody(expr1, rel_op, expr2));
				CalcScreen.opstore.get().display();*/
				CalcScreen.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
				for (int k = start; k < target.size(); k++) {
					if (target.get(k) instanceof ParenthesisClose) {
						end = k;
						break;
					}
					if (k == target.size() - 1)
						end = k;
				}
				i = start;
			} else if (target.get(i) instanceof ExpressionSubtraction) {
				ExpressionSubtraction tempexp = new ExpressionSubtraction(
						target.get(i - 1), target.get(i + 1));
				float answer = tempexp.value();
				Constant solution = new Constant(answer);
				target.remove(i + 1);
				target.remove(i);
				target.remove(i - 1);
				target.add(i - 1, solution);
				display();
				/*CalcScreen.opstore.set(new EquationBody(expr1, rel_op, expr2));
				CalcScreen.opstore.get().display();*/
				CalcScreen.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
				for (int k = start; k < target.size(); k++) {
					if (target.get(k) instanceof ParenthesisClose) {
						end = k;
						break;
					}
					if (k == target.size() - 1)
						end = k;
				}
				i = start;
			}
		}
		System.out.println("end addsub step");
	}

	private boolean isNumerical(Operand op) {
		if (op instanceof Constant || op instanceof Variable
				|| op instanceof NamedConstant) {
			return true;
		}
		else return false;
	}
}
