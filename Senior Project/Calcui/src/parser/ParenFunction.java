package parser;

public abstract class ParenFunction extends Function {

	// splits the arithmetic operator into the different kinds of binary
	// expressions
	public enum ParenOperator {
		sin, cos, tan, arcsin, arccos, arctan, log, ln, lg, parenmult
	};

	protected Operand op1;
	protected Operand op2;

	public ParenFunction() {
	}

	public ParenFunction(Operand op) {
	}

}
