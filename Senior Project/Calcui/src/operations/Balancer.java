package operations;

//x+gr/65*hh-ujuj
import java.util.ArrayList;
import java.util.List;

import parser.BooleanExpression;
import parser.EquationBody;
import parser.ExpressionAddition;
import parser.ExpressionDivision;
import parser.ExpressionMultiplication;
import parser.ExpressionStatement;
import parser.ExpressionSubtraction;
import parser.Operand;
import parser.ParenthesisClose;
import parser.ParenthesisOpen;
import parser.Variable;
import screens.CalcScreen;

import com.me.calculator.OperationStorage;

public class Balancer {
	private ExpressionStatement expr1, expr2;
	private ExpressionStatement before, after;
	private Variable v;
	private BooleanExpression rel_op;

	List<Operand> templeft = new ArrayList<Operand>();
	List<Operand> tempright = new ArrayList<Operand>();

	public Balancer(EquationBody eq, Variable v) {
		expr1 = eq.getexpr1();
		expr2 = eq.getexpr2();
		rel_op = eq.getrelop();
		this.v = v;
	}

	public ExpressionStatement getExpr1() {
		return expr1;
	}

	public ExpressionStatement getExpr2() {
		return expr2;
	}

	public BooleanExpression getRelOp() {
		return rel_op;
	}

	public void balance() {
		// determine which side of the equation the variable is in
		if (expr1.doesContain(v)) {
			before = expr1;
			after = expr2;
		} else {
			before = expr2;
			after = expr1;
		}

		do {
			System.out.println("loop start. size " + before.size());
			// step 1: add/sub
			System.out.println("addsub");
			addsub();

			// step 2: mult/div
			System.out.println("multdiv");
			multdiv();

			// step 3: parentheses and anything nested
			System.out.println("paren");
			parenbal();
		} while (before.size() > 1);

		//CalcScreen.eqstore.set(new EquationBody(expr1, rel_op, expr2));
		//CalcScreen.eqstore.get().display();
	}

	public void addsub() {
		System.out.println("addsub start. size = " + before.size());
		int parencount = 0;
		for (int i = (before.size() - 1); i >= 0; i--) {
			// System.out.println("current: "+i);

			// ignore parenthesized items
			if (before.get(i) instanceof ParenthesisClose)
				parencount++;
			if (before.get(i) instanceof ParenthesisOpen)
				parencount--;

			// System.out.println("parencount: "+parencount);

			// balance out addition/subtraction
			if ((before.get(i) instanceof ExpressionAddition || before.get(i) instanceof ExpressionSubtraction)
					&& parencount == 0) {
				// System.out.println("found");
				for (int j = i; j < before.size(); j++) {
					tempright.add(before.get(j));
				}
				for (int j = 0; j <= i; j++) {
					templeft.add(before.get(j));
				}
				// IF the left side of the operator has the variable, remove
				// right side
				if (templeft.contains(v)) {
					for (int j = before.size() - 1; j >= i; j--) {
						before.remove(j);
					}
					// flip operator
					if (tempright.get(0) instanceof ExpressionAddition) {
						// Flipping addition to subtraction, Adding in
						// parentheses if needed
						tempright.remove(0);
						if (tempright.size() > 1
								& !(tempright.get(0) instanceof ParenthesisOpen)) {
							tempright.add(0, new ParenthesisOpen());
							tempright.add(new ParenthesisClose());
						}
						tempright.add(0, new ExpressionSubtraction());
					} else if (tempright.get(0) instanceof ExpressionSubtraction) {
						tempright.remove(0);
						if (tempright.size() > 1
								& !(tempright.get(0) instanceof ParenthesisOpen)) {
							tempright.add(0, new ParenthesisOpen());
							tempright.add(new ParenthesisClose());
						}
						tempright.add(0, new ExpressionAddition());
					}
					// put entirety of right side of equation in parentheses
					// before adding in statement
					if (after.size() > 1
							& !(after.get(after.size() - 1) instanceof ParenthesisClose)) {
						after.add(0, new ParenthesisOpen());
						after.add(new ParenthesisClose());
					}
					// add flipped expression to the END of other side
					for (Operand op : tempright) {
						after.add(op);
					}
				}
				// IF the right side of the operator has the variable, remove
				// left side
				else {
					for (int j = i; j >= 0; j--) {
						before.remove(j);
					}
					// flip operator, rework for end of other side
					if (templeft.get(i) instanceof ExpressionAddition) {
						// Flipping addition to subtraction, Adding in
						// parentheses if needed
						templeft.remove(i);
						if (templeft.size() > 1
								& !(templeft.get(0) instanceof ParenthesisOpen)) {
							templeft.add(0, new ParenthesisOpen());
							templeft.add(new ParenthesisClose());
						}
						templeft.add(0, new ExpressionSubtraction());
					} else if (templeft.get(i) instanceof ExpressionSubtraction) {
						// Flipping subtraction to addition, Adding in
						// parentheses if needed
						templeft.remove(i);
						if (templeft.size() > 1
								& !(templeft.get(0) instanceof ParenthesisOpen)) {
							templeft.add(0, new ParenthesisOpen());
							templeft.add(new ParenthesisClose());
						}
						templeft.add(0, new ExpressionAddition());
					}
					// put entirety of right side of equation in parentheses
					// before adding in statement
					if (after.size() > 1
							& !(after.get(after.size() - 1) instanceof ParenthesisClose)) {
						after.add(0, new ParenthesisOpen());
						after.add(new ParenthesisClose());
					}
					// add flipped expression to the END of other side
					for (Operand op : templeft) {
						after.add(op);
					}
				}
				// System.out.println("sorted. new size is "+before.size());
				i = before.size();

				templeft.removeAll(templeft);
				tempright.removeAll(tempright);
				// asdfgh+7654/iujhnb*gv=76

				expr1 = before;
				expr2 = after;

				CalcScreen.opstore.set(new EquationBody(expr1, rel_op, expr2));
				CalcScreen.opstore.get().display();
				System.out.println("before size " + before.size());

			}
		}
	}

	public void multdiv() {
		int parencount = 0;
		for (int i = (before.size() - 1); i >= 0; i--) {
			System.out.println("current: " + i);
			// ignore parenthesized items
			if (before.get(i) instanceof ParenthesisClose)
				parencount++;
			if (before.get(i) instanceof ParenthesisOpen)
				parencount--;

			// balance out multiplication/division
			if ((before.get(i) instanceof ExpressionMultiplication || before
					.get(i) instanceof ExpressionDivision) && parencount == 0) {
				for (int j = i; j <= before.size() - 1; j++) {
					tempright.add(before.get(j));
				}
				for (int j = 0; j <= i; j++) {
					templeft.add(before.get(j));
				}

				// For Multiplication
				if (before.get(i) instanceof ExpressionMultiplication) {
					// IF the left side of the operator has the variable, remove
					// right side
					if (templeft.contains(v)) {
						for (int j = before.size() - 1; j >= i; j--) {
							before.remove(j);
						}
						// Flipping multiplication to division, Adding in
						// parentheses if needed
						tempright.remove(0);
						if (tempright.size() > 1
								& !(tempright.get(0) instanceof ParenthesisOpen)) {
							tempright.add(0, new ParenthesisOpen());
							tempright.add(new ParenthesisClose());
						}
						tempright.add(0, new ExpressionDivision());
						// put entirety of right side of equation in parentheses
						// before adding in division statement
						if (after.size() > 1 /*
											 * & !(after.get(after.size()-1)
											 * instanceof ParenthesisClose)
											 */) {
							after.add(0, new ParenthesisOpen());
							after.add(new ParenthesisClose());
						}
						// add flipped expression to the END of other side
						for (Operand op : tempright) {
							after.add(op);
						}
					}
					// IF the right side of the operator has the variable,
					// remove left side
					else {
						for (int j = i; j >= 0; j--) {
							before.remove(j);
						}
						// Flipping multiplication to division, Adding in
						// parentheses if needed
						templeft.remove(i);
						if (templeft.size() > 1
								& !(templeft.get(0) instanceof ParenthesisOpen)) {
							templeft.add(0, new ParenthesisOpen());
							templeft.add(new ParenthesisClose());
						}
						templeft.add(0, new ExpressionDivision());
						// put entirety of right side of equation in parentheses
						// before adding in division statement
						if (after.size() > 1 /*
											 * & !(after.get(after.size()-1)
											 * instanceof ParenthesisClose)
											 */) {
							after.add(0, new ParenthesisOpen());
							after.add(new ParenthesisClose());
						}
						// add flipped expression to the END of other side
						for (Operand op : templeft) {
							after.add(op);
						}
					}
				}

				// For Division
				else if (before.get(i) instanceof ExpressionDivision) {
					// IF the left side of the operator has the variable
					// (numerator), remove right side (denominator)
					if (templeft.contains(v)) {
						for (int j = before.size() - 1; j >= i; j--) {
							before.remove(j);
						}
						// Flipping division to multiplication, Adding in
						// parentheses if needed
						tempright.remove(0);
						if (tempright.size() > 1
								& !(tempright.get(0) instanceof ParenthesisOpen)) {
							tempright.add(0, new ParenthesisOpen());
							tempright.add(new ParenthesisClose());
						}
						tempright.add(0, new ExpressionMultiplication());
						// put entirety of right side of equation in parentheses
						// before adding in multiplication statement
						if (after.size() > 1 /*
											 * & !(after.get(after.size()-1)
											 * instanceof ParenthesisClose)
											 */) {
							after.add(0, new ParenthesisOpen());
							after.add(new ParenthesisClose());
						}
						// add flipped expression to the END of other side
						for (Operand op : tempright) {
							after.add(op);
						}
					}
					// IF the right side of the operator has the variable
					// (denominator), remove left side (numerator)
					// TODO add in inversion so it works right and stuff
					else {
						for (int j = i; j >= 0; j--) {
							before.remove(j);
						}
						// Flipping division to multiplication, Adding in
						// parentheses if needed
						templeft.remove(i);
						if (templeft.size() > 1
								& !(templeft.get(0) instanceof ParenthesisOpen)) {
							templeft.add(0, new ParenthesisOpen());
							templeft.add(new ParenthesisClose());
						}
						templeft.add(0, new ExpressionMultiplication());
						// put entirety of right side of equation in parentheses
						// before adding in multiplication statement
						if (after.size() > 1 /*
											 * & !(after.get(after.size()-1)
											 * instanceof ParenthesisClose)
											 */) {
							after.add(0, new ParenthesisOpen());
							after.add(new ParenthesisClose());
						}
						// add flipped expression to the END of other side
						for (Operand op : templeft) {
							after.add(op);
						}
					}
				}
				i = before.size();
				templeft.removeAll(templeft);
				tempright.removeAll(tempright);
				// asdfgh+7654/iujhnb*gv=76

				expr1 = before;
				expr2 = after;
				CalcScreen.opstore.set(new EquationBody(expr1, rel_op, expr2));
				CalcScreen.opstore.get().display();
				System.out.println("before size " + before.size());
			}
		}
	}

	public void parenbal() {
		if (before.get(0) instanceof ParenthesisOpen) {
			// check for close parenthesis at end
			if (before.get(before.size() - 1) instanceof ParenthesisClose) {
				// remove outer parentheses and start recursive balance in case
				// there are more parens
				before.remove(0);
				before.remove(before.size() - 1);

				expr1 = before;
				expr2 = after;
				CalcScreen.opstore.set(new EquationBody(expr1, rel_op, expr2));
				CalcScreen.opstore.get().display();
				System.out.println("before size " + before.size());
			}
		}
	}

}
// x+y/((dd*f)-eee)